package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.util.streams.StingArrayCollector;
import org.json.JSONObject;

import Controladores.ControladorCampo;
import Controladores.ControladorProyectosLote;
import Modelos.Lote;
import Modelos.Proyecto;

/**
 * Servlet implementation class operationCancalerProyecto
 */
public class operationCancalerProyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public operationCancalerProyecto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
		String datosJSON = request.getParameter("inf");
		
		 String lote;
		 String motivoCancelar;
		 
		 
		 String mjResult;
		 
		 ControladorProyectosLote controladorProyecto = new ControladorProyectosLote();
		 
		 
		 PrintWriter PW = response.getWriter();
		 response.setContentType("text/plain");
		 
		 Pattern patron = Pattern.compile("Num ([0-9]+)",Pattern.CASE_INSENSITIVE);
		 Matcher matcher;
		  
		  if(datosJSON != null) {
				
			  JSONObject json = new JSONObject(datosJSON);
			  lote = json.getString("lotesSelect");
			  motivoCancelar = json.getString("motivCancel");
			 
			  matcher = patron.matcher(lote);
			
			  if(matcher.find() && motivoCancelar != null) {
				
				  lote = matcher.group(1);
				  
				  try {
					  
					 List listProyectActFromLote = controladorProyecto.consultar(new Proyecto(), "as P where P.loteConProyecto.nroLote = " + lote +" and P.estado <> 'cancelado' and P.estado <> 'finalizado'");
					 
					 if(listProyectActFromLote.size() > 0) {
						 
						 Proyecto proyect = (Proyecto)listProyectActFromLote.get(0);
						 controladorProyecto.actualizarProyecto(proyect.getNroProyecto(),"cancelado",motivoCancelar,proyect.getLote(),proyect.getCultivo());
					
						 mjResult ="El proyecto de lote a sido cancelado con exito";
						 PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"1\"}] ");
					 }else {
						 mjResult ="El lote seleccionado no contine proyectos activos que cancelar";
						 PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"-1\"}] ");
					 }
					 
				  }catch(Exception E) {
					  	 mjResult = "Se a producido un error al obtener y cancelar el proyecto del lote seleccionado [Error: "+E.getMessage()+"]";
					  	 PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"-1\"}] ");
				  }
				  
			  }else {
				  mjResult ="No se a recivido correctamente el lote seleccionado [ Vuelva a intentarlo ]";
				  PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"-1\"}] ");
			  }
		  }else {
			  mjResult ="No se a podido recibir los datos del formulario correctamente [ Vuelva a intentarlo ]";
			  PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"-1\"}] ");
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
