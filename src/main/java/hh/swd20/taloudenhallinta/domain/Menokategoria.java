package hh.swd20.taloudenhallinta.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Menokategoria {
	@Id //määrää tietokantataulun pääavaimen (PK)
	@GeneratedValue(strategy = GenerationType.AUTO) //määrää että tietokantapalvelimen on luotava uniikki id tietoriville
	private Long menokategoriaId;
	private String menokategoriaNimi;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "menokategoria")
	@JsonIgnoreProperties("menokategoria")
	private List<Meno> menotapahtumat;

	public Menokategoria(String menokategoriaNimi) {
		super();
		this.menokategoriaNimi = menokategoriaNimi;
		this.menotapahtumat = new ArrayList<>();
	}
	
	public Menokategoria() {
		super();
		this.menokategoriaNimi = null;
		this.menotapahtumat = new ArrayList<>();
	}

	public Long getMenokategoriaId() {
		return menokategoriaId;
	}

	public void setMenokategoriaId(Long menokategoriaId) {
		this.menokategoriaId = menokategoriaId;
	}

	public String getMenokategoriaNimi() {
		return menokategoriaNimi;
	}

	public void setMenokategoriaNimi(String menokategoriaNimi) {
		this.menokategoriaNimi = menokategoriaNimi;
	}

	public List<Meno> getMenotapahtumat() {
		return menotapahtumat;
	}

	public void setMenotapahtumat(List<Meno> menotapahtumat) {
		this.menotapahtumat = menotapahtumat;
	}

	@Override
	public String toString() {
		return "Menokategoria [menokategoriaId=" + menokategoriaId + ", menokategoriaNimi=" + menokategoriaNimi + "]";
	}

}
