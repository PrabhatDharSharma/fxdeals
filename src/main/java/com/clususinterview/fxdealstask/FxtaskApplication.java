package com.clususinterview.fxdealstask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FxtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxtaskApplication.class, args);
    }

}
