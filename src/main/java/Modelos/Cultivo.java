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
@Table(name = "cultivo")
public class Cultivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_nro_cultivo")
	private int nroTipoCultivo;
	@Column(name = "nombre")
	private String nombreCultivo;
	@Column(name = "caracteristicas")
	private String caracteristicasCultivo;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_nro_suelo")
	private Suelo sueloParaCultivo;
	
	
	@OneToMany(mappedBy = "laboreoDeCultivo",cascade = {CascadeType.PERSIST})//TODO
	private List<Laboreo> laboreos;
	
	@OneToMany(mappedBy = "cultivoEnProyecto",cascade = CascadeType.PERSIST)
	List<Proyecto> proyectosConEsteCultivo;
	
	public Cultivo() {
		
	}
	
	public Cultivo(String nombreCultivo, String caracteristicasCultivo,Suelo sueloQueTendra) {
		this.nombreCultivo = nombreCultivo;
		this.caracteristicasCultivo = caracteristicasCultivo;
		sueloParaCultivo = sueloQueTendra;
	}

	public int getNroTipoCultivo() {
		return nroTipoCultivo;
	}
	
	public void setNroTipoCultivo(int nroTipoCultivo) {
		this.nroTipoCultivo = nroTipoCultivo;
	}
	public String getNombreCultivo() {
		return nombreCultivo;
	}
	
	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}
	
	public String getCaracteristicasCultivo() {
		return caracteristicasCultivo;
	}
	
	public void setCaracteristicasCultivo(String caracteristicasCultivo) {
		this.caracteristicasCultivo = caracteristicasCultivo;
	}

}
