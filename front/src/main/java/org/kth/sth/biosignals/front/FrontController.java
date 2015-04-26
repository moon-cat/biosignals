package org.kth.sth.biosignals.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableAutoConfiguration
public class FrontController {

    @RequestMapping("/home")
    String home() throws Exception{
        return "home";
    }

    @RequestMapping("/upload")
    String upload() throws Exception{
        return "upload";
    }

    @RequestMapping("/manage")
    String manage() throws Exception{
        return "manage";
    }

    @RequestMapping("/visualize")
    String visualize() throws Exception{
        return "visualize";
    }

    @RequestMapping("/analyze")
    String analyze() throws Exception{
        return "analyze";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FrontController.class, args);
    }
}
