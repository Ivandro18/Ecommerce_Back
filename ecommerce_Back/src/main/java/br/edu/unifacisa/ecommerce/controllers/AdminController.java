package br.edu.unifacisa.ecommerce.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.unifacisa.ecommerce.services.AdminService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	// adiciona categorias
		@RequestMapping(value="/admin/addCat", 
				params = {"name"},method = RequestMethod.GET)
		public ResponseEntity<String> addCat(@RequestParam String name, @RequestHeader HttpHeaders headers) {
			String sessionToken = headers.get("sessionToken").get(0);
			System.out.println("adiciona categoria ="+ name );
			String returnCreat = this.adminService.addCat(name, sessionToken);
			return new ResponseEntity<String>(returnCreat, HttpStatus.CREATED);
		}
		
		// listar categorias
		@RequestMapping(value="/admin/getCats",method = RequestMethod.GET)
		public ResponseEntity<ArrayList<String>> getCats( ) {
			ArrayList<String> returnCreat = this.adminService.getCats();
			System.out.println(" listar categorias = " + returnCreat);
			return new ResponseEntity<ArrayList<String>>(returnCreat, HttpStatus.CREATED);
		}
}
