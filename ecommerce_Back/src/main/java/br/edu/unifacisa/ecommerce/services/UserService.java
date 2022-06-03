package br.edu.unifacisa.ecommerce.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unifacisa.ecommerce.entities.User;
import br.edu.unifacisa.ecommerce.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserService() {
	}

	public ArrayList<User> getAllUsers(String sessionToken) {
		return this.userRepository.getAllUsers(sessionToken);
	}

	public String addUser(User user) {
		return this.userRepository.addUser(user);
	}

	public User findUserById(String id) {
		return this.userRepository.findUserById(id);
	}

	public String login(String userName, String password) {
		return this.userRepository.login(userName, password);
	}

	public String getuserType(String sessionToken) {
		return this.userRepository.getUserType(sessionToken);
	}

	
	
	
	
	
}
