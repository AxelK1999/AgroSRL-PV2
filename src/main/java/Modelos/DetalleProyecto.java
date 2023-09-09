package Modelos;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_proyecto")
public class DetalleProyecto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_nro_detalle")
	private int nroDetalle;
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	@Column(name = "fecha_fin")
	private Date fechaFinalizacion;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "fk_nro_proyecto")
	private Proyecto proyecto;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "fk_nro_laboreo")
	private Laboreo laboreo;
	
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="relacion_situacionanormal_detalleproyecto", joinColumns={@JoinColumn(name="fk_nro_detalle")}, inverseJoinColumns={@JoinColumn(name="fk_nro_incidencia")})
	private List<SituacionAnormal> situacionesAnormales;
	
	public DetalleProyecto() {

	}
	
	public DetalleProyecto(Date fechaInicio, Date fechaFinalizacion,Proyecto proyecto, Laboreo laboreo) {
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFinalizacion;
		this.proyecto = proyecto;
		this.laboreo = laboreo;
	}
	
	public int getNroDetalle() {
		return nroDetalle;
	}
	public void setNroDetalle(int nroDetalle) {
		this.nroDetalle = nroDetalle;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	
	public Laboreo getLaboreo() {
		return this.laboreo;
	}
	
	//TODO
	
}