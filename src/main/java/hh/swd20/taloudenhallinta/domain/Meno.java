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
public class Meno {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //määrää että tietokantapalvelimen on luotava uniikki id tietoriville
	private Long menoId;
	
	@NotNull(message="Aseta päivämäärä.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate pvm; //muotoa YYYY-mm-DD
	
	@NotNull(message="Aseta summa.")
	@Positive(message="Summan pitää olla positiivinen luku.")
	private double summa;
	
	private String muistiinpano;
	
	@ManyToOne
	@JsonIgnoreProperties("menotapahtumat")
	@JoinColumn(name="menokategoriaId")
	private Menokategoria menokategoria;
	
	@ManyToOne
	@JsonIgnoreProperties({"menot", "tulot"}) //ignore both Jasen lists
	@JoinColumn(name="jasenId")
	private Jasen jasen;
	
	public Meno() {
		super();
		this.pvm = null;
		this.summa = 0;
		this.menokategoria = null;
		this.jasen = null;
		this.muistiinpano = null;
	}
	
	public Meno(LocalDate pvm, double summa, String muistiinpano, Menokategoria menokategoria, Jasen jasen) {
		super();
		this.pvm = pvm;
		this.summa = summa;
		this.muistiinpano = muistiinpano;
		this.menokategoria = menokategoria;
		this.jasen = jasen;
	}
	
	public Meno(LocalDate pvm, double summa, String muistiinpano) {
		super();
		this.pvm = pvm;
		this.summa = summa;
		this.muistiinpano = muistiinpano;
		this.menokategoria = null;
		this.jasen = null;
	}
	
	public Meno(LocalDate pvm, double summa, String muistiinpano, Menokategoria menokategoria) {
		super();
		this.pvm = pvm;
		this.summa = summa;
		this.muistiinpano = muistiinpano;
		this.menokategoria = menokategoria;
		this.jasen = null;
	}
	
	public Meno(LocalDate pvm, double summa, String muistiinpano, Jasen jasen) {
		super();
		this.pvm = pvm;
		this.summa = summa;
		this.muistiinpano = muistiinpano;
		this.menokategoria = null;
		this.jasen = jasen;
	}

	public Long getMenoId() {
		return menoId;
	}

	public void setMenoId(Long menoId) {
		this.menoId = menoId;
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

	public Menokategoria getMenokategoria() {
		return menokategoria;
	}

	public void setMenokategoria(Menokategoria menokategoria) {
		this.menokategoria = menokategoria;
	}

	public Jasen getJasen() {
		return jasen;
	}

	public void setJasen(Jasen jasen) {
		this.jasen = jasen;
	}

	@Override
	public String toString() {
		if (this.menokategoria != null && this.jasen != null) {
			return "Meno [menoId=" + menoId + ", pvm=" + pvm + ", summa=" + summa + ", menokategoria=" + menokategoria + ", jasen=" + jasen + "]";
			
		} else if (this.menokategoria != null && this.jasen == null) {
			return "Meno [menoId=" + menoId + ", pvm=" + pvm + ", summa=" + summa + ", menokategoria=" + menokategoria + "]";
			
		} else if (this.jasen != null && this.menokategoria == null) {
			return "Meno [menoId=" + menoId + ", pvm=" + pvm + ", summa=" + summa + ", jasen=" + jasen + "]";
	
		} else {
			return "Meno [menoId=" + menoId + ", pvm=" + pvm + ", summa=" + summa + "]";
		}
	}

}
