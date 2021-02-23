package com.zgy.bootintegration.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * @author: z.g.y
 * @description: 代码自动生成器
 * @date: Created in 2020/5/19 2:26
 * @modified:
 */
public class CodeAutoGenerator {
    public static void main(String[] args) {
        // 1.构建一个代码自动生成器对象
        AutoGenerator ag = new AutoGenerator();

        // 2.配置策略
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java/");
        gc.setAuthor("zgy");
        gc.setOpen(false);
        gc.setFileOverride(false);  //是否覆盖
        gc.setServiceName("%sSevice"); //去掉service的I前缀
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        ag.setGlobalConfig(gc);

        // 3.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/myschool?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        ag.setDataSource(dsc);

        // 4.包的设置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("myschool");
        pc.setParent("org.zgy");
        pc.setEntity("pojo");
        pc.setService("service");
        pc.setController("controller");
        ag.setPackageInfo(pc);

        // 5.策略配置，比如
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("city", "teacher", "parents", "student", "souvenir"); //要映射的表名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel); // 驼峰命名
        strategyConfig.setEntityLombokModel(true); //自动lombok
        //strategyConfig.setLogicDeleteFieldName("deleted");
        //自动填充
        TableFill createTime = new TableFill("createTime", FieldFill.INSERT);
        TableFill updateTime = new TableFill("updateTime", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategyConfig.setTableFillList(tableFills);
        //乐观锁
        strategyConfig.setVersionFieldName("version");
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        ag.setStrategy(strategyConfig);
        // 执行
        ag.execute();


    }

}
