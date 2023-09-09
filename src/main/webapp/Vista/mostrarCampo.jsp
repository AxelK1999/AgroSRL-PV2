<%@page import="java.util.ArrayList"%>
<%@page import="DAO_Modelos.Dao_BD"%>
<%@page import="Modelos.EstadoCampo"%>
<%@page import="Modelos.Campo"%>
<%@page import="Controladores.ControladorCampo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>

	<style  type ="text/css"> <%@include file="./estilosCSS/estilosMostrarCampos.css" %> </style>
	<meta charset="ISO-8859-1">
	<title>Mis Campos</title>
	
</head>
 
  <body>
  
  		<jsp:include page = "navBar.jsp" ></jsp:include>
  	
  	<div class="section">
  		
  		<label class="labelListaCampos">Lista de campos registrados</label>
  	
		<table>
				<thead><tr> <th>Num. Campo</th> <th>Direccion</th> <th>Estado</th> <th>Num. Estado</th> <th>Num. Lotes</th> </tr></thead>
			
				<%
					
					//Nota: Para sebar que tipo retorna una consulta para realizar correctamente el casting, utilizar getClass().getNmae();
					
					//Alternativa (1): CampoYEstado --> es una fila [Columna] de la table resultante de la consulta sql
					  
					/*				
					ControladorCampo CC = new ControladorCampo(); 
					List<Object[]> camposList = (List<Object[]>)CC.consultaNativa();	
				
					Dao_BD BD = new Dao_BD();
					List<Object[]> camposList = BD.consultaNativa(new Campo(), "select * from campo as C inner join estadocampo as EC on C.fk_id_estado = EC.pk_id_estado");
					
					for( Object[] CampoYEstado : camposList){
						
						out.print(CampoYEstado[0].getClass().getName()+" -- "+CampoYEstado[1].getClass().getName()+" -- "+CampoYEstado[2].getClass().getName()+" -- "+CampoYEstado[3].getClass().getName()+" -- "+CampoYEstado[4].getClass().getName()+" -- ###");
						
						//out.print("<tr><th>"+ ((Campo)CampoYEstado[0]).getDireccion() +"</th> <th>"+ ((Campo)CampoYEstado[0]).getNroCampo() +"</th> <th>"+ ((EstadoCampo)CampoYEstado[1]).getNombreEstado() +"</th> <th>"+ ((EstadoCampo)CampoYEstado[1]).getIdEstado() +"</th> </tr>");
					}  
					
					*/
					
					
					//-----------------------------------------------------------------
					
					 //Alternativa (2): Retorna un lista de Campos Con sus Obejtos Estados cargador por el inner join {Excepto CC.cantidadLotesEnCampo Tipo long y accesos a fila colimna}
					ControladorCampo CC = new ControladorCampo(); 
					CC.actualizarEstadoTodoCampos();
					
					/*List<Object> camposList = (List<Object>)CC.consultar(new Campo(), "as C INNER JOIN C.estado ");
					
					
					Long cantidadLotes; //CC.cantidadLotesEnCampo --> retorna objeto de tipo Long
					
					for( Object CampoYEstado : camposList){
						
						if( CC.cantidadLotesEnCampo( ((Campo)CampoYEstado).getNroCampo() ).size()  >  0 )
							cantidadLotes = (Long)CC.cantidadLotesEnCampo( ((Campo)CampoYEstado).getNroCampo() ).get(0)[1]; // get --> retorna una fila y con [Coluna]
						else
							cantidadLotes = new Long(0);
						
						
						out.print("<tr><th>"+ ((Campo)CampoYEstado).getNroCampo() +"</th> <th>"+ ((Campo)CampoYEstado).getDireccion() +"</th> <th>"+ ((Campo)CampoYEstado).getEstado().getNombreEstado() +"</th> <th>"+ ((Campo)CampoYEstado).getEstado().getIdEstado() +
								  "</th> <th>"+ cantidadLotes.intValue()  +"</th> </tr>");
					}	*/
					
				   //-------------------------------------------------------------------------------------------------------
					
					 List<Campo> camposList = CC.consultarCampos();
					
					Long cantidadLotes; //CC.cantidadLotesEnCampo --> retorna objeto de tipo Long
					
					for( Campo CampoYEstado : camposList){
						
						if( CC.cantidadLotesEnCampo( ( CampoYEstado.getNroCampo() )).size()  >  0 )
							cantidadLotes = (Long)CC.cantidadLotesEnCampo( CampoYEstado.getNroCampo() ).get(0)[1]; // get --> retorna una fila y con [Coluna]
						else
							cantidadLotes = new Long(0);
						
						out.print("<tr><th>"+ CampoYEstado.getNroCampo() +"</th> <th>"+ CampoYEstado.getDireccion() +"</th> <th>"+ CampoYEstado.getEstado().getNombreEstado() +"</th> <th>"+ CampoYEstado.getEstado().getIdEstado() +
								  "</th> <th>"+ cantidadLotes.intValue()  +"</th> </tr>");
					}
					
					
					
				%>
			</table>	
			
			<div class="contentBtnCargar">
				<a href="menuPrincipal.jsp"><button>Volver</button></a>
			</div>
			
		</div>
  </body>
</html>