package com.qigan.qiganshop.util.upload;

import com.qigan.qiganshop.util.exception.ExceptionUtil;
import com.qigan.qiganshop.util.result.ImageResponse;
import com.qigan.qiganshop.util.result.MusicResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadFiles {

    /**
     * 多文件上传
     *
     * @param files
     * @param type
     * @return
     */
    public List<String> uploadFiles(MultipartFile[] files, String type) {
        ArrayList<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            ImageResponse imageResponse = (ImageResponse) this.uploadFile(file, type);
            if (imageResponse != null) {
                list.add(imageResponse.getImageurl());
            }
        }
        return list;
    }

    /**
     * 文件属性获取
     *
     * @param file
     * @return
     */

    public Object uploadFile(MultipartFile file, String type) {
        // 获取文件的扩展名
        String filename = file.getOriginalFilename();
        String name = filename.substring(0, filename.lastIndexOf("."));
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        // musicId
        String md5Hex = null;
        try {
            md5Hex = DigestUtils.md5Hex(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取文件名以及扩展名,确定类型后到指定数据表中查找是否存在该文件的对应,如果存在,禁止上传
        String[] audioArr = {"au", "snd", "mid", "rmi", "mp3", "aif", "aifc", "aiff", "m3u", "ra", "ram", "wav"};
        String[] pictureArr = {"png", "bmp", "cod", "gif", "ief", "jpe", "jpeg", "jpg", "jfif", "svg", "tif", "tiff", "ras", "cmx", "ico", "pnm", "pbm", "pgm", "ppm", "rgb", "xbm", "xpm", "xwd"};
        String[] videoArr = {"png", "bmp", "cod", "gif", "ief", "jpe", "jpeg", "jpg", "jfif", "svg", "tif", "tiff", "ras", "cmx", "ico", "pnm", "pbm", "pgm", "ppm", "rgb", "xbm", "xpm", "xwd"};

        if ("ad".equals(type) ||
                "banner".equals(type) ||
                "goods".equals(type) || "app".equals(type)
                || "cate".equals(type) || "activity".equals(type) || "apk".equals(type)) {
            // 如果是音频文件（通过文件后缀进行判断）
            if (Arrays.asList(audioArr).contains(extName)) {
                // 上传成功
                try {
                    String response = uploadOneFile(file, extName, "music");
                    if ("ERROR".equals(response)) {
                        return new MusicResponse("", name, "", "上传失败,服务器错误!");
                    }
                    return new MusicResponse(md5Hex, name, response, "上传成功！");

                } catch (Exception e) {
                    // 上传失败
                    return new MusicResponse("", name, "", "上传失败,服务器错误!");
                }
            }
            // 如果是图片文件
            if (Arrays.asList(pictureArr).contains(extName)) {
                // 上传成功
                try {
                    String response = uploadOneFile(file, extName, "image/" + type);
                    if ("ERROR".equals(response)) {
                        return new ImageResponse("", name, "", "上传失败,服务器错误!");
                    }
                    return new ImageResponse(md5Hex, name, response, "上传成功！");
                } catch (Exception e) {
                    // 上传失败
                    return new ImageResponse("", name, "", "上传失败,服务器错误!");
                }
            }
            // 如果是apk文件
            if ("apk".equals(extName)) {
                // 上传成功
                try {
                    String response = uploadOneFile(file, extName, type + "/");
                    if ("ERROR".equals(response)) {
                        return new ImageResponse("", name, "", "上传失败,服务器错误!");
                    }
                    return new ImageResponse(md5Hex, name, response, "上传成功！");
                } catch (Exception e) {
                    // 上传失败
                    return new ImageResponse("", name, "", "上传失败,服务器错误!");
                }
            }
        }
        return null;
    }


    /**
     * 上传单个文件
     *
     * @param extName
     * @param fileType
     * @return
     */
    private String uploadOneFile(MultipartFile file, String extName, String fileType) {
        String os = System.getProperty("os.name");


        try {
            // 获取文件的MD5值
            String MD5 = DigestUtils.md5Hex(file.getInputStream());
            // 拼接文件保存路径（文件夹）
            String path = "";
            if (os.toLowerCase().startsWith("win")) {
                // 项目部署在Windows系统中
                path = "C:\\home\\FileResources\\" + fileType + "\\" + MD5.substring(0, 1) + "\\" + MD5.substring(1, 2) + "\\";
            } else {

                path = "/home/FileResources/" + fileType + "/" + MD5.substring(0, 1) + "/" + MD5.substring(1, 2) + "/";
            }
            // 返回拼接的url(文件的访问路径)
            String url = fileType + "/" + MD5.substring(0, 1) + "/" + MD5.substring(1, 2) + "/" + MD5 + "." + extName;
            //String url = null;
            // 保存文件
            File file1 = new File(path);
            // 创建文件夹
            file1.mkdirs();
            // 保存文件
            try {
                //path = "/Users/wanghao/Desktop/hahaha/";
                String Filepath = path + MD5 + "." + extName;
                boolean mkdirs = new File(path).mkdirs();
                file.transferTo(new File(Filepath));
                File file2 = new File(Filepath);
                // 上传到七牛云

                // url = qiNiuService.uploadFile(file2, fileType);

            } catch (Exception e) {
                e.printStackTrace();
                e.printStackTrace();
            }


            return url != null ? url : "Error";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }


}