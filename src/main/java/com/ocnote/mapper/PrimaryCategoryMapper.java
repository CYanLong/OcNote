package com.ocnote.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ocnote.entity.PrimaryCategory;

public interface PrimaryCategoryMapper {
	
	@Select("select * from primcategory where uid=#{uid}")
	public List<PrimaryCategory> getPrimaryCategoryByUser(@Param("uid") int uid);

	@Insert("insert into primcategory values "
			+ "(#{id}, #{user.id}, #{categoryName}, #{createTime})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void addPrimaryCategory(PrimaryCategory pc);
	
	@Delete("delete from primcategory where id=#{id}")
	public void deletePrimaryCategory(@Param("id") String pid);
	
}
