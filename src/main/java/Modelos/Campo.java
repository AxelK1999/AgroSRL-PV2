package Modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "campo")
public class Campo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_nro_campo")
	private int nroCampo;
	@Column(name = "direccion")
	private String direccion;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "fk_id_estado")
	private EstadoCampo estado;
	
	@OneToMany(mappedBy = "campoDeLote",cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE} )
	private List<Lote> lotesList;
	
	public Campo(){
		
	}
	
	public Campo(String direccion,EstadoCampo estado) {
		this.direccion = direccion;
		this.estado = estado;
	}
	
	public int getNroCampo() {
		return nroCampo;
	}
	public void setNroCampo(int nroCampo) {
		this.nroCampo = nroCampo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setNombre(String direccion) {
		this.direccion = direccion;
	}
	
	public EstadoCampo getEstado() {
		return this.estado;
	}

}

