package com.finance.rksamples.fincalmicroservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static int userCount = 3;
	
	@Autowired
	private UserRepository userRepository;
	

	private static List<User> users = new ArrayList<>();
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Peter", new Date()));
		users.add(new User(3, "John", new Date()));
	}
	
	/***** Dao methods to Pull data ********/
	public List<User> findAll() {
		
		List<User> userList = userRepository.findAll();
		System.err.println("Calling repository -> "+userList);
		
		return userList;
		//return users;
	}
	
	
	//Save User data
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		System.out.println(user);
		users.add(user);
		return user;
	}
	
	
	//Find one user
	public User findOne(int id) {
		
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	
	
	//Delete User using Lambda Expression
	public User deleteById(Integer id) {
		
		//Scenario-1 -> Delete user from users List and return Void
		//users.removeIf(user -> user.getId() == id);
		
		
		//Returns user object if not found null
		User user = users.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
		System.out.println("User Object ---------> "+user);
		
		//Scenario-2 -> Delete user and return that deleted user object to client
		if(users.removeIf(o -> o.getId() == id)) {
			return user;
		}
		return null;
		
	}
	
}
