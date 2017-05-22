package br.com.arua.hamburgeria.DaoContollers;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import br.com.arua.hamburgeria.dao.SandwichsDAO;
import br.com.arua.hamburgeria.dao.controllers.SandwichsDaoController;
import br.com.arua.hamburgeria.mocks.SandwichsDAOMock;
import br.com.arua.hamburgeria.models.Sandwichs;
import br.com.arua.hamburgeria.models.factorys.SandwichFactory;



public class SandwichsDaoControllerTest {

	private SandwichsDAO fakeDAO;
	private SandwichsDaoController controller;
	
	@Before
	public void loadController(){
		this.fakeDAO	 = new SandwichsDAOMock().getMock();
		this.controller  = new SandwichsDaoController(this.fakeDAO);
	}
	
	
	/***
	 * Check if can register one sandwich;
	 * @throws Exception 
	 */
	@Test
	public void shouldSaveOneSandwich() throws Exception{
		
		controller.save(SandwichFactory.getXBacon());
		verify(fakeDAO, times(1)).saveSandwich((Sandwichs)notNull());
	}
	
	/***
	 * Check if can register multiple distinct Sandwichs;
	 * @throws Exception 
	 */
	@Test
	public void shouldSavemultipleSandwichs() throws Exception{
		controller.save(SandwichFactory.getXBacon());
		controller.save(SandwichFactory.getXBurger());
		controller.save(SandwichFactory.getXEgg());
		controller.save(SandwichFactory.getXEggBacon());
		
		verify(fakeDAO, times(4)).saveSandwich((Sandwichs)notNull());
	}
	
	
	/***
	 * Check if can't register two Sandwichs with the same name;
	 * @throws Exception 
	 */
	@Test(expected=Exception.class)
	public void shouldSaveOnlyOneCopy() throws Exception{
								
		controller.save(new Sandwichs("X-Bacon"));
		controller.save(new Sandwichs("X-Bacon"));
		
		fail();
	}


}
