package br.edu.unifacisa.ecommerce.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Repository;

import br.edu.unifacisa.ecommerce.entities.User;
import br.edu.unifacisa.ecommerce.enums.TypeOfUser;
import br.edu.unifacisa.ecommerce.utils.TokenUtils;

@Repository
public class UserRepository {

	 ArrayList<User> users;
	
	public UserRepository() {
		this.users = new ArrayList<User>();
		this.users.add(new User( "Administrador", "ad", "ad", TypeOfUser.ADMIN));
		this.users.add(new User( "vendedor", "v", "v", TypeOfUser.SELLER));
		this.users.add(new User( "cliente", "c", "c", TypeOfUser.CLIENT));
		
	}

	public ArrayList<User> getAllUsers(String sessionToken) {
		User user = findUserByToken(sessionToken);
		if (user != null && user.getUserType().equals(TypeOfUser.ADMIN)) {
			return this.users;
		} else {
			return null;
		}
	}
	
	public boolean authenticateToken (String sessionToken) {
		User user = findUserByToken(sessionToken);
		if (user.getSessionToken().equals(sessionToken))
			return true;
		return false;
	}
	
	public User findUserByToken(String sessionToken) {
		System.out.println("metodo findUserByToken" + sessionToken);
		for (int i = 0; i < this.users.size(); i++) {
			String token = this.users.get(i).getSessionToken();
			if(token != null) {
				if (token.equals(sessionToken)) {
					this.users.get(i).toString();
					return this.users.get(i);
				}
			}
		}
		System.out.print("usuario não encontrado");
		return null;
	}	

	public String addUser(User user2) {
		
		
		for (User user : this.users) {
			if (user.getUserName().equals(user2.getUserName())) {
				return "Usuário ja existe";
			}
		}
		this.users.add(user2);
		System.out.println("Adicionando usuáro" + user2.toString());
		//Persistence.saveData(user2);
		return "Usuário criado com sucesso";
	}

	public User findUserById(String userName) {
		for (User user : this.users) {
			if (user.getUserName().equals(userName)) {
				return user;
			}
		}
		return null;
	}

	public String login(String userName, String password) {
		for (User user : this.users) {
			if (user.getUserName().equals(userName) && 
					user.getPassword().equals(password)) {
				String sessionToken = TokenUtils.createToken(
						Long.toString(new Date().getTime()));
				user.setSessionToken(sessionToken);
				return sessionToken;
			}
		}
		return "Usuário não encontrado";
	}

	public String getUserType(String sessionToken) {
		System.out.println("get user type token" + sessionToken);
		User user = this.findUserByToken(sessionToken);
		System.out.println("findUserByToken" + user.toString());
		if (user.getUserType() != null)
			return String.valueOf(user.getUserType());
		return "não encontrado";
	}

	
}
