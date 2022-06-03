package br.edu.unifacisa.ecommerce.repositories;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.unifacisa.ecommerce.entities.Product;
import br.edu.unifacisa.ecommerce.entities.User;

@Repository
public class SellerRepository {
	@Autowired
	UserRepository userRepository;


	public SellerRepository() {
		
	}



	public ArrayList<Product> getLisMyProducts(String sessionToken) {
		User user = userRepository.findUserByToken(sessionToken);
		return user.getLisMyProducts();
	}

	public String addProducts(Product product, String sessionToken) {
		User user = userRepository.findUserByToken(sessionToken);
		product.setSeller(user.getUserName());
		user.addProduct(product);
		return "\nProduto adicionado com sucesso\n";
	}

}
