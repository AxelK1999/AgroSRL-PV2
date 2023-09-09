
document.addEventListener("DOMContentLoaded",inicio,true);

//TODO : al cargar la pagina por primera ves ya funcione

function inicio(){
	
	document.querySelector(".campoSelect").addEventListener("change",obtenerDatos,true)
	
	function obtenerDatos(e){
		e.preventDefault();
		let lote = document.querySelector(".campoSelect").value;
		peticionAJAX(lote);
	}
	
	
	function peticionAJAX(data){
		
		let request = new XMLHttpRequest();
		request.open("POST","http://localhost:9999/appWebTPF/obtenerListaLotesParaSelect",true);
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		
		request.onreadystatechange = function(){
			
			//respuesta exitosa
			if(request.readyState == 4 && request.status == 200){
				
				let respuesta = request.responseText;
				resetForm(respuesta);
				
			}else if(request.readyState == 4 && request.status != 200){
				
				let mj = request.responseText;
				alert("Se a producido un problema al obtener una respuesta del servidor ["+ mj +"]");
				
			}	
		};
			
		request.send("campoSelect="+data);
	}
	
	function resetForm(respuestaJSON){
		
		let contenedor = document.querySelector(".contentSelectLote");
		let contenedor_Select = document.querySelector(".contentSelectLote-select");
		let contenedor_label = document.querySelector(".contentSelectLote-label");
		let documentFragment = document.createDocumentFragment();
		
		try{
			
			// Limpa el objeto documentFragment de elementos anteriormente insertados
			while(documentFragment.firstChild != null){
				 	documentFragment.removeChild(documentFragment.firstChild);
			}
			
			//Limpia el los elementos insertodos con documentFragment en contenedor_Select del DOM
			while(contenedor_Select.firstChild != null){
				 	contenedor_Select.removeChild(contenedor_Select.firstChild);
			}
			
			let arrayResultJSONObjetos = JSON.parse(respuestaJSON);
			
			if(arrayResultJSONObjetos[0].num == "-1"){
				
				contenedor_Select.setAttribute("style","display:none");
				contenedor_label.innerText = arrayResultJSONObjetos[0].mj;
				
				
			}else{   //todo correcto => 
				
				contenedor_Select.setAttribute("style","display:block");
				contenedor_label.innerText = "Seleccione el lote para el proyecto:";
				
				
				for(let i = 1; i < arrayResultJSONObjetos.length ; i++ ){
					
					let newElement = document.createElement("option");
					newElement.innerHTML = arrayResultJSONObjetos[i].superficie + " Num "+ arrayResultJSONObjetos[i].nrolote;					
					documentFragment.appendChild(newElement);
				}
			
				contenedor_Select.appendChild(documentFragment);
				
			}
		
		}catch(err){
			contenedor_Select.setAttribute("style","display:none");
			contenedor_label.innerText  ="No se encuentran lotes registrados para el campo seleccionado [" + err +"]";
		}
		
	}
	
}

