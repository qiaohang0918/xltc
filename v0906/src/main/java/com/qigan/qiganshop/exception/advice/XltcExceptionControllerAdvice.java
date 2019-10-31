/**
 * 
 */
package com.qigan.qiganshop.exception.advice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.exception.XltcTokenRuntimeException;
import com.qigan.qiganshop.util.result.XltcResult;

/**
 * @author wyy
 *
 */
@ControllerAdvice
public class XltcExceptionControllerAdvice {

	private final static Logger log = Logger.getLogger(XltcExceptionControllerAdvice.class);

//	private final static String MESSAGE = "message";

	private final static String DEFAULT_MESSAGE = "系统错误,请联系管理员";

	private final static String URL = "url";

	private final static String PARAMS = "params";

	@ExceptionHandler(XltcRuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public XltcResult handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		log.error("ServerRuntimeException : ", ex);
		Map<String, Object> result = new HashMap<>();
		result.put(PARAMS, request.getParameterMap());
		result.put(URL, request.getRequestURL());
		return XltcResult.build(HttpStatus.BAD_REQUEST.value(), ex.getMessage() == null ? DEFAULT_MESSAGE : ex.getMessage(), result);
	}
	
	@ExceptionHandler(XltcTokenRuntimeException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public XltcResult handleTokenException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		log.error("ServerRuntimeException : ", ex);
		Map<String, Object> result = new HashMap<>();
		result.put(PARAMS, request.getParameterMap());
		result.put(URL, request.getRequestURL());
		return XltcResult.build(HttpStatus.UNAUTHORIZED.value(), ex.getMessage() == null ? DEFAULT_MESSAGE : ex.getMessage(), result);
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public XltcResult handleNullException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		log.error("ServerRuntimeException : ", ex);
		Map<String, Object> result = new HashMap<>();
		result.put(PARAMS, request.getParameterMap());
		result.put(URL, request.getRequestURL());
		return XltcResult.build(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage() == null ? DEFAULT_MESSAGE : ex.getMessage(), result);
	}

}
