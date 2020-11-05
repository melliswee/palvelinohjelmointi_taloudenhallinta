package hh.swd20.taloudenhallinta;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.taloudenhallinta.domain.Jasen;
import hh.swd20.taloudenhallinta.domain.JasenRepository;
import hh.swd20.taloudenhallinta.domain.Meno;
import hh.swd20.taloudenhallinta.domain.MenoRepository;
import hh.swd20.taloudenhallinta.domain.Menokategoria;
import hh.swd20.taloudenhallinta.domain.MenokategoriaRepository;
import hh.swd20.taloudenhallinta.domain.Tulo;
import hh.swd20.taloudenhallinta.domain.TuloRepository;
import hh.swd20.taloudenhallinta.domain.Tulokategoria;
import hh.swd20.taloudenhallinta.domain.TulokategoriaRepository;
import hh.swd20.taloudenhallinta.domain.User;
import hh.swd20.taloudenhallinta.domain.UserRepository;


@SpringBootApplication
public class TaloudenhallintaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(TaloudenhallintaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaloudenhallintaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo (MenoRepository mrepository, MenokategoriaRepository mkrepository, JasenRepository jrepository, TulokategoriaRepository tkrepository, TuloRepository trepository, UserRepository urepository) {
		return (args) -> {
			
			User u1= new User("user", "$2a$04$KFeHewQhVXuybwKFWPTkxeLtNw8GWJR8LWcFEoi/NtJN2weBRQmCS", "USER");
			User u2= new User("admin", "$2a$05$vtpBT/bIy1OmlrweYtCNYeLh0oAhjEC54Ui6XtFQY4ImVOm/RPXm6", "ADMIN");
			urepository.save(u1);
			urepository.save(u2);
			
			System.out.println("Hello");
			Jasen j1 = new Jasen("Mellu");
			Jasen j2 = new Jasen("Mikko");
			jrepository.save(j1);
			jrepository.save(j2);
			
			
			Menokategoria mk1 = new Menokategoria("Ruoka ja juoma kotona");
			Menokategoria mk2 = new Menokategoria("Ruoka ja juoma ulkona");
			mkrepository.save(mk1);
			mkrepository.save(mk2);
			
			Tulokategoria tk1 = new Tulokategoria("Palkka- ja yrittäjätulot");
			Tulokategoria tk2 = new Tulokategoria("Asumistuki");
			tkrepository.save(tk1);
			tkrepository.save(tk2);
			
			LocalDate date = LocalDate.now();
			Meno m1 = new Meno(date, 12.50, "Prismasta", mk1, j1);
			Meno m2 = new Meno(date, 32.00, "Wolt-tilaus", mk2, j2);
			mrepository.save(m1);
			mrepository.save(m2);
			
			Tulo t1 = new Tulo(date, 1100.0, "Palkka töistä", tk1, j1);
			Tulo t2 = new Tulo(date, 324.5, "", tk2, j1);
			trepository.save(t1);
			trepository.save(t2);
			
			log.info("listataan kaikki menot");
			
			for(Meno meno : mrepository.findAll()) {
				log.info(meno.toString());
			}
			
			log.info("listataan kaikki tulot");
			
			for(Tulo tulo : trepository.findAll()) {
				log.info(tulo.toString());
			}
			
			log.info("listataan kaikki menokategoriat");
			
			for(Menokategoria menokat : mkrepository.findAll()) {
				log.info(menokat.toString());
			}
			
			log.info("listataan kaikki tulokategoriat");
			
			for(Tulokategoria tulokat : tkrepository.findAll()) {
				log.info(tulokat.toString());
			}
			
			log.info("listataan kaikki jäsenet");
			
			for(Jasen jasen : jrepository.findAll()) {
				log.info(jasen.toString());
			}
			
		};
	}

}
