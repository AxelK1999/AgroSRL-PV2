<%@page import="Modelos.Proyecto"%>
<%@page import="Modelos.Lote"%>
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
		<title>Agro A.R.S</title>
		<style  type ="text/css"> <%@include file="./estilosCSS/normalize.css" %> </style>
		<style  type ="text/css"> <%@include file="./estilosCSS/estilosMenuPrincipal.css" %> </style>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" data-auto-a11y="true"></script>
	</head>

	<body>
		
		<jsp:include page = "navBar.jsp" ></jsp:include>
		
	 	
	   <div class="section">
	   	
				<div class ="section-estadisticas">
				   <p>Campos</p>
				   <span> 
				   		 <% 
				  		 	ControladorCampo CC = new ControladorCampo() ;
						 	out.print(CC.numeroCampos(" "));
						  %>
				  </span>
				</div>
				
				<div class ="section-estadisticas">
				   <p>Lotes</p>
				   <span> <% 
							ControladorLote CL = new ControladorLote();
							out.print(CL.cantidadLotes());
						  %>
				  </span>
				</div>
				
				<div class ="section-estadisticas">
				   <p>Proyectos Activos</p>
				   <span><% 
				   			ControladorProyectosLote CP = new ControladorProyectosLote();
							out.print(CP.cantidadProyectosAct());
						  %>
				   </span>
				</div>
	   </div> 
	     
	   
	   <div class="footer">
	   
	   		
	   	<div class="footer-content">
	   		<ul class="footer-content-listaFooter">
	   			<li>Contacto:</li>
	   			<li>axelx33@hotmail.com</li>
	   			<li><a href="https://www.instagram.com/kaechele_axel/">Instagram</a></li>	
	   		</ul>
	   	</div>
	   		
	   	<div class="footer-content">	
	   		<ul class="footer-content-listaFooter">
	   			<li>Copyright 2022:</li>	
	   			<li>© Axel Agustin Kaechele</li>
	   			<li>© Mirko Ivan kczacouski</li>
	   		</ul>
	   	</div>	
	   		
	   	<div class="footer-content">		
	   		<ul class="footer-content-listaFooter">
	   			<li>Universidad:</li>		
	   			<li><a href="https://www.ugd.edu.ar/">UGD</a></li>
	   		</ul>
	   	</div>	
	   		
	   
	   </div>
	   
	
	</body>
</html>