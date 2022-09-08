package com.yangteng.workbackstage;

import com.yangteng.workbackstage.comm.PythonScriptLoad;
import com.yangteng.workbackstage.comm.TableToEntityConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkBackstageApplicationTests {

    @Autowired
    PythonScriptLoad load;

    @Test
    void contextLoads() {

    }

    @Test
    public void test2() throws Exception {
        System.out.println(load.getScripts());
    }

}
