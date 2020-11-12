package hh.swd20.taloudenhallinta.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Tulo {
	@Id //määrää tietokantataulun pääavaimen (PK)
	@GeneratedValue(strategy = GenerationType.AUTO) //määrää että tietokantapalvelimen on luotava uniikki id tietoriville
	private Long tuloId;
	
	@NotNull(message="Aseta päivämäärä")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate pvm; //muotoa YYYY-mm-DD
	
	@NotNull(message="Aseta summa.")
	@Positive(message="Summan pitää olla positiivinen luku.")
	private double summa;
	
	private String muistiinpano;
	
	@ManyToOne
	@JsonIgnoreProperties("tulotapahtumat")
	@JoinColumn(name="tulokategoriaId")
	private Tulokategoria tulokategoria;
	
	@ManyToOne
	@JsonIgnoreProperties({"tulot", "menot"})//ignore both Jasen lists
	@JoinColumn(name="jasenId")
	private Jasen jasen;
	
	public Tulo() {
		super();
		this.pvm =null;
		this.summa = 0;
		this.tulokategoria = null;
		this.jasen = null;
		this.muistiinpano = null;
	}
	
	public Tulo(LocalDate pvm, double summa, String muistiinpano, Tulokategoria tulokategoria, Jasen jasen) {
		super();
		this.pvm = pvm;
		this.summa = summa;
		this.tulokategoria = tulokategoria;
		this.jasen = jasen;
		this.muistiinpano = muistiinpano;
	}
	
	
	public Tulo(LocalDate pvm, double summa, String muistiinpano) {
		super();
		this.pvm = pvm;
		this.summa = summa;
		this.muistiinpano = muistiinpano;
		this.tulokategoria = null;
		this.jasen = null;
	}
	
	public Tulo(LocalDate pvm, double summa, String muistiinpano, Tulokategoria tulokategoria) {
		super();
		this.pvm = pvm;
		this.summa = summa;
		this.muistiinpano = muistiinpano;
		this.tulokategoria = tulokategoria;
		this.jasen = null;
	}
	
	public Tulo(LocalDate pvm, double summa, String muistiinpano, Jasen jasen) {
		super();
		this.pvm = pvm;
		this.summa = summa;
		this.muistiinpano = muistiinpano;
		this.tulokategoria = null;
		this.jasen = jasen;
	}

	public Long getTuloId() {
		return tuloId;
	}

	public void setTuloId(Long tuloId) {
		this.tuloId = tuloId;
	}

	public LocalDate getPvm() {
		return pvm;
	}

	public void setPvm(LocalDate pvm) {
		this.pvm = pvm;
	}

	public double getSumma() {
		return summa;
	}

	public void setSumma(double summa) {
		this.summa = summa;
	}

	public String getMuistiinpano() {
		return muistiinpano;
	}

	public void setMuistiinpano(String muistiinpano) {
		this.muistiinpano = muistiinpano;
	}

	public Tulokategoria getTulokategoria() {
		return tulokategoria;
	}

	public void setTulokategoria(Tulokategoria tulokategoria) {
		this.tulokategoria = tulokategoria;
	}

	public Jasen getJasen() {
		return jasen;
	}

	public void setJasen(Jasen jasen) {
		this.jasen = jasen;
	}

	@Override
	public String toString() {
		
		if (this.tulokategoria != null && this.jasen != null) {
			return "Tulo [tuloId=" + tuloId + ", pvm=" + pvm + ", summa=" + summa + ", tulokategoria=" + tulokategoria + ", jasen=" + jasen + "]";
			
		} else if (this.tulokategoria != null && this.jasen == null) {
			return "Tulo [tuloId=" + tuloId + ", pvm=" + pvm + ", summa=" + summa + ", tulokategoria=" + tulokategoria + "]";
			
		} else if (this.jasen != null && this.tulokategoria == null) {
			return "Tulo [tuloId=" + tuloId + ", pvm=" + pvm + ", summa=" + summa + ", jasen=" + jasen + "]";
			
		} else {
			return "Tulo [tuloId=" + tuloId + ", pvm=" + pvm + ", summa=" + summa + "]";
		}
	}
	

}
