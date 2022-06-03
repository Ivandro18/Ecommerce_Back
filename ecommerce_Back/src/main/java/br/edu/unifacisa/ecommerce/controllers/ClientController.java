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

import br.edu.unifacisa.ecommerce.entities.Product;
import br.edu.unifacisa.ecommerce.entities.ProductRequest;
import br.edu.unifacisa.ecommerce.entities.ShippingData;
import br.edu.unifacisa.ecommerce.services.ClientService;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;
	// listar produtos pela categoria
	@RequestMapping(value = "/client/listProductsByCategory/{id}",method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Product>> getUser(@PathVariable String id) {
		ArrayList<Product> response = this.clientService.listProductsByCategory(id);
		HttpStatus returnStatus = HttpStatus.OK;
		if (response == null) {
			returnStatus = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<ArrayList<Product>>(response, returnStatus);
	}
	//adiciona item no carrinho
	@RequestMapping(value="/client/addIntenCart", method = RequestMethod.POST)
	public ResponseEntity<String> addItenCart(@RequestBody ProductRequest item, @RequestHeader HttpHeaders headers) {
		//System.out.println("add produto "+ product.toString());
		System.out.print("add iten no carrinho" + item.toString());
		String sessionToken = headers.get("sessionToken").get(0);
		String returnCreat = this.clientService.addItenCart(item, sessionToken);
		return new ResponseEntity<String>(returnCreat, HttpStatus.CREATED);
	}
	// listar produtos itens do carrinho
	@RequestMapping(value = "/client/listMyCart",method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ProductRequest>> getUser(@RequestHeader HttpHeaders headers) {
		String sessionToken = headers.get("sessionToken").get(0);
		ArrayList<ProductRequest>response = this.clientService.getMyCart(sessionToken);
		HttpStatus returnStatus = HttpStatus.OK;
		if (response == null) {
			returnStatus = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<ArrayList<ProductRequest>>(response, returnStatus);
	}
	//confirma compra
	@RequestMapping(value="/client/confirmPurchase", method = RequestMethod.POST)
	public ResponseEntity<String> confirmPurchase(@RequestBody ShippingData shippingData, @RequestHeader HttpHeaders headers) {
		System.out.println("confirma compra " + shippingData.toString());
		String sessionToken = headers.get("sessionToken").get(0);
		String returnCreat = this.clientService.confirmPurchase(shippingData, sessionToken);
		return new ResponseEntity<String>(returnCreat, HttpStatus.CREATED);
	}
	

	@RequestMapping(value = "/client/getMyRequests",method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ProductRequest>> getMyRequests(@RequestHeader HttpHeaders headers) {
		String sessionToken = headers.get("sessionToken").get(0);
		ArrayList<ProductRequest> response = this.clientService.getMyRequests(sessionToken);
		//System.out.println("getMyRequests " + response.toString());
		HttpStatus returnStatus = HttpStatus.OK;
		if (response == null) {
			returnStatus = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<ArrayList<ProductRequest>>(response, returnStatus);
	}
}
