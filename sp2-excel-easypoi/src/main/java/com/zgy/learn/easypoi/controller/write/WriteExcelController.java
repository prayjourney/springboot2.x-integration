package com.zgy.learn.easypoi.controller.write;

import com.zgy.learn.easypoi.pojo.PrimaryStudent;
import com.zgy.learn.easypoi.service.WriteExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/6/28
 * @modified:
 */
@RestController
public class WriteExcelController {
    @Autowired
    private WriteExcelService writeExcelService;

    @PostMapping("write")
    public String writeObject(PrimaryStudent student, String fileName) throws IOException {
        if (null == student) {
            return "数据无效!";
        }
        // 存放路径设置
        String userPath = System.getProperty("user.dir");
        if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
            fileName = fileName + ".xlsx";
        } else {
            fileName = userPath + File.separator + fileName;
        }

        try {
            writeExcelService.writeObject(student, fileName);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "导出完成！";
    }

    /**
     * 一次性存放多条数据，list导入
     *
     * @param students
     * @param fileName
     * @return
     * @throws IOException
     */
    @PostMapping("write-list")
    public String writeList(@RequestBody List<PrimaryStudent> students, String fileName) throws IOException {
        if (null == students) {
            return "数据无效!";
        }
        // 存放路径设置
        String userPath = System.getProperty("user.dir");
        if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
            fileName = fileName + ".xlsx";
        } else {
            fileName = userPath + File.separator + fileName;
        }

        try {
            writeExcelService.writeList(students, fileName);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "导出完成！";
    }

}
