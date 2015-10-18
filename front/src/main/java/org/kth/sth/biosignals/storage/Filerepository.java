package org.kth.sth.biosignals.storage;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class FileRepository implements EdfRepository{

    private final static Logger log = Logger.getAnonymousLogger();

    @Value("${storage.path}")
    private String fileLocation;


    @Override
    public void save(Edf edf){

        ObjectMapper object2JsonMapper = new ObjectMapper(new JsonFactory());
        String dataFileName = fileLocation + "/data/" + edf.getEdfProperties().getUuid() + ".json";
        File dataFile = new File(dataFileName);

        if (!dataFile.exists()){
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                log.severe("failed to create data file: " + e.getMessage());
            }
        }

        try {
            object2JsonMapper.writeValue(dataFile, edf);
        } catch (IOException e) {
            log.severe("failed to save data file: " + e.getMessage());
        }

        String propertiesFileName = fileLocation + "/properties/" + edf.getEdfProperties().getUuid() + ".json";
        File propertiesFile = new File(propertiesFileName);

        if (!propertiesFile.exists()){
            try {
                propertiesFile.createNewFile();
            } catch (IOException e) {
                log.severe("failed to create properties file: " + e.getMessage());
            }
        }

        try {
            object2JsonMapper.writeValue(propertiesFile, edf.getEdfProperties());
        } catch (IOException e) {
            log.severe("failed to save properties file: " + e.getMessage());
        }
    }

    @Override
    public Edf get(String uuid) {

        File edfFile = new File(fileLocation + "/data/" + uuid + ".json");
        ObjectMapper object2JsonMapper = new ObjectMapper(new JsonFactory());
        Edf edf = null;

        try {
            edf = object2JsonMapper.readValue(edfFile, Edf.class);
        } catch (IOException e) {

            log.severe("failed to read file: " + e.getMessage());
        }

        return edf;
    }

    @Override
    public List<EdfProperties> list() {
        File propertiesDir = new File(fileLocation + "/properties");
        File[] propertiesFiles = propertiesDir.listFiles();
        List<EdfProperties> properties = new ArrayList<EdfProperties>();
        ObjectMapper object2JsonMapper = new ObjectMapper(new JsonFactory());


        if (propertiesFiles != null){
            for (File propertiesFile : propertiesFiles){
                try {
                    properties.add(object2JsonMapper.readValue(propertiesFile, EdfProperties.class));
                } catch (IOException e) {

                    log.severe("failed to read property file: " + e.getMessage());
                }
            }
        }

        return properties;
    }

    @Override
    public boolean remove(String uuid){

        boolean removed = false;

        File edfFile = new File(fileLocation + "/data/" + uuid + ".json");
        File edfPropertiesFile = new File(fileLocation + "/properties/" + uuid + ".json");

        boolean dataDeleted = edfFile.delete();
        boolean propertiesDeleted = edfPropertiesFile.delete();

        return dataDeleted && propertiesDeleted;
    }

}
