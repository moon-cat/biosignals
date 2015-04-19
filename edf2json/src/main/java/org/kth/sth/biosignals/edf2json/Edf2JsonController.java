package org.kth.sth.biosignals.edf2json;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Edf2JsonController {

    @RequestMapping("/")
    @ResponseBody
    String index() throws Exception{
        return "Welcome to Biosignal";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Edf2JsonController.class, args);
    }
}
