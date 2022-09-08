package com.yangteng.workbackstage;


import com.yangteng.workbackstage.comm.PythonScriptLoad;
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


}
