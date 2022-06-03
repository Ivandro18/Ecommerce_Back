package br.edu.unifacisa.ecommerce.repositories;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.unifacisa.ecommerce.entities.Product;
import br.edu.unifacisa.ecommerce.entities.ProductRequest;
import br.edu.unifacisa.ecommerce.entities.ShippingData;
import br.edu.unifacisa.ecommerce.entities.User;
import br.edu.unifacisa.ecommerce.enums.TypeOfUser;

@Repository
public class ClientRepository {
	
	@Autowired
	SellerRepository sellerRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	UserRepository userRepository;
	
	static int idRequestCount;
	
	public ClientRepository () {
		ClientRepository.idRequestCount = 0;
	}

	public ArrayList<Product> listProductsByCategory(String id) {
		id = adminRepository.idToNameCategory(id);
		ArrayList<Product> p = new ArrayList<>();
		for (User u : userRepository.users) {
			if (u.getUserType() == TypeOfUser.SELLER) {
				p.addAll(u.getProducts(id));
			}
		}		
		return p;
	}

	public String addItenCart(ProductRequest item, String sessionToken) {
		User user = userRepository.findUserByToken(sessionToken);
		return user.addItemCart(item);
		
	}

	public ArrayList<ProductRequest> getMyCart(String sessionToken) {
		User user = userRepository.findUserByToken(sessionToken);
		return user.getMyCart();
	}

	

	public ArrayList<ProductRequest> getMyRequests(String sessionToken) {
		User user = userRepository.findUserByToken(sessionToken);
		return user.getRequests();
	}

}
