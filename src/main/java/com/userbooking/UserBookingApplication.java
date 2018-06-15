package com.userbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableConfigurationProperties
@SpringBootApplication
@EnableSwagger2
public class UserBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserBookingApplication.class, args);
    }
}
