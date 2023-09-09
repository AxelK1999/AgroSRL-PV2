package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.sqm.mutation.internal.idtable.BeforeUseAction;

import com.sun.tools.classfile.StackMap_attribute.stack_map_frame;

import Controladores.ControladorCampo;
import Controladores.ControladorCultivos;
import Controladores.ControladorProyectosLote;

/**
 * Servlet implementation class regNewProyect
 */
public class regNewProyect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regNewProyect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		//String campo = request.getParameter("campoSelect");
		//ControladorCampo contrCampo = new ControladorCampo();
		
		PrintWriter PW = response.getWriter();
		String lote = request.getParameter("loteSelect");
		String cultivoSelect = request.getParameter("cultivoSelect");
		String Mj = "Se a producido un problema con la informacion del formulario ";
		String URL = "iniciarProyecto.jsp";//TODO
		
		if(lote == null || cultivoSelect == null || lote.length() < 1 || cultivoSelect.length()  < 1)
			Mj = "No se a podido recibir los datos del fomulario";
		else {
			
			Pattern patron;
			Matcher matcher;
			patron = Pattern.compile("Num ([0-9]+)",Pattern.CASE_INSENSITIVE);
			matcher = patron.matcher(cultivoSelect);
			
			if(matcher.find()) {
				
				try {
					
					int numCultivo = Integer.parseInt(matcher.group(1));
					matcher = patron.matcher(lote);
					
					if(matcher.find()) {
						
						int numeroLote = Integer.parseInt(matcher.group(1));
						ControladorProyectosLote CPL = new ControladorProyectosLote();
						
						int result= CPL.agregarProyecto("preparacion", numeroLote, numCultivo);
						
						if(result == 1) {
							
							if((result = CPL.agregarDetalle(Date.valueOf(LocalDate.now()), (int)new ControladorCultivos().obtenerLaboreoInicialDeCultivo(numCultivo)[0] ,(int)CPL.ultimoProyectoInsertado())) == 1) {
								
								/*
								matcher = patron.matcher(campo);
								if(matcher.find())
									contrCampo.actualizarEstdoUnCampo(Integer.parseInt(matcher.group(1)));
								*/
								
								Mj = "El proyecto a sido iniciado correctamente";
								URL = "campoParaProyecto.jsp";
							}else
								Mj = "El detalle del proyecto no a podido ser registrado [num Error: "+result+"]";
							
						}else
							 Mj="Se a producido un problema al intentar registrar el proyecto [num. Error:"+ result +"]" ;//TODO  (Objrto error para Mj)
					}else
						Mj = "No se a podido encotnrar el numero de lote para el cultivo";
					
				} catch (Exception e) {
					Mj = "Se ha producido un error al intentar registrar el nuevo proyecto [ Error:"+ e.getMessage() +"]";
				}
				
			}else
				Mj = "No se a podido obtener el nro. del lote de los datos del fomrulario";
			
		}
		
		
		//PrintWriter PW = response.getWriter();
		//TODO (Ocultar boton y select para solo mostrar Mj en caso de fallo)
		PW.print("			<div class =\"content\">  \r\n"
				+ "				<p>" + Mj +" </p>  \r\n"
				+ "				<a href=\" "+ URL  +"\" style=\"\"> <button >Aceptar</button> </a>  \r\n"
				+ "			</div>"
				+ "");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
