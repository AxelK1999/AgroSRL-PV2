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

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "suelo")
public class Suelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_nro_suelo")
	private int nroSuelo;
	@Column(name = "caracteristicas")
	private String caractersticasSuelo;
	
	@OneToMany(mappedBy = "sueloDeLote",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH} )
	private List<Lote> lotes;
    
	@OneToMany(mappedBy = "sueloParaCultivo",cascade = {CascadeType.PERSIST})
	private List<Cultivo> cultivosConSuelo;
	
	public Suelo() {}
	
	public Suelo(String caractersticasSuelo) {
		this.caractersticasSuelo = caractersticasSuelo;
	}
	
	public int getNroSuelo() {
		return nroSuelo;
	}

	public void setNroSuelo(int nroSuelo) {
		this.nroSuelo = nroSuelo;
	}

	public String getCaractersticasSuelo() {
		return caractersticasSuelo;
	}
	
	public void setCaractersticasSuelo(String caractersticasSuelo) {
		this.caractersticasSuelo = caractersticasSuelo;
	}	
}
