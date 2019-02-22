package com.inmin.rest.webservices.restfulwebservices.user;


import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

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
public class UserResource {

	@Autowired
	private UserDAO userDao;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		
		return userDao.findAll();
		
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = userDao.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		//here get the link to all users, serverpath+ "/users"
		
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	//should return the cerated URI and status as CREATED(201)
	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User savedUser = userDao.save(user);
		 
		 URI location = ServletUriComponentsBuilder
				 		.fromCurrentRequest()
				 		.path("/{id}")
				 		.buildAndExpand(savedUser.getId())
				 		.toUri();
		 
		 return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user= userDao.deleteById(id);
		
		if(user==null) {
			throw new UserNotFoundException("id- "+id );
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
