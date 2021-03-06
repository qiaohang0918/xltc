package com.qigan.qiganshop.controller.frontend;

import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Message;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.service.MessageService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.exception.ExceptionUtil;
import com.qigan.qiganshop.util.result.XltcResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SuppressWarnings("all")
@RestController
@RequestMapping("/app/message")
@Api(tags = {"APP消息管理接口"})
public class MessageController {

    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;
    @Autowired
    private JsonResult jr;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private AppUserService userService;
    @Autowired
    private MessageService messageService;

    @RequestMapping("/unread.do")
    @ApiOperation(
            value = "是否有未读消息",
            notes = "token 用户标识",
            httpMethod = "POST")
    public JsonResult unread(String token,HttpServletRequest request, HttpServletResponse response){
        try{
            String tokenStr = ServletRequestUtils.getStringParameter(request, "token", "");
            AppUser user = userService.getAppUserByToken(tokenStr);
            if(user != null){
                List<Message> list = messageService.unread(user.getUserId());
                if(list != null){
                    Map<String,Object> map = new HashMap<>();
                    map.put("size",list.size());
                    return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功",map);
                }
            }
            return jr.jsonResultData(ResultCode.FAIL.res_code());
        }catch(Exception e){
            return jr.jsonResultData(ResultCode.ERROR.res_code());
        }
    }


    @RequestMapping("/getunread.do")
    @ApiOperation(
            value = "是否有未读消息并且返回未读信息接口",
            notes = "token 用户标识",
            httpMethod = "POST")
    public JsonResult getunread(String token,HttpServletRequest request, HttpServletResponse response){
        try{
            String tokenStr = ServletRequestUtils.getStringParameter(request, "token", "");
            AppUser user = userService.getAppUserByToken(tokenStr);
            if(user != null){
                Map<String,Object> map = messageService.getunread(user.getUserId());
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功",map);
            }
            return jr.jsonResultData(ResultCode.FAIL.res_code());
        }catch(Exception e){
            return jr.jsonResultData(ResultCode.ERROR.res_code());
        }
    }

    @RequestMapping("/markRead.do")
    @ApiOperation(
            value = "标记为已读",
            notes = "token 用户标识",
            httpMethod = "POST")
    public JsonResult markRead(String token,String messageid,HttpServletRequest request, HttpServletResponse response){
        try{
            int suc = messageService.markRead(messageid);
            if(suc == 1){
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功");
            }
            return jr.jsonResultData(ResultCode.FAIL.res_code());
        }catch(Exception e){
            return jr.jsonResultData(ResultCode.ERROR.res_code());
        }
    }

    @RequestMapping("/findAll.do")
    public XltcResult findAll(String token){
    	return XltcResult.ok(messageService.findAllByUser(token));
    }
    
    @RequestMapping("/findList.do")
    public XltcResult findList(String token, CommonPage page){
    	return XltcResult.ok(messageService.findList(token, page));
    }

    @RequestMapping("/findByUserAndType.do")
    public XltcResult findAll(String token, String type, CommonPage page){
    	return XltcResult.ok(messageService.findMessageByUserAndType(token, type, page));
    }
    
    @RequestMapping("/showMessage.do")
    public XltcResult showMessage(String token){
    	return XltcResult.ok(messageService.isShowMessage(token));
    }
    
    @RequestMapping("/showAlertMessage.do")
    public XltcResult showAlertMessage(String token){
    	return XltcResult.ok(messageService.showAlertMessage(token));
    }
   
    @RequestMapping("/delete.do")
    public XltcResult delete(String... ids){
    	messageService.delete(ids);
    	return XltcResult.ok();
    }
    
}
