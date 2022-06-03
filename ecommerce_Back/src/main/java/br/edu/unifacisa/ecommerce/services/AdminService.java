package br.edu.unifacisa.ecommerce.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unifacisa.ecommerce.repositories.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	public String addCat(String name, String sessionToken) {
		return this.adminRepository.addCat(name, sessionToken);
	}

	public ArrayList<String> getCats() {
		return this.adminRepository.getCats();
	}
}
