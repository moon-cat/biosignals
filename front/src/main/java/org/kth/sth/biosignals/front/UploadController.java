package org.kth.sth.biosignals.front;

import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfMetadata;
import org.kth.sth.biosignals.edf2json.model.EdfProperties;

import org.kth.sth.biosignals.storage.DataAccessHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@RestController
@Scope("session")
public class UploadController {

    @Autowired
    private  SessionData sessionData;

    @Autowired
    private DataAccessHelper dataAccessHelper;


    @RequestMapping(value = "/upload/data", method = RequestMethod.POST)
    EdfMetadata upload(@RequestParam("file") MultipartFile file,
                  @RequestParam("name") String name) throws Exception{

        String uuid = UUID.randomUUID().toString();
        EdfProperties edfProperties = createEdfProperties(uuid, file, name);
        Edf edf = dataAccessHelper.uploadEdf(file, edfProperties);
        sessionData.setEdf(edf);
        return edf.getEdfMetadata();
    }

    private EdfProperties createEdfProperties(String uuid, MultipartFile file, String name) {
        EdfProperties edfProperties = new EdfProperties();
        edfProperties.setUuid(uuid);
        edfProperties.setName(name);
        edfProperties.setOriginalFilename(file.getOriginalFilename());
        edfProperties.setCreatedDate(new Date().toString());
        edfProperties.setModifiedDate(new Date().toString());
        return edfProperties;
    }

}
