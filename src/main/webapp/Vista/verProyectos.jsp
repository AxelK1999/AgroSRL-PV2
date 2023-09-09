<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<style  type ="text/css"> <%@include file="./estilosCSS/estilosVerProyectos.css" %> </style>
	<title>Insert title here</title>
</head>
<body>
		
		
		<jsp:include page = "navBar.jsp" ></jsp:include>
	
	<div class="section">	
		<jsp:include page = "seleccionCampo.jsp" ></jsp:include>
	
	
		<script src="./JS/ajaxPetLotesDeCampoParaSelect.js"></script>
		<jsp:include page = "seleccionLote.jsp" ></jsp:include>
	
		
		<button type="button" class="btnVerProyects">Ver Poyectos</button>
		
		<div class="ContentResultConsultaProyect">
			
			<table>
				<thead class="table-titulosColumnas">
					<tr>
						<th>Nro. Proyecto</th>
						<th>Estado</th>
						<th>Motivo Cancelacion</th>
						<th>Cultivo</th>
					</tr>
				</thead>
				<tbody class="table-filasResultados"></tbody>
			</table>
			
		</div>
		
		<script src="./JS/AjaxPetListaProyectosDelote.js"></script>
	</div>
</body>
</html>