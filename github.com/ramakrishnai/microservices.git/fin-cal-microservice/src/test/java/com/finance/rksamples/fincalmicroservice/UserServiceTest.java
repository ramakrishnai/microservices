package com.finance.rksamples.fincalmicroservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.finance.rksamples.fincalmicroservice.user.User;
import com.finance.rksamples.fincalmicroservice.user.UserDaoService;
import com.finance.rksamples.fincalmicroservice.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserDaoService userService;
	
	
	@Test
	public void testGetUsers_returnUserList() throws Exception {
		
		List<User> users = new ArrayList<>();
		users.add(new User(1001, "Adam", new Date()));
		users.add(new User(1002, "Peter", new Date()));
		users.add(new User(1003, "John", new Date()));
		
		//No Call to DB through UserRepositry, as we are Mocking the Users List above to test UserRepository. 
		when(userRepository.findAll()).thenReturn(users.stream().collect(Collectors.toList()));				
		assertEquals(3, userService.findAll().size());
	
	}
	
}
