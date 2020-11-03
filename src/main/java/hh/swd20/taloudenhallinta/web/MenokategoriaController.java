package hh.swd20.taloudenhallinta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.taloudenhallinta.domain.Menokategoria;
import hh.swd20.taloudenhallinta.domain.MenokategoriaRepository;
import hh.swd20.taloudenhallinta.domain.TulokategoriaRepository;

@CrossOrigin
@Controller
public class MenokategoriaController {
	
	@Autowired
	private MenokategoriaRepository mkrepository;
	//koska kategorioille on vain yhteinen html-templaatti, laitetaan tänne myös TuloRepo
	@Autowired
	private TulokategoriaRepository tkrepository;
	
	@GetMapping("/kategorialista")
	public String menokategoriaLista(Model model) {
		model.addAttribute("menokategoriat", mkrepository.findAll());
		model.addAttribute("tulokategoriat", tkrepository.findAll());
		return "kategorialista";
	}
	
	@GetMapping("/addmenokategoria")
	public String addMenokategoria(Model model) {
		model.addAttribute("menokategoria", new Menokategoria());
		return "addmenokategoria";
	}
	
	@PostMapping("/savemenokategoria")
	public String saveMenokategoria(Menokategoria menokategoria) {
		mkrepository.save(menokategoria);
		return "redirect:kategorialista";
	}
	
	@GetMapping("/deletemenokategoria/{id}")
	public String deleteMenokategoria(@PathVariable("id") Long menokategoriaId, Model model) {
		mkrepository.deleteById(menokategoriaId);
		return "redirect:../kategorialista";
	}
	
	@GetMapping("editmenokategoria/{id}")
	public String editMenokategoria(@PathVariable("id") Long menokategoriaId, Model model) {
		model.addAttribute("menokategoria", mkrepository.findById(menokategoriaId));
		return "editmenokategoria";
	}

}
