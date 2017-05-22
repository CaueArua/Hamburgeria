package br.com.arua.hamburgeria.models.factorys;

import br.com.arua.hamburgeria.models.Ingredients;

public class IngredientsLibrary {
	
	public static Ingredients getLettuce(){
		return new Ingredients("Alface", 0.40).setId(1);
	}

	public static Ingredients getBacon(){
		return new Ingredients("Bacon", 2.00).setId(2);
	}

	public static Ingredients getHamburger(){
		return new Ingredients("Hamburguer de carne", 3.00).setId(3);
	}

	public static Ingredients getEgg(){
		return new Ingredients("Ovo", 0.80).setId(4);
	}

	public static Ingredients getCheese(){
		return new Ingredients("Queijo", 1.50).setId(5);
	}




}


