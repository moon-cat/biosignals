package org.kth.sth.biosignals.front;

import org.kth.sth.biosignals.edf2json.Edf2JsonConverter;
import org.kth.sth.biosignals.edf2json.model.Edf;
import org.kth.sth.biosignals.edf2json.model.EdfProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
public class UploadController {

    @Autowired
    private  SessionData sessionData;

    @RequestMapping(value = "/upload/data", method = RequestMethod.POST)
    Edf upload(@RequestParam("file") MultipartFile file,
                  @RequestParam("name") String name) throws Exception{

        Edf edf = new Edf2JsonConverter().readEdf(file.getInputStream());
        EdfProperties edfProperties = new EdfProperties();

        edfProperties.setName(name);
        edfProperties.setOriginalFilename(file.getOriginalFilename());
        edfProperties.setCreatedDate(new Date().toString());
        edfProperties.setModifiedDate(new Date().toString());
        edf.setEdfProperties(edfProperties);

        sessionData.setEdf(edf);

        return edf;
    }

}
