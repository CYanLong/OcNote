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

import com.ocnote.entity.User;
import com.ocnote.mapper.UserMapper;
import com.ocnote.service.UserService;

@WebAppConfiguration
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml", 
	"classpath:spring/applicationContext-trans.xml", "classpath:spring/springmvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SecoContrTest {
	
	
	
	@Autowired
    private WebApplicationContext wac;

	@Autowired
	private MockHttpSession session;
	
    private MockMvc mockMvc;
    
    private String rootUrl = "/secondCategorys";
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
	@Test
	public void addSecondCategory() throws Exception{
		
		/*mockMvc.perform(MockMvcRequestBuilders.post(rootUrl)
				.param("sName", "∏±±ÍÃ‚")
				.param("pId", "6")
				.sessionAttr("user", new User().setId(17)))	
				.andDo(print())
				.andExpect(status().isOk());*/
		
	}
	
	//@Test
	public void deleteSecondCategory() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.delete(rootUrl + "/1"))
				.andDo(print())
				.andExpect(status().isNoContent());
	}
	
}
