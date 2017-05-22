package br.com.arua.hamburgeria.models.factorys;

import br.com.arua.hamburgeria.models.Sandwichs;
import static br.com.arua.hamburgeria.models.factorys.IngredientsLibrary.*;


public class SandwichFactory {

	private Sandwichs sandwich;

	public SandwichFactory(String name) {
		sandwich = new Sandwichs(name);
	}

	public SandwichFactory whithLettuce(int qtd) {
		sandwich.add(getLettuce(), qtd);
		return this;
	}

	public SandwichFactory whithBacon(int qtd) {
		sandwich.add(getBacon(), qtd);
		return this;
	}

	public SandwichFactory whithHamburger(int qtd) {
		sandwich.add(getHamburger(), qtd);
		return this;
	}

	public SandwichFactory whithEgg(int qtd) {
		sandwich.add(getEgg(), qtd);
		return this;
	}

	public SandwichFactory whithCheese(int qtd) {
		sandwich.add(getCheese(), qtd);
		return this;
	}

	public Sandwichs make() {
		return sandwich;
	}

	public SandwichFactory setName(String name) {
		sandwich.setName(name);
		return this;
	}

	public static Sandwichs getXBacon() {
		return new SandwichFactory("X-Bacon")
				.whithBacon(1)
				.whithHamburger(1)
				.whithCheese(1)
				.make();
	}

	public static Sandwichs getXBurger() {
		return new SandwichFactory("X-Burger")
				.whithHamburger(1)
				.whithCheese(1)
				.make();
	}

	public static Sandwichs getXEgg() {
		return new SandwichFactory("X-Egg")
				.whithEgg(1)
				.whithHamburger(1)
				.whithCheese(1)
				.make();
	}

	public static Sandwichs getXEggBacon() {
		return new SandwichFactory("X-Egg Bacon")
				.whithEgg(1)
				.whithBacon(1)
				.whithHamburger(1)
				.whithCheese(1)
				.make();
	}

}
