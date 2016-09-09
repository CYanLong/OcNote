package com.ocnote.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ocnote.entity.User;

public interface UserMapper {
	

	@Select("select * from user "
			+ "where email=#{email} and password=#{password}")
	public User loginByEmail(@Param("email") String email, @Param("password") String password);
	
	@Select("select * from user "
			+ "where username=#{username} and password=#{password}")
	public User loginByUsername(@Param("username") String username, @Param("password") String password);
	
	@Insert("insert into user values (#{id}, #{username}, #{password}, #{token}, #{email})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void register(User user);
	
	@Select("select exists(select 1 from user where username=#{username})")
	public boolean existUserName(String username);
	
	@Select("select exists(select 1 from user where email=#{email})")
	public boolean existEmail(String email);
	
	
	@Select("select password from user where username=#{username}")
	public String select(@Param("username") String username);
	
}
