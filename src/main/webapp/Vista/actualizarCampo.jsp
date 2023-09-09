<%@page import="Modelos.EstadoCampo"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Modelos.Campo"%>
<%@page import="Controladores.ControladorCampo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<style  type ="text/css"> <%@include file="./estilosCSS/actualizarCampo.css" %> </style>

</head>
	<body>
		
		<jsp:include page = "navBar.jsp" ></jsp:include>
		
		<div class="contentAct">
		
		
		<form action = "resultadoOperacion/resultOperationActCampo.jsp" method="get">
			
			<div class = 'titulo'><label>Actualizacion de Campo</label></div>
					
			<jsp:include page = "seleccionCampo.jsp" ></jsp:include> 
		
			<div class = 'input-Estado'>
			
				<label>Ingrese el estado del campo:</label>
			
				<select name="newEstado">
					<%
					  /*
						ControladorCampo C_C = new ControladorCampo();
						List estadosList = C_C.consultar(new EstadoCampo()," ");
					
						Iterator it = estadosList.iterator();
						while(it.hasNext()){
							EstadoCampo estado = (EstadoCampo)it.next();
							out.print("<option>"+ estado.getNombreEstado() +"  Num "+ estado.getIdEstado() +"</option>");
						}*/
						
						ControladorCampo CC = new ControladorCampo();
						List<EstadoCampo> estadocampoList = CC.consultarTodoEstados();
						for(EstadoCampo EC : estadocampoList){
							out.print("<option>"+ EC.getNombreEstado() +"  Num "+ EC.getIdEstado() +"</option>");
						}
					
					%>
				</select>
			</div>
			
			<div class = 'input-Direccion'>
			
				<label>Ingrese la nueva direccion(opcional)</label>
				<input type="text" name="newDireccion" maxlength="40">
			</div>
			
			
			<div class="contentAct-btnsCargar">
			 
			  <a href="http://localhost:9999/appWebTPF/Vista/menuPrincipal.jsp"><button type="button">Volver</button></a>
			  <input type="submit" value ="Actualizar"> 
			
			</div>
			
					
		</form>
		
		</div>
		
	</body>
</html>