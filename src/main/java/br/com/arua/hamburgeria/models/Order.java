package br.com.arua.hamburgeria.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Order {
	
	private Map<Integer,Sandwichs> sandwichs;
	
	public Order() {
		sandwichs = new HashMap<>();
	}
	
	public Map<Integer,Sandwichs> getSandwichs() {
		return Collections.unmodifiableMap(sandwichs);
	}

	public void addSandwich(Sandwichs sandwich) {
		Integer laxIndex = sandwichs.keySet().stream().max(Integer::compareTo).orElse(0);
		sandwichs.put(++laxIndex ,sandwich);
	}
	
	public void removeSandwich(Integer id) {
		sandwichs.remove(id);
	}

	public Sandwichs getSandwich(Integer id) {
		return sandwichs.get(id);
	}
	
	
}
