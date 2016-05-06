package com.ocnote.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ocnote.domain.PrimaryCategory;
import com.ocnote.domain.SecondCategory;
import com.ocnote.domain.User;
import com.ocnote.service.PrimaryCategoryService;
import com.ocnote.service.SecondCategoryService;
import com.ocnote.service.ServiceBeanFactory;

@Controller
@RequestMapping(value="secondCategorys")
public class SecondCategoryController {

	private SecondCategoryService secondCategoryService = ServiceBeanFactory.getSecondCategoryService();	
	private PrimaryCategoryService primaryCategoryService = ServiceBeanFactory.getPrimaryCategoryService();
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addSecondCategory(HttpServletRequest request,String sName, String pId){
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			
		}
		SecondCategory sc = new SecondCategory();
		sc.setCategoryName(sName);
		sc.setCreateTime(new Date());
		sc.setUser(user);
		PrimaryCategory pc = primaryCategoryService.getPrimaryCategory(pId);
		sc.setPrimaryCategory(pc);
		secondCategoryService.saveOrUpdateSecondCategory(sc);
		return sc.getId();
	}
	
	@RequestMapping(value="/{sId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSecondCategory(@PathVariable("sId") String sId){
		
		secondCategoryService.deleteSecondCategory(sId);
				
	}
	
	
	
}
