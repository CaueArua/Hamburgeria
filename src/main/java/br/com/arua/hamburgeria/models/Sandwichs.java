package br.com.arua.hamburgeria.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.arua.hamburgeria.promotions.PromotionHandler;


public class Sandwichs {

	private Integer ID;
	private String name;
	private Map<Integer,Ingredients> ingredients;
	private Double value;
	private PromotionHandler promotionHandler = PromotionHandler.gi();
	
	private Map<Integer,Ingredients> discountingredients;
	private double discontPercentage ;
	
	public Sandwichs(String name) {
		this.name = name;
		this.ingredients = new HashMap<>();
		this.discountingredients = new HashMap<>();
		this.discontPercentage = 0.0;
	}
	
	public Sandwichs add(Ingredients ingredient){
		return add(ingredient,1);
	}
	
	public Sandwichs add(Ingredients ingredient, Integer quantity){
		if(! ingredients.containsKey(ingredient.getId())){
			ingredients.put(ingredient.getId(), ingredient);
		}
		
		ingredients.get(ingredient.getId()).add(quantity);
		
		calValue();
		return this;
	} 
	
	public Sandwichs remove(Ingredients ingredient){
		return remove(ingredient,1);
	}
	
	public Sandwichs remove(Ingredients ingredient, Integer quantity){
		Integer id = ingredient.getId();
		
		if(! ingredients.containsKey(id)){
			return this;
		}
		
		ingredients.get(id).remove(quantity);
		
		if(ingredients.get(id).getQuantity() == 0){
			ingredients.remove(id);
		}
		
		calValue();
		return this;
	} 
	
	private void calValue() {
		value = 0d;
		discountingredients.clear();
		discontPercentage = 0d;
		
		promotionHandler.applyPromotions(this);
	
		
		ingredients.forEach((id,ingredient) ->		 value += ingredient.getValue());
		discountingredients.forEach((id,ingredient) -> value -= ingredient.getValue());
		
		value -= (value * (discontPercentage/100.0) );
	}


	public Double getValue() {
		calValue();
		return this.value;
	}
	
	public void setPromotionHandler(PromotionHandler promotionHandler) {
		this.promotionHandler = promotionHandler;
	}
	
	public Integer countIngredient(Ingredients ingredient) {
		Ingredients model = ingredients.get(ingredient.getId());
		return model != null ? model.getQuantity() : 0;
	}

	public void addDiscount(Ingredients ingredient, Integer freeItens) {
		if(! discountingredients.containsKey(ingredient.getId())){
			discountingredients.put(ingredient.getId(), ingredient);
		}
		
		discountingredients.get(ingredient.getId()).add(freeItens);
	}

	public Map<Integer,Ingredients> getIngredients() {
		return Collections.unmodifiableMap(ingredients);
	}

	public Map<Integer,Ingredients> getDiscountingredients() {
		calValue();
		return Collections.unmodifiableMap(discountingredients);
	}

	public void addDiscount(Double discount) {
		this.discontPercentage = Math.max(this.discontPercentage, discount);		
	}
	
	public Double getDiscontPercentage() {
		calValue();
		return discontPercentage;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setID(Integer id) {
		this.ID = id;
	}
	
	public Integer getID() {
		return ID;
	}

}
