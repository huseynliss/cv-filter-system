package com.example.cvfiltersystem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@PropertySource("classpath:multipart.properties")
@PropertySource("classpath:mail.properties")
@PropertySource("classpath:jwt.properties")
public class CvFilterSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(CvFilterSystemApplication.class, args);

    }

}
