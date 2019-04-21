package com.mall.bit.cqt.mall.controller;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传
 */
@Controller
@Api(tags = "文件上传模块")
public class UploadController {

    private static final String UPLOAD_PATH = "/static/upload/";
    @ResponseBody
    @RequestMapping(value = {"upload"})
    public Map<String,Object> upload(MultipartFile dropzFile,MultipartFile[] wEditor, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        //dropZone
        if(dropzFile != null){
            result.put("fileName", "http://localhost:8080"+UPLOAD_PATH +(write(dropzFile,request)).getName());
        }
        //wangEditor
        else {
            List<String> filePaths = new ArrayList();
            String serverPath = String.format("%s://%s:%s%s%s", request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath(), UPLOAD_PATH);
            for (MultipartFile editorFile : wEditor) {
                filePaths.add(write(editorFile, request).getName());

            }
            result.put("errno",0);
            result.put("data",serverPath+filePaths);
        }
        return result;
    }

    /**
     * 写入方法
     * @param multipartFile 传输对象
     * @param request
     * @return
     */
    public File write(MultipartFile multipartFile,HttpServletRequest request){
        //获取上传的原始文件名
        String fileName = multipartFile.getOriginalFilename();
        //设置文件上传路径
        String filePath = request.getSession().getServletContext().getRealPath("/static/upload");
        //获取文件后缀
        String fileSuffix = fileName.substring(fileName.indexOf("."),fileName.length());

        File file = new File(filePath);
        //判断是否为目录,不是则创建上传用的文件夹
        if(!file.exists()){
            file.mkdir();
        }
        //用UUID设置文件名唯一
        file = new File(filePath, UUID.randomUUID()+fileSuffix);

        try {
            //写入文件
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;


    }
}