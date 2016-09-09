package com.ocnote.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ocnote.entity.SecondCategory;

public interface SecondCategoryMapper {
	
	@Select("select * from secategory where pid=#{pid}")
	public List<SecondCategory> selectByPrim(int pid);
	
	@Insert("insert into secategory values ("
			+ "#{id}, #{categoryName}, #{createTime},"
			+ "#{user.id}, #{pid})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	public void addSecondCategory(SecondCategory sc);
	
	@Delete("delete from secategory where id=#{sId}")
	public void deleteSecondCategory(@Param("sId") String sId);
	
	//根据sid得到pid
	@Select("select pid from secategory where id=#{sid}")
	public int getPid(@Param("sid") int sid);
}
