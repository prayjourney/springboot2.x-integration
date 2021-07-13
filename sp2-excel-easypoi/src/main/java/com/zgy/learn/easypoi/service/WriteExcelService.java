package com.zgy.learn.easypoi.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.zgy.learn.easypoi.pojo.PrimaryStudent;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/6/28
 * @modified:
 */
@Service
public class WriteExcelService {
    public void writeObject(PrimaryStudent student, String fileName) throws IOException {
        /**
         *  excel生成的工具类:
         *  ExportParams：一些基本配置, 名字, sheet名字
         *  POIEmployee.class :导出的对象的类型
         *  list：要导出的数据
         */
        List<PrimaryStudent> data = new ArrayList<>();
        data.add(student);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("员工信息", "员工表"),
                PrimaryStudent.class, data);

        //写一个文件输出流，把内存中的excel文件写出去
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
    }

    public void writeList(List<PrimaryStudent> students, String fileName) throws IOException {
        /**
         *  excel生成的工具类:
         *  ExportParams：一些基本配置, 名字, sheet名字
         *  POIEmployee.class :导出的对象的类型
         *  list：要导出的数据
         */
        List<PrimaryStudent> data = new ArrayList<>();
        data.addAll(students);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("员工信息", "员工表"),
                PrimaryStudent.class, data);

        //写一个文件输出流，把内存中的excel文件写出去
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
    }

}
