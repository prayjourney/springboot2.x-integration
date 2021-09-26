package com.zgy.learn.zipupbigfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class ZipUpBigFileApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(ZipUpBigFileApplication.class, args);
    }

}
