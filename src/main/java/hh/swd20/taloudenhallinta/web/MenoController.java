package hh.swd20.taloudenhallinta.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.taloudenhallinta.domain.JasenRepository;
import hh.swd20.taloudenhallinta.domain.Meno;
import hh.swd20.taloudenhallinta.domain.MenoRepository;
import hh.swd20.taloudenhallinta.domain.MenokategoriaRepository;
import hh.swd20.taloudenhallinta.domain.TuloRepository;

@CrossOrigin
@Controller
public class MenoController {
	
	@Autowired
	private MenoRepository mrepository;
	
	@Autowired
	private MenokategoriaRepository mkrepository;
	
	@Autowired
	private JasenRepository jrepository;
	
	@Autowired
	private TuloRepository trepository;
	
	@GetMapping("/")
	public String FirstPage() {
		return "login";
	}
	
	@GetMapping("/login")
	public String LogIn() {
		return "login";
	}
	
	@GetMapping("/index")
	public String ByeMoneys(Model model) {
		String msg = "Bye moneys!";
		model.addAttribute("message",msg);
		return "index";
	}
	
	@GetMapping("/etusivu")
	public String haeEtusivu(Model model) {
		model.addAttribute("menot", mrepository.findAll());
		model.addAttribute("tulot", trepository.findAll());
		return "etusivu";
	}
	
	@GetMapping({"/menolista"})
	public String haeMenot(Model model) {
		model.addAttribute("menot", mrepository.findAll());
		return "menolista";
	}
	
	// RESTful service to get all expenses
    @RequestMapping(value="/menot", method = RequestMethod.GET)
    public @ResponseBody List<Meno> menoListRest() {	
        return (List<Meno>) mrepository.findAll();
    }
    
	// RESTful service to get expense by id
    @RequestMapping(value="/menot/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Meno> findMenoRest(@PathVariable("id") Long menoId) {	
    	return mrepository.findById(menoId);
    } 
	
	
	@GetMapping("/addmeno")
	public String addMeno(Model model) {
		model.addAttribute("meno", new Meno());
		model.addAttribute("menokategoriat", mkrepository.findAll());
		model.addAttribute("jasenet", jrepository.findAll());
		return "addmeno";
	}
	
	@PostMapping("/savemeno")
	public String savemeno(@Valid Meno meno, BindingResult bresult, Model model) {
		if (bresult.hasErrors()) {
			
			model.addAttribute("menokategoriat", mkrepository.findAll()); //tarvitaan mukaan pudotusvalikkoja varten
			model.addAttribute("jasenet", jrepository.findAll()); //tarvitaan mukaan pudotusvalikkoja varten
			
			if (meno.getMenoId() == null) {
				return "addmeno"; //redirect-endpoint ei kelpaa, koska unohtaa virheellisen menon
			} else {
				return "editmeno"; //redirect-endpoint ei kelpaa, koska unohtaa virheellisen menon
			}
		} else {
			mrepository.save(meno);
			return "redirect:menolista";
		}
	}
	
	@GetMapping("/deletemeno/{id}")
	public String deleteMeno(@PathVariable("id") Long menoId, Model model) {
		mrepository.deleteById(menoId);
		return "redirect:../menolista";
	}
	
	
	@GetMapping("/editmeno/{id}")
	public String editMeno(@PathVariable("id") Long menoId, Model model) {
		model.addAttribute("meno", mrepository.findById(menoId));
		model.addAttribute("menokategoriat", mkrepository.findAll());
		model.addAttribute("jasenet", jrepository.findAll());
		return "editmeno";
	}

}
