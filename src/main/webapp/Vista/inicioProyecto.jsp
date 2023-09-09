
<%@page import="Modelos.Campo"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelos.Lote"%>
<%@page import="Controladores.ControladorLote"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="javax.xml.xpath.XPathEvaluationResult"%>
<%@page import="java.util.regex.Pattern"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	
<form action="http://localhost:9999/appWebTPF/regNewProyect" method="get">

	<jsp:include page = "navBar.jsp" ></jsp:include>
	
	<jsp:include page = "seleccionCampo.jsp" ></jsp:include>
	
	
	<script src="./JS/ajaxPetLotesDeCampoParaSelect.js"></script>
	<jsp:include page = "seleccionLote.jsp" ></jsp:include>
	
	
	<script src="./JS/AjaxPeticionCultivosParaProy.js"></script>
	<div class="contentSelectCultivo">
		<label class="contentSelectCultivo-label">Seleccione un proyecto de cultivo:</label>
		<select class="contentSelectCultivo-select" name = "cultivoSelect" value="cultivos"></select>
	</div>
	
	<script src="./JS/actualizacionFormInicioProyecto.js"></script>
	
	
	<div class ="contentCarga" > 
		<input type="submit" value="Iniciar Proyecto">
	</div>

</form>

	
	

	

</body>
</html>