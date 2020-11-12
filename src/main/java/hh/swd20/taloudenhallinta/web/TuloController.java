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
import hh.swd20.taloudenhallinta.domain.Tulo;
import hh.swd20.taloudenhallinta.domain.TuloRepository;
import hh.swd20.taloudenhallinta.domain.TulokategoriaRepository;

@CrossOrigin
@Controller
public class TuloController {
	
	@Autowired
	private TuloRepository trepository;
	
	@Autowired
	private TulokategoriaRepository tkrepository;
	
	@Autowired
	private JasenRepository jrepository;
	
	@GetMapping({"/tulolista"})
	public String haeTulot(Model model) {
		model.addAttribute("tulot", trepository.findAll());
		return "tulolista";
	}
	
	// RESTful service to get all income
    @RequestMapping(value="/tulot", method = RequestMethod.GET)
    public @ResponseBody List<Tulo> tuloListRest() {	
        return (List<Tulo>) trepository.findAll();
    }
    
	// RESTful service to get income by id
    @RequestMapping(value="/tulot/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Tulo> findTuloRest(@PathVariable("id") Long tuloId) {	
    	return trepository.findById(tuloId);
    } 
	
	
	@GetMapping("/addtulo")
	public String addTulo(Model model) {
		model.addAttribute("tulo", new Tulo());
		model.addAttribute("tulokategoriat", tkrepository.findAll());
		model.addAttribute("jasenet", jrepository.findAll());
		return "addtulo";
	}
	
	@PostMapping("/savetulo")
	public String savetulo(@Valid Tulo tulo, BindingResult bresult, Model model) {
		if (bresult.hasErrors()) {
			
			model.addAttribute("tulokategoriat", tkrepository.findAll());
			model.addAttribute("jasenet", jrepository.findAll());
			
			if (tulo.getTuloId() == null) {
				return "addtulo";
			} else {
				return "edittulo";
			}
			
		} else {
			trepository.save(tulo);
			return "redirect:tulolista";
		}
	}
	
	@GetMapping("/deletetulo/{id}")
	public String deleteTulo(@PathVariable("id") Long tuloId, Model model) {
		trepository.deleteById(tuloId);
		return "redirect:../tulolista";
	}
	
	//TODO
	@GetMapping("/edittulo/{id}")
	public String editTulo(@PathVariable("id") Long tuloId, Model model) {
		model.addAttribute("tulo", trepository.findById(tuloId));
		model.addAttribute("tulokategoriat", tkrepository.findAll());
		model.addAttribute("jasenet", jrepository.findAll());
		return "edittulo";
	}

}
