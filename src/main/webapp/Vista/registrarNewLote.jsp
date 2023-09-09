<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="Modelos.Suelo"%>
<%@page import="Controladores.ControladorCultivos"%>
<%@page import="java.util.*"%>
<%@page import="Modelos.Campo"%>
<%@page import="Controladores.ControladorCampo"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		
		<jsp:include page = "navBar.jsp" ></jsp:include>
		
		<form action="./resultadoOperacion/resultOperationRegLote.jsp" method="get">
				
			<jsp:include page = "seleccionCampo.jsp" ></jsp:include>
			
			<jsp:include page = "seleccionSuelo.jsp" ></jsp:include>
			
			<label>Ingrese la superficie del lote( en has )</label>
			<input type="text" name="superficieLote" class ="inputSuperficie" maxlength="8">
			
			<input type="submit" name="btnRegLote" value="Cargar Lote">
			
		</form>
		
		

	</body>
</html>