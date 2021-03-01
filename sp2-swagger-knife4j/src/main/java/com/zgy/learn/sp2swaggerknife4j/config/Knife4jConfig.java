package com.zgy.learn.sp2swaggerknife4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author: z.g.y
 * @date: 2021/3/1
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Knife4j整合springboot2.x")
                        .description("Knife4j整合springboot2.x, knife4j是为Java " +
                                "MVC框架集成Swagger生成Api文档的增强解决方案, 目前已经发行的Knife4j版本, Knife4j本身已经引入了springfox, " +
                                "开发者在使用时不用再单独引入Springfox的具体版本")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact("xx@qq.com")
                        .version("1.0")
                        .build())
                // 分组名称
                .groupName("2.X版本")
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.zgy.learn.sp2swaggerknife4j.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
