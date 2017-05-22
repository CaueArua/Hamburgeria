package br.com.arua.hamburgeria.models;

public class Ingredients {
	
	private Integer id;
	private String name;
	private Double unitaryValue;
	private Integer quantity;
	
	public Ingredients(String name, Double valor){
		this.unitaryValue = valor;
		this.name = name;
		this.quantity = 0;
	}
	
	

	public Integer getId() {
		return id;
	}

	public Ingredients setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Double getUnitaryValue() {
		return unitaryValue;
	}
	
	public Double getValue() {
		return unitaryValue * quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public void add(Integer quantity){
		this.quantity += quantity;
	}
	
	public void remove(Integer quantity){
		this.quantity -= quantity;
	}
	
	

}
