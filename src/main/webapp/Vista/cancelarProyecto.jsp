
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page = "navBar.jsp" ></jsp:include>

	<div class = "form">
	
		<jsp:include page = "seleccionCampo.jsp" ></jsp:include>
	
		<script src="./JS/ajaxPetLotesDeCampoParaSelect.js"></script>
		<jsp:include page = "seleccionLote.jsp" ></jsp:include>
		
		
		<div class="contentMotivo">
			<label>Ingrese la motivo de cancelar el proyecto:</label>
			<input type="text" name="inputMotivoCancelar" class="inputMotivoCancelar">
		</div>
		
		
		<div class="contnetInfResult">
				
		</div>

		<button class="form-btnCancelProyect" type="button">Cancelar proyecto</button>

		<script src="./JS/AjaxPetCancelarProyecto.js"></script>
	
	</div>


</body>
</html>