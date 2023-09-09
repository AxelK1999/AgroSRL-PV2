<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@page import="java.util.*"%>
<%@page import="Modelos.Suelo"%>
<%@page import="Controladores.ControladorCultivos"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

			<label>Seleccione el tipo de suelo</label>
			<select name="sueloSelect">
				<%
					ControladorCultivos C_Cultivo = new ControladorCultivos();	
					
					/*List sueloList = C_Cultivo.consultar(new Suelo()," ");
					
					Iterator it = sueloList.iterator();
					while(it.hasNext()){
						Suelo suelo = (Suelo)it.next();
						out.print("<option>"+ suelo.getCaractersticasSuelo()+"  Num "+ suelo.getNroSuelo()+"</option>");
					}*/
					
					
					List<Suelo> sueloList = C_Cultivo.consultarTodoSuelo();
					for(Suelo S : sueloList){
						out.print("<option>"+ S.getCaractersticasSuelo()+"  Num "+ S.getNroSuelo()+"</option>");
					}
					
					
				%>
			</select>

</body>
</html>