package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import Controladores.ControladorLote;
import Controladores.ControladorProyectosLote;
import Modelos.Lote;
/**
 * Servlet implementation class obtenerListaLotesParaSelect
 */
public class obtenerListaLotesParaSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public obtenerListaLotesParaSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String campo = request.getParameter("campoSelect");
		String mjResult;
		int numLote;
		
		//JSONObject json = new JSONObject();// TODO
		
		Pattern patronIdentificar = Pattern.compile("Num ([0-9]+)",Pattern.CASE_INSENSITIVE);// TODO : Modulo restrictivo
		Matcher matcher;
		

		PrintWriter PW = response.getWriter();
		response.setContentType("text/plain");
		
		ControladorLote controladorLote = new ControladorLote();
		ControladorProyectosLote controladorProyctLote = new ControladorProyectosLote();
		
		
		if(campo != null){
			
			matcher = patronIdentificar.matcher(campo);
			
			if(matcher.find()) {
				
				campo = matcher.group(1);
				
				try {
				
					List listaLotes = controladorLote.consultar(" L WHERE L.campoDeLote.nroCampo = " + campo);
					    
					
					if(listaLotes.size() == 0){
						
						mjResult = "Este campo no posee lotes registrados [seleccione un campo con lotes disponibles]";
						PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"-1\"}] ");
						
					}else{
						
						Iterator It = listaLotes.iterator();
						PW.print("[{\"mj\":\"Operacion exitosa\", \"num\": \"1\"}");
						while(It.hasNext()){
							Lote lote = (Lote)It.next();
							PW.print(",{\"superficie\": \"" + Math.round(lote.getSuperficie()) + "\", \"nrolote\": \""+ lote.getNroLote() +"\"}");	
						}
						PW.print("]");
					
					}
					
				}catch(Exception E) {
					mjResult = "Se a producido un error al intentar obtener los lotes del campo [Error: "+ E.getMessage() +"]";
					PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"-1\"}] ");
				}
				
			}else {
				mjResult =	"Se a producido un problema al obtener el nro. de campo con lotes [vuelva a intentarlo]";
				PW.print("[{\"mj\":\" "+ mjResult +" \", \"num\": \"-1\"}] ");
			}
			
		}else {
			mjResult =	"Campo recivido Nulo [vuelva a intentarlo]";
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
