package br.com.arua.hamburgeria.conf;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.arua.hamburgeria.dao.IngredientsDAO;
import br.com.arua.hamburgeria.dao.SandwichsDAO;
import br.com.arua.hamburgeria.dao.controllers.IngredientsDaoController;
import br.com.arua.hamburgeria.dao.controllers.SandwichsDaoController;
import br.com.arua.hamburgeria.models.Ingredients;
import br.com.arua.hamburgeria.models.Sandwichs;
import br.com.arua.hamburgeria.promotions.HasXAndHasntY;
import br.com.arua.hamburgeria.promotions.PromotionHandler;
import br.com.arua.hamburgeria.promotions.TakeXpayY;

@Component
public class DataBaseConf {

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {

		try {

			IngredientsDaoController ingredientsDaoController = new IngredientsDaoController(new IngredientsDAO());
			SandwichsDaoController sandwichsDaoController = new SandwichsDaoController(new SandwichsDAO());

			Ingredients lettuce = new Ingredients("Alface", 0.40);
			Ingredients bacon = new Ingredients("Bacon", 2.00);
			Ingredients hamburger = new Ingredients("Hamburguer de carne", 3.00);
			Ingredients egg = new Ingredients("Ovo", 0.80);
			Ingredients cheese = new Ingredients("Queijo", 1.50);

			ingredientsDaoController.save(lettuce);
			ingredientsDaoController.save(bacon);
			ingredientsDaoController.save(hamburger);
			ingredientsDaoController.save(egg);
			ingredientsDaoController.save(cheese);

			Sandwichs XBacon = new Sandwichs("X-Bacon").add(ingredientsDaoController.getIngredientByID(bacon.getId()))
					.add(ingredientsDaoController.getIngredientByID(hamburger.getId()))
					.add(ingredientsDaoController.getIngredientByID(cheese.getId()));

			Sandwichs XBurger = new Sandwichs("X-Burger")
					.add(ingredientsDaoController.getIngredientByID(hamburger.getId()))
					.add(ingredientsDaoController.getIngredientByID(cheese.getId()));

			Sandwichs XEgg = new Sandwichs("X-Egg").add(ingredientsDaoController.getIngredientByID(egg.getId()))
					.add(ingredientsDaoController.getIngredientByID(hamburger.getId()))
					.add(ingredientsDaoController.getIngredientByID(cheese.getId()));

			Sandwichs XEggBacon = new Sandwichs("X-Egg Bacon")
					.add(ingredientsDaoController.getIngredientByID(egg.getId()))
					.add(ingredientsDaoController.getIngredientByID(bacon.getId()))
					.add(ingredientsDaoController.getIngredientByID(hamburger.getId()))
					.add(ingredientsDaoController.getIngredientByID(cheese.getId()));

			sandwichsDaoController.save(XBacon);
			sandwichsDaoController.save(XBurger);
			sandwichsDaoController.save(XEgg);
			sandwichsDaoController.save(XEggBacon);

			PromotionHandler.gi()
				.addPromotion(new TakeXpayY(ingredientsDaoController.getIngredientByID(hamburger.getId()), 3, 2,ingredientsDaoController));

			PromotionHandler.gi()
				.addPromotion(new TakeXpayY(ingredientsDaoController.getIngredientByID(cheese.getId()), 3, 2,ingredientsDaoController));

			PromotionHandler.gi()
				.addPromotion(new HasXAndHasntY(ingredientsDaoController.getIngredientByID(lettuce.getId()),
					ingredientsDaoController.getIngredientByID(bacon.getId()), 10.0));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
