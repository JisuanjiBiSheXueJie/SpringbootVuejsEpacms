package com.kevin.epacms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kevin
 * @since 2022/10/5 12:29
 */
public class CodeGenerator {
    public static void main(String[] args) {
        List<String> tables = new ArrayList<>();
//        tables.add("t_grade");
//        tables.add("t_instructor_student");
//        tables.add("t_leave");
//        tables.add("t_news");
//        tables.add("t_notice");
//        tables.add("t_permission");
//        tables.add("t_record");
//        tables.add("t_role");
//        tables.add("t_role_permission");
//        tables.add("t_upload");
//        tables.add("t_user");
//        tables.add("t_user_grade");
        tables.add("t_operation_log");
        tables.add("t_exception_log");

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/epacms?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai","root","root")
                .globalConfig(builder -> {
                    builder.author("kevin")  //作者
                            .outputDir(System.getProperty("user.dir")+"\\src\\main\\java")    //输出路径(写到java目录)
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd");
//                            .fileOverride();            //开启覆盖之前生成的文件

                })
                .packageConfig(builder -> builder.parent("com.kevin.epacms")
//                        .moduleName("chat")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .mapper("mapper")
                        .xml("mapper")
                        .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+"\\src\\main\\resources\\mapper")))
                .strategyConfig(builder -> builder.addInclude(tables)
                        .addTablePrefix("t_")
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .entityBuilder()
                        .enableLombok()
                        .logicDeleteColumnName("deleted")
                        .enableTableFieldAnnotation()
                        .controllerBuilder()
                        // 映射路径使用连字符格式，而不是驼峰
                        .enableHyphenStyle()
                        .formatFileName("%sController")
                        .enableRestStyle()
                        .mapperBuilder()
                        //生成通用的resultMap
                        .enableBaseResultMap()
                        .superClass(BaseMapper.class)
                        .formatMapperFileName("%sMapper")
//                        .enableMapperAnnotation()
                        .formatXmlFileName("%sMapper"))
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

