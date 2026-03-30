package com.green.greengram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class GreengramjpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreengramjpaApplication.class, args);
    }

}
