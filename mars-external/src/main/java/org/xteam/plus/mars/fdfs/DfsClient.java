package org.xteam.plus.mars.fdfs;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Date: 2017-11-28
 * Time: 14:28
 *  分布式文件客户端接口
 * @author yankun
 */
public interface DfsClient {
    /**
     * 上传图片文件
     * @param multipartFile
     * @return   ImageFileInfo
     * @throws IOException
     */
    public ImageFileInfo uploadImageFile(MultipartFile multipartFile) throws Exception;

    /**
     * 上传文件
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public FileInfo uploadFile(MultipartFile multipartFile) throws Exception;


    /**
     * 上传文件
     * @param file
     * @return
     * @throws IOException
     */
    public FileInfo upload(File file)throws Exception;


}
