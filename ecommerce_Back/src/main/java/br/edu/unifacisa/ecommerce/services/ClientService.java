package br.edu.unifacisa.ecommerce.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unifacisa.ecommerce.entities.Product;
import br.edu.unifacisa.ecommerce.entities.ProductRequest;
import br.edu.unifacisa.ecommerce.entities.ShippingData;
import br.edu.unifacisa.ecommerce.entities.User;
import br.edu.unifacisa.ecommerce.repositories.ClientRepository;
import br.edu.unifacisa.ecommerce.repositories.UserRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UserRepository userRepository;

	public ArrayList<Product> listProductsByCategory(String id) {
		return this.clientRepository.listProductsByCategory(id);
	}

	public String addItenCart(ProductRequest item, String sessionToken) {
		return this.clientRepository.addItenCart(item, sessionToken);
	}

	public ArrayList<ProductRequest> getMyCart(String sessionToken) {
		return this.clientRepository.getMyCart(sessionToken);
	}

	public String confirmPurchase(ShippingData shippingData, String sessionToken) {
		User client = userRepository.findUserByToken(sessionToken);
		for (ProductRequest cart : client.getMyCart()) {
			cart.setFormPayment(shippingData.getFormPayment());
			cart.setAddress(shippingData.getAddress());
			cart.setStatus("Não enviado");
			User seller = userRepository.findUserById(cart.getSeller());
			seller.addMySales(cart);			
		}
		
		client.resetCart();
		return "Compra confirmada";
	}



	public ArrayList<ProductRequest> getMyRequests(String sessionToken) {
		return this.clientRepository.getMyRequests(sessionToken);
	}
	
}
