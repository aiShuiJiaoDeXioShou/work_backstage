package com.yangteng.workbackstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WorkBackstageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkBackstageApplication.class, args);
    }

}
