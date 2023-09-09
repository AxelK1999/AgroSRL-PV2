<%@page import="Controladores.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<style  type ="text/css"> <%@include file="../estilosCSS/estiloResultOperationReg.css" %> </style>
	<style  type ="text/css"> <%@include file="../estilosCSS/normalize.css" %> </style>
</head>
		
		<%
			ControladorCampo controladorCampo = new ControladorCampo();
			int resultOperation = controladorCampo.agregarCampo(request.getParameter("direccion"), 1);
			String Mj;	 
			if(resultOperation == -4 || resultOperation == -1) 
				 Mj = "La operacion de registro de un campo a fallado [Error: "+ resultOperation +" ]";
			else if(resultOperation == -10)
				Mj = " ¡¡ Ya exsiste un campo con esa direccion registrado !! ";
			else if(resultOperation == -11)
				Mj = "La operacion fallo [Direccion es demasiado extensa]";
			else
				Mj = "El registro de un campo a sido realizado con exito ";
		%>

		<body>
			
			<div class ="content">
				<p  ><% out.print("<span>"+Mj+"</span>"); %></p>
				<a href="../registrarCampo.jsp" style=""> <button >Aceptar</button> </a>
			</div>
			
		</body>
		
</html>