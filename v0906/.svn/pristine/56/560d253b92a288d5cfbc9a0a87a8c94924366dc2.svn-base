/*
package com.qigan.qiganshop.util.upload;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


*/
/**
 * 该 service 用于操作七牛云文件
 *//*

@Service
public class QiNiuService {

    @Value("${accessKey}")
    private String accessKey;
    @Value("${secretKey}")
    private String secretKey;
    @Value("${musicbucket}")
    private String musicbucket;
    @Value("${videobucket}")
    private String videobucket;
    @Value("${picturebucket}")
    private String picturebucket;
    @Value("${musicFileURL}")
    private String musicFileURL;
    @Value("${videoFileURL}")
    private String videoFileURL;
    @Value("${pictureFileURL}")
    private String pictureFileURL;

    */
/**
     * 文件上传,根据 flag 进行判断是音乐文件还是视频文件,如果不符合,则直接返回 null
     *//*

    public String uploadFile(File file, String flag) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String fileName = null;
        String bucket = checkFlag(flag);
        try {
            String MD5 = DigestUtils.md5Hex(new FileInputStream(file));
            Auth auth = Auth.create(accessKey, secretKey);
            if (bucket == null) {
                return null;
            }
            String upToken = auth.uploadToken(bucket);
            String extName = file.getName().substring(file.getName().lastIndexOf("."));
            fileName = MD5 + extName;
            try {
                Response response = uploadManager.put(file, fileName, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                    ex2.printStackTrace();
                }
            }
            if ("video".equals(bucket)) {
                videoHLS(fileName);
                fileName = MD5 + ".m3u8";
            }
        } catch (Exception ex) {
            //ignore
            ex.printStackTrace();
        }

        // 判断文件类型,返回不同的文件地址
        if (fileName != null) {
            // 判断是否是视频文件,如果为视频文件,需要进行切片

            return fileName;
        }
        return null;


    }

    */
/**
     * 判断是操作音频文件还是视频文件还是图片文件
     *//*

    private String checkFlag(String flag) {
        String bucket = null;
        if ("music".equals(flag)) {

            bucket = musicbucket;
        } else if ("video".equals(flag)) {
            bucket = videobucket;

        } else if (flag.startsWith("image")) {
            bucket = picturebucket;
        } else {
            return null;
        }
        return bucket;
    }

    */
/**
     * 文件删除
     * 通过文件的 md5 值进行删除文件,为方便批量删除,设置接收参数为集合
     *//*

    public List<String> deleteFiles(List<String> fileIds, String flag) {
        ArrayList<String> result = new ArrayList<>();

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释
        String bucket = checkFlag(flag);
        if (bucket == null) {
            return null;
        }
        for (String key : fileIds) {
            Auth auth = Auth.create(accessKey, secretKey);
            BucketManager bucketManager = new BucketManager(auth, cfg);
            try {
                bucketManager.delete(bucket, key);
                result.add(key);
            } catch (QiniuException ex) {
                //如果遇到异常，说明删除失败
                ex.printStackTrace();
            }
        }
        return result;
    }

    */
/**
     * 视频切片
     * 将 MP4 视频文件切割成多个.ts 文件和一个 .m3u8 文件
     *//*


    public void videoHLS(String key) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        // 创建连接
        Auth auth = Auth.create(accessKey, secretKey);
        String save_as_filename = key.substring(0, key.indexOf(".")) + ".m3u8";  // 分片后的文件名


        String notifyURL = "";
        boolean force = true;

        String m3u8SaveEntry = String.format("%s:%s", videobucket, save_as_filename);
        String fopM3u8 = String.format("avthumb/m3u8/segtime/10/vcodec/libx265/s|saveas/%s",
                UrlSafeBase64.encodeToString(String.format(m3u8SaveEntry)));

        String mp4SaveEntry = String.format("%s:%s", videobucket, key);
        String fopMp4 = String.format("avthumb/mp4/vcodec/libx265/s|saveas/%s",
                UrlSafeBase64.encodeToString(mp4SaveEntry));

        //join fop together
        String fops = StringUtils.join(new String[]{fopM3u8, fopMp4}, ";");

        try {

            OperationManager operationManager = new OperationManager(auth, cfg);
            String id = operationManager.pfop(videobucket, key, fops, "mp4tom3u8", notifyURL, force);
            //assertNotNull(id);
           // assertNotEquals("", id);
            String purl = "http://api.qiniu.com/status/get/prefop?id=" + id;
            System.err.println(purl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    */
/**
     * 查看切片结果
     *//*


    public void checkHLSResult(String purl) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            Thread.sleep(5000);
            HttpGet get = new HttpGet(purl);
            CloseableHttpResponse execute = httpClient.execute(get);
            if (execute != null && execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                String result = entityToString(entity);
                // 状态码0成功，1等待处理，2正在处理，3处理失败，4通知提交失败。
                if (result.indexOf("\"code\":0") != -1) {
                    // 处理成功 修改视频切片状态 其他状态不做修改

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    */
/**
     * 解析七牛云切片结果
     *
     * @param entity
     * @return
     * @throws IOException
     *//*

    private static String entityToString(HttpEntity entity) {
        String result = null;
        try {
            if (entity != null) {
                long lenth = entity.getContentLength();
                if (lenth != -1 && lenth < 2048) {
                    result = EntityUtils.toString(entity, "UTF-8");
                } else {
                    InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                    CharArrayBuffer buffer = new CharArrayBuffer(2048);
                    char[] tmp = new char[1024];
                    int l;
                    while ((l = reader1.read(tmp)) != -1) {
                        buffer.append(tmp, 0, l);
                    }
                    result = buffer.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
*/
