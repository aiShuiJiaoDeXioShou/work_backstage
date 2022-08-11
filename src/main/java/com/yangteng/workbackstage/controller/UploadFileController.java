package com.yangteng.workbackstage.controller;

import cn.hutool.core.io.FileUtil;
import com.yangteng.workbackstage.comm.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 文件的上传与下载
 */
@RestController
public class UploadFileController {


    // 获取配置文件里面指定的路径
    @Value("${upload.file}")
    private String uploadUrl;

    @Autowired
    private HttpServletResponse response;

    @GetMapping("/dom")
    public void dom(String name) throws Exception {
        final BufferedInputStream reader = new BufferedInputStream(Files.newInputStream(Paths.get(uploadUrl + name)));
        byte[] bytes = new byte[1024];
        final ServletOutputStream outputStream = response.getOutputStream();
        int len;
        while ((len = reader.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }
        reader.close();
        outputStream.close();
    }


    // 上传文件
    @PostMapping("/upload")
    public R upload(MultipartFile file) throws IOException {
        // 随机生成id
        final String randomID = UUID.randomUUID().toString();
        if (FileUtil.isDirEmpty(new File(uploadUrl))) FileUtil.mkdir(uploadUrl);
        // 根据路径生成文件
        final int start = file.getName().lastIndexOf(".");
        final String suffix = file.getName().substring(start);
        final String newPath = uploadUrl + randomID + "." + suffix;
        file.transferTo(new File(newPath));
        return R.ok("static/" + randomID + "." + suffix);
    }
}
