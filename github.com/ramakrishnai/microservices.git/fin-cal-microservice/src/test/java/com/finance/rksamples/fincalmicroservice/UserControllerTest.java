package com.finance.rksamples.fincalmicroservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.finance.rksamples.fincalmicroservice.user.PostsRepository;
import com.finance.rksamples.fincalmicroservice.user.User;
import com.finance.rksamples.fincalmicroservice.user.UserRepository;
import com.finance.rksamples.fincalmicroservice.user.UserResource;

@RunWith(SpringRunner.class)
@WebMvcTest(UserResource.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private PostsRepository postsRepository;
	
	@MockBean
	private UserResource userController;
	
	
	@Test
	public void testGetUsers() throws Exception {
		
		List<User> users = new ArrayList<>();
		users.add(new User(1001, "Adam", new Date()));
		users.add(new User(1002, "Peter", new Date()));
		users.add(new User(1003, "John", new Date()));
		
		
		//No Call to DB through Repositry as I mock Users list above
		when(userController.getAllUsers()).thenReturn(users.stream().collect(Collectors.toList()));				
		assertEquals(3, userController.getAllUsers().size());
		
		RequestBuilder request = get("/jpa/users").accept(MediaType.APPLICATION_JSON);
		MvcResult result =  mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	
	}
	
	@Test
	public void testUser_returnGivenUser() throws Exception {
		
		//No Call to DB through Repositry as I mock Users list above
		Resource<User> value = new Resource<>(new User(1001, "Adam", new Date()));
		when(userController.getUser(1001)).thenReturn(value);				
		
		RequestBuilder request = get("/jpa/users/{id}", 1001).accept(MediaType.APPLICATION_JSON);
		MvcResult result =  mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(jsonPath("name").value("Adam"))
				.andReturn();
		
		assertEquals(200, result.getResponse().getStatus());
	
	}
	
	
	
	
	
}
