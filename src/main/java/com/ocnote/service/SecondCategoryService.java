package com.ocnote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ocnote.entity.SecondCategory;
import com.ocnote.mapper.SecondCategoryMapper;

@Service
public class SecondCategoryService {
	
	@Autowired
	private SecondCategoryMapper secMapper;
	
	@Transactional
	public int addSecondCategory(SecondCategory sc) {
		secMapper.addSecondCategory(sc);
		return sc.getId();
	}
	
	@Transactional
	public void deleteSecondCategory(String sId) {
		secMapper.deleteSecondCategory(sId);
	}

}
