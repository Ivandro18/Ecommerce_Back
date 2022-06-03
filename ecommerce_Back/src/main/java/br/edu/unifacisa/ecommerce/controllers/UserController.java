package br.edu.unifacisa.ecommerce.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.unifacisa.ecommerce.entities.User;
import br.edu.unifacisa.ecommerce.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public ResponseEntity< ArrayList<User> > listarTodosUsuarios(@RequestHeader HttpHeaders headers) {
		ArrayList<User> users = null;
		try {
			String sessionToken = headers.get("sessionToken").get(0);
			users = userService.getAllUsers(sessionToken);
			if (users != null) {
				return new ResponseEntity<ArrayList<User>>(users, HttpStatus.OK);
			} else {
				return new ResponseEntity<ArrayList<User>>(users, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<ArrayList<User>>(users, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/user", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody User user) {
		System.out.println("Cria Usuario "+ user.toString());
		String returnCreat = this.userService.addUser(user);
		return new ResponseEntity<String>(returnCreat, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable String id) {
		User foundUser = this.userService.findUserById(id);
		HttpStatus returnStatus = HttpStatus.OK;
		if (foundUser == null) {
			returnStatus = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<User>(foundUser, returnStatus);
	}
	
	@RequestMapping(value = "/user/login",
			params = {"userName", "password"},
			method = RequestMethod.GET)
	public ResponseEntity<String> login(@RequestParam String userName, @RequestParam String password) {
		String sessionToken = this.userService.login(userName, password);
		HttpStatus returnStatus = HttpStatus.OK;
		if (sessionToken == null) {
			returnStatus = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<String>(sessionToken, returnStatus);
	}
	
	@RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable String id) {
		User deletedUser = this.userService.findUserById(id);
		HttpStatus returnStatus = HttpStatus.OK;
		if (deletedUser == null) {
			returnStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<User>(deletedUser, returnStatus);
	}	
	
	@RequestMapping(value="/user/userType", method = RequestMethod.GET)
	public ResponseEntity<String> getUserType(@RequestHeader HttpHeaders headers) {
		
		String sessionToken = headers.get("sessionToken").get(0);
		System.out.println("user controler, user type" +sessionToken );
		String userType = userService.getuserType(sessionToken);
		HttpStatus returnStatus = HttpStatus.OK;
		if (userType == null) {
			returnStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<String>(userType, returnStatus);
	}

}