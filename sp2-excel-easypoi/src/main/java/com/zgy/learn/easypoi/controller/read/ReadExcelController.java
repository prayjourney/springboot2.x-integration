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

    /**
     * 设置需要读取的行数
     *
     * @param file
     * @param line
     * @return
     * @throws Exception
     */
    @PostMapping("read-setline")
    public String readExcelSetLine(MultipartFile file, Integer line) throws Exception {
        if (null == file || line < 0) {
            return "输入文件不合法！";
        }

        // 导入的参数的设置
        ImportParams params = new ImportParams();
        // 设置表头所在的行, 从第1行开始
        params.setHeadRows(2);
        // 开始读取的sheet位置, 默认为0
        params.setStartSheetIndex(0);
        // 手动控制读取的行数, 目前来看这个是要-表头head的一行, 剩下的就是可以读取的数量
        params.setReadRows(line);

        List<PrimaryStudent> result = ExcelImportUtil.importExcel(file.getInputStream(),
                PrimaryStudent.class, params);
        for (PrimaryStudent student : result) {
            System.out.println(student.toString());
        }

        return "读取完成！";
    }

    @PostMapping("read-all")
    public String readExcelAllLine(MultipartFile file) throws Exception {
        if (null == file) {
            return "输入文件不合法！";
        }

        // 导入的参数的设置
        ImportParams params = new ImportParams();
        // 设置表头所在的行, 从第1行开始
        params.setHeadRows(2);
        // 开始读取的sheet位置, 默认为0
        params.setStartSheetIndex(0);
        // 手动控制读取的行数, 不去控制
        // params.setReadRows(2);

        List<PrimaryStudent> result = ExcelImportUtil.importExcel(file.getInputStream(),
                PrimaryStudent.class, params);
        System.out.println("一共读取了：" + result.size() + "行数据！");
        for (PrimaryStudent student : result) {
            System.out.println(student.toString());
        }

        return "读取完成！";
    }
}
