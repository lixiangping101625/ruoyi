package com.ruoyi.web.controller;

import com.ruoyi.common.enums.OSSFileEnum;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Administrator
 * @descition
 * @date 2019/12/10 0010  11:17
 */
@Controller
@RequestMapping("/ali_oss")
public class AliOSSController {


    @Autowired
    private AliOSSUtils ossUtils;

    /** 文件上传*/
    @RequestMapping(value = "/upload")
    @ResponseBody
    public AjaxResult uploadBlog(@RequestParam("img") MultipartFile file,@RequestParam Integer simpleUploadType) {
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        try {

            if (file!=null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    OSSFileEnum ossFileEnum = OSSFileEnum.convertCode2Instance(simpleUploadType);
                    String path = ossUtils.simpleUpload(newFile, ossFileEnum);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return AjaxResult.error("上传失败");
        }
        return AjaxResult.success("上传成功");
    }

}