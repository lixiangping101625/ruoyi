package com.ruoyi.web.controller;

import com.sun.javafx.binding.StringFormatter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 表单文件上传
 */
@RestController
@RequestMapping("/file")
public class FormFileController {

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg) {
//                         @RequestPart("photos") MultipartFile[] photos) {

        System.out.println(StringFormatter.concat("上传的信息：email={},username={},headerImg={}",
                email, username, headerImg.getSize()));
        if (!headerImg.isEmpty()) {
            //保存文件到服务器
            String originalFilename = headerImg.getOriginalFilename();
            try {
                File file = new File("H:\\springboot\\virtual\\upload" + File.separator + originalFilename);
                if (!file.exists()) {
                    file.mkdirs();
                }
                headerImg.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        if (photos.length > 0) {
//            for (MultipartFile multipartFile : photos) {
//                System.out.println(multipartFile.getOriginalFilename());
//                try {
//                    String originalFilename = multipartFile.getOriginalFilename();
//                    multipartFile.transferTo(new File("D:\\spring_annation\\file\\" + originalFilename));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return "上传成功";
    }


}