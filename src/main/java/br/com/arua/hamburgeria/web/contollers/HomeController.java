package br.com.arua.hamburgeria.web.contollers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.arua.hamburgeria.dao.controllers.IngredientsDaoController;
import br.com.arua.hamburgeria.dao.controllers.SandwichsDaoController;
import br.com.arua.hamburgeria.models.Ingredients;
import br.com.arua.hamburgeria.models.Order;
import br.com.arua.hamburgeria.models.Sandwichs;

@Controller
@RequestMapping("/")

public class HomeController {

	SandwichsDaoController sandwichDao;
	IngredientsDaoController ingredientsDao;
	HttpSession session;
	
	@Autowired
	public HomeController(SandwichsDaoController sandwichDao,IngredientsDaoController ingredientsDao , HttpSession session) {
		this.sandwichDao = sandwichDao;
		this.ingredientsDao = ingredientsDao;
		this.session = session;
	}
		
	@RequestMapping("")
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("cardapio");	
		modelAndView.addObject("sandwichs",sandwichDao.getAllSandwichs());
		modelAndView.addObject("order",session.getAttribute("order"));
		modelAndView.addObject("ingredients",ingredientsDao.getAllIngredients());
		
		
		return modelAndView;
	}
	
	@RequestMapping("buyCuston")
	public void buyCuston(HttpServletResponse response) {
		Sandwichs sandwich = (Sandwichs)session.getAttribute("custon");
		
		Order order = (Order)session.getAttribute("order");
		order.addSandwich(sandwich);
		
		response.setStatus(HttpServletResponse.SC_OK);
		sendASJson(response, order);
	} 
		
	@RequestMapping("orderSandwich")
	public void orderSandwich(Integer id, HttpServletResponse response) {
		Sandwichs sandwich = sandwichDao.getSandwichByid(id);
		
		Order order = (Order)session.getAttribute("order");
		order.addSandwich(sandwich);
		
		response.setStatus(HttpServletResponse.SC_OK);
		sendASJson(response, order);
	} 
	
	@RequestMapping("purchase")
	public void remove(HttpServletResponse response) {
		session.removeAttribute("order");
		session.setAttribute("order",new Order());
		
		response.setStatus(HttpServletResponse.SC_OK);
		
	}
	
	@RequestMapping("remove")
	public void remove(Integer id, HttpServletResponse response) {
		
		Order order = (Order)session.getAttribute("order");
		order.removeSandwich(id);
		
		response.setStatus(HttpServletResponse.SC_OK);
		sendASJson(response, order);		
	} 
	
	@RequestMapping("custonItenAddIngredient")
	public void custonItenAddIngredient(Integer id,HttpServletResponse response) {
		Sandwichs sandwich = (Sandwichs)session.getAttribute("custon");
		Ingredients ingredient = ingredientsDao.getIngredientByID(id);
		sandwich.add(ingredient);
		
		response.setStatus(HttpServletResponse.SC_OK);
		sendASJson(response, sandwich);		
	}
	
	@RequestMapping("custonItenRemoveIngredient")
	public void custonItenRemoveIngredient(Integer id,HttpServletResponse response) {
		Sandwichs sandwich = (Sandwichs)session.getAttribute("custon");
		Ingredients ingredient = ingredientsDao.getIngredientByID(id);
		sandwich.remove(ingredient);
		
		response.setStatus(HttpServletResponse.SC_OK);
		sendASJson(response, sandwich);		
	}
	
	
	@RequestMapping("getCustonIten")
	public void getCustonIten(HttpServletResponse response) {
		Sandwichs sandwich = new Sandwichs("Personalizado");
		
		session.removeAttribute("custon");
		session.setAttribute("custon", sandwich);
		
		response.setStatus(HttpServletResponse.SC_OK);
		sendASJson(response, sandwich);			
	}

	private void sendASJson(HttpServletResponse response, Object obj) {
		try {
			response.setContentType("application/json");
	        String json = new Gson().toJson(obj);
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	

	



}
