document.addEventListener("DOMContentLoaded",inicializarEventos,true);
	
function inicializarEventos(){	

	document.querySelector(".form-btnCancelProyect").addEventListener("click",realizarPeticionAjax,true);
	
	function realizarPeticionAjax(e){
		e.preventDefault();
		let motivoCancelar = document.querySelector(".inputMotivoCancelar").value;
		let datosEnviar = '{"lotesSelect":'+ lote +',"motivCancel":'+ motivoCancelar +'}';
		peticionAjax(datosEnviar);
	}
	
	
	function peticionAjax(data){
		
		let request = new XMLHttpRequest();
		request.open("POST","http://localhost:9999/appWebTPF/operationCancalerProyecto",true);
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
		
		let contenedorInforme = document.querySelector(".contnetInfResult");
		let documentFragment = document.createDocumentFragment();
		
		
			// Limpa el objeto documentFragment de elementos anteriormente insertados
			while(documentFragment.firstChild != null){
				 	documentFragment.removeChild(documentFragment.firstChild);
			}
			
			//Limpia el los elementos insertodos con documentFragment en contenedor_Select del DOM
			while(contenedorInforme.firstChild != null){
				 	contenedorInforme.removeChild(contenedorInforme.firstChild);
			}
		
		try{
		
		let ResultJSONObjetos = JSON.parse(respuestaJSON);
			
			let label = document.createElement("label");
			label.innerText = ResultJSONObjetos[0].mj;
			
			if(ResultJSONObjetos[0].num == "-1")
				label.setAttribute("style","background-color:red");
			else
				label.setAttribute("style","background-color:green");
				
		documentFragment.appendChild(label);
		contenedorInforme.appendChild(documentFragment);
		
		}catch(err){
			alert("Se a producido un problema en la respuesta del servido Vuelva a intentarlo [Error "+ err +" ]");
		}
		
	}

}
