package com.mall.service.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author X
 * @version 1.0
 * @className OssService
 * @description OSS服务层
 * @date 2020/4/7-15:43
 **/
public interface OssService {

    /***
     * 图片上传方法
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);
}
