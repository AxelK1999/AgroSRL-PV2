package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controladores.ControladorProyectosLote;
import Modelos.Lote;
import Modelos.Proyecto;

/**
 * Servlet implementation class obtenerListaProyectos
 */
public class obtenerListaProyectos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public obtenerListaProyectos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO    sea POST 
		
		String lote = request.getParameter("inf");
		String mjResult;
		
		Pattern patronIdentificar = Pattern.compile("Num ([0-9]+)",Pattern.CASE_INSENSITIVE);// TODO : Modulo restrictivo
		Matcher matcher;
		
		PrintWriter PW = response.getWriter();
		response.setContentType("text/plain");
		
		if(lote != null) {
			
			matcher = patronIdentificar.matcher(lote);
			
			if(matcher.find()) {
				
				lote = matcher.group(1);
				
				try {
				
					List listaProyect = new ControladorProyectosLote().consultar(new Proyecto(), "as P where P.loteConProyecto.nroLote = "+lote);
					   
					if(listaProyect.size() == 0){
						
						mjResult = "El lote seleccionado no posee proyectos registrados";
						PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"-1\"}] ");
						
					}else{
						
						Iterator It = listaProyect.iterator();
						PW.print("[{\"mj\":\"Operacion exitosa\", \"num\": \"1\"}");
						while(It.hasNext()){
							
							Proyecto proyecto = (Proyecto)It.next();
							PW.print(",{\"nroProyecto\": \"" + proyecto.getNroProyecto() + "\", \"estado\": \""+ proyecto.getEstado() +"\", \"motivoCancelar\":\" "+ proyecto.getMotivoCancelar() +"\",\"cultivo\":\" "+ proyecto.getCultivo().getNombreCultivo() +" \"}");	
							
						}
						PW.print("]");
					
					}
					
				}catch(Exception E) {
					mjResult = "Se a producido un error al intentar obtener los proyectos del lote [Error: "+ E.getMessage() +"]";
					PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"-1\"}] ");
				}
				
			}else {
				mjResult =	"Se a producido un problema al obtener el nro. de lote con los proyectos [vuelva a intentarlo]";
				PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"-1\"}] ");
			}
			
		}else {
			mjResult =	"Lote recibido Nulo [vuelva a intentarlo]";
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
