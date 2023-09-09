package Modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proyecto")
public class Proyecto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_nro_proyecto")
	private int nroProyecto;
	@Column(name = "estado")
	private String estado;
	@Column(name = "motivo_cancelar")
	private String motivoCancelar;
	
	//------ Consulta HQL trae con el proyecto tambien su cultivo y lote --------------
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "fk_nro_lote")
	private Lote loteConProyecto;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "fk_nro_cultivo")
	private Cultivo cultivoEnProyecto;
	
	@OneToMany(mappedBy = "proyecto",cascade = {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<DetalleProyecto> detalleProyecto;
	
	public Proyecto() {}
	
	public Proyecto(String estado, Lote loteConProyecto, Cultivo cultivoEnProyecto) {
		this.estado = estado;
		this.loteConProyecto = loteConProyecto;
		this.cultivoEnProyecto = cultivoEnProyecto;
	}
	
	
	public int getNroProyecto() {
		return nroProyecto;
	}
	public void setNroProyecto(int nroProyecto) {
		this.nroProyecto = nroProyecto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMotivoCancelar() {
		return motivoCancelar;
	}
	public void setMotivoCancelar(String motivoCancelar) {
		this.motivoCancelar = motivoCancelar;
	}
	
	
	public Cultivo getCultivo(){
		return this.cultivoEnProyecto;
	}
	
	public Lote getLote() {
		return this.loteConProyecto;
	}
	
	
}