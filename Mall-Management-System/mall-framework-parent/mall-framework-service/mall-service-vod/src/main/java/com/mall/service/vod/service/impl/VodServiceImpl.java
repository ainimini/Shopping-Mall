package com.mall.service.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.mall.common.entity.dto.CustomException;
import com.mall.service.vod.service.VodService;
import com.mall.service.vod.utils.AliyunVodSDKUtils;
import com.mall.service.vod.utils.ConstantPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author X
 * @version 1.0
 * @ClassName VodServiceImpl
 * @description
 * @date 2020/4/10
 **/
@Slf4j
@Service
public class VodServiceImpl implements VodService {

    /***
     * 上传视频到阿里云
     * @param file
     * @return
     */
    @Override
    public String uploadAlyiVideo(MultipartFile file) {
        try {
            //上传文件输入流
            InputStream inputStream = file.getInputStream();
            //上传原始视频名称
            String originalFilename = file.getOriginalFilename();
            //上传视频名称
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                log.warn(errorMessage);
                if (StringUtils.isEmpty(videoId)) {
                    throw new CustomException(20001, errorMessage);
                }
            }
            return videoId;
        } catch (IOException e) {
            throw new CustomException(20001, "guli vod 服务上传失败");
        }
    }

    /***
     * 删除视频
     * @param videoId
     */
    @Override
    public void removeVideo(String videoId) {
        try{
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            DeleteVideoRequest request = new DeleteVideoRequest();

            request.setVideoIds(videoId);

            DeleteVideoResponse response = client.getAcsResponse(request);

            System.out.print("RequestId = " + response.getRequestId() + "\n");

        }catch (ClientException e){
            throw new CustomException(20001, "视频删除失败");
        }
    }

    /***
     * 批量删除视频
     * @param videoIdList
     */
    @Override
    public void removeVideoList(List<String> videoIdList) {
        try {
            //初始化
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            //创建请求对象
            //一次只能批量删20个
            String str = org.apache.commons.lang.StringUtils.join(videoIdList.toArray(), ",");
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(str);

            //获取响应
            DeleteVideoResponse response = client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new CustomException(20001, "视频删除失败");
        }
    }
}
