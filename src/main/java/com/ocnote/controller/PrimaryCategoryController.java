package com.ocnote.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ocnote.entity.PrimaryCategory;
import com.ocnote.entity.User;
import com.ocnote.mapper.PrimaryCategoryMapper;
import com.ocnote.mapper.SecondCategoryMapper;
import com.ocnote.service.PrimaryCategoryService;

@Controller
@RequestMapping(value="primaryCategorys")
public class PrimaryCategoryController {
	
	@Autowired
	private PrimaryCategoryService primaryCategoryService ;	
	
	@Autowired
	private PrimaryCategoryMapper primaryCategoryMapper;
	
	@Autowired
	private SecondCategoryMapper seMapper;
	
	//得到登录用户的PrimaryCategory.
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<PrimaryCategory> getPrimaryCategorys(HttpServletRequest request){	
		User u = getUserBySession(request);
		
		Stream<PrimaryCategory> stream = 
				primaryCategoryMapper.getPrimaryCategoryByUser(u.getId()).parallelStream();
		
		return stream.map((primCate) -> {
			primCate.setSecondCategorys(seMapper.selectByPrim(primCate.getId()));
			return primCate;
		}).collect(Collectors.toList());
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addPrimaryCategory(HttpServletRequest request,String pName){	
		
		System.out.println(pName);
		
		User u = (User)request.getSession().getAttribute("user");
		System.err.println(u);
		PrimaryCategory p = new PrimaryCategory();
		
		p.setUser(u);
		p.setCategoryName(pName);
		p.setCreateTime(LocalDateTime.now().toString());
		p.setUser(u);
	
		int id = primaryCategoryService.saveOrUpdatePrimaryCategory(p);	
		return String.valueOf(id);
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
