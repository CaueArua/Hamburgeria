package br.com.arua.hamburgeria.mocks;

import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.arua.hamburgeria.dao.IngredientsDAO;
import br.com.arua.hamburgeria.models.Ingredients;

public class IngredientsDAOMock{

	/***
	 * Stores the ingredients
	 */
	private Map<Integer, Ingredients> ingredients;
	
	public IngredientsDAOMock() {
		ingredients = new HashMap<>();
	}
	
	public IngredientsDAO getMock() {
		IngredientsDAO mock = mock(IngredientsDAO.class);
		
		when(mock.getIngredientsList()).thenReturn(getIngredientsList());
		
		doAnswer(invocation -> {
			Ingredients ingredient = (Ingredients) invocation.getArguments()[0];
			saveIngredient(ingredient);
			return null;
		}).when(mock).saveIngredient((Ingredients) notNull());
		
		when(mock.getIngredientByName((String) notNull())).thenAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			String name = (String) args[0];

			return getIngredientByName(name);
		});

		when(mock.getIngredientByID((Integer) notNull())).thenAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			int id = (int) args[0];

			return getIngredientByID(id);
		});

		return mock;
	}
	
	

	private void saveIngredient(Ingredients ingredient) {
		Integer laxIndex = ingredients.keySet().stream().max(Integer::compareTo).orElse(0);
		laxIndex++;
		ingredient.setId(laxIndex);
		ingredients.put(laxIndex, ingredient);
	}

	private List<Ingredients> getIngredientsList() {
		return Collections.unmodifiableList(new ArrayList<>(ingredients.values()));
	}

	
	private Ingredients getIngredientByName(String name) {
		return get(ingredients.values()
				.stream()
				.filter(ingredient -> ingredient.getName().equals(name))
				.findFirst()
				.orElse(null));
	}
	
	private Ingredients getIngredientByID(Integer id){
		return get(ingredients.get(id));
	}
	
	private Ingredients get(Ingredients model){
		if(model == null){return null;}
		Ingredients ingredients = new Ingredients(model.getName(), model.getUnitaryValue());
		ingredients.setId(model.getId());
		
		return ingredients;
	}
	
}
