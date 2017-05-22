package br.com.arua.hamburgeria.DaoContollers;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import br.com.arua.hamburgeria.dao.IngredientsDAO;
import br.com.arua.hamburgeria.dao.controllers.IngredientsDaoController;
import br.com.arua.hamburgeria.mocks.IngredientsDAOMock;
import br.com.arua.hamburgeria.models.Ingredients;
import br.com.arua.hamburgeria.models.factorys.IngredientsLibrary;



public class IngredientsDaoControllerTest {

	private IngredientsDAO fakeDAO;
	private IngredientsDaoController controller;

	
	@Before
	public void loadController(){
		this.fakeDAO	 = new IngredientsDAOMock().getMock();
		this.controller  = new IngredientsDaoController(this.fakeDAO);
	}
	
	
	/***
	 * Check if can register one ingredients;
	 * @throws Exception 
	 */
	@Test
	public void shouldSaveOneIngredient() throws Exception{
		IngredientsDAO fakeDAO	 = mock(IngredientsDAO.class);
		IngredientsDaoController controller  = new IngredientsDaoController(fakeDAO);
		
		controller.save(new Ingredients("Alface", 0.40));
		verify(fakeDAO, times(1)).saveIngredient((Ingredients)notNull());
	}
	
	/***
	 * Check if can register multiple distinct ingredients;
	 * @throws Exception 
	 */
	@Test
	public void shouldSavemultipleIngredient() throws Exception{
		IngredientsDAO fakeDAO	 = mock(IngredientsDAO.class);
		IngredientsDaoController controller  = new IngredientsDaoController(fakeDAO);
		
		controller.save(IngredientsLibrary.getLettuce());
		controller.save(IngredientsLibrary.getBacon());
		controller.save(IngredientsLibrary.getHamburger());
		controller.save(IngredientsLibrary.getEgg());
		controller.save(IngredientsLibrary.getCheese());
		
		verify(fakeDAO, times(5)).saveIngredient((Ingredients)notNull());
	}
	
	
	/***
	 * Check if can't register two ingredients with the same name;
	 * @throws Exception 
	 */
	@Test(expected=Exception.class)
	public void shouldSaveOnlyOneCopy() throws Exception{
								
		controller.save(new Ingredients("Alface", 0.40));
		controller.save(new Ingredients("Alface", 0.50));
		
		fail();
	}


}
