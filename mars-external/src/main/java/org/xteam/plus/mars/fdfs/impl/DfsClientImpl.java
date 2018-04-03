package org.xteam.plus.mars.fdfs.impl;


import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.fdfs.FileInfo;
import org.xteam.plus.mars.fdfs.ImageFileInfo;
import org.xteam.plus.mars.fdfs.DfsClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class DfsClientImpl extends Logging implements DfsClient {

    @Override
    public ImageFileInfo uploadImageFile(MultipartFile multipartFile) throws Exception {
        ImageFileInfo imageFileInfo = null;
        InputStream inputStream = null;
        try{
            ClientGlobal.initByProperties("fastdfs-client.properties");
            inputStream = multipartFile.getInputStream();
            BufferedImage image = ImageIO.read(inputStream);
            //如果image=null 表示上传的不是图片格式
            if (image != null) {
                imageFileInfo = new ImageFileInfo();
                //获取图片宽度，单位px
                imageFileInfo.setWidth(image.getWidth());
                //获取图片高度，单位px
                imageFileInfo.setHeight(image.getHeight());
                //图片大小
                imageFileInfo.setSize(multipartFile.getSize());
                // 建立连接
                TrackerClient tracker = new TrackerClient();
                TrackerServer trackerServer = tracker.getConnection();
                StorageServer storageServer = null;
                StorageClient1 client = new StorageClient1(trackerServer, storageServer);
                String fileName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
                imageFileInfo.setFileName(multipartFile.getOriginalFilename());
                //上传上到图片服务器
                String fileAddress = client.upload_file1(multipartFile.getBytes(), fileName, null);
                //设置服务器存放地址
                imageFileInfo.setStoreAddress(fileAddress);
               logInfo("上传文件到服务器:" + imageFileInfo);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return imageFileInfo;
    }

    @Override
    public FileInfo uploadFile(MultipartFile multipartFile) throws Exception  {
        FileInfo fileInfo = null;
            ClientGlobal.initByProperties("fastdfs-client.properties");
            if (multipartFile.getInputStream() != null) {
                fileInfo = new FileInfo();
                //大小
                fileInfo.setSize(multipartFile.getSize());
                // 建立连接
                TrackerClient tracker = new TrackerClient();
                TrackerServer trackerServer = tracker.getConnection();
                StorageServer storageServer = null;
                StorageClient1 client = new StorageClient1(trackerServer, storageServer);
                String fileName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
                fileInfo.setFileName(multipartFile.getOriginalFilename());
                //上传上到图片服务器
                String fileAddress = client.upload_file1(multipartFile.getBytes(), fileName, null);
                //设置服务器存放地址
                fileInfo.setStoreAddress(fileAddress);
                logInfo("上传文件到服务器:" + fileInfo);
            }

        return fileInfo;
    }

    @Override
    public FileInfo upload(File file) throws Exception {
        FileInfo fileInfo = null;
            ClientGlobal.initByProperties("fastdfs-client.properties");
            if (file != null) {
                fileInfo = new FileInfo();
                //大小
                fileInfo.setSize(file.getTotalSpace());
                // 建立连接
                TrackerClient tracker = new TrackerClient();
                TrackerServer trackerServer = tracker.getConnection();
                StorageServer storageServer = null;
                StorageClient1 client = new StorageClient1(trackerServer, storageServer);
                //上传上到图片服务器
                String fileAddress = client.upload_file1(getBytes(file), null, null);
                //设置服务器存放地址
                fileInfo.setStoreAddress(fileAddress);
                logInfo("上传文件到服务器:" + fileInfo);
            }

        return fileInfo;
    }

    /**
     * 获得指定文件的byte数组
     */
    private byte[] getBytes(File file){
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (Exception e) {
        logError("获取byte[]异常",e);
        }
        return buffer;
    }
}