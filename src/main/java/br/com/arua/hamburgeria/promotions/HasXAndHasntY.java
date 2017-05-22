package br.com.arua.hamburgeria.promotions;

import br.com.arua.hamburgeria.interfaces.PromotionsInterface;
import br.com.arua.hamburgeria.models.Ingredients;
import br.com.arua.hamburgeria.models.Sandwichs;

public class HasXAndHasntY implements PromotionsInterface{
	private Integer hasIngrediantID;
	private Integer hasntIngrediantID;
	private Double discount;
	
	public HasXAndHasntY(Ingredients hasIngrediant, Ingredients hasntIngrediant, Double discount) throws Exception {
		validate(hasIngrediant, hasntIngrediant, discount);
		
		this.hasIngrediantID = hasIngrediant.getId();
		this.hasntIngrediantID = hasntIngrediant.getId();
		this.discount = discount;
	}

	@Override
	public void applyPromotion(Sandwichs sandwich) {
		if(		 sandwich.getIngredients().containsKey(hasIngrediantID) 
			&& ! sandwich.getIngredients().containsKey(hasntIngrediantID)
		){
			sandwich.addDiscount(discount);
		}
	}
	
	private void validate(Ingredients hasIngrediant, Ingredients hasntIngrediant, Double discount) throws Exception{
		if(hasIngrediant == null || hasntIngrediant == null || discount == null){throw new Exception("Parameters can't be equals to NULL");}
		if(hasIngrediant == hasntIngrediant){throw new Exception("Take can't be less than or equal to than 0");}
		
		if(discount  <= 0 )	{throw new Exception("Pay can't be more than take");}
		if(discount  >= 100 )	{throw new Exception("Pay can't be more than take");}
	}
}
