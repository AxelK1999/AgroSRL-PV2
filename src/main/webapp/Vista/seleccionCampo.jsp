<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 
<%@page import="java.util.*"%>
<%@page import="Modelos.Campo"%>
<%@page import="Controladores.ControladorCampo"%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
		<style>
			
			
		
			.contentSelectCampo{
				display: flex;
				justify-content: center;
				
			}
		
			.contentSelectCampo-label{
				margin:auto 10px;
			}
			
		</style>

<title>Insert title here</title>
</head>
	
		<div class="contentSelectCampo">
			<label class="contentSelectCampo-label">Seleccione el Campo</label>
			<select name="campoSelect" class = "campoSelect">
				<%/*
					ControladorCampo CC = new ControladorCampo();
					List camposList = CC.consultar(new Campo()," ");
				
					Iterator iterador = camposList.iterator();
					while(iterador.hasNext()){
						Campo campo = (Campo)iterador.next();
						out.print("<option>"+ campo.getDireccion() +"  Num "+campo.getNroCampo()+"</option>");
					}*/
					
					ControladorCampo CC = new ControladorCampo();
					List<Campo> camposList = CC.consultarCampos();
					for(Campo C : camposList){
						out.print("<option>"+ C.getDireccion() +"  Num "+C.getNroCampo()+"</option>");
					}
					
				%>
			</select>	
		</div>	
	
</html>