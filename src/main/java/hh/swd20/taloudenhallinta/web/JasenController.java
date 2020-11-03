package hh.swd20.taloudenhallinta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.taloudenhallinta.domain.Jasen;
import hh.swd20.taloudenhallinta.domain.JasenRepository;

@CrossOrigin
@Controller
public class JasenController {
	
	@Autowired
	private JasenRepository jrepository;
	
	@GetMapping("/jasenlista")
	public String haeJaseset(Model model) {
		model.addAttribute("jasenet", jrepository.findAll());
		return "jasenlista";
	}
	
	@GetMapping("/addjasen")
	public String addJasen(Model model) {
		model.addAttribute("jasen", new Jasen());
		return "addjasen";
	}
	
	@PostMapping("/savejasen")
	public String savejasen(Jasen jasen) {
		jrepository.save(jasen);
		return "redirect:jasenlista";
	}
	
	@GetMapping("/deletejasen/{id}")
	public String deleteJasen(@PathVariable("id") Long jasenId, Model model) {
		jrepository.deleteById(jasenId);
		return "redirect:../jasenlista";
	}
	
	//TODO: editjasen
	@GetMapping("/editjasen/{id}")
	public String editJasen(@PathVariable("id") Long jasenId, Model model) {
		model.addAttribute("jasen", jrepository.findById(jasenId));
		return "editjasen";
	}

}
