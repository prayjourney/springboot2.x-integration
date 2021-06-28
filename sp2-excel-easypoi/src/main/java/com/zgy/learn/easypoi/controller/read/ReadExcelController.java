package com.zgy.learn.easypoi.controller.read;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.zgy.learn.easypoi.pojo.PrimaryStudent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/6/29
 * @modified:
 */
@RestController
public class ReadExcelController {
    @PostMapping("read")
    public String readExcel(MultipartFile file) throws Exception {
        if (null == file) {
            return "输入文件不合法！";
        }

        // 导入的参数的设置
        ImportParams params = new ImportParams();
        // 设置表头所在的行, 从第1行开始
        params.setHeadRows(2);
        // 开始读取的sheet位置, 默认为0
        params.setStartSheetIndex(0);
        // 手动控制读取的行数
        params.setReadRows(2);

        List<PrimaryStudent> result = ExcelImportUtil.importExcel(file.getInputStream(),
                PrimaryStudent.class, params);
        for (PrimaryStudent student : result) {
            System.out.println(student.toString());
        }

        return "读取完成！";
    }
}
