package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controladores.ControladorProyectosLote;
import Modelos.DetalleProyecto;

/**
 * Servlet implementation class obtenerTablaDetalles
 */
public class obtenerTablaDetalles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public obtenerTablaDetalles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter PW = response.getWriter();
		String mjResult;
		
		String nroProyecto = request.getParameter("proyecto");
		ControladorProyectosLote CPL = new ControladorProyectosLote();
	
		PW.print("<html><body> <form action=\"http://localhost:9999/appWebTPF/Vista/verProyectos.jsp\">");
		
		try{
		
			if(nroProyecto != null) {
			
				List<Object[]> listaDetalles =  CPL.consultarDetallesDeProyecto(Integer.parseInt(nroProyecto));
				
				PW.print("<table> <thead><tr> <th> Nro. Detalle </th> <th> Fecha Inicializacion </th> <th> Fecha Finalizacion </th> <th> Laboreo </th> <th> Etapa Laboreo</th> <th> Situacion Anormal </th> </tr></thead>");
			
				for(Object[] object : listaDetalles){
						PW.print("<tr><th>"+ (Integer)object[0] +"</th> <th>"+ object[1]  +"</th> <th>"+ object[2] +"</th> <th>"+ object[3] +"</th> <th>"+ object[4] +"</th> <th>"+ object[5] +"</th> </tr>");
				}
			
				PW.print("</table>");
			}else {
				mjResult = "No se a podido recibir datos del proyecto del formulario correctamente [vuelva a intentarlo]";
				PW.print(" <div class=\"contentResultOperation\"> <label> "+ mjResult +" </label> </div>");
			}
		}catch(Exception E){
			mjResult = "Se a producido un error al procesar los datos recividos en el servidor [Vuelva a intentarlo] [Error: "+E.getMessage()+"]";
			PW.print(" <div class=\"contentResultOperation\"> <label> "+ mjResult +" </label> </div>");
		}
		
		PW.print("<input type=\"submit\" value=\"Volver\"> </form> </body> </html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
