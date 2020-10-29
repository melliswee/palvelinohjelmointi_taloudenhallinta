package hh.swd20.taloudenhallinta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.taloudenhallinta.domain.Meno;
import hh.swd20.taloudenhallinta.domain.MenoRepository;
import hh.swd20.taloudenhallinta.domain.MenokategoriaRepository;

@CrossOrigin
@Controller
public class MenoController {
	
	@Autowired
	private MenoRepository mrepository;
	
	@Autowired
	private MenokategoriaRepository mkrepository;
	
	@GetMapping("/index")
	public String ByeMoneys(Model model) {
		String msg = "Bye moneys!";
		model.addAttribute("message",msg);
		return "index";
	}
	
	@GetMapping("/menolista")
	public String haeMenot(Model model) {
		model.addAttribute("menot", mrepository.findAll());
		return "menolista";
	}
	
	@GetMapping("/addmeno")
	public String addMeno(Model model) {
		model.addAttribute("meno", new Meno());
		model.addAttribute("menokategoriat", mkrepository.findAll());
		return "addmeno";
	}
	
	//TODO: html-templaattia ei ole vielä
	@PostMapping("/savemeno")
	public String savemeno(Meno meno) {
		mrepository.save(meno);
		return "redirect:menolista";
	}
	
	@GetMapping("/deletemeno/{id}")
	public String deleteMeno(@PathVariable("id") Long menoId, Model model) {
		mrepository.deleteById(menoId);
		return "redirect:../menolista";
	}
	
	//TODO: html-templaattia ei ole vielä
	@GetMapping("/editmeno/{id}")
	public String editMeno(@PathVariable("id") Long menoId, Model model) {
		model.addAttribute("meno", mrepository.findById(menoId));
		model.addAttribute("menokategoriat", mkrepository.findAll());
		return "editmeno";
	}

}
