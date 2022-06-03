package br.edu.unifacisa.ecommerce.entities;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.unifacisa.ecommerce.enums.TypeOfUser;
import br.edu.unifacisa.ecommerce.repositories.UserRepository;

public class User {

	//private String id;
	private String name;
	private String userName;
	private String password;
	private TypeOfUser userType;
	private String sessionToken;
	private ArrayList<ProductRequest> requests;
	private ArrayList<Product> products;
	private ArrayList <ProductRequest> myCart;
	private ArrayList <ProductRequest> mySales;
	private static int idCount = 100;
	

	public User() {
	}
	
	public User( String name, String userName, String password, TypeOfUser userType) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.setUserType(userType);
		this.requests = new ArrayList<>();
		this.products = new ArrayList<>();
		this.myCart = new ArrayList<>();
		this.mySales = new ArrayList<>();
		this.products.add(new Product("RTX 3060", 2500, 10, "Muinto boa!", "Placa de vídeo", "v", 0));
		this.products.add(new Product("RTX 3070", 3500, 20, "Muinto boa!", "Placa de vídeo", "v", 1));
	}
	
	public String addItemCart(ProductRequest item) {
		this.myCart.add(item);
		return "item adicionado";
	}
	
	public void addMySales(ProductRequest cart) {
		this.mySales.add(cart);
		
	}

	public ArrayList<Product> getProducts(String category) {
		ArrayList<Product> p = new ArrayList<>();
		for (Product pro : this.products) {
			if (pro.getCategory().equals(category)) {
				p.add(pro);
			}
		}
		return p;
	}
	
	public void resetCart() {
		this.requests.addAll(this.myCart);
		this.myCart = new ArrayList<>(); 
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<ProductRequest> getMySales() {
		return mySales;
	}

	public void setMySales(ArrayList<ProductRequest> mySales) {
		this.mySales = mySales;
	}

	public void setMyCart(ArrayList<ProductRequest> myCart) {
		this.myCart = myCart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TypeOfUser getUserType() {
		return userType;
	}

	public void setUserType(TypeOfUser userType) {
		this.userType = userType;
	}

	public String getSessionToken() {
		return this.sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}	
	
	public ArrayList<ProductRequest> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<ProductRequest> requests) {
		this.requests = requests;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", userName=" + userName + ", password=" + password + ", userType=" + userType
				+ ", sessionToken=" + sessionToken + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(sessionToken);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(sessionToken, other.sessionToken);
	}


	public ArrayList<ProductRequest> getMyCart() {
		return this.myCart;
	}

	public void addProduct(Product product) {
		product.setId(User.idCount++);
		this.products.add(product);
		
	}

	public ArrayList<Product> getLisMyProducts() {
		// TODO Auto-generated method stub
		return this.products;
	}

	

	

	


	
}
