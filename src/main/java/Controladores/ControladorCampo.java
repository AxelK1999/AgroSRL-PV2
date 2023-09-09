package Controladores;

import java.util.*;
import DAO_Modelos.Dao_BD;
import Modelos.Campo;
import Modelos.EstadoCampo;
import Modelos.Lote;
import Modelos.Proyecto;

public class ControladorCampo {
    
	Dao_BD dao_CRUD;
	
    public ControladorCampo(){
    	dao_CRUD = new Dao_BD();
    }
    
    public int agregarCampo(String direccion, int nro_estado) {
    	EstadoCampo estado = new EstadoCampo("Creado");
    	estado.setIdEstado(nro_estado);
    	
    	if(direccion == null)
    		return -4;
    	if(direccion.length() > 50)
			return -11;// longitud demasado largo
		if(dao_CRUD.countElement(new Campo(), "where direccion = '"+direccion+"'") > 0)
			return -10;
    	
		return dao_CRUD.agregar(new Campo(direccion, estado));
    	
    }
    
    public int eliminarCampo(int nro_Campo) {
    	Campo C = new Campo(null,null);
    	C.setNroCampo(nro_Campo);
    	return dao_CRUD.eliminar(C);
    }
    
    public int actualizarCampo(int nro_Campo,String direccion,EstadoCampo estdo) {
    	Campo C = new Campo(direccion, estdo);
    	C.setNroCampo(nro_Campo);
    	return dao_CRUD.actualizar(C);
    }
    
    public Campo consultarCampo(int nro_Campo) {
    	return (Campo)dao_CRUD.obtener(nro_Campo,Campo.class);
    }
    
    public int agregarEstadoCampo(String estado) {
    	return dao_CRUD.agregar(new EstadoCampo(estado));
    }
    public int actualizarEstadoCampo(int nro_EstadoCampo,String estado) {
    	EstadoCampo estadoCampo = new EstadoCampo(estado);
    	estadoCampo.setIdEstado(nro_EstadoCampo);
    	return dao_CRUD.actualizar(estadoCampo);
    }
    public int eliminarUnEstado(int nro_Estado) {
    	EstadoCampo e = new EstadoCampo(null);
    	e.setIdEstado(nro_Estado);
    	return dao_CRUD.eliminar(e);
    }
    
    public  java.util.List consultar(Object o,String filtro){
    	return dao_CRUD.obtenerTodos(o,filtro);
	}
    
    //-----------------------------------------------------
    
    public  java.util.List consultarCampos(){
    	return (List<Campo>)dao_CRUD.obtenerTodos(new Campo(), "as C INNER JOIN C.estado ");
	
    }
    
    
    public  java.util.List consultarTodoEstados(){
    	return (List<EstadoCampo>)dao_CRUD.obtenerTodos(new EstadoCampo(), "");
	
    }
    
    
    //-----------------------------------------------------
    
    
    
    
    public long numeroCampos(String filtro){
    	return dao_CRUD.countElement(new Campo(), filtro);
    }
    
    public List consultarEstadosCampo() {
    	return (List) dao_CRUD.obtenerTodos(new EstadoCampo()," ");
    }
    
  //Optimizar(definir en lugares corresponete para mejor rendimiento):
    public void actualizarEstadoTodoCampos() {
    	 
    	List<Object> camposList = (List<Object>)consultar(new Campo()," ");
    	 int proyectosActivos,cantLotes;
    	 Campo C;
    	 EstadoCampo EC = new EstadoCampo();
    	
    	 for( Object O : camposList){
    		 
    		 C = (Campo)O;
    		 
    		 proyectosActivos = (int) dao_CRUD.countElement(new Proyecto() , "as P inner join P.loteConProyecto  where P.loteConProyecto.campoDeLote.nroCampo = "+ C.getNroCampo() +" and P.estado <> 'cancelado' and P.estado <> 'finalizado'");
    		 if(proyectosActivos >= 1) {
    			  EC.setIdEstado(2);
    			 actualizarCampo(C.getNroCampo(),C.getDireccion(),EC);
    		 }else if((cantLotes =  (int)dao_CRUD.countElement(new Lote(),"as L  where L.campoDeLote.nroCampo = "+ C.getNroCampo())) == proyectosActivos && cantLotes > 0) {
    			 EC.setIdEstado(3);
    			 actualizarCampo(C.getNroCampo(),C.getDireccion(),EC);
    		 }else if(cantLotes != 0 && proyectosActivos == 0) {
    			 EC.setIdEstado(4);
    			 actualizarCampo(C.getNroCampo(),C.getDireccion(),EC);
    		 }
    		 
    	 }
    	 
    	 
    }
    
    
    public void actualizarEstdoUnCampo(int nroCampo) {
   	 int proyectosActivos,cantLotes;
   	 Campo C = consultarCampo(nroCampo);
   	 EstadoCampo EC = new EstadoCampo();
   	
   		 proyectosActivos = (int) dao_CRUD.countElement(new Proyecto() , "as P inner join P.loteConProyecto  where P.loteConProyecto.campoDeLote.nroCampo = "+ C.getNroCampo() +" and P.estado <> 'cancelado' and P.estado <> 'finalizado'");
   		 if(proyectosActivos >= 1) {
   			  EC.setIdEstado(2);
   			 actualizarCampo(C.getNroCampo(),C.getDireccion(),EC);
   		 }else if((cantLotes =  (int)dao_CRUD.countElement(new Lote(),"as L  where L.campoDeLote.nroCampo = "+ C.getNroCampo())) == proyectosActivos && cantLotes > 0) {
   			 EC.setIdEstado(3);
   			 actualizarCampo(C.getNroCampo(),C.getDireccion(),EC);
   		 }else if(cantLotes != 0 && proyectosActivos == 0) {
   			 EC.setIdEstado(4);
   			 actualizarCampo(C.getNroCampo(),C.getDireccion(),EC);
   		 }
   		 
    }
    
    
    public List<Object[]> cantidadLotesEnCampo(int nroCampo) {
    	return dao_CRUD.consultaNativa(new Campo(),"select campo.pk_nro_campo, count(pk_nro_campo)as cantidad from campo inner join lote on campo.pk_nro_campo = lote.fk_nro_campo "
    			+ "group by pk_nro_campo, campo.pk_nro_campo having campo.pk_nro_campo = " + nroCampo );
    }
}
