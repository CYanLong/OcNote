package com.ocnote.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CharacterEncodingInterceptor 
				implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Interceptor Request ====>" + request.getCharacterEncoding());
		request.setCharacterEncoding("utf-8");
		System.out.println("Interceptor Response before ======>" + response.getCharacterEncoding());
		response.setCharacterEncoding("utf-8");
		System.out.println("Interceptor Response after ======>" + response.getCharacterEncoding());
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
