package com.lxw.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lxw.oss.service.OssService;
import com.lxw.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: lxw
 * @Date: 2022/3/5 21:37
 * @Description: 文件上传到阿里云oos
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;


        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //上传文件流
        try {
            InputStream inputStream = file.getInputStream();
            //文件名称
            String fileName = file.getOriginalFilename();

            //在文件名称里面添加一个随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");

            fileName = uuid + fileName;

            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //把日期拼接到文件名称上实现通过日期建立文件夹分类
            fileName = datePath + fileName;

            // 创建PutObject请求。
            /**
             * bucketName Bucket名称
             * fileName 上传到oss文件路径和文件名称 /aa/bb/1.jpg
             * inputStream 上传文件输入流
             */
            ossClient.putObject(bucketName, fileName, inputStream);

            //上传到阿里云oss路径返回
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;

            return url;
        } catch (Exception oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");

        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return null;

    }
}
