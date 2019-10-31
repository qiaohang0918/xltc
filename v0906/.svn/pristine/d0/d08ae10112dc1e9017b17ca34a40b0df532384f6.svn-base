package com.qigan.qiganshop.util.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Component;

/**
 * @author wanghao
 * @date 2018/9/25  17:26
 * .............................................
 * 佛祖镇楼                  BUG辟易
 * 佛曰:
 * 写字楼里写字间，写字间里程序员；
 * 程序人员写程序，又拿程序换酒钱。
 * 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。
 * 但愿老死电脑间，不愿鞠躬老板前；
 * 奔驰宝马贵者趣，公交自行程序员。
 * 别人笑我忒疯癫，我笑自己命太贱；
 * 不见满街漂亮妹，哪个归得程序员？
 */
@Component
public class SmsUtil {
    /**
     * 读取配置文件的信息,将账号和密码进行加载
     */

    final String accessKeyId = "LTAIKhP3UpHFif20";
    /**
     * 你的accessKeySecret，参考本文档步骤2
     */
    final String accessKeySecret = "OfKYOrIn9Dix9VMlwDTN4dgW8eTN6w";

    /**
     * 模版类型:      验证码
     * 模版名称:      用户注册
     * 模版CODE:     SMS_163555215
     * 模版内容:      尊敬的用户，您的注册会员动态密码为：${code}，请勿泄漏于他人！
     * 申请说明:      用于新用户注册时的短信验证
     *
     * @param phoneNum      手机号
     * @param checkCode     验证码(随机生成)
     * @param signName      (短信签名)
     * @param templateParam 短信模板
     * @return 成功返回success 失败返回 fail
     * @throws Exception
     */

    public String sendMessage(String phoneNum, String checkCode, String signName, String templateParam) throws Exception {
        /** 替换成你的AK
         你的accessKeyId,参考本文档步骤2
         */

        // 设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化ascClient需要的几个参数
        // 短信API产品名称（短信产品名固定，无需修改）
        final String product = "Dysmsapi";
        // 短信API产品域名（接口地址固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";

        // 初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
        request.setPhoneNumbers(phoneNum);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateParam);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"" + checkCode + "\"}");
        // 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        /* 请求失败这里会抛ClientException异常 */
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {
            // 请求成功
            return "success";
        }

        return "fail";
    }

    /**
     * 模版类型:      短信通知
     * 模版名称:      配送通知
     * 模版CODE:      SMS_169113401
     * 模版内容:      喜乐到家提醒您，您的订单${deliveryerId}已在配送途中，请耐心等待，如有问题可拨打快递员${deliveryerName}电话${phone},感谢您的支持，祝您生活愉快！
     * 变量属性:      deliveryerId-其他号码；deliveryerName-其他；phone-电话号码；
     * 申请说明:      当配送人员开始配送时，向用户发送短信通知其订单有谁开始配送
     *
     * @param phoneNum
     * @param deliveryerId
     * @param deliveryerName
     * @param phone
     * @param signName
     * @param templateParam
     * @return
     */
    public String sendDeliveryMessage(
            String phoneNum,
            String deliveryerId,
            String deliveryerName,
            String phone,
            String signName,
            String templateParam) {
        try {
            /** 替换成你的AK
             你的accessKeyId,参考本文档步骤2
             */

            // 设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            // 初始化ascClient需要的几个参数
            // 短信API产品名称（短信产品名固定，无需修改）
            final String product = "Dysmsapi";
            // 短信API产品域名（接口地址固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";

            // 初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            // 组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            // 使用post提交
            request.setMethod(MethodType.POST);
            // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            request.setPhoneNumbers(phoneNum);
            // 必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            // 必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateParam);
            // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam("{\"deliveryerId\":\"" + deliveryerId + "\",\"deliveryerName\":\"" + deliveryerName + "\",\"phone\":" + phone + "}");

            // 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            // request.setSmsUpExtendCode("90997");
            // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");
            /* 请求失败这里会抛ClientException异常 */
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {
                // 请求成功
                return "success";
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return "fail";
    }
}
