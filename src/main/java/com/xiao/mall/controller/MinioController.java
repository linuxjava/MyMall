package com.xiao.mall.controller;

import com.xiao.mall.common.api.CommonResult;
import com.xiao.mall.dto.MinioUploadDto;
import io.minio.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;


@Api(tags = "MinIO对象存储管理", description = "MinIO对象存储管理")
@Controller
@RequestMapping("/minio")
public class MinioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

//    @ApiOperation("文件上传")
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult upload(@RequestParam("file") MultipartFile file) {
//        try {
//            //创建一个MinIO的Java客户端
//            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
//            boolean isExist = minioClient.bucketExists(BUCKET_NAME);
//            if (isExist) {
//                LOGGER.info("存储桶已经存在！");
//            } else {
//                //创建存储桶并设置只读权限
//                minioClient.makeBucket(BUCKET_NAME);
//                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
//            }
//            String filename = file.getOriginalFilename();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            // 设置存储对象名称
//            String objectName = sdf.format(new Date()) + "/" + filename;
//            // 使用putObject上传一个文件到存储桶中
//            minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
//            LOGGER.info("文件上传成功!");
//            MinioUploadDto minioUploadDto = new MinioUploadDto();
//            minioUploadDto.setName(objectName);
//            minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
//
//            return CommonResult.success(minioUploadDto);
//        } catch (Exception e) {
//            LOGGER.info("上传发生错误: {}！", e.getMessage());
//        }
//        return CommonResult.failed();
//    }
//
//    @ApiOperation("文件删除")
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult delete(@RequestParam("objectName") String objectName) {
//        try {
//            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
//            minioClient.removeObject(BUCKET_NAME, objectName);
//            return CommonResult.success(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CommonResult.failed();
//    }


    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(@RequestParam("file") MultipartFile file) {
        try {
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(ENDPOINT)
                            .credentials(ACCESS_KEY, SECRET_KEY)
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (!found) {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            } else {
                System.out.println("Bucket 'mall' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.

            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String objectName = sdf.format(new Date()) + "/" + filename;
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build());

            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(objectName);
            minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
            return CommonResult.success(minioUploadDto);
        } catch (Exception e) {
            LOGGER.info("上传发生错误: {}！", e.getMessage());
        }
        return CommonResult.failed();
    }

    @ApiOperation("文件删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("objectName") String objectName) {
        try {
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(ENDPOINT)
                            .credentials(ACCESS_KEY, SECRET_KEY)
                            .build();

            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(BUCKET_NAME).object(objectName).build();

            minioClient.removeObject(removeObjectArgs);
            return CommonResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }
}
