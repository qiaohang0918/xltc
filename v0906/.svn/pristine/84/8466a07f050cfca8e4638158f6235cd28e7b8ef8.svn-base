package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import com.qigan.qiganshop.util.upload.PictureOSSUtils;
import com.qigan.qiganshop.util.upload.UploadFiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传接口
 *
 * @author wanghao
 * @time 2019-04-22 14:47
 */
@SuppressWarnings("all")

@RestController
@RequestMapping("/upload")
@Api(value = "图片上传 controller", tags = {"WEB 图片上传"})
public class FileUploadController {
    @Autowired
    private JsonResult jr;
    @Autowired
    private UploadFiles uploadFiles;
    @Autowired
    private PublicControl pc;

    /**
     * SPU 商品仅提供修改,查询功能,无新增,删除
     */
    @RequestMapping(value = "uploads.do", consumes = "multipart/form-data")
    @ApiOperation(
            value = "多文件上传",
            notes = "用于商品信息的文件上传,文件集合和文件所属类型,都放在 body 中进行发送,  \n" +
                    "type 类型暂定 goods(商品图片),banner(轮播图),homepage(首页分类图片) 等接口需要使用,后期存在活动",
            httpMethod = "POST")
    public JsonResult upload(@RequestParam MultipartFile[] files, @RequestParam String type) {
        if (files.length != 0) {
        	List<String> list = new ArrayList<>();
        	for (MultipartFile file : files) {
        		Map<String, String> upResult = PictureOSSUtils.uploadPictureToOSS(file, type);
        		 if (upResult != null && upResult.get(PictureOSSUtils.RESULT).equals(PictureOSSUtils.SUCCESS)) {
        			 list.add(upResult.get(PictureOSSUtils.IMGURL));
        		 }
			}
//            List<String> list = uploadFiles.uploadFiles(files, type);
            List<String> result = pc.addPicHeadForList(list);
            if (files.length == list.size()) {
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), result);
            } else if (list.size() > 0 && files.length != list.size()) {

                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), result);
            }
            return jr.jsonResultData(ResultCode.FAIL.res_code(), ResultCode.FAIL.message(), "文件上传失败!");
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), ResultCode.FAIL.message(), "请先选择文件!");
    }
}
