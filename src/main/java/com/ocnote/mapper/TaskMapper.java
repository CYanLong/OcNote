package com.ocnote.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ocnote.entity.Task;

public interface TaskMapper {

	@Select("select * from task where sid=#{sid}")
	public List<Task> getTasksBySid(@Param("sid") int sid);
	
	@Insert("insert into task values ("
			+ "#{id}, #{taskName}, #{taskText}, "
			+ "#{createDate}, #{isComplation}, "
			+ "#{user.id}, #{pid}, "
			+ "#{sid})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	public void addTask(Task task);

	@Delete("delete from task "
			+ "where createDate=#{date} and sid=#{sid}")
	public void deleteByDate(@Param("date") String date, @Param("sid") int sid);

	@Delete("delete from task where id=#{id}")
	public void deleteById(@Param("id") int id);
	
	@Update("update task set "
			+ "isComplation=#{isComp} "
			+ "where id=#{id}")
	public void updateComp(@Param("id") int id,
			@Param("isComp") boolean isComp);
	
	@Update("update task set "
			+ "taskTest=#{text}"
			+ "where id=#{id}")
	public void updateTask(@Param("id") int id, @Param("text") String text);
	
	@Select("select taskTest from task where "
			+ "id=#{id}")
	public String getText(int id);
}
