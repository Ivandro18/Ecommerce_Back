package br.edu.unifacisa.ecommerce.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.unifacisa.ecommerce.entities.Product;
import br.edu.unifacisa.ecommerce.entities.ProductRequest;
import br.edu.unifacisa.ecommerce.services.SellerService;

@Controller
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	// listar produtos
	@RequestMapping(value="/seller/getListMyProducts",method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Product>> getCats( @RequestHeader HttpHeaders headers) {
		String sessionToken = headers.get("sessionToken").get(0);
		System.out.println("listar meus produtos token = " + sessionToken);
		ArrayList<Product> returnCreat = this.sellerService.getListMyProducts(sessionToken);
		System.out.println("listar meus produtos = " + returnCreat);
		return new ResponseEntity<ArrayList<Product>>(returnCreat, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/seller/addProducts", method = RequestMethod.POST)
	public ResponseEntity<String> addProduct(@RequestBody Product product, @RequestHeader HttpHeaders headers) {
		System.out.println("add produto "+ product.toString());
		String sessionToken = headers.get("sessionToken").get(0);
		String returnCreat = this.sellerService.addProduct(product, sessionToken);
		return new ResponseEntity<String>(returnCreat, HttpStatus.CREATED);
	}
	@RequestMapping(value = "/client/getMySales",method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ProductRequest>> getMySales(@RequestHeader HttpHeaders headers) {
		String sessionToken = headers.get("sessionToken").get(0);
		ArrayList<ProductRequest> response = this.sellerService.getMySales(sessionToken);
		//System.out.println("getMyRequests " + response.toString());
		HttpStatus returnStatus = HttpStatus.OK;
		if (response == null) {
			returnStatus = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<ArrayList<ProductRequest>>(response, returnStatus);
	}
}
