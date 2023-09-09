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

import Controladores.ControladorCultivos;
import Controladores.ControladorLote;
import Controladores.ControladorProyectosLote;
import Modelos.Cultivo;
import Modelos.Lote;
import org.json.JSONObject;
/**
 * Servlet implementation class selectCultivosParaProyecto
 */
public class selectCultivosParaProyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public selectCultivosParaProyecto() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lote = request.getParameter("loteSelect");
		String Mj;
		Integer numLote = null;
		
		
	
		PrintWriter PW = response.getWriter();
		response.setContentType("text/plain");
		
		if(lote != null){
			
				JSONObject json = new JSONObject(lote);
				lote = json.getString("lotesSelect"); 
			
				Pattern patron;
				Matcher matcher;
				patron = Pattern.compile("Num ([0-9]+)",Pattern.CASE_INSENSITIVE);
				matcher = patron.matcher(lote);
				
				if(matcher.find()){
					
					ControladorLote CLote = new ControladorLote();
					ControladorCultivos CCultivo = new ControladorCultivos();
					ControladorProyectosLote CPL = new ControladorProyectosLote();
					
					try{
						
						numLote = Integer.parseInt( matcher.group(1) );
						Long proyectoActivosEnLote = (Long)CPL.cantidadProyectosActEnLote(numLote);
						
						if( proyectoActivosEnLote.intValue() > 0) {	// Controla que el lote seleccionado no tenga ya un proyecto activo en el momento
							
							Mj = "El lote seleccionado ya posee un proyecto activo [Finalice el proyecto o seleccione otro lote]";
							PW.print("[{\"mj\":\" "+ Mj +" \", \"num\": \"-1\"}] ");
						
						}else{ // De no haber proyecto activo => podra iniciar un proyecto en ese lote
						
							Lote L = CLote.consultarLote(numLote);
						
							List cultivosList = CCultivo.consultar(new Cultivo(), "C WHERE C.sueloParaCultivo.nroSuelo = " + L.getSuelo().getNroSuelo()); // filtra los cultivos acorde al tipo de suelo del lote seleccioando
						
							if(cultivosList.size() == 0){ // si no hay cultivos con el tipo de suelo equivalente al del lote => 
								Mj = "No se han encontrado cultivos con el tipo de suelo equvialente al del lote seleccionado";
								PW.print("[{\"mj\":\" "+ Mj +" \", \"num\": \"-1\"}]");
							}else{ // De lo contrario procede a registrar
						
							
								Iterator It = cultivosList.iterator();
								PW.print("[{\"mj\":\"Operacion exitosa\", \"num\": \"1\"}");
							
								while(It.hasNext()){
									
									Cultivo C = (Cultivo)It.next();
									PW.print(",{\"nombreCultivo\": \"" + C.getNombreCultivo() + "\", \"nroCultivo\": \""+ C.getNroTipoCultivo() +"\"}");	
								}
								PW.print("]");
							
							}	
						}
					}catch(Exception E){
						Mj = "Se a producido un problema al obtener los posibles cultivos para el lote [Error:"+ E.getMessage() +"  Ubicacion: "+ E.getLocalizedMessage() +"]";
						PW.print("[{\"mj\":\" "+ Mj +" \", \"num\": \"-1\"}]");
					}
					
				}else {
					Mj = "No se a podido obtener el nro. de lote seleccionado para obtener sus cultivos disponibles [Vualva a intentarlo]";
					PW.print("[{\"mj\":\" "+ Mj +" \", \"num\": \"-1\"}]");
				}
		}else {
			Mj = "No se a logrado obtener el lote seleccionado [Vualva a intentarlo]";
			PW.print("[{\"mj\":\" "+ Mj +" \", \"num\": \"-1\"}]");
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
