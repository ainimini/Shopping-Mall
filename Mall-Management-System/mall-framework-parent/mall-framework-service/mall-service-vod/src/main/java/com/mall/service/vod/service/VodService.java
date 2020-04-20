package com.mall.service.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author X
 * @version 1.0
 * @className VodService
 * @description
 * @date 2020/4/10-2:12
 **/
public interface VodService {

    /***
     * 上传视频到阿里云
     * @param file
     * @return
     */
    String uploadAlyiVideo(MultipartFile file);

    /***
     * 删除视频
     * @param videoId
     */
    void removeVideo(String videoId);

    /***
     * 批量删除视频
     * @param videoIdList
     */
    void removeVideoList(List<String> videoIdList);
}
