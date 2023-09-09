<%@page import="Controladores.ControladorLote"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.sun.tools.sjavac.comp.dependencies.NewDependencyCollector"%>
<%@page import="Modelos.EstadoCampo"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="javax.xml.xpath.XPathEvaluationResult"%>
<%@page import="java.util.regex.Pattern"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Resultado registro de lote</title>
</head>

	<% 
		String Mj,numeroSuelo = "none";
		String numCampo = "none";
		Pattern patron;
		Matcher matcher;
		
		float superficie;
	
		String suelo = (String)request.getParameter("sueloSelect");
		String campo = (String)request.getParameter("campoSelect");
		String suprficieLote = (String)request.getParameter("superficieLote");
	
		if(suelo.replaceAll(" ", "").length() == 0 ||  suprficieLote.replaceAll(" ", "").length() == 0 || campo.replaceAll(" ", "").length() == 0)
			Mj = "No a seleccionado o rellenado alguno de lo items ¡Es obligatrio!";
		else
		{
			patron = Pattern.compile("Num ([0-9]+)",Pattern.CASE_INSENSITIVE);
			matcher = patron.matcher(suelo);
			if(!matcher.find())
				Mj = "Error no se a podido obtener el numero del suelo";
			else{
				numeroSuelo = matcher.group(1);
				matcher = patron.matcher(campo);
				if(!matcher.find())
					Mj = "Error no se a podido obtener el numero del campo";
				else{
					
					numCampo = matcher.group(1);
						
					int result;
				
					try{
						
						superficie = Float.parseFloat(suprficieLote);
						ControladorLote CL = new ControladorLote();
						result = CL.agregarLote(superficie, Integer.parseInt(numCampo), Integer.parseInt(numeroSuelo));
						
						if(result == -4)
							Mj = "Se a producido un error al intentar agregar el lote[Nro. Error -4]";
						else if(result == -1)
							Mj = "Se a producido un error al intentar establecer la conexion con la BD [Nro. Error -1]";
						else
							Mj = "La operacion de registro de lote a sido realizada con exito";
						
					}catch(Exception E){
						Mj = "No se a podido realizar la operacion correctamente. 'Verifique haber ingresado los datos correctos' ["+E.getMessage()+"]";
					}
					
				}
					
			}
		}
	

	%>
	<body>
		
		<div class ="content">
				<p> <% out.print("<span>" + Mj + "</span>");%> </p>
				<a href="http://localhost:9999/appWebTPF/Vista/registrarNewLote.jsp" style=""> <button >Aceptar</button> </a>
			</div>
		
	</body>
</html>