<%@page import="com.sun.tools.sjavac.comp.dependencies.NewDependencyCollector"%>

<%@page import="Modelos.EstadoCampo"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="javax.xml.xpath.XPathEvaluationResult"%>
<%@page import="java.util.regex.Pattern"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@page import="Controladores.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	
		<%
			
			String Mj,numeroEstado = "none";
			String numCampo = "none";
			Pattern patron;
			Matcher matcher;
			
			String newDireccion = (String)request.getParameter("newDireccion");
			String estadoCampo = (String)request.getParameter("newEstado");
			String campoModificar = (String)request.getParameter("campoSelect");//TODO
			
			if(newDireccion == null || newDireccion.replaceAll(" ", "").length() == 0){
				newDireccion = campoModificar;
				newDireccion = newDireccion.replaceAll("\\d+", " ");
				newDireccion = newDireccion.replaceAll("Num", " ");
			}
			
			patron = Pattern.compile("Num ([0-9]+)",Pattern.CASE_INSENSITIVE);
			
			if(campoModificar == null)
				Mj = " Operacion fallida [Numero de campo nulo] ";
			else{
				
				matcher = patron.matcher(campoModificar);
				
				if(!matcher.find())
					Mj = "Error no se a podido obtener el numero del campo";
				else{
				
					numCampo = matcher.group(1);
					matcher = patron.matcher(estadoCampo);
					
					if(!matcher.find())
						Mj = "Error no se a podido obtener el numero del estado campo";
					else{
						
						numeroEstado = matcher.group(1);
						ControladorCampo controladorCampo = new ControladorCampo();
						
						EstadoCampo E = new EstadoCampo();

						E.setIdEstado(Integer.parseInt(numeroEstado));
							
						if(controladorCampo.actualizarCampo(Integer.parseInt(numCampo),newDireccion,E) == 1)
							Mj = "La operacion de actualizacion a sido exitosa";
						else
							Mj = "No se a podido actualizar el Campo";
					}	
				}
			}
				
		%>

		<body>
			
			<div class ="content">
				<p> <% out.print("<span>" + Mj + "</span>");%> </p>
				<a href="../actualizarCampo.jsp" style=""> <button >Aceptar</button> </a>
			</div>
			
		</body>
</html>