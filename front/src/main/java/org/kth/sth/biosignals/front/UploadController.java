package org.kth.sth.biosignals.front;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {


    @RequestMapping(value = "/upload/data", method = RequestMethod.POST)
    String upload(@RequestParam("file") MultipartFile file,
                  @RequestParam("name") String name) throws Exception{
        System.out.println(name);
        System.out.println(file.getOriginalFilename());

        return "{}";
    }

}
