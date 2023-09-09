package Modelos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "estado_campo")
public class EstadoCampo {

	//auto incremental
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_id_estado")
	private int idEstado;
	@Column(name = "nombre_estado")
	private String nombreEstado;

	@OneToMany(mappedBy = "estado",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Campo> camposConEstado;
	
	public EstadoCampo() {}
	
	public EstadoCampo(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	
	
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

}
