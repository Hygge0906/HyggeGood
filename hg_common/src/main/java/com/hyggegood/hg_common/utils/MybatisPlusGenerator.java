package com.hyggegood.hg_common.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("all")
@Slf4j
@Data
public class MybatisPlusGenerator {

    protected static String URL = "jdbc:mysql://localhost:3306/hgsql?characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false";
    protected static String USERNAME = "root";
    protected static String PASSWORD = "root";

    protected static DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(URL, USERNAME, PASSWORD);

    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(
                        (scanner/*lamdba*/, builder/*变量*/) ->
                                builder.author("HyggeGood-YangDengYu")
//                                        .enableSwagger()
                                        .fileOverride()
                                        //生成的文件的输出目录
                                        .outputDir("E:\\ProjectFiles\\HG\\HyggeGood\\hg_admin" + "\\src\\main\\java")
                                        .commentDate("yyyy-MM-dd")
                                        .dateType(DateType.TIME_PACK)
                )
                .packageConfig((builder) ->
                        builder.parent("com.hyggegood.admin")
                                .entity("entity")
                                .service("service")
                                .serviceImpl("service.impl")
                                .mapper("mapper")
                                .xml("mapper")
                                //xml文件的绝对路径
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\ProjectFiles\\HG\\HyggeGood\\hg_admin\\src\\main\\resources\\mapper"))
                )
                .injectionConfig((builder) ->
                        builder.beforeOutputFile(
                                (a, b) -> log.warn("tableInfo: " + a.getEntityName())
                        )
                )
                .strategyConfig((scanner, builder) ->
                        builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                                .addTablePrefix("auth_")
                                .entityBuilder()
                                .enableChainModel()
                                .enableLombok()
                                .enableTableFieldAnnotation()
                                .addTableFills(
                                        new Column("created_at", FieldFill.INSERT),
                                        new Column("updated_at", FieldFill.INSERT_UPDATE)
                                )
                                .controllerBuilder()
                                .enableRestStyle()
                                .enableHyphenStyle()
                                .serviceBuilder()
                                .formatServiceFileName("%sService")
                                .formatServiceImplFileName("%sServiceImpl")
                                .mapperBuilder()
                                .formatMapperFileName("%sMapper")
                                .formatXmlFileName("%sXml")
                                .build())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}