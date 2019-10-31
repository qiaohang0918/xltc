package com.qigan.qiganshop.controller.utils;

import com.qigan.qiganshop.util.result.Result;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * 向上抽取controller公共部分
 *
 * @author wanghao
 */

@ApiIgnore
@Component
public class PublicControl {
    @Autowired
    private JsonResult jr;
    @Value("${local_pic_url}")
    private String picHead;

    public JsonResult addUtils(Integer add) {
        if (add > 0) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "添加成功");
        } else {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "添加失败");
        }
    }

    public JsonResult deleteUtils(String[] strings, Integer delete) {
        if (strings.length == delete) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "删除成功!");
        } else if (delete == 0) {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "删除失败!");
        } else if (strings.length < delete) {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "本次删除成功" + delete + "条数据,删除数大于传入数量,可能存在重复数据,联系管理员修复问题!");
        } else {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "本次删除仅成功" + delete + "条数据,可能提交的数据中存在已删除数据,请刷新页面重试!");
        }
    }

    public JsonResult updateUtils(Integer update) {
        if (update > 0) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "修改成功");
        } else {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "修改失败!");
        }
    }

    public static Boolean checkNotNull(Object o) {
        if (o != null && !"".equals(o)) {
            return true;
        }
        return false;
    }

    public List<String> addPicHeadForList(List<String> list) {
        ArrayList<String> result = new ArrayList<>();

        for (String s : list) {
            result.add(addPicHead(s));
        }
        return result;
    }

//    public String addPicHead(String url) {
//        return picHead + url;
//    }
    public String addPicHead(String url) {
    	return url;
    }

}
