document.addEventListener("DOMContentLoaded",inicializarEventos,true);
	
function inicializarEventos(){	

	document.querySelector(".btnVerProyects").addEventListener("click",realizarPeticionAjax,true);
	
	function realizarPeticionAjax(e){
		e.preventDefault();
		let lote = document.querySelector(".contentSelectLote-select").value;
		peticionAjax(lote);
	}
	
	function peticionAjax(data){
		
		let request = new XMLHttpRequest();
		request.open("POST","http://localhost:9999/appWebTPF/obtenerListaProyectos",true);//TODO
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		
		request.onreadystatechange = function(){ //para cada evento de estados de la respuesta
			
			//respuesta exitosa
			if(request.readyState == 4 && request.status == 200){
				
				let respuesta = request.responseText;
				resetForm(respuesta);
				
			}else if(request.readyState == 4 && request.status != 200){
				
				let mj = request.responseText;
				alert("Se a producido un problema al obtener una respuesta del servidor ["+ mj +"]");
				
			}	
		};
			
		request.send("inf="+data);
	}
	
	function resetForm(respuestaJSON){
		let filasResultados = document.querySelector(".table-filasResultados");
		let documentFragment = document.createDocumentFragment();
		
		
			// Limpa el objeto documentFragment de elementos anteriormente insertados
			while(documentFragment.firstChild != null){
				 	documentFragment.removeChild(documentFragment.firstChild);
			}
			
			//Limpia el los elementos insertodos con documentFragment en contenedor_Select del DOM
			while(filasResultados.firstChild != null){
				 	filasResultados.removeChild(filasResultados.firstChild);
			}
		
		
		//-----------------    TODO -----------------------
		
		
		try{
		
		let ResultJSONObjetos = JSON.parse(respuestaJSON);
		console.log(respuestaJSON);
		if(ResultJSONObjetos[0].num == "1"){
			
			for(let i = 1; i < ResultJSONObjetos.length; i++ ){
				
				let fila = document.createElement("tr");
				let elementCol1 = document.createElement("th");
				let elementCol2 = document.createElement("th");
				let elementCol3 = document.createElement("th");
				let elementCol4 = document.createElement("th");
				let elementCol5 = document.createElement("th");
				let elementLink = document.createElement("a");
				
				elementCol1.innerHTML = ResultJSONObjetos[i].nroProyecto;
				fila.appendChild(elementCol1);
				elementCol2.innerHTML = ResultJSONObjetos[i].estado;
				fila.appendChild(elementCol2);
				elementCol3.innerHTML = ResultJSONObjetos[i].motivoCancelar;
				fila.appendChild(elementCol3);
				elementCol4.innerHTML = ResultJSONObjetos[i].cultivo;
				fila.appendChild(elementCol4);
				console.log("la cccc");
				elementLink.setAttribute("href","http://localhost:9999/appWebTPF/obtenerTablaDetalles?proyecto="+ResultJSONObjetos[i].nroProyecto);
				elementLink.innerHTML = "Ver Detalle";
				elementCol5.appendChild(elementLink);
				fila.appendChild(elementCol5);
				
				documentFragment.appendChild(fila);
				
			}
			
			filasResultados.appendChild(documentFragment);
			
		}
		
	}catch(err){
		alert("Se a producido un problema en la respuesta del servido Vuelva a intentarlo [Error "+ err +" ]");
	}
		
	}

}