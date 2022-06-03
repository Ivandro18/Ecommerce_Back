package br.edu.unifacisa.ecommerce.entities;

public class ProductRequest {
	
	private int 	prodId;
	private String 	prodName;
	private String 	status;
	private String 	seller;
	private String 	formPayment;
	private String 	address;
	private int		quantity;
	private double 	price;
	
	
	public String getFormPayment() {
		return formPayment;
	}
	public void setFormPayment(String formPayment) {
		this.formPayment = formPayment;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ProductRequest [prodId=" + prodId + ", prodName=" + prodName + ", seller=" + seller + ", quantity="
				+ quantity + ", price=" + price + "]";
	}	

}
