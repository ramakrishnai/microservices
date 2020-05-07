package com.finance.rksamples.fincalmicroservice.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource { //You can use name UserController 

	
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//Retrieve All users
	@GetMapping(path="/jpa/users")
	public List<User> getAllUsers() {
		System.err.println("Junit Test call from UserResource (Rest Controller) to getAllUsers() URI --------> /jpa/users");
		return userRepository.findAll();
	}
	
	
	//GET a specific user -> /users/1
	@GetMapping(path="/jpa/users/{id}")
	public Resource<User> getUser(@PathVariable Integer id) {
		System.err.println("Junit Test call from UserResource (Rest Controller) for Specific User URI --------> /jpa/users/{id}");
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		/*
		 * "all-users, SERVER_PATH + "/users", Spring Boot HATEOAS starter - easily add links using 
		 * the method name to show links within the REST Response as information to the Consumers of REST API.
		 * Run the resource for More Details: http://localhost:8084/users/1 
		 */
		Resource<User> resource = new Resource<User>(user.get());
		
		//ControllerLinkBuilder is a static class, check the static imports above.
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		
		return resource;
	}
	
	
	/*
	 * POST   - create an user - /users  
	 * input  - Json object within Body (without User Id) 
	 * Output - Created & Return the created URI to consumer client
	 * 
	 * @Valid - open User Pojo validation happens if any invalid input in Http Request,
	 * The validation error as 'Bad Request' will be 
	 * returned by ResponseEntityExceptionHander.handleMethodArgumentNotValid() 
	 * So just override this method to customise with your own-exception in FinCalxxxExceptionHandler.
	 * check: FinCalxxxExceptionHandler.handleMethodArgumentNotValid method  
	 * 
	 */
	@PostMapping(path="/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		
		User savedUser = userRepository.save(user);
		
		//users/{id}  - savedUser.getId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getId()).toUri();
		
		//ResponseEntity is from spring boot 
		return ResponseEntity.created(location).build();
		
	}
	
	
	
	
	
	//Delete User
	@DeleteMapping(path="/jpa/user/{id}") 
	public void deleteUser(@PathVariable Integer id) {
		
		userRepository.deleteById(id);	
	}
	
	
	
	
	
	//Retrieve All Posts for the User,  http://localhost:8084/users/10001/posts -> check User Id before using URL
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Post> getAllPostsOfUser(@PathVariable Integer id) {
		
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id: "+id);
		}
		
		return user.get().getPosts();
	}
	
	
	
	
	
	//POST - Create user posts
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostsOfUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id: "+id);
		}
		
		User user = userOptional.get();
		post.setUser(user);
		postsRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(post.getId()).toUri();
		
		//ResponseEntity is from spring boot 
		return ResponseEntity.created(location).build();
		
	}
	
	
	
}//End of Class
