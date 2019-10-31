package com.qigan.qiganshop.util.pay.wxpay;

public class UtilXml {

	
	public static void getXml(){
		Pay  pay = new Pay();
		pay.setAppid("wxd930ea5d5a258f4f");
		pay.setBody("test");
		pay.setMch_id("10000100");
		pay.setNonce_str("ibuaiVcKdpRxkhJA");
		//XStream xstream = new XStream();
		Xstreamutil.xstream.alias("xml", Pay.class);
		String xml = Xstreamutil.xstream.toXML(pay);
		System.out.println(xml);
	}
	
	public static void main(String []args){
		getXml();
	}
	
}
