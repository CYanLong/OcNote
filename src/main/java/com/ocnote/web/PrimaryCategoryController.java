package com.ocnote.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ocnote.domain.PrimaryCategory;
import com.ocnote.domain.User;
import com.ocnote.service.PrimaryCategoryService;
import com.ocnote.service.ServiceBeanFactory;
import com.ocnote.service.ServiceBeanFactory;

@Controller
@RequestMapping(value="primaryCategorys")
public class PrimaryCategoryController {
	
	private PrimaryCategoryService primaryCategoryService = ServiceBeanFactory.getPrimaryCategoryService();	
		
	//得到登录用户的PrimaryCategory.
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<PrimaryCategory> getPrimaryCategorys(HttpServletRequest request){	
		User u = getUserBySession(request);
		if(u == null){
			
		}
		return primaryCategoryService.getPrimaryCategoryByUser(u.getId());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addPrimaryCategory(HttpServletRequest request,String pName){
		
		System.out.println("controller ==========>"+request.getCharacterEncoding());
			
		User u = (User)request.getSession().getAttribute("user");
		System.out.println(pName);
		PrimaryCategory p = new PrimaryCategory();
		if(u == null){
			System.out.println("user not find");
		}
		p.setUser(u);
		p.setCategoryName(pName);
		p.setCreateTime(new Date());
		primaryCategoryService.saveOrUpdatePrimaryCategory(p);	
		return p.getId();
	}
	
	@RequestMapping(value="/{pId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePrimaryCategory(@PathVariable String pId){
		primaryCategoryService.deletePrimaryCategory(pId);
	}
	
	private User getUserBySession(HttpServletRequest request){
		return (User)request.getSession().getAttribute("user");
	}
	
	
}
