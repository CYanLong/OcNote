package com.ocnote.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ocnote.entity.User;
import com.ocnote.mapper.UserMapper;
import com.ocnote.service.UserService;
import com.ocnote.util.CipherUtils;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	//登录
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(HttpServletRequest request, 
			HttpSession session, 
			@RequestParam("loginName") String loginName, @RequestParam("password") String password){
		
		Map<String,String> msg = new HashMap<>();
		
		
		if(userMapper.existUserName(loginName) && userMapper.existEmail(loginName)){
			msg.put("usernameMsg", "此用户名未注册");
			msg.put("passwordMsg", "");
			return msg;
		}
		
		password = CipherUtils.cipher(password);
		
		User user = userMapper.loginByEmail(loginName, password);
		
		if(user == null){
			user = userMapper.loginByUsername(loginName, password);
		}
		
		if(user == null){
			msg.put("usernameMsg", "");
			msg.put("passwordMsg", "用户名或密码错误");	
			return msg;
		}
		//登录成功
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
		
		
		Map<String,String> msg = new HashMap<>();
		
		//验证用户名
		if(userMapper.existUserName(user.getUsername())){
			msg.put("usernameMsg", "此用户已注册");
			msg.put("emailMsg", "");
			return msg;
		}
		
		if(userMapper.existEmail(user.getEmail())){
			msg.put("emailMsg", "邮箱已注册");
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
	public boolean existUserName(String username){
		return !userMapper.existUserName(username);
	}	
	
	@ResponseBody
	@RequestMapping(value="email", method=RequestMethod.POST)
	public boolean existEmail(String email){
		return !userMapper.existEmail(email);
	}
	
	@ResponseBody
	@RequestMapping(value="username", method=RequestMethod.GET)
	public String getUsername(HttpSession session){
		return ((User)session.getAttribute("user")).getUsername();
	}
	
}
