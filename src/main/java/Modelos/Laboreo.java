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
@Table(name = "Laboreo")
public class Laboreo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_nro_laboreo")
	private int nroLaboreo;
	@Column(name = "etapa")
	private String etapaLaboreo;
	@Column(name = "caracteristicas")
	private String caracteristicasLaboreo;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "fk_nro_cultivo")
	private Cultivo laboreoDeCultivo;
	
	@OneToMany(mappedBy = "laboreo",cascade = {CascadeType.PERSIST})
	private List<DetalleProyecto> detallesProyecto;
	
	public Laboreo() {
		
	}
	
	public Laboreo(String etapaLaboreo, String caractLaboreo,Cultivo cultivo) {
		this.etapaLaboreo = etapaLaboreo;
		this.caracteristicasLaboreo = caractLaboreo;
		laboreoDeCultivo = cultivo;
		
	}

	public int getNroLaboreo() {
		return nroLaboreo;
	}

	public void setNroLaboreo(int nroLaboreo) {
		this.nroLaboreo = nroLaboreo;
	}

	public String getEtapaLaboreo() {
		return etapaLaboreo;
	}

	public void setEtapaLaboreo(String etapaLaboreo) {
		this.etapaLaboreo = etapaLaboreo;
	}

	public String getCaracteristicasLaboreo() {
		return caracteristicasLaboreo;
	}

	public void setCaracteristicasLaboreo(String direccionLaboreo) {
		this.caracteristicasLaboreo = direccionLaboreo;
	}
	
	
	
}