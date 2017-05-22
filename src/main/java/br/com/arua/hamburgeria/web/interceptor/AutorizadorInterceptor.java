package br.com.arua.hamburgeria.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.arua.hamburgeria.models.Order;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		if (request.getSession().getAttribute("order") == null) {
			request.getSession().setAttribute("order", new Order());
		}
		
		return true;
		
	}
}