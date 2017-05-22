package br.com.arua.hamburgeria.dao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.arua.hamburgeria.dao.IngredientsDAO;
import br.com.arua.hamburgeria.models.Ingredients;

@Repository
public class IngredientsDaoController {

	private IngredientsDAO dao;
	
	@Autowired
	public IngredientsDaoController(IngredientsDAO dao){
		this.dao = dao;
	}
	
	/***
	 * Save a new ingredient 
	 * @param ingredient ingredient to be saved 
	 * @throws Exception If the ingredient already exists return a Exception
	 */
	public void save(Ingredients ingredient) throws Exception{
		
		/*Checks if the ingredient exists, if exists throws a exception*/
		if(dao.getIngredientByName(ingredient.getName()) != null){
			throw new Exception("Ingredient already registered.");
		}
		
		/*If the ingredient doesn’t exist, save it */
		dao.saveIngredient(ingredient);
	}
	
	public List<Ingredients> getAllIngredients() {
		return dao.getIngredientsList();
	}

	public Ingredients getIngredientByName(String name) {
		return dao.getIngredientByName(name);
	}
	
	public Ingredients getIngredientByID(Integer id){
		return dao.getIngredientByID(id);
	}
	
}
