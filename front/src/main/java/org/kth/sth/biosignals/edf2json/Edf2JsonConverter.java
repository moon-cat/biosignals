package org.kth.sth.biosignals.edf2json;


import org.codehaus.jackson.map.ObjectMapper;
import org.kth.sth.biosignals.edf2json.model.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigInteger;


public class Edf2JsonConverter {

    public String parse(InputStream edfFile) throws Exception{

        Edf edf = readEdf(edfFile);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(edf);
    }

    public Edf readEdf(InputStream edfFile) throws Exception {
        Edf edf = new Edf();

        EdfMetadata edfMetadata = readMetadata(edfFile);
        edf.setEdfMetadata(edfMetadata);

        System.out.println("Metadata done");

        EdfData edfData = readData(edfFile, edfMetadata);
        edf.setEdfData(edfData);

        System.out.println("Data done");

        return edf;
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
            edfMetadata.getSignalMetadata().get(i).setLabel(readString(edfFile, 16));
        }

        for (int i = 0; i < noOfSignals; i++){
            edfMetadata.getSignalMetadata().get(i).setTransducerType(readString(edfFile, 80));
        }

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setPhysicalDimension(readString(edfFile, 8));
        }

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setPhysicalMin(readDouble(edfFile, 8).intValue());
        }

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setPhysicalMax(readDouble(edfFile, 8).intValue());
        }

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setDigitalMin(readDouble(edfFile, 8).intValue());
        }

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setDigitalMax(readDouble(edfFile, 8).intValue());
        }

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setPrefiltering(readString(edfFile, 80));
        }

        int noOfSamplesInDataRecord = readInt(edfFile, 8);

        for (int i = 0; i < noOfSignals; i++) {
            edfMetadata.getSignalMetadata().get(i).setNoOfSamplesInDataRecord(noOfSamplesInDataRecord);
        }

        for (int i = 0; i < noOfSignals; i++) {
            skip(edfFile, 32);
        }
    }

    private EdfData readData(InputStream edfFile, EdfMetadata edfMetadata) throws Exception {
        EdfData edfData = new EdfData();


        for (int k = 0; k < edfMetadata.getNoOfDataRecords(); k++) {
            EdfDataRecord edfDataRecord = new EdfDataRecord();
            edfDataRecord.setDataRecordId("" + k);
            edfData.getEdfDataRecords().add(edfDataRecord);

            for (int i = 0; i < edfMetadata.getNoOfSignalsInDataRecord(); i++) {
                EdfSignalDataRecord edfSignalDataRecord = new EdfSignalDataRecord();
                edfSignalDataRecord.setSignalId("" + i);
                edfDataRecord.getEdfSignalDataRecords().add(edfSignalDataRecord);

                for (int j = 0; j < edfMetadata.getSignalMetadata().get(i).getNoOfSamplesInDataRecord(); j++){
                    edfSignalDataRecord.getData().add(readIntFromBytes(edfFile));
                }

            }
        }
        return edfData;
    }

    private String readString(InputStream edfFile, int length)  throws Exception{
        byte[] bytes = new byte[length];
        edfFile.read(bytes);
        return new String(bytes).trim();
    }

    private Integer readInt(InputStream edfFile, int length)  throws Exception{
        return new Integer(readString(edfFile, length).trim());
    }

    private Double readDouble(InputStream edfFile, int length)  throws Exception{
        return new Double(readString(edfFile, length).trim());
    }


    private void skip(InputStream edfFile, int length) throws Exception {
        edfFile.skip(length);
    }

    private Integer readIntFromBytes(InputStream edfFile) throws Exception{
        byte[] bytes = new byte[1];
        edfFile.read(bytes);
        return new BigInteger(bytes).intValue();
    }

}
