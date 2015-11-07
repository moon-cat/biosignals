package org.kth.sth.biosignals;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@SpringBootApplication
public class BiosignalsSpringBootApplication {

    @PostConstruct
    public void init(){
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        System.out.println("Memory settings: " + memoryBean.getHeapMemoryUsage().getMax());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BiosignalsSpringBootApplication.class, args);
    }


}
