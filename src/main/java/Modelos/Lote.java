package Modelos;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "lote")
public class Lote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_nro_lote")
	private int nroLote;
	
	@Column(name = "superficie")
	private float superficie;
	
	 
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="fk_nro_campo")
	private Campo campoDeLote;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})//TODO
	@JoinColumn(name="fk_nro_suelo")
	private Suelo sueloDeLote;
	
	@OneToMany(mappedBy = "loteConProyecto",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE})
	List<Proyecto> proyectosDeLote;
	
	public Campo getCampoDeLote() {
		return campoDeLote;
	}


	public void setCampoDeLote(Campo campoDeLote) {
		this.campoDeLote = campoDeLote;
	}

	public Lote() {
		
	}
	
	public Lote(float superficie, Campo campoDeLote,Suelo sueloLote) {
		this.superficie = superficie;
		this.campoDeLote = campoDeLote;
		this.sueloDeLote = sueloLote;
		
	}
	
	public int getNroLote() {
		return nroLote;
	}


	public void setNroLote(int nroLote) {
		this.nroLote = nroLote;
	}


	public double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(float superficie) {
		this.superficie = superficie;
	}
	
	
	public Suelo getSuelo() {
		return sueloDeLote;
	}
	
}