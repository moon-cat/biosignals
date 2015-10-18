package org.kth.sth.biosignals.front;

import org.kth.sth.biosignals.edf2json.Edf2JsonConverter;
import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfProperties;

import org.kth.sth.biosignals.storage.EdfRepository;
import org.kth.sth.biosignals.storage.InMemoryRepository;
import org.kth.sth.biosignals.storage.RedisRepository;
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
    EdfRepository repository;


    @RequestMapping(value = "/upload/data", method = RequestMethod.POST)
    Edf upload(@RequestParam("file") MultipartFile file,
                  @RequestParam("name") String name) throws Exception{

        Edf edf = new Edf2JsonConverter().readEdf(file.getInputStream());
        EdfProperties edfProperties = new EdfProperties();

        edfProperties.setUuid(UUID.randomUUID().toString());
        edfProperties.setName(name);
        edfProperties.setOriginalFilename(file.getOriginalFilename());
        edfProperties.setCreatedDate(new Date().toString());
        edfProperties.setModifiedDate(new Date().toString());
        edfProperties.setMetadataIdentification(edf.getEdfMetadata().getLocalRecordingIdentification());
        edf.setEdfProperties(edfProperties);

        sessionData.setEdf(edf);
        repository.save(edf);

        return edf;
    }

}
