package br.com.arua.hamburgeria.promotions;

import java.util.ArrayList;
import java.util.List;

import br.com.arua.hamburgeria.interfaces.PromotionsInterface;
import br.com.arua.hamburgeria.models.Sandwichs;

public class PromotionHandler {
	private List<PromotionsInterface> promotions = new ArrayList<>();
	
	private static PromotionHandler instance = new PromotionHandler();
	
	private PromotionHandler(){};
	
	public void addPromotion(PromotionsInterface promotion){
		promotions.add(promotion);
	}
	
	public void applyPromotions(Sandwichs sandwich){
		promotions.forEach(promo -> promo.applyPromotion(sandwich));
	}

	public static PromotionHandler gi() {
		return instance;
	}
	
}
