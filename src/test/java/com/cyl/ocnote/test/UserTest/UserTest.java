package com.cyl.ocnote.test.UserTest;

import org.junit.Test;

import com.ocnote.service.ServiceBeanFactory;
import com.ocnote.service.UserService;

public class UserTest {

	UserService service = ServiceBeanFactory.getUserService();
	
	@Test
	public void testVarifyUsername(){
		String username = "Jhon";
		boolean result = service.verifyUserName(username);
		System.out.println(result);
	}
	@Test
	public void testVerifyEmail(){
		String email = "15636039213@163.com";
		boolean result = service.verifyEmail(email);
		System.out.println(result);
	}
	
	@Test
	public void testLoginName(){
		String loginName = "15636039213@163.com";
		if(service.verifyUserName(loginName) && service.verifyEmail(loginName)){
			System.out.println("µÇÂ¼ÃûÎ´×¢²á");
		}else{
			System.out.println("µÇÂ¼Ãû´æÔÚ");
		}
		
	}
	
}
