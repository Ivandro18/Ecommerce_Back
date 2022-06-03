package br.edu.unifacisa.ecommerce.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unifacisa.ecommerce.entities.Product;
import br.edu.unifacisa.ecommerce.entities.ProductRequest;
import br.edu.unifacisa.ecommerce.entities.User;
import br.edu.unifacisa.ecommerce.repositories.SellerRepository;
import br.edu.unifacisa.ecommerce.repositories.UserRepository;

@Service
public class SellerService {
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private UserRepository userRepository;

	public ArrayList<Product> getListMyProducts(String sessionToken) {
		return this.sellerRepository.getLisMyProducts(sessionToken);
	}

	public String addProduct(Product product, String sessionToken) {
		return this.sellerRepository.addProducts(product, sessionToken);
	}

	public ArrayList<ProductRequest> getMySales(String sessionToken) {
		User seller = userRepository.findUserByToken(sessionToken);
		return seller.getMySales();
	}
	
}
