package hh.swd20.taloudenhallinta.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import hh.swd20.taloudenhallinta.domain.Tulokategoria;
import hh.swd20.taloudenhallinta.domain.TulokategoriaRepository;

@CrossOrigin
@Controller
public class TulokategoriaController {
	
	@Autowired
	private TulokategoriaRepository tkrepository;
	
	// RESTful service to get all expense categories
    @RequestMapping(value="/tulokategoriat", method = RequestMethod.GET)
    public @ResponseBody List<Tulokategoria> tulokategoriaListRest() {	
        return (List<Tulokategoria>) tkrepository.findAll();
    }
    
	// RESTful service to get an expense category by id
    @RequestMapping(value="/tulokategoriat/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Tulokategoria> findTulokategoriaRest(@PathVariable("id") Long tulokategoriaId) {	
    	return tkrepository.findById(tulokategoriaId);
    } 
	
	@GetMapping("/addtulokategoria")
	public String addTulokategoria(Model model) {
		model.addAttribute("tulokategoria", new Tulokategoria());
		return "addtulokategoria";
	}
	
	@PostMapping("/savetulokategoria")
	public String saveTulokategoria(@Valid Tulokategoria tulokategoria, BindingResult bresult) {
		if (bresult.hasErrors()) {
			if (tulokategoria.getTulokategoriaId() == null) {
				return "addtulokategoria";
			} else {
				return "edittulokategoria";
			}
		} else {
			tkrepository.save(tulokategoria);
			return "redirect:kategorialista";
		}
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
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
