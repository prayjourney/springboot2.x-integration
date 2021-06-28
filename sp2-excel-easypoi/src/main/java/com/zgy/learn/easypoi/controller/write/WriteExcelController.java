package com.zgy.learn.easypoi.controller.write;

import com.zgy.learn.easypoi.pojo.PrimaryStudent;
import com.zgy.learn.easypoi.service.WriteExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
        try {
            writeExcelService.writeObject(student, fileName);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "导出完成！";
    }

}
