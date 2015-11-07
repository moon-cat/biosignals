package org.kth.sth.biosignals.edf2json;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.kth.sth.biosignals.edf2json.model.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.math.BigInteger;
import java.util.ArrayList;


@Component
public class Edf2JsonConverter {

    private final static Logger log = Logger.getLogger(Edf2JsonConverter.class);


    public Edf readMetadataAndFirstDataRecordBatch(InputStream edfFile, Integer readRecords) throws Exception {
        Edf edf = new Edf();

        EdfMetadata edfMetadata = readMetadata(edfFile);
        edf.setEdfMetadata(edfMetadata);

        log.info("Metadata done");

        if (readRecords > edfMetadata.getNoOfDataRecords()){
            readRecords = edfMetadata.getNoOfDataRecords();
        }

        EdfData edfData = readData(edfFile, edfMetadata, readRecords, 0);
        edf.setEdfData(edfData);

        log.info("Data done");

        return edf;
    }

    public EdfData readDataRecordBatch(InputStream edfFile, Integer skipRecords, Integer readRecords, EdfMetadata edfMetadata) throws Exception {
        if (skipRecords > edfMetadata.getNoOfDataRecords()){
            return null;
        }

        if (skipRecords + readRecords > edfMetadata.getNoOfDataRecords()){
            readRecords = edfMetadata.getNoOfDataRecords() - skipRecords;
        }
        log.info("Skip: " + skipRecords);
        log.info("REad: " + readRecords);



        skipMetadata(edfFile, edfMetadata);
        log.info("Metadata skiped");

        skipData(edfFile, edfMetadata, skipRecords);
        log.info("Data skiped");

        EdfData edfData = readData(edfFile, edfMetadata, readRecords, skipRecords);
        log.info("Data done");

        return edfData;
    }



    private EdfMetadata readMetadata(InputStream edfFile) throws Exception {
        EdfMetadata edfMetadata = new EdfMetadata();

        edfMetadata.setVersion(readString(edfFile, 8));
        edfMetadata.setLocalPatientIdentification(readString(edfFile, 80));
        edfMetadata.setLocalRecordingIdentification(readString(edfFile, 80));
        edfMetadata.setStartDate(readString(edfFile, 8));
        edfMetadata.setStartTime(readString(edfFile, 8));
        edfMetadata.setHeaderLengthInBytes(readInt(edfFile, 8));
        skip(edfFile, 44);
        edfMetadata.setNoOfDataRecords(readInt(edfFile, 8));
        edfMetadata.setDataRecordDurationInSeconds(readDouble(edfFile, 8));
        edfMetadata.setNoOfSignalsInDataRecord(readInt(edfFile, 4));

        readSignalMetadata(edfFile, edfMetadata, edfMetadata.getNoOfSignalsInDataRecord());
        return edfMetadata;
    }

    private void readSignalMetadata(InputStream edfFile, EdfMetadata edfMetadata, int noOfSignals) throws Exception {
        for (int i = 0; i < noOfSignals; i++){
            SignalMetadata signalMetadata = new SignalMetadata();
            edfMetadata.getSignalMetadata().add(signalMetadata);
        }

        for (int i = 0; i < noOfSignals; i++){
            edfMetadata.getSignalMetadata().get(i).setLabel(readString(edfFile, 16).trim());
        }

        System.out.println("Labels done");

        for (int i = 0; i < noOfSignals; i++){
            edfMetadata.getSignalMetadata().get(i).setTransducerType(readString(edfFile, 80));
        }

        System.out.println("Tr.type done");

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setPhysicalDimension(readString(edfFile, 8));
        }

        System.out.println("Ph.dim. done");


        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setPhysicalMin(readDouble(edfFile, 8).intValue());
        }

        System.out.println("Ph.min. done");

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setPhysicalMax(readDouble(edfFile, 8).intValue());
        }

        System.out.println("Ph.max. done");

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setDigitalMin(readDouble(edfFile, 8).intValue());
        }

        System.out.println("Dig.min. done");

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setDigitalMax(readDouble(edfFile, 8).intValue());
        }

        System.out.println("Dig.max. done");

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setPrefiltering(readString(edfFile, 80));
        }

        System.out.println("Prefil. done");

        for (int i = 0; i < noOfSignals; i++) {
            int noOfSamplesInDataRecord = readInt(edfFile, 8);
            edfMetadata.getSignalMetadata().get(i).setNoOfSamplesInDataRecord(noOfSamplesInDataRecord);
        }

        System.out.println("No.of.samples done");

        for (int i = 0; i < noOfSignals; i++) {
            skip(edfFile, 32);
        }
    }

    private void skipMetadata(InputStream edfFile, EdfMetadata edfMetadata) throws Exception {
        skip(edfFile, 256);
        skip(edfFile, 256 * edfMetadata.getNoOfSignalsInDataRecord());
    }

    private EdfData readData(InputStream edfFile, EdfMetadata edfMetadata, Integer noOfRecords, Integer noOfSkipedRecords) throws Exception {
        EdfData edfData = new EdfData();

        edfData.setEdfDataRecords(new ArrayList<EdfDataRecord>(noOfRecords));

        for (int k = 0; k < noOfRecords; k++) {
            EdfDataRecord edfDataRecord = new EdfDataRecord();
            edfDataRecord.setDataRecordId("" + (k + noOfSkipedRecords));
            edfDataRecord.setEdfSignalDataRecords(new ArrayList<EdfSignalDataRecord>(edfMetadata.getNoOfSignalsInDataRecord()));
            edfData.getEdfDataRecords().add(edfDataRecord);


            for (int i = 0; i < edfMetadata.getNoOfSignalsInDataRecord(); i++) {
                EdfSignalDataRecord edfSignalDataRecord = new EdfSignalDataRecord();
                edfSignalDataRecord.setSignalId("" + i);
                edfSignalDataRecord.setData(new ArrayList<Object>(edfMetadata.getSignalMetadata().get(i).getNoOfSamplesInDataRecord()));
                edfDataRecord.getEdfSignalDataRecords().add(edfSignalDataRecord);

                for (int j = 0; j < edfMetadata.getSignalMetadata().get(i).getNoOfSamplesInDataRecord(); j++){
                    edfSignalDataRecord.getData().add(readIntFromBytes(edfFile));
                }
            }
        }
        return edfData;
    }


    private void skipData(InputStream edfFile, EdfMetadata edfMetadata, Integer noOfRecords) throws Exception {
        for (int k = 0; k < noOfRecords; k++) {
            for (int i = 0; i < edfMetadata.getNoOfSignalsInDataRecord(); i++) {
                skip(edfFile, 2 * edfMetadata.getSignalMetadata().get(i).getNoOfSamplesInDataRecord());
            }
        }
    }

    private String readString(InputStream edfFile, int length)  throws Exception{
        byte[] bytes = new byte[length];
        edfFile.read(bytes);
        return new String(bytes).trim();
    }

    private Integer readInt(InputStream edfFile, int length)  throws Exception{
        String value = readString(edfFile, length).trim();
        try {
            return new Integer(value);
        } catch (NumberFormatException e) {
            System.err.println("Not integer: " + value);
        }
        return 0;
    }

    private Double readDouble(InputStream edfFile, int length)  throws Exception{
        String value = readString(edfFile, length).trim();
        try {
            return new Double(value);
        } catch (NumberFormatException e) {
            System.err.println("Not double: " + value);
        }
        return 0.0;
    }


    private void skip(InputStream edfFile, int length) throws Exception {
        edfFile.skip(length);
    }

    private Integer readIntFromBytes(InputStream edfFile) throws Exception{
        byte[] bytes = new byte[2];
        edfFile.read(bytes);
        return new BigInteger(bytes).intValue();
    }

}
