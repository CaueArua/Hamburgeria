package br.com.arua.hamburgeria.promotions;

import br.com.arua.hamburgeria.dao.controllers.IngredientsDaoController;
import br.com.arua.hamburgeria.interfaces.PromotionsInterface;
import br.com.arua.hamburgeria.models.Ingredients;
import br.com.arua.hamburgeria.models.Sandwichs;

public class TakeXpayY implements PromotionsInterface {

	private IngredientsDaoController ingredientsDaoController ;
	private Integer ingredientID;
	private Integer take;
	private Integer pay;
	
	public TakeXpayY(Ingredients ingredient, Integer take, Integer pay,IngredientsDaoController ingredientsDaoController ) throws Exception {
		
		validate(ingredient, take, pay);
		
		this.ingredientsDaoController  = ingredientsDaoController;
		this.ingredientID = ingredient.getId();
		this.take = take;
		this.pay = pay;
	}

	@Override
	public void applyPromotion(Sandwichs sandwich) {
		Ingredients ingredient = ingredientsDaoController.getIngredientByID(ingredientID);
		
		Integer numberOfPromotions = (int)Math.floor(sandwich.countIngredient(ingredient) / take);
		Integer freeItens = (take - pay) * numberOfPromotions;
		sandwich.addDiscount(ingredient,freeItens);
	}

	private void validate(Ingredients ingredient, Integer take, Integer pay) throws Exception{
		if(ingredient == null || take == null || pay == null){throw new Exception("Parameters can't be equals to NULL");}
		if(take <= 0)	{throw new Exception("Take can't be less than or equal to than 0");}
		if(pay  <= 0)	{throw new Exception("Pay can't be less than or equal to than 0");}
		if(pay  > take)	{throw new Exception("Pay can't be more than take");}
	}
	
}
