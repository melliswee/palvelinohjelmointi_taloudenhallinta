package hh.swd20.taloudenhallinta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.taloudenhallinta.domain.Tulokategoria;
import hh.swd20.taloudenhallinta.domain.TulokategoriaRepository;

@CrossOrigin
@Controller
public class TulokategoriaController {
	
	@Autowired
	private TulokategoriaRepository tkrepository;
	
	@GetMapping("/addtulokategoria")
	public String addTulokategoria(Model model) {
		model.addAttribute("tulokategoria", new Tulokategoria());
		return "addtulokategoria";
	}
	
	@PostMapping("/savetulokategoria")
	public String saveTulokategoria(Tulokategoria tulokategoria) {
		tkrepository.save(tulokategoria);
		return "redirect:kategorialista";
	}
	
	@GetMapping("/deletetulokategoria/{id}")
	public String deleteTulokategoria(@PathVariable("id") Long tulokategoriaId, Model model) {
		tkrepository.deleteById(tulokategoriaId);
		return "redirect:../kategorialista";
	}
	
	@GetMapping("edittulokategoria/{id}")
	public String editTulokategoria(@PathVariable("id") Long tulokategoriaId, Model model) {
		model.addAttribute("tulokategoria", tkrepository.findById(tulokategoriaId));
		return "edittulokategoria";
	}

}
