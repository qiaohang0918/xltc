/**
 * 
 */
package com.qigan.qiganshop.util.upload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.qigan.qiganshop.exception.XltcRuntimeException;

/**
 * @author wyy
 *
 */
public class PictureOSSUtils {
	// 阿里云文件服务器地址
	private static String OSS_ADRESS = "http://xiletongcheng.oss-cn-qingdao.aliyuncs.com";
	// 阿里云endpoint
	private static String END_POINT = "oss-cn-qingdao.aliyuncs.com";
	// 阿里云accesskey
	private static String ACCESS_KEY_ID = "LTAIwqA7Jnesfz1q";
	// 阿里云accesskeysecret
	private static String ACCESS_KEY_SECRET = "3shLpG7h595hprDhBwUhIXlspV3PbK";
	// 阿里云Bucket名称
	private static String BUCKET_NAME = "xiletongcheng";
	
	public static final String SUCCESS = "SUCCESS";
	
	public static final String FAIL = "fail";
	
	public static final String RESULT = "result";
	
	public static final String IMGURL = "imgurl";
	
	public static final String IMAGE = "image";

	/**
	 * 
	 * @param uploadFile
	 *            文件流
	 * @param folder_name
	 *            文件夹名称
	 * @return
	 */
	public static Map<String, String> uploadPictureToOSS(MultipartFile uploadFile, String folder_name) {
		try {
			// 接收上传的文件
			// 取扩展名
			 String originalFilename = uploadFile.getOriginalFilename();
			 String extName =
			 originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//			String extName = uploadFile.getContentType().split("/")[1];
//			String temp = uploadFile.getOriginalFilename();
//			String suffix = temp.substring(temp.lastIndexOf('.'));
			String picName = IMAGE + "/" + folder_name + "/" + IDUtils.genImageName() + "." + extName;

			OSS ossClient = new OSSClientBuilder().build(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

			byte[] content = uploadFile.getBytes();
			ossClient.putObject(BUCKET_NAME, picName, new ByteArrayInputStream(content));

			String url = OSS_ADRESS + "/" + picName;
			Map<String, String> result = new HashMap<String, String>();
			result.put(RESULT, SUCCESS);
			result.put(IMGURL, url);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> result = new HashMap<String, String>();
			result.put(RESULT, FAIL);
			result.put(IMGURL, "图片上传失败");
			return result;
		}

	}
	
	public static Object generatePresignedUrl(String objectName, long expiration){
		if(StringUtils.isBlank(objectName))
			throw XltcRuntimeException.throwable("图片地址为空");
		
		List<String> picUrls = null;
		if(objectName.indexOf(",") != -1)
			picUrls = new ArrayList<>(Arrays.asList(objectName.split(",")));
		
		List<String> result = new ArrayList<>();
		OSS ossClient = new OSSClientBuilder().build(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		if(picUrls != null){
			for (String urls : picUrls) {
				result.add(generatePresignedUrl(ossClient, urls, expiration));
			}
			return result;
		}
		return generatePresignedUrl(ossClient, objectName, expiration);
	}
	
	private static String generatePresignedUrl(OSS ossClient, String objectName, long expiration){
		URL url = ossClient.generatePresignedUrl(BUCKET_NAME, objectName, new Date(expiration));
		if(url == null)
			throw XltcRuntimeException.throwable("oss 图片加密失败");
		return url.toString();
	}
	
	public static void main(String[] args) {
//		System.err.println(generatePresignedUrl("image/goods/b/1/b1842c3bc607c2b616c92940427c7ad4.jpg",  new Date().getTime() + 3600 * 1000));
		
		String suffix = "123.apk".substring("123.apk".lastIndexOf('.'));
		String picName = IMAGE + "/" + "test" + "/" + IDUtils.genImageName() + suffix;
		System.out.println(picName);
	}
}
