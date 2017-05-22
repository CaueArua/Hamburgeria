package br.com.arua.hamburgeria.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.arua.hamburgeria.models.Ingredients;

@Repository
public class IngredientsDAO {

	/***
	 * Stores the ingredients
	 */
	private static Map<Integer, Ingredients> ingredients = new HashMap<>();

	public void saveIngredient(Ingredients ingredient) {
		Integer laxIndex = ingredients.keySet().stream().max(Integer::compareTo).orElse(0);
		laxIndex++;
		ingredient.setId(laxIndex);
		ingredients.put(laxIndex, ingredient);
	}

	public List<Ingredients> getIngredientsList() {
		return Collections.unmodifiableList(new ArrayList<>(ingredients.values()));
	}

	public Ingredients getIngredientByName(String name) {
		return get(ingredients.values()
				.stream()
				.filter(ingredient -> ingredient.getName().equals(name))
				.findFirst()
				.orElse(null));
	}
	
	public Ingredients getIngredientByID(Integer id){
		return get(ingredients.get(id));
	}
	
	private Ingredients get(Ingredients model){
		if(model == null){return null;}
		Ingredients ingredients = new Ingredients(model.getName(), model.getUnitaryValue());
		ingredients.setId(model.getId());
		
		return ingredients;
	}
	
}
