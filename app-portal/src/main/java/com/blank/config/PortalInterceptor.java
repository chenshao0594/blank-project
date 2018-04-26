package com.blank.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PortalInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView mv)
			throws Exception {
		// mv.addObject("currentMenu", attributeValue);
		System.out.println("拦截器MyInterceptor------->2、请求之后调用，在视图渲染之前，也就是Controller方法调用之后");

	}

}
