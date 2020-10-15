package com.lizhao.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * 文件上传控制器
 */


@Controller
public class UploadController {

    /**
     *
     * @param dropzFile:参数名称要和前端上传的名称对应 dropzone
     * @param editfile: wangedit
     * @return
     */

    private static final String UPLOAD_PATH="/static/upload/";
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropzFile, MultipartFile editFile, HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();
        MultipartFile myfile = dropzFile==null ? editFile : dropzFile;
        // 获取文件后缀
        String filename = myfile.getOriginalFilename();
        String fileSuffix = filename.substring(filename.lastIndexOf("."));
        String filepath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        File file = new File(filepath);
        if(!file.exists()){
            file.mkdir();
            System.out.println(file.getAbsolutePath());
        }
        // 将文件写入目标
        file = new File(filepath, UUID.randomUUID()+fileSuffix); // 文件名要唯一
        try {
            myfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(dropzFile!=null){
            result.put("fileName", UPLOAD_PATH+file.getName()); // 加UPLOAD_PATH是为了前端列表页直接点查看图片时，地址正确
        }
        else{
            /**
             * request.getScheme():获取服务器协议
             * request.getServerName()：获取服务器名称
             * request.getServerPort()：获取服务器端口号
             */
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            result.put("errno", 0);
            result.put("data", new String[]{serverPath + UPLOAD_PATH + file.getName()});
        }

        return result;
    }
}
