package org.kth.sth.biosignals;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class BiosignalsSpringBootApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BiosignalsSpringBootApplication.class, args);
    }


}
