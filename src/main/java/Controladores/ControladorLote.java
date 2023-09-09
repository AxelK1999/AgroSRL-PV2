package Controladores;

import java.util.LinkedList;
import java.util.List;

import DAO_Modelos.Dao_BD;
import Modelos.Campo;
import Modelos.EstadoCampo;
import Modelos.Lote;
import Modelos.Proyecto;
import Modelos.Suelo;

public class ControladorLote {
    
	Dao_BD dao_CRUD;
	
    public ControladorLote(){
    	dao_CRUD = new Dao_BD();
    }
    
    public int actualizarLote(int nro_Lote,float superficie, Campo Campo,Suelo Suelo) {
    	Lote lote = new Lote(superficie, Campo, Suelo);
    	lote.setNroLote(nro_Lote);
    	return dao_CRUD.actualizar(lote);
    }
    
    public int agregarLote(float superficie, int nro_campo,int nro_suelo) {//TODO
    	Campo C = new Campo(null, null);
    	C.setNroCampo(nro_campo);
    	Suelo S = new Suelo(null);
    	S.setNroSuelo(nro_suelo);
    	//TODO
    	return dao_CRUD.agregar(new Lote(superficie, C, S));
    }
    
    
    
    public int eliminarLote(int nro_Lote) {
    	Lote lote = new Lote(0, null, null);
    	lote.setNroLote(nro_Lote);
    	return dao_CRUD.eliminar(lote);
    }
    
    public Lote consultarLote(int nro_Lote) {
    	return (Lote)dao_CRUD.obtener(nro_Lote,Lote.class);
    }
    
    public java.util.List consultar(String filtro){
		return dao_CRUD.obtenerTodos(new Lote(),filtro);
	}
    
    public int unirLote(Lote loteDestino,Lote otroLote) {
    	return 1; //TODO
    }
    public int separarLote(Lote lote) {
    	return 1; ///TODO
    }
    
    public long cantidadLotes() {
    	return dao_CRUD.countElement(new Lote(), " ");
    }
    
    public List consultaNativa(String query) {
    	return dao_CRUD.consultaNativa(new Lote(),query);
    }
}
