$(document).ready(
		function() {
			
			alertify.set('notifier','position', 'top-right');
			
			$('#order').dataTable(
					{
						"ordering" : false,
						"info" : false,
						"searching" : false,
						"paging" : false,
						"language" : {
							"lengthMenu" : 'Mostar <select>'
									+ '<option value="10">10</option>'
									+ '<option value="20">20</option>'
									+ '<option value="25">25</option>'
									+ '</select> Notas',
							"sInfo" : "_START_ - _END_ de _TOTAL_ ",
							"sInfoEmpty" : ""
						}
					});
			

			// Get the <span> element that closes the modal
		    // When the user clicks on <span> (x), close the modal
			document.getElementsByClassName("close")[0].onclick = function() {closeModal()}
		});

function closeModal(){
	alertify.confirm(
			'Canselar compra', 
			'Deseja Realmente canselar esse item?',
			function(){ 	
				var modal = document.getElementById('myModal');
				modal.style.display = "none";
			},
			function(){ }
	);	
}

function buyCuston(){
	$.get("buyCuston" , loadOrder);
	var modal = document.getElementById('myModal');
	modal.style.display = "none";
}

function purchase() {
	$.get("purchase",function(responce){
		alertify.success("Compra realizada com sucesso");
		loadOrder(responce);
	});
}

function buy(id) {
	$.get("orderSandwich?id=" + id, loadOrder);
}

function remove(id) {
	alertify.confirm(
			'Excluir item', 
			'Deseja Realmente excluir esse item?',
			function(){ $.get("remove?id=" + id, loadOrder) },
			function(){ }
	);	
}

function loadOrder(responce) {
	var table = $('#order').DataTable();
	table.clear().draw();

	for (keys in responce.sandwichs) {
		var item = responce.sandwichs[keys]

		var row = "<b>" + item.name+  "</b><br>";
		var x = 0;
		
		for(ingredientID in item.ingredients){
			if(x> 0){row = row + ", 	";}
			row = row + (item.ingredients[ingredientID].quantity > 1 ? item.ingredients[ingredientID].quantity + ' X ' : ''  )  +  item.ingredients[ingredientID].name;
			x++;
		}
		
		table.row.add([
			row,
			item.value === undefined ? 'R$  ' : 'R$ ' + item.value,
			'<a href="#" onclick="remove(' + keys + ');">Remover</a>'
		]).draw()
	}

	for (keys in responce.sandwichs) {
		console.log(keys)
	}
}

function additional() {
	$.get("getCustonIten", loadAdditional);
}

function loadAdditional(responce){
    
    var sandwich 	=  responce;
    var value = sandwich.value != undefined ? sandwich.value : 0.00;
     
    var itens = $('*[id^="ingredient_"]');
    
    for(iten in itens ){
    	itens.html("0");
    }
    
    $('#additionalName').html('<p>' + sandwich.name + '</p>')
    $('#additionalValue').html('<p> R$ ' + value + '</p>')
	
    for(iten in sandwich.ingredients){
    	var ingredient = sandwich.ingredients[iten];
    	 $('#ingredient_'+ingredient.id+'_qtd').html(ingredient.quantity)
    }    
    
    
    var modal = document.getElementById('myModal');
    modal.style.display = "block";
}

function custonItenAddIngredient(id) {
	$.get("custonItenAddIngredient?id=" + id, loadAdditional);
}

function custonItenRemoveIngredient(id) {
	$.get("custonItenRemoveIngredient?id=" + id, loadAdditional);
}



