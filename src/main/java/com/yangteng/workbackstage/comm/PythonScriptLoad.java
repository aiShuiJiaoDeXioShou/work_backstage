package com.yangteng.workbackstage.comm;

import cn.hutool.core.util.RuntimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

/**
 * 此类运行python脚本,实现对服务的热维护
 */
@Component
public class PythonScriptLoad {
    private Map<String, String> scripts;
    /**
     * 获取所有能运行的python脚本
     * @return 返回所有能运行的python脚本
     */
    public Map<String, String> getScripts() {
        scripts = scripts();
        return scripts;
    }

    /**
     * 获取指定能运行的python脚本
     * @param name 需要运行python脚本的名称
     * @return python脚本的路径
     */
    public String getScript(String name) {
        if (Objects.isNull(scripts)) scripts = scripts();
        return scripts.get(name);
    }

    @Value("${python.script.path}")
    private String pythonLoadPath;

    /**
     * 判断该路径是否具有指定后缀
     */
    public static Boolean isSuffix(String path,String suffix){
        int i = path.lastIndexOf(".");
        String substring = path.substring(i+1);
        return substring.equals(suffix);
    }

    /**
     * 获取该目录下所有的python脚本
     * @return python路径数组
     */
    private Map<String,String> scripts() {
        File file = new File(pythonLoadPath);
        if (!file.isDirectory()){
            throw new RuntimeException("没有指定python脚本的文件夹！");
        }
        File[] files = file.listFiles();
        assert files != null;

        Map<String,String> res = new HashMap<>();
        for (File s: files) {
            if(isSuffix(s.getName(),"py")){
                res.put(s.getName(),s.getPath());
            }
        }

        if (res.size() == 0) {
            throw new RuntimeException("该文件夹下面没有Python脚本！");
        }

        return res;
    }

    /**
     * java 调用命令行运行python脚本，并且获取运行结果
     */
    public <T> List<T> execute(String script,Class<T> type,String... args) {
        List<T> t;
        String exec = RuntimeUtil.execForStr("python " + script);
        try {
            t = JSON.parseArray(exec, type);
        }catch (JSONException e){
            throw new RuntimeException("无法解析运行python脚本的数据，请确定是否有效输出了JSON数据！");
        }
        return t;
    }

    /**
     * 运行python脚本服务
     */
    public <T> List<T> runPythonScript(String scriptName, Class<T> type,String... args){
        String s = getScript(scriptName);
        return execute(s,type,args);
    }
}
