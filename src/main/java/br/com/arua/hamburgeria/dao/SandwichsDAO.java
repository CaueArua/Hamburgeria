package br.com.arua.hamburgeria.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.arua.hamburgeria.models.Ingredients;
import br.com.arua.hamburgeria.models.Sandwichs;

@Repository
public class SandwichsDAO {

	/***
	 * Stores the Sandwichs
	 */
	private static Map<Integer, Sandwichs> sandwichs = new HashMap<>();

	public void saveSandwich(Sandwichs sandwich) {
		Integer laxIndex = sandwichs.keySet().stream().max(Integer::compareTo).orElse(0);
		laxIndex++;
		sandwich.setID(laxIndex);
		sandwichs.put(laxIndex, sandwich);
	}

	public List<Sandwichs> getSandwichsList() {
		return Collections.unmodifiableList(new ArrayList<>(sandwichs.values()));
	}

	public Sandwichs getSandwichByName(String name) {
		return get(sandwichs.values()
				.stream()
				.filter(Sandwich -> Sandwich.getName().equals(name))
				.findFirst()
				.orElse(null));
	}

	public Sandwichs getSandwichByID(Integer id) {
		return get(sandwichs.get(id));
	}
	
	
	private Sandwichs get(Sandwichs model){
		if(model == null){ return null;}
				
		Sandwichs sandwich = new Sandwichs(model.getName());
		model.getIngredients()
			.forEach((ingredientID,ingredient) -> 
				{
					Ingredients newIngredient = new Ingredients(ingredient.getName(), ingredient.getUnitaryValue());
					newIngredient.setId(ingredient.getId());
					sandwich.add(newIngredient);
				});
		
		return sandwich;
	}
}
