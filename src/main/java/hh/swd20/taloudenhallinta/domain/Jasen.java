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
public class Jasen {
	@Id //määrää tietokantataulun pääavaimen (PK)
	@GeneratedValue(strategy = GenerationType.AUTO) //määrää että tietokantapalvelimen on luotava uniikki id tietoriville
	private Long jasenId;
	private String nimi;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "jasen")
	@JsonIgnoreProperties("jasen")
	private List<Meno> menot;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "jasen")
	@JsonIgnoreProperties("jasen")
	private List<Tulo> tulot;


	public Jasen(String nimi) {
		super();
		this.nimi = nimi;
		this.menot = new ArrayList<>();
		this.tulot = new ArrayList<>();
	}
	
	public Jasen() {
		super();
		this.nimi = null;
		this.menot = new ArrayList<>();
		this.tulot = new ArrayList<>();
	}

	public Long getJasenId() {
		return jasenId;
	}

	public void setJasenId(Long jasenId) {
		this.jasenId = jasenId;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public List<Meno> getMenot() {
		return menot;
	}

	public void setMenot(List<Meno> menot) {
		this.menot = menot;
	}

	public List<Tulo> getTulot() {
		return tulot;
	}

	public void setTulot(List<Tulo> tulot) {
		this.tulot = tulot;
	}

	@Override
	public String toString() {
		return "Jasen [jasenId=" + jasenId + ", nimi=" + nimi + "]";
	}
	
}
