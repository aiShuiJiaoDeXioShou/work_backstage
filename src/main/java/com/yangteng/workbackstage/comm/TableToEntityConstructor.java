package com.yangteng.workbackstage.comm;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.Collections;

public class TableToEntityConstructor {
        public static final String BASE_PATH = "D:/3.Node.js/worspace-javaScript/workdev/WorkBackstage/src/main";

        // 新版本的代码生成器
        public static void AutoTable(String entity_package, String mapper_package, String service_package,
                        String... tables) {
                FastAutoGenerator.create(
                                "jdbc:mysql://localhost:3306/work?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false",
                                "root",
                                "root")
                                .globalConfig(builder -> {
                                        builder.author("林河") // 设置作者
                                                        .enableSwagger() // 开启 swagger 模式
                                                        .disableOpenDir()
                                                        .commentDate("yyyy-MM-dd")
                                                        // 指定输出目录
                                                        .outputDir(BASE_PATH + "/java");
                                })
                                .packageConfig(builder -> {
                                        builder.parent("com.yangteng") // 设置父包名
                                                        .moduleName("workbackstage") // 设置父包模块名
                                                        .pathInfo(Collections.singletonMap(OutputFile.xml,
                                                                        BASE_PATH + "\\resources\\mapper"))
                                                        .entity(entity_package.isEmpty() ? "entity" : entity_package) // 设置mapperXml生成路径
                                                        .mapper(mapper_package.isEmpty() ? "mapper" : mapper_package) // 设置mapperXml生成路径
                                                        .service(service_package.isEmpty() ? "service"
                                                                        : service_package)
                                                        .serviceImpl(service_package.isEmpty() ? "service.impl"
                                                                        : service_package + ".impl"); // 设置mapperXml生成路径
                                })
                                .strategyConfig(builder -> {
                                        builder.addInclude(tables) // 设置需要生成的表名
                                                        .addTablePrefix("t_", "c_", "ua_","us_")// 设置过滤表前缀
                                                        .build()
                                                        .entityBuilder()
                                                        .enableChainModel()
                                                        // 设置采用Lombok策略
                                                        .enableLombok()
                                                        .enableChainModel()
                                                        .enableTableFieldAnnotation()
                                                        .logicDeletePropertyName("deleted")
                                                        .idType(IdType.ASSIGN_ID)
                                                        .enableFileOverride()
                                                        .addTableFills(
                                                                        new Property("updateTime",
                                                                                        FieldFill.INSERT_UPDATE),
                                                                        new Property("createTime", FieldFill.INSERT),
                                                                        new Property("createUser", FieldFill.INSERT),
                                                                        new Property("updateUser",
                                                                                        FieldFill.INSERT_UPDATE)
                                                        )
                                                        .build();
                                })
                                .templateConfig(builder -> {
                                        builder.disable(TemplateType.CONTROLLER).entity("ftl/entity");
                                })
                                .templateEngine(new FreemarkerTemplateEngine())
                                .execute();
        }

        public static void main(String[] args) {
                // TableToEntityConstructor.AutoTable("book_collect",
                // "monthly_ticket","user_consume");
                // TableToEntityConstructor.AutoTable("work_book");
                // 根据数据库里面的权限认证生成代码
                /* TableToEntityConstructor.AutoTable(
                 "entity.ua",
                 "mapper",
                 "service",
                 "ua_role",
                 "ua_user_for_role",
                 "ua_authority",
                 "ua_role_for_authority"
                 );*/

                /*// Book 章节管理
                TableToEntityConstructor.AutoTable(
                                "entity",
                                "mapper",
                                "service",

                        "book_chapter",
                        "chapter_comment",
                        "book_collect",
                        "chapter_comment",
                        "monthly_ticket",
                        "user_consume",
                        "work_book",
                        "work_user"
                );*/

        /*    TableToEntityConstructor.AutoTable(
                    "entity.us",
                    "mapper",
                    "service",
                    "us_recharge",
                    "us_consume",
                    "us_coupon",
                    "us_coupon_user_bag"
            );*/

            TableToEntityConstructor.AutoTable(
                    "entity.book",
                    "mapper",
                    "service",
                    "book"

            );
        }
}
