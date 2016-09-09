package com.ocnote.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ocnote.entity.SecondCategory;
import com.ocnote.entity.User;
import com.ocnote.service.SecondCategoryService;

@Controller
@RequestMapping(value="secondCategorys")
public class SecondCategoryController {

	@Autowired
	private SecondCategoryService secondCategoryService ;	
		
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addSecondCategory(HttpServletRequest request,String sName, String pId){
		User user = (User)request.getSession().getAttribute("user");
		
		SecondCategory sc = new SecondCategory();
		sc.setCategoryName(sName);
		sc.setCreateTime(LocalDate.now().toString());
		sc.setUser(user);
		sc.setPid(Integer.valueOf(pId));
		
		int id = secondCategoryService.addSecondCategory(sc);
		return String.valueOf(id);
	}
	
	@RequestMapping(value="/{sId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSecondCategory(@PathVariable("sId") String sId){
		secondCategoryService.deleteSecondCategory(sId);
	}
	
	
	
}
