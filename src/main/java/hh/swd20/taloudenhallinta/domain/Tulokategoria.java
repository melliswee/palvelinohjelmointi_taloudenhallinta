package hh.swd20.taloudenhallinta.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Tulokategoria {
	@Id //määrää tietokantataulun pääavaimen (PK)
	@GeneratedValue(strategy = GenerationType.AUTO) //määrää että tietokantapalvelimen on luotava uniikki id tietoriville
	private Long tulokategoriaId;
	
	@NotEmpty(message="Nimi ei voi olla tyhjä.")
	@Size(min=2, max=30, message="Nimessä täytyy olla vähintään 2 kirjainta ja enintään 30.")
	private String tulokategoriaNimi;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tulokategoria")
	@JsonIgnoreProperties("tulokategoria")
	private List<Tulo> tulotapahtumat;
	
	public Tulokategoria(String tulokategoriaNimi) {
		super();
		this.tulokategoriaNimi = tulokategoriaNimi;
		this.tulotapahtumat = new ArrayList<>();
	}

	public Tulokategoria() {
		super();
		this.tulokategoriaNimi = null;
		this.tulotapahtumat = new ArrayList<>();
	}

	public Long getTulokategoriaId() {
		return tulokategoriaId;
	}

	public void setTulokategoriaId(Long tulokategoriaId) {
		this.tulokategoriaId = tulokategoriaId;
	}

	public String getTulokategoriaNimi() {
		return tulokategoriaNimi;
	}

	public void setTulokategoriaNimi(String tulokategoriaNimi) {
		this.tulokategoriaNimi = tulokategoriaNimi;
	}

	public List<Tulo> getTulotapahtumat() {
		return tulotapahtumat;
	}

	public void setTulotapahtumat(List<Tulo> tulotapahtumat) {
		this.tulotapahtumat = tulotapahtumat;
	}

	@Override
	public String toString() {
		return "Tulokategoria [tulokategoriaId=" + tulokategoriaId + ", tulokategoriaNimi=" + tulokategoriaNimi + "]";
	}
	
}
