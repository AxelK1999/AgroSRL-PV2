<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

		<style  type ="text/css"> <%@include file="./estilosCSS/normalize.css" %> </style>
		<style  type ="text/css"> <%@include file="./estilosCSS/estilosNavBar.css" %> </style>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" data-auto-a11y="true"></script>

</head>
	<body>

		<div class="navBar">
			<a href="menuPrincipal.jsp"><i class="fas fa-home"></i>Inicio</a>
			
			<div class="navBar-dropDown">
				<button class="navBar-dropDown-btn">Campos</button>
				<div class="navBar-dropDown-btn-itemsContent">
					<a href="http://localhost:9999/appWebTPF/Vista/registrarCampo.jsp">Registrar nuevo campo</a>
					<a href="http://localhost:9999/appWebTPF/Vista/actualizarCampo.jsp">Actualizar un campo</a>
					<a href="http://localhost:9999/appWebTPF/Vista/mostrarCampo.jsp">Ver campos</a>
				</div>
			</div>	
			<div class="navBar-dropDown">
				<button class="navBar-dropDown-btn">Lotes</button>
				<div class="navBar-dropDown-btn-itemsContent">
					<a href="http://localhost:9999/appWebTPF/Vista/registrarNewLote.jsp">Registrar nuevo lote</a>
					<a href="">Actualizar un lote</a>
					<a href="">Unir dos lotes</a>
					<a href="">Separar lote</a>
					<a href="">Ver lotes</a>
				</div>
			</div>
			
			<div class="navBar-dropDown">
				<button class="navBar-dropDown-btn">Proyectos</button>
				<div class="navBar-dropDown-btn-itemsContent">
					<a href="http://localhost:9999/appWebTPF/Vista/inicioProyecto.jsp">Iniciar nuevo proyecto</a>
					<a href="http://localhost:9999/appWebTPF/Vista/cancelarProyecto.jsp">Cancelar proyecto</a>
					<a href="">Regitrar Incidencia</a>
					<a href="">Finalizar laboreo</a>
					<a href="http://localhost:9999/appWebTPF/Vista/verProyectos.jsp">Ver proyectos</a>
				</div>
			</div>
			
			<div class="navBar-dropDown">
				<button class="navBar-dropDown-btn">Propiedades</button>
				<div class="navBar-dropDown-btn-itemsContent">
					<a href="">Regitrar nuevo cultivo</a>
					<a href="">Registrar tipo de suelo</a>
					<a href="">Registrar nuevo estado</a>
				</div>
			</div>
			
		</div>



	</body>
</html>