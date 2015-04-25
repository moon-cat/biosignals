package org.kth.sth.biosignals.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableAutoConfiguration
public class FrontController {

    @RequestMapping("/hello")
    @ResponseBody
    String index() throws Exception{
        return "Welcome to Biosignal FRONT";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FrontController.class, args);
    }
}
