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

import br.com.arua.hamburgeria.dao.SandwichsDAO;
import br.com.arua.hamburgeria.models.Ingredients;
import br.com.arua.hamburgeria.models.Sandwichs;

public class SandwichsDAOMock {

	/***
	 * Stores the Sandwichs
	 */
	private Map<Integer, Sandwichs> sandwichs;

	public SandwichsDAOMock() {
		sandwichs = new HashMap<>();
	}

	public SandwichsDAO getMock() {
		SandwichsDAO mock = mock(SandwichsDAO.class);

		when(mock.getSandwichsList()).thenReturn(getSandwichsList());
		
		doAnswer(invocation -> {
			Sandwichs sandwich = (Sandwichs) invocation.getArguments()[0];
			saveSandwich(sandwich);
			return null;
		}).when(mock).saveSandwich((Sandwichs) notNull());
		
		when(mock.getSandwichByName((String) notNull())).thenAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			String name = (String) args[0];

			return getSandwichByName(name);
		});

		when(mock.getSandwichByID((Integer) notNull())).thenAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			int id = (int) args[0];

			return getSandwichByID(id);
		});

		return mock;
		
	}

	private  void saveSandwich(Sandwichs sandwich) {
		Integer laxIndex = sandwichs.keySet().stream().max(Integer::compareTo).orElse(0);
		laxIndex++;
		sandwich.setID(laxIndex);
		sandwichs.put(laxIndex, sandwich);
	}

	private List<Sandwichs> getSandwichsList() {
		return Collections.unmodifiableList(new ArrayList<>(sandwichs.values()));
	}

	private Sandwichs getSandwichByName(String name) {
		return get(sandwichs.values().stream().filter(Sandwich -> Sandwich.getName().equals(name)).findFirst()
				.orElse(null));
	}

	private Sandwichs getSandwichByID(Integer id) {
		return get(sandwichs.get(id));
	}

	private Sandwichs get(Sandwichs model) {
		if (model == null) {
			return null;
		}

		Sandwichs sandwich = new Sandwichs(model.getName());
		model.getIngredients().forEach((ingredientID, ingredient) -> {
			Ingredients newIngredient = new Ingredients(ingredient.getName(), ingredient.getUnitaryValue());
			newIngredient.setId(ingredient.getId());
			newIngredient.add(ingredient.getQuantity());
			sandwich.add(newIngredient);
		});

		return sandwich;
	}
}
