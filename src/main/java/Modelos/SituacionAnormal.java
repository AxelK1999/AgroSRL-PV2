package Modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "situacion_anormal")
public class SituacionAnormal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_nro_incidencia")
	private int idIncidencia;
	@Column(name = "nombre")
	private String nombreIncidencia;
	
	 @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "situacionesAnormales")
	 private List<DetalleProyecto> detallesProyecto;
	
	 public SituacionAnormal() {}
	 
	public SituacionAnormal(String nombreIncidencia) {
		this.nombreIncidencia = nombreIncidencia;
	}
	
	
	public int getIdIncidencia() {
		return idIncidencia;
	}
	public void setIdIncidencia(int idIncidencia) {
		this.idIncidencia = idIncidencia;
	}
	public String getNombreIncidencia() {
		return nombreIncidencia;
	}
	public void setNombreIncidencia(String nombreIncidencia) {
		this.nombreIncidencia = nombreIncidencia;
	}
		
}