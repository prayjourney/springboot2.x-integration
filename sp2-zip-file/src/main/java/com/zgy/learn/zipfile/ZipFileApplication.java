package com.zgy.learn.zipfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
class ZipFileApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(ZipFileApplication.class, args);
    }

}
