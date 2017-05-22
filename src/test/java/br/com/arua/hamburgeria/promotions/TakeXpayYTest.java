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

public class TakeXpayYTest {

	private PromotionHandler fakeHandler;
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
	public void mustDiscountAnItem() throws Exception {
		Sandwichs xTripleBacon = new SandwichFactory("")
				.whithBacon(1)
				.whithHamburger(3)
				.whithCheese(1)
				.make();
		xTripleBacon.setPromotionHandler(fakeHandler);
		
		fakeHandler.addPromotion(new TakeXpayY(IngredientsLibrary.getHamburger(), 3, 2,controller));
		assertEquals((Double)9.5, xTripleBacon.getValue());		
		
		int actual = xTripleBacon.getDiscountingredients().get(IngredientsLibrary.getHamburger().getId()).getQuantity();
		assertEquals(1, actual);
		
	}

	@Test
	public void mustNotAllowNullParameters(){
		try{
			new TakeXpayY(null, 3, 2,null);
			fail();
		}catch(Exception e){}
		
		try{
			new TakeXpayY(IngredientsLibrary.getBacon(), null, 2,null);
			fail();
		}catch(Exception e){}		
		
		try{
			new TakeXpayY(IngredientsLibrary.getBacon(), 3, null,null);
			fail();
		}catch(Exception e){}		
	}
	
	@Test
	public void mustDiscountMultipleItens() throws Exception {
		Sandwichs sandwich = new SandwichFactory("")
				.whithBacon(1)
				.whithHamburger(6)
				.whithCheese(8)
				.make();
		sandwich.setPromotionHandler(fakeHandler);
		
	
		TakeXpayY extraBurger = new TakeXpayY(IngredientsLibrary.getHamburger(), 3, 2,controller);
		TakeXpayY extraCheese = new TakeXpayY(IngredientsLibrary.getCheese(), 4, 2,controller);
	
		fakeHandler.addPromotion(extraBurger);
		fakeHandler.addPromotion(extraCheese);
		
		int discontHamburger = sandwich.getDiscountingredients().get(IngredientsLibrary.getHamburger().getId()).getQuantity();
		int discontCheese = sandwich.getDiscountingredients().get(IngredientsLibrary.getCheese().getId()).getQuantity();
		
		assertEquals(2, discontHamburger);
		assertEquals(4, discontCheese);
		assertEquals((Double)20.0, sandwich.getValue());
	}
	
	@Test
	public void shouldntDiscont() throws Exception{
		Sandwichs sandwich = new SandwichFactory("")
				.whithBacon(1)
				.whithHamburger(2)
				.whithCheese(1)
				.make();
		
		sandwich.setPromotionHandler(fakeHandler);
		
		fakeHandler.addPromotion(new TakeXpayY(IngredientsLibrary.getHamburger(), 3, 2,controller));

		int actual = sandwich.getDiscountingredients().get(IngredientsLibrary.getHamburger().getId()).getQuantity();

		assertEquals(0, actual);
		assertEquals((Double)9.5, sandwich.getValue());
	} 
}
