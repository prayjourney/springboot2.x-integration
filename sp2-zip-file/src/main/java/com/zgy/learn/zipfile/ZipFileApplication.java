package com.zgy.learn.zipfile;

import com.zgy.learn.zipfile.util.ZipUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@SpringBootApplication
class ZipFileApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(ZipFileApplication.class, args);
    }

}
