package com.mall.service.oss.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.mall.common.entity.Result;
import com.mall.service.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author X
 * @version 1.0
 * @ClassName Osscontroller
 * @description OSS控制层
 * @date 2020/4/7
 **/
@Api(description = "OSS")
@RestController
@RequestMapping(value = "/oss/fileOss")

public class OssController {

    @Autowired
    private OssService ossService;

    /***
     * 图片上传方法
     * @param file
     * @return
     */
    @ApiOperation(value = "图片上传方法")
    @PostMapping(value = "/uploadOssFile")
    public Result uploadOssFile(MultipartFile file) {
        //获取上传文件 MultipartFile
        String url = ossService.uploadFileAvatar(file);
        return Result.ok().data("url",url);
    }

}
