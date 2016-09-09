package com.ocnote.contr.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ocnote.entity.Task;
import com.ocnote.entity.User;

@WebAppConfiguration
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml", 
	"classpath:spring/applicationContext-trans.xml", "classpath:spring/springmvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskContrTest {
	
	
	@Autowired
    private WebApplicationContext wac;

	@Autowired
	private MockHttpSession session;
	
    private MockMvc mockMvc;
    
    private String rootUrl = "/tasks";
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
	@Test
	public void addTask() throws Exception{
	
		/*mockMvc.perform(MockMvcRequestBuilders.post(rootUrl)
				.param("taskName", "taskName lalal")
				.param("createDate", "2016-09-22")
				.param("sId", "3")
				.sessionAttr("user", new User().setId(17)))	
				.andDo(print())
				.andExpect(status().isOk());*/
		
	}
	
	@Test
	public void deleteTaskByCreateTime() throws Exception{
		
		
		/*mockMvc.perform(MockMvcRequestBuilders.delete(rootUrl + "/2016-09-22")
				.param("sId", "2"))
				.andDo(print())
				.andExpect(status().isNoContent());*/
		
	}
	
	@Test
	public void addTaskByCreateTime() throws Exception{
		/*mockMvc.perform(MockMvcRequestBuilders.post(rootUrl + "/2016-03-21")
				.param("sId", "2")
				.param("taskName", "ladadad")
				.sessionAttr("user", new User().setId(17)))
			.andDo(print())
			.andExpect(status().isOk());*/
	}
	
	@Test
	public void setComp() throws Exception{
		/*mockMvc.perform(MockMvcRequestBuilders.post(rootUrl + "/2016-03-21/3")
				.param("isComplation", "true")
				)
			.andDo(print())
			.andExpect(status().isNoContent());*/
	}
	
	@Test
	public void deleteTask() throws Exception{
		/*mockMvc.perform(MockMvcRequestBuilders.post(rootUrl + "/2016-03-21/3")
				.param("sId", "2")
				)
			.andDo(print())
			.andExpect(status().isNoContent());*/
	}
	
	@Test
	public void updateTaskText() throws Exception{
		/*mockMvc.perform(MockMvcRequestBuilders.put(rootUrl + "/2016-03-21/3")
				.content("taskText=sdsdsfefewfds")
				)
			.andDo(print())
			.andExpect(status().isNoContent());*/
	}
	
	@Test
	public void getTaskText() throws Exception{
		/*mockMvc.perform(MockMvcRequestBuilders.get(rootUrl + "/2016-03-21/3")
				)
			.andDo(print())
			.andExpect(status().isOk());*/
	}
}
