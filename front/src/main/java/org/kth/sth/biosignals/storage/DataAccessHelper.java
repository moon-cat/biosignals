package org.kth.sth.biosignals.storage;

import org.apache.log4j.Logger;
import org.kth.sth.biosignals.edf2json.Edf2JsonConverter;
import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfData;
import org.kth.sth.biosignals.edf2json.model.EdfMetadata;
import org.kth.sth.biosignals.edf2json.model.EdfProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class DataAccessHelper {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private Edf2JsonConverter edf2JsonConverter;

    private final static Logger log = Logger.getLogger(DataAccessHelper.class);


    private final static int DATA_RECORD_BATCH_SIZE = 50;


    public Edf uploadEdf(MultipartFile originalFile, EdfProperties edfProperties) throws Exception{
        Edf edf = edf2JsonConverter.readMetadataAndFirstDataRecordBatch(originalFile.getInputStream(), DATA_RECORD_BATCH_SIZE);

        edf.getEdfMetadata().setEdfProperties(edfProperties);
        edfProperties.setMetadataIdentification(edf.getEdfMetadata().getLocalRecordingIdentification());

        fileRepository.saveProperties(edfProperties);
        fileRepository.saveMetadata(edfProperties.getUuid(), edf.getEdfMetadata());
        fileRepository.saveDataRecords(edfProperties.getUuid(), edf.getEdfData(), 0);
        originalFile.transferTo(new File(fileRepository.getOriginalDataFileName(edfProperties.getUuid())));

        return edf;
    }

    public EdfMetadata getMetadata(String uuid){
        return fileRepository.getMetadata(uuid);
    }

    public EdfData loadRecord(String uuid, Integer record, EdfMetadata edfMetadata) throws Exception{

        log.info("Reading record: " + record);

        int batch = (record + 1) / DATA_RECORD_BATCH_SIZE;

        log.info("Reading batch from file: " + batch);

        EdfData edfData = fileRepository.getDataRecords(uuid, batch);

        if (edfData == null){
            File originalFile = new File(fileRepository.getOriginalDataFileName(uuid));
            edfData = edf2JsonConverter.readDataRecordBatch(
                    new FileInputStream(originalFile),
                    batch * DATA_RECORD_BATCH_SIZE, DATA_RECORD_BATCH_SIZE,
                    edfMetadata);
            fileRepository.saveDataRecords(uuid, edfData, batch);
        }

        return edfData;
    }

    public List<EdfProperties> list(){
        return fileRepository.list();
    }

    public boolean remove(String uuid){
        return  fileRepository.remove(uuid);
    }
}
