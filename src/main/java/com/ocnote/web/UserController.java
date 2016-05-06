package com.ocnote.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ocnote.domain.User;
import com.ocnote.service.ServiceBeanFactory;
import com.ocnote.service.UserService;
import com.ocnote.util.CipherUtils;



@Controller
public class UserController {
	
	private UserService userService = ServiceBeanFactory.getUserService();
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> login(HttpServletRequest request, 
			HttpServletResponse response,
			String loginName, String password){
		
		Map<String,String> msg = new HashMap<>();
		//verifyUserName. �����ڣ����� false. �����ڣ�����true. 
		//ֻ��������֤�������ڣ�ִ�С�
		if(userService.verifyUserName(loginName) && userService.verifyEmail(loginName)){
			msg.put("usernameMsg", "���û���δע��");
			msg.put("passwordMsg", "");
			return msg;
		}
		
		password = CipherUtils.cipher(password);
		
		User user = userService.loginByEmail(loginName, password);
		
		if(user == null){
			user = userService.loginByUsername(loginName, password);
		}
		
		if(user == null){
			msg.put("usernameMsg", "");
			msg.put("passwordMsg", "�û������������");	
			return msg;
		}
		//��¼�ɹ�
		request.getSession().setAttribute("user", user);
		
		return msg;
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	public void logout(HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("user");
		
		response.setHeader("Location", "log-in.html");
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public Map<String,String> register(HttpServletRequest request,
			HttpServletResponse response,
			User user){
		
		System.out.println(user.getEmail());
		
		Map<String,String> msg = new HashMap<>();
		
		//��֤�û���
		if(!userService.verifyUserName(user.getUsername())){
			msg.put("usernameMsg", "���û���ע��");
			msg.put("emailMsg", "");
			return msg;
		}
		
		if(!userService.verifyEmail(user.getEmail())){
			msg.put("emailMsg", "������ע��");
			msg.put("usernameMsg", "");
			
			return msg;
		}
		
		user.setPassword(CipherUtils.cipher(user.getPassword()));
		
		userService.register(user);
		
		request.getSession().setAttribute("user", user);
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value="username", method=RequestMethod.POST)
	public boolean verifyUserName(String username){
		return userService.verifyUserName(username);
	}	
	
	@ResponseBody
	@RequestMapping(value="email", method=RequestMethod.POST)
	public boolean verifyEmail(String email){
		return userService.verifyEmail(email);
	}
	
	
	@RequestMapping(value="username",method = RequestMethod.GET)
	public @ResponseBody String getUsername(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		return user.getUsername();
	}
}
