/**
 * 
 */
package com.qigan.qiganshop.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.pojo.CloseOrderModel;
import com.qigan.qiganshop.service.XltcPayCloseService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.pay.PayUtil;
import com.qigan.qiganshop.util.pay.wxpay.Httpsrequest;
import com.qigan.qiganshop.util.pay.wxpay.Sign;
import com.qigan.qiganshop.util.pay.wxpay.Xstreamutil;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class XltcPayCloseServiceImpl implements XltcPayCloseService {

	@Value("${Key}")
	private String Key;

	@Value("${appid}")
	private String appid;

	@Value("${mch_id}")
	private String mch_id;

	@Autowired
	JedisUtil jedis;

	@Value("${serverUrl}")
	public String serverUrl;
	@Value("${appId}")
	public String appId;
	@Value("${app_private_key}")
	public String app_private_key;
	@Value("${format}")
	public String format;
	@Value("${charset}")
	public String charset;
	@Value("${alipay_public_key}")
	public String alipay_public_key;
	@Value("${sign_type}")
	public String sign_type;


	private enum PayType {
		wxpay, alipay
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qigan.qiganshop.service.XltcPayCloseService#closePayByOrder(java.lang
	 * .String)
	 */
	@Override
	public boolean closePayByOrder(String orderId) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(orderId))
			return false;
		String value = jedis.getFromHash(RedisConstant.ORDER_PAY, orderId);
		if (StringUtils.isBlank(value))
			return false;
		String type = "";
		String outTradeNo = "";
		try {
			String[] values = value.split(":");
			type = values[0];
			outTradeNo = values[2];
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		boolean result = false;
		PayType payType = PayType.valueOf(type);
		switch (payType) {
		case wxpay:
			result = this.cloaseByWX(outTradeNo);
			break;
		case alipay:
			return true;
		default:
			return false;
		}
		return result;
	}

	private boolean cloaseByWX(String outTradeNo) {
		String nonceStr = PayUtil.getRandomString(20);
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str",  nonceStr);
		parameters.put("out_trade_no", outTradeNo);
		String sign = Sign.createSign(parameters, Key);
		
		CloseOrderModel pay = new CloseOrderModel();
		pay.setAppid(appid);
		pay.setMch_id(mch_id);
		pay.setNonce_str(nonceStr);
		pay.setOut_trade_no(outTradeNo);
		pay.setSign(sign);
		Xstreamutil.xstream.alias("xml", CloseOrderModel.class);
		String xml = Xstreamutil.xstream.toXML(pay).replaceAll("__", "_");
		String requestUrl = "https://api.mch.weixin.qq.com/pay/closeorder";
		String closeXMLResult = "";
		try {
			closeXMLResult = Httpsrequest.HttpsRequest(requestUrl, "POST", xml);
			Map<String, String> map = PayUtil.xmlToMap(closeXMLResult);
			if(map.containsKey("return_code")){
				if("SUCCESS".equalsIgnoreCase(map.get("return_code"))){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}



//	private boolean cloaseByAli(String outTradeNo) {
//		try {
//			AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId,
//					app_private_key, format, charset, alipay_public_key, sign_type);
//			HashMap<String, String> parmMap = new HashMap<>();
//
//			AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
//			request.setBizContent("{" +
////                    "\"trade_no\":\""+split[2]+"\"," +
//					"\"out_trade_no\":\""+outTradeNo+"\"," +
//					"\"operator_id\":\"XLTC\"" +
//					"  }");
//			AlipayTradeCloseResponse response = alipayClient.execute(request);
//			System.out.println(response.getSubCode());
//			if(response.isSuccess()){
//				System.out.println("调用成功");
//				//检查状态码
//				String subCode = response.getSubCode();
//				if("ACQ.TRADE_HAS_SUCCESS".equals(subCode)){
//					//取消订单成功，--->
//					return true;
//				}else{
//					System.out.println(response.getSubMsg());
//					return false;
//				}
//			} else {
//				System.out.println("调用失败");
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}


}
