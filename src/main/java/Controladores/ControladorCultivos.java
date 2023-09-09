package Controladores;

import Modelos.Suelo;

import java.util.List;
import DAO_Modelos.Dao_BD;
import Modelos.Cultivo;
import Modelos.Laboreo;

public class ControladorCultivos {
    
	Dao_BD dao_CRUD;
	
    public ControladorCultivos(){
        dao_CRUD = new Dao_BD();
    }
    
    public int agregarCultivo(String nombre,String caracterisitcas,Suelo suelo) {
    	return dao_CRUD.agregar(new Cultivo(nombre, caracterisitcas, suelo));
    }
    
    public int actualizarCultivo(int nro_Cultivo,String nombre,String caracterisitcas,Suelo suelo) {
    	Cultivo C = new Cultivo(nombre, caracterisitcas, suelo);
    	C.setNroTipoCultivo(nro_Cultivo);
    	return dao_CRUD.actualizar(C);
    }
    
    public int eliminarCultivo(int nro_Cultivo) {
    	Cultivo C = new Cultivo(null, null, null);
    	C.setNroTipoCultivo(nro_Cultivo);
    	return dao_CRUD.eliminar(C);
    }
    
    public Cultivo consultarCultivo(int nro_Cultivo) {
    	return (Cultivo)dao_CRUD.obtener(nro_Cultivo,Cultivo.class);
    }
    
    public int agregarTipoSuelo(String tipoSuelo) {
    	return dao_CRUD.agregar(new Suelo(tipoSuelo));
    }
    
    public int actualizarTipoSuelo(int nro_Suelo,String tipoSuelo) {
    	 Suelo s = new Suelo(tipoSuelo);
    	 s.setNroSuelo(nro_Suelo);
    	 return dao_CRUD.actualizar(s);
    }
    public int eliminarTipoSuelo(int nro_Suelo) {
    	Suelo s = new Suelo(null);
   	    s.setNroSuelo(nro_Suelo);
   	    return dao_CRUD.eliminar(s);
    }
    
    public Suelo consultarTipoSuelo(int nro_Suelo) {
    	return (Suelo)dao_CRUD.obtener(nro_Suelo,Suelo.class);
    }
    
    
    public int agregarLaboreo(String etapaLaboreo, String direccionLaboreo,Cultivo cultivo) {
    	return dao_CRUD.agregar(new Laboreo(etapaLaboreo, direccionLaboreo, cultivo));
    }
    public int actualizarLaboreo(int nro_Laboreo,String etapaLaboreo, String direccionLaboreo,Cultivo cultivo) {
    	Laboreo L = new Laboreo(etapaLaboreo, direccionLaboreo, cultivo);
    	L.setNroLaboreo(nro_Laboreo);
    	return dao_CRUD.actualizar(L);
    }
    public int eliminarLaboreo(int nro_laboreo) {
    	Laboreo L = new Laboreo(null,null ,null );
    	L.setNroLaboreo(nro_laboreo);
    	return dao_CRUD.eliminar(L);
    }
    
    public Object[] obtenerLaboreoInicialDeCultivo(int pk_cultivo) {
         return	dao_CRUD.consultaNativa(new Laboreo(), "select * from laboreo as L where L.etapa = '1' and L.fk_nro_cultivo = " + pk_cultivo).get(0);
    }
    
    public List consultar(Object o,String filtro){
		return dao_CRUD.obtenerTodos(o,filtro);
	}
    
    //--------------------------------------------
    
    public List consultarTodoSuelo(){
		return (List<Suelo>)dao_CRUD.obtenerTodos(new Suelo()," ");
	}
    
    //--------------------------------------------
    
}
