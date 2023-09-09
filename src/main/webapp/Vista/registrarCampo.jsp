<%@page import="Modelos.Lote"%>
<%@page import="java.util.ResourceBundle.Control"%>
<%@page import="org.hibernate.transform.AliasToEntityMapResultTransformer"%>
<%@page import="javax.persistence.metamodel.SetAttribute"%>
<%@page import="Modelos.Campo"%>
<%@page import="java.io.Console"%>
<%@page import="org.hibernate.HibernateException"%>
<%@page import="java.util.*"%>
<%@page import="Controladores.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
		<title>Registro Campo</title>
		<style  type ="text/css"> <%@include file="./estilosCSS/normalize.css" %> </style>
		<style  type ="text/css"> <%@include file="./estilosCSS/estilosRegistrarCampo.css" %> </style>
</head>


<body>
		
	<jsp:include page = "navBar.jsp" ></jsp:include>
		
	<div class="section">
		<form action="resultadoOperacion/resultOperationRegCampo.jsp" method="get">
			<h1>Registro Campo</h1>
			<div class="section-form-contentInputs">
				<label>Ingrese la direccion del campo:</label>	
				<input type="text" name="direccion" placeholder="Argentina Posadas AV.Lopes y planes">
			</div>
			<hr>
				<p><span>NOTA:</span>El estado del campo sera "Creado" inicialmente</p>
			<hr>
			<div class="section-form-cargar">
				<a href="menuPrincipal.jsp"><input type="button" value ="Volver"></a>
				<input type="submit" value="Registrar" name="btnRegistrar">
			</div>	
		</form>
	</div>	
	
</body>


</html>