package com.cyl.ocnote.test.PrimaryCategoryTest;

import java.util.List;

import org.junit.Test;

import com.ocnote.domain.PrimaryCategory;
import com.ocnote.service.PrimaryCategoryService;
import com.ocnote.service.ServiceBeanFactory;

public class PrimaryCategoryServiceTest {
	
	PrimaryCategoryService service = ServiceBeanFactory.getPrimaryCategoryService();
	
	@Test
	public void testPrimaryCategoryByUser(){
		//test data
		String userId = "20146218";
		
		List<PrimaryCategory> result = service.getPrimaryCategoryByUser(userId);
		
		System.out.println(result);
	}
}
