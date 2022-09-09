package com.yangteng.workbackstage;


import com.yangteng.workbackstage.comm.PythonScriptLoad;
import com.yangteng.workbackstage.comm.TableToEntityConstructor;
import org.junit.jupiter.api.Test;

public class Test111 {
    @Test
    public void test1() throws Exception {
        String path = "D:\\1uihsdfs.java";
        int i = path.lastIndexOf(".");
        int i2 = path.lastIndexOf("/");
        int i3 = path.lastIndexOf("\\");
        System.out.println(i2+"- "+i3);
        String substring = path.substring(i2+1,i);
        System.out.println(substring);
    }

    // 表生成器
    @Test
    void AutoTable() {
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
                "entity.us",
                "mapper",
                "service",
                "ex_record"

        );
    }


}
