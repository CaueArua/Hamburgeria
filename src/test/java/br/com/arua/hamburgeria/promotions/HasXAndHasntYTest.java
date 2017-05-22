package br.com.arua.hamburgeria.promotions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import br.com.arua.hamburgeria.dao.IngredientsDAO;
import br.com.arua.hamburgeria.dao.controllers.IngredientsDaoController;
import br.com.arua.hamburgeria.mocks.IngredientsDAOMock;
import br.com.arua.hamburgeria.mocks.PromotionHandlerMock;
import br.com.arua.hamburgeria.models.Sandwichs;
import br.com.arua.hamburgeria.models.factorys.IngredientsLibrary;
import br.com.arua.hamburgeria.models.factorys.SandwichFactory;

public class HasXAndHasntYTest {
	
	
	private PromotionHandler fakeHandler ;
	private IngredientsDAO fakeDAO;
	private IngredientsDaoController controller;
	
	@Before
	public void loadController() throws Exception{
		this.fakeDAO	 = new IngredientsDAOMock().getMock();
		this.controller  = new IngredientsDaoController(this.fakeDAO);
		this.fakeHandler = new PromotionHandlerMock().getMock();
	
		
		controller.save(IngredientsLibrary.getLettuce());
		controller.save(IngredientsLibrary.getBacon());
		controller.save(IngredientsLibrary.getHamburger());
		controller.save(IngredientsLibrary.getEgg());
		controller.save(IngredientsLibrary.getCheese());
		
	}
	
	
	@Test
	public void mustNotAllowNullParameters(){
		try{
			new HasXAndHasntY(null, IngredientsLibrary.getBacon(), 2d);
			fail();
		}catch(Exception e){}
		
		try{
			new HasXAndHasntY(IngredientsLibrary.getBacon(),null, 2d);
			fail();
		}catch(Exception e){}		
		
		try{
			new HasXAndHasntY(IngredientsLibrary.getBacon(),IngredientsLibrary.getBacon(), null);
			fail();
		}catch(Exception e){}		
	}
	
	
	@Test
	public void mustDiscountAnItem() throws Exception {
		Sandwichs sandwich = new SandwichFactory("")
				.whithLettuce(1)
				.whithHamburger(1)
				.whithCheese(1)
				.make();
		
		sandwich.setPromotionHandler(fakeHandler);
		fakeHandler.addPromotion(new HasXAndHasntY(IngredientsLibrary.getLettuce(),IngredientsLibrary.getBacon(),10.0));

		assertEquals((Double)10.0, sandwich.getDiscontPercentage());
		assertEquals((Double)4.41, sandwich.getValue());
	}
	
	@Test
	public void shouldntDiscont() throws Exception{
		Sandwichs sandwich = new SandwichFactory("")
				.whithLettuce(1)
				.whithHamburger(1)
				.whithCheese(1)
				.whithBacon(1)
				.make();

		sandwich.setPromotionHandler(fakeHandler);
		fakeHandler.addPromotion(new HasXAndHasntY(IngredientsLibrary.getLettuce(),IngredientsLibrary.getBacon(),10.0));

		assertEquals((Double)0.0, sandwich.getDiscontPercentage());
		assertEquals((Double)6.9, sandwich.getValue());
	} 
	
	@Test
	public void shouldntAcumulateDiscont() throws Exception{
		Sandwichs sandwich = new SandwichFactory("")
				.whithLettuce(1)
				.whithHamburger(1)
				.whithCheese(1)
				.make();

		sandwich.setPromotionHandler(fakeHandler);
		
		HasXAndHasntY light = new HasXAndHasntY(IngredientsLibrary.getLettuce(),IngredientsLibrary.getBacon(),10.0);
		HasXAndHasntY light2 = new HasXAndHasntY(IngredientsLibrary.getLettuce(),IngredientsLibrary.getEgg(),8.0);
		
		fakeHandler.addPromotion(light);
		fakeHandler.addPromotion(light2);
		
		assertEquals((Double)10.0, sandwich.getDiscontPercentage());
		assertEquals((Double)4.41, sandwich.getValue());
	} 
	
}
