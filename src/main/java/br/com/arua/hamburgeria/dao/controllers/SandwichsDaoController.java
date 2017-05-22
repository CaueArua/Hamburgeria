package br.com.arua.hamburgeria.dao.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.arua.hamburgeria.dao.SandwichsDAO;
import br.com.arua.hamburgeria.models.Sandwichs;

@Repository
public class SandwichsDaoController {

	List<Sandwichs> sandwichs;
	
	private SandwichsDAO dao;
	
	@Autowired
	public SandwichsDaoController(SandwichsDAO dao){
		this.dao = dao;
	}
	
	/***
	 * Save a new ingredient 
	 * @param ingredient ingredient to be saved 
	 * @throws Exception If the ingredient already exists return a Exception
	 */
	public void save(Sandwichs sandwich) throws Exception{
		
		/*Checks if the sandwich exists, if exists throws a exception*/
		if(dao.getSandwichByName(sandwich.getName()) != null){
			throw new Exception("Ingredient already registered.");
		}
		
		/*If the sandwich doesn’t exist, save it */
		dao.saveSandwich(sandwich);
	}

	public List<Sandwichs> getAllSandwichs() {
		return dao.getSandwichsList();
	}

	public Sandwichs getSandwichByid(Integer id) {
		return dao.getSandwichByID(id);
	}
	
	
}
