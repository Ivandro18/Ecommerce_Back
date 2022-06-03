package br.edu.unifacisa.ecommerce.repositories;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
	@Autowired
	UserRepository userRepository;
	
private ArrayList<String> categories;
	
	public AdminRepository() {
		this.categories = new ArrayList<String>();
		categories.add("Placa de vídeo");
		categories.add("Processador");
		categories.add("Memória RAM");
		
	}
	
	public String addCat(String name, String sessionToken) {
		if (userRepository.authenticateToken(sessionToken)) {
			this.categories.add(name);
			return "Categoria adicionado com sucesso";
		}
		return "Falha na autenticação";
	}
	public ArrayList<String>getCats() {
		return this.categories;
	}

	public String idToNameCategory(String id) {
		return categories.get(Integer.parseInt(id));
	}
}
