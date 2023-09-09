package Controladores;

import Modelos.SituacionAnormal;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import DAO_Modelos.Dao_BD;
import Modelos.Campo;
import Modelos.Cultivo;
import Modelos.DetalleProyecto;
import Modelos.Laboreo;
import Modelos.Lote;
import Modelos.Proyecto;

public class ControladorProyectosLote {
    
Dao_BD dao_CRUD;
	
	public ControladorProyectosLote() {
		dao_CRUD = new Dao_BD();
	}

	
	public int agregarProyecto(String estado,int nro_lote,int nro_Cultivo) {
		Lote L = new Lote(0, null, null);
		L.setNroLote(nro_lote);
		Cultivo C = new Cultivo(null, null, null);
		C.setNroTipoCultivo(nro_Cultivo);
		return dao_CRUD.agregar(new Proyecto(estado, L, C));
	}
	
	//------ Consulta HQL actualiza el proyecto tambien su cultivo y lote --------------
	public int actualizarProyecto(int nroProyecto,String estado,String motivoCancelar,Lote lote, Cultivo cultivo) {
		Proyecto P = new Proyecto(estado, lote, cultivo);
		P.setMotivoCancelar(motivoCancelar);
		P.setNroProyecto(nroProyecto);
		return dao_CRUD.actualizar(P);
	}
	
	public int eliminarProyecto(int nro_Proyecto) {
		Proyecto proyecto = new Proyecto(null, null, null);
		proyecto.setNroProyecto(nro_Proyecto);
		return dao_CRUD.eliminar(proyecto);
	}
	
	public Proyecto consultarProyecto(int nro_Proyecto) {
		return (Proyecto)dao_CRUD.obtener(nro_Proyecto,Proyecto.class);
	}
	
	
	
	public int agregarDetalle(Date fechaInicio,int nroLaboreo, int nroProyecto) {
		
		Proyecto P = new Proyecto();	
		P.setNroProyecto(nroProyecto);
		
		Laboreo L = new Laboreo();
		L.setNroLaboreo(nroLaboreo);
		return dao_CRUD.agregar(new DetalleProyecto(fechaInicio, null, P, L));
	}
	
	
	
	public int actualizarDetalle(Date fechaInicio, Date fechaFinalizacion,Proyecto proyecto, Laboreo laboreo) {
		return dao_CRUD.actualizar(new DetalleProyecto(fechaInicio, fechaFinalizacion, proyecto, laboreo));
	}
	
	public int eliminarDetalle(int nro_Detalle) {
		DetalleProyecto detalleProyecto = new DetalleProyecto(null, null, null, null);
		detalleProyecto.setNroDetalle(nro_Detalle);
		return dao_CRUD.eliminar(detalleProyecto);
	}
	
	public DetalleProyecto consultarDetalle(int nroDetalle) {
		return (DetalleProyecto)dao_CRUD.obtener(nroDetalle,DetalleProyecto.class);
	}
	
	public int agregarSituacionAnormal(String Incidencia) {
		return dao_CRUD.agregar(new SituacionAnormal(Incidencia));
	}
	
	public int actualizarSituacionAnormal(int nro_Situacion,String Incidencia) {
		SituacionAnormal situacionAnormal = new SituacionAnormal(Incidencia);
		situacionAnormal.setIdIncidencia(nro_Situacion);
		return dao_CRUD.actualizar(situacionAnormal);
	}
	
	public int eliminarSituacionAnormal(int nro_SituacionAnormal) {
		SituacionAnormal situacionAnormal = new SituacionAnormal(null);
		situacionAnormal.setIdIncidencia(nro_SituacionAnormal);
		return dao_CRUD.eliminar(situacionAnormal);
	}
	
	public SituacionAnormal consultarSituacionAnormal(int nro_SituacionAnoraml) {
		return (SituacionAnormal)dao_CRUD.obtener(nro_SituacionAnoraml,SituacionAnormal.class);
	}
	
	public java.util.List consultar(Object o,String filtro){
		return dao_CRUD.obtenerTodos(o,filtro);
	}
    
	public List<Object[]> consultarDetallesDeProyecto(int nroProyect) {
		return dao_CRUD.consultaNativa(new DetalleProyecto(), "select dp.pk_nro_detalle,dp.fecha_inicio,dp.fecha_fin,l.caracteristicas,l.etapa,sa.nombre from laboreo as l inner join detalle_proyecto as dp on l.pk_nro_laboreo = dp.fk_nro_laboreo "
				+ "	left join relacion_situacionanormal_detalleproyecto as r_sa_dp on r_sa_dp.fk_nro_detalle = dp.pk_nro_detalle "
				+ "	left join situacion_anormal as sa on sa.pk_nro_incidencia = r_sa_dp.fk_nro_incidencia "
				+ "	group by dp.fk_nro_proyecto, dp.pk_nro_detalle,dp.fecha_inicio,dp.fecha_fin,l.caracteristicas,l.etapa,sa.nombre "
				+ "	having dp.fk_nro_proyecto = "+ nroProyect);
	}
	
	public long cantidadProyectosAct() {
		return dao_CRUD.countElement(new Proyecto(), "where estado <> 'cancelado' and estado <> 'finalizado'");
	}
	
	public long cantidadProyectos() {
		return dao_CRUD.countElement(new Proyecto(), " ");
	}
	
	public Object ultimoProyectoInsertado() {
		return dao_CRUD.consultaNativa(new Proyecto(), "SELECT MAX(pk_nro_proyecto) FROM proyecto").get(0);
	}
	
	public Object cantidadProyectosActEnLote(int nroLote){
		return dao_CRUD.consultaNativa(new Proyecto()," select count(pk_nro_proyecto) as cantProy from proyecto as P where P.fk_nro_lote = "+ nroLote +" and P.estado <> 'cancelado' and P.estado <> 'finalizado' ").get(0);
	}
	
}
