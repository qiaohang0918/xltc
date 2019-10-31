/**
 * 
 */
package com.qigan.qiganshop.util.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qigan.qiganshop.annocation.XltcLogRecord;
import com.qigan.qiganshop.pojo.Manager;
import com.qigan.qiganshop.service.XltcOptInfoService;

/**
 * @author wyy
 *
 */
@Service
public class LogIntercept implements HandlerInterceptor {

	public static final String MANAGER_INFO = "manager_info";

	@Autowired
	private XltcOptInfoService optInfoService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerMethod handleMethod = (HandlerMethod) handler;
		Method method = handleMethod.getMethod();
		XltcLogRecord xltcAnno = method.getAnnotation(XltcLogRecord.class);
		
		if (xltcAnno != null) {
			String path = request.getRequestURI();
			HttpSession session = request.getSession();
			Manager user = (Manager) session.getAttribute(MANAGER_INFO);
			if (user != null) {
				optInfoService.recordOptInfo(user.getNo(), user.getName(), path, xltcAnno.apiName());
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
