<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Lanchonete</title>

<link href="<c:url value="/resources/css/alertify.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/cardapio.css" />" rel="stylesheet">

<script type="text/javascript" language="javascript"	src="resources/js/jquery.js"></script>
<script type="text/javascript" language="javascript"	src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript"	src="resources/js/alertify.min.js"></script>
<script type="text/javascript" language="javascript"	src="resources/js/cardapio.js"></script>




</head>
<body>
<div class="container">
 <div class="fixed">
 	<div> <h1 align="center"> Cardápio</h1></div>
	<table id="Menu" class="display" cellspacing="0" width="90%" border=1>
		<thead>
			<tr>
				<th></th>
				<th>Lanche</th>
				<th>valor</th>
				<th></th>
			</tr>
		</thead>
		<c:forEach items="${sandwichs}" var="sandwich">
			<tr>
				<td align="center">
					<img class="img" src="<c:url value="/resources/images/${sandwich.getName()}.jpg" />" alt=""> 
				</td>
				<td>${sandwich.name}<br>
				<c:forEach items="${sandwich.ingredients}" var="ingredient"
						varStatus="loop">
						<c:if test="${ingredient.value.quantity != 1}">${ingredient.value.quantity}</c:if>
						${ingredient.value.name}
						<c:if test="${!loop.last}">,</c:if>
					</c:forEach>
				</td>
				<td>R$ ${sandwich.value}</td>
				<td><a href="#" onclick="buy(${sandwich.ID});">Adicionar</a></td>
			</tr>
		</c:forEach>
			<tr>
				<td align="center">
					<img class="img" src="<c:url value="/resources/images/Construa.jpg" />" alt=""> 
				</td>
				<td>Personalizado<br>
				<td></td>
				<td><a href="#" onclick="additional();">Adicionar</a></td></td>
			</tr>
	</table>
</div>
  <div class="flex-item">
  
 	<div> <h1 align="center"> Pedido</h1></div>
	
	<table id="order" class="display" cellspacing="0" width="90%" border=1>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Valor</th>
				<th></th>
			</tr>
		</thead>
		<c:forEach items="${order.sandwichs}" var="sandwich">
			<tr>
				<td><b> ${sandwich.value.name} </b> : <br>
				<c:forEach items="${sandwich.value.ingredients}" var="ingredient"
						varStatus="loop">
						<c:if test="${ingredient.value.quantity != 1}">${ingredient.value.quantity} X </c:if>
		${ingredient.value.getName()}
		<c:if test="${!loop.last}">,</c:if>
					</c:forEach></td>
				<td>R$ ${sandwich.value.value}</td>
				<td><a href="#" onclick="remove(${sandwich.key});">Remover</a></td>
			</tr>
		</c:forEach>
		<tfoot>
			<tr>
				<th></th>
				<th></th>
				<th><button  onclick="purchase()">Efetuar Compra</button></th>
				
			</tr>
		</tfoot>
	</table>
	</div>
</div>




	
	<!-- Modal content -->
	<!-- The Modal -->
	<div id="myModal" class="modal">
	
	  <!-- Modal content -->
	  <div class="modal-content">
	    <div class="modal-header">
	      <span class="close">&times;</span>
	      <h2 id="additionalName"></h2>
	    </div>
	    <div class="modal-body">
	    	<table>
	    		<c:forEach items="${ingredients}" var="ingredient" varStatus="loop">
	    			<tr>
	    				<td> <div id="ingredient_${ingredient.id}_qtd">0</div> </td>
	    				<td> - ${ingredient.name} <td>
	    				<td> <a href="#" onclick="custonItenAddIngredient(${ingredient.id});">[+]</a> </td>
	    				<td> <a href="#" onclick="custonItenRemoveIngredient(${ingredient.id});">[-]</a> </td>
	    			</td>
				</c:forEach>	
	    	</table>
	    	
	    	
	    </div>
	    <div class="modal-footer">
	      <h3 id="additionalValue"></h3>
	      <button onclick="buyCuston()"> Confirmar Compra</button>
	    </div>
	  </div>

</div>
	
	
</body>
</html>