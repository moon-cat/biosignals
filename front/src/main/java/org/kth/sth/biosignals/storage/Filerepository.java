package org.kth.sth.biosignals.storage;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfData;
import org.kth.sth.biosignals.edf2json.model.EdfMetadata;
import org.kth.sth.biosignals.edf2json.model.EdfProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class FileRepository{

    private final static Logger log = Logger.getLogger(FileRepository.class);

    @Value("${storage.path}")
    private String fileLocation;


    @PostConstruct
    public void init(){
        File root = new File(fileLocation);

        if (!root.exists()){
            root.mkdir();
        }

        File propertiesRoot = new File(fileLocation + "/properties");
        if (!propertiesRoot.exists()){
            propertiesRoot.mkdir();
        }
    }

    public void saveMetadata(String uuid, EdfMetadata edfMetadata){
        createEdfRootFolder(uuid);
        saveJsonObjectToFile(edfMetadata, getMetadataFileName(uuid));
    }

    public void saveProperties(EdfProperties edfProperties){
        saveJsonObjectToFile(edfProperties, getPropertiesFileName(edfProperties.getUuid()));
    }
    public EdfMetadata getMetadata(String uuid) {
        return (EdfMetadata) readJsonObjectFromFile(getMetadataFileName(uuid), EdfMetadata.class);
    }

    public EdfData getDataRecords(String uuid, int batchNo) {
        return (EdfData) readJsonObjectFromFile(getDataFileName(uuid, batchNo), EdfData.class);
    }
    public void saveDataRecords(String uuid, EdfData edfData, int batchNo){
        saveJsonObjectToFile(edfData, getDataFileName(uuid, batchNo));
    }

    public List<EdfProperties> list() {
        File propertiesDir = new File(fileLocation + "/properties");
        File[] propertiesFiles = propertiesDir.listFiles();
        List<EdfProperties> properties = new LinkedList<EdfProperties>();
        ObjectMapper object2JsonMapper = new ObjectMapper(new JsonFactory());


        if (propertiesFiles != null){
            for (File propertiesFile : propertiesFiles){
                try {
                    properties.add(object2JsonMapper.readValue(propertiesFile, EdfProperties.class));
                } catch (IOException e) {

                    log.error("failed to read property file", e);
                }
            }
        }

        return properties;
    }

    public boolean remove(String uuid) {

        File edfRootFolder = new File(getEdfRootFolder(uuid));
        File edfPropertiesFile = new File(getPropertiesFileName(uuid));

        boolean dataDeleted = edfRootFolder.delete();
        boolean propertiesDeleted = edfPropertiesFile.delete();

        return dataDeleted && propertiesDeleted;
    }

    private String getEdfRootFolder(String uuid) {
        return fileLocation + "/" + uuid;
    }


    private void saveJsonObjectToFile(Object object, String fileName) {
        ObjectMapper object2JsonMapper = new ObjectMapper(new JsonFactory());
        File metadataFile = new File(fileName);
        try {
            metadataFile.createNewFile();
        } catch (IOException e) {
            log.error("failed to create file " + fileName, e);
        }

        try {
            object2JsonMapper.writeValue(metadataFile, object);
        } catch (IOException e) {
            log.error("failed to save  file " + fileName, e);
        }
    }


    private String getMetadataFileName(String uuid) {
        return fileLocation + "/" + uuid + "/metadata.json";
    }

    private String getPropertiesFileName(String uuid) {
        return fileLocation + "/properties/" + uuid + ".json";
    }

    public String getOriginalDataFileName(String uuid) {
        return fileLocation + "/" + uuid + "/originaldata.bin";
    }

    private String getDataFileName(String uuid, int batchNo) {
        return fileLocation + "/" + uuid + "/data-" + batchNo + ".json";
    }

    private Object readJsonObjectFromFile(String fileName, Class clazz) {
        File file = new File(fileName);
        ObjectMapper object2JsonMapper = new ObjectMapper(new JsonFactory());

        Object object = null;

        try {
            object = object2JsonMapper.readValue(file, clazz);
        } catch (IOException e) {

            log.error("failed to read file: " + e.getMessage());
        }

        return object;
    }

    private void createEdfRootFolder(String uuid) {
        String rootFolderName = getEdfRootFolder(uuid);
        File root = new File(rootFolderName);
        root.mkdir();
    }



}
