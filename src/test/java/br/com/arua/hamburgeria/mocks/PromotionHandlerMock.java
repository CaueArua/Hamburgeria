package br.com.arua.hamburgeria.mocks;

import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.arua.hamburgeria.interfaces.PromotionsInterface;
import br.com.arua.hamburgeria.models.Sandwichs;
import br.com.arua.hamburgeria.promotions.PromotionHandler;

public class PromotionHandlerMock{
	private List<PromotionsInterface> promotions = new ArrayList<>();
	
	public PromotionHandler getMock() {
		PromotionHandler mock = mock(PromotionHandler.class);
		
		doAnswer(invocation -> {
			PromotionsInterface promotion = (PromotionsInterface) invocation.getArguments()[0];
			addPromotion(promotion);
			return null;
		}).when(mock).addPromotion((PromotionsInterface) notNull());
		
		doAnswer(invocation -> {
			Sandwichs sandwich = (Sandwichs) invocation.getArguments()[0];
			applyPromotions(sandwich);
			return null;
		}).when(mock).applyPromotions((Sandwichs) notNull());
		
		return mock;
		
	}
	
	
	
	public void addPromotion(PromotionsInterface promotion){
		promotions.add(promotion);
	}
	
	public void applyPromotions(Sandwichs sandwich){
		promotions.forEach(promo -> promo.applyPromotion(sandwich));
	}

	
}
