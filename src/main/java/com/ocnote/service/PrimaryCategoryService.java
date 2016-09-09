package com.ocnote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ocnote.entity.PrimaryCategory;
import com.ocnote.mapper.PrimaryCategoryMapper;

@Service
public class PrimaryCategoryService {
	
	@Autowired
	private PrimaryCategoryMapper primMapper;
	
	@Transactional
	public List<PrimaryCategory> getPrimaryCategoryByUser(int id) {
		return primMapper.getPrimaryCategoryByUser(id);
	}

	@Transactional
	public int saveOrUpdatePrimaryCategory(PrimaryCategory p) {
		primMapper.addPrimaryCategory(p);
		return p.getId();
	}

	@Transactional
	public void deletePrimaryCategory(String pId) {
		primMapper.deletePrimaryCategory(pId);
	}

	

}
