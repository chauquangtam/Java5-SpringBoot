package edu.poly.fpt.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.fpt.models.Depart;
import edu.poly.fpt.service.DepartService;

@Controller
@RequestMapping("/departs")
public class DepartController {
	@Autowired
	private DepartService departService;

	@RequestMapping("/list")
	public String listDeparts(ModelMap model) {
		List<Depart> list = (List<Depart>) departService.findAll();
		model.addAttribute("departs", list);
		return "departs/list";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {
		model.addAttribute("depart", new Depart());
		return "departs/addOrEdit";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute Depart depart) {
		String message = "New depart inserted!";
		if (depart.getId() != null && depart.getId() > 0) {
			message = "The depart updated!";
		}
		departService.save(depart);
		model.addAttribute(depart);
		model.addAttribute("message", message);
		return "redirect:/departs/list";
	}

	@GetMapping("/edit/{id}")
	public String edit(ModelMap model, @PathVariable(name = "id") Integer id) {
		Optional<Depart> opDepart = departService.findById(id);
		if (opDepart.isPresent()) {
			model.addAttribute("depart", opDepart.get());
		} else {
			return listDeparts(model);
		}
		return "departs/addOrEdit";
	}

	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Integer id) {
		departService.deleteById(id);
		System.out.println(id);
		return "redirect:/departs/list";
	}

	@RequestMapping("/find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") String name) {
		List<Depart> list = (List<Depart>) departService.findByNameLikeOrderByNameAsc(name);
		model.addAttribute("departs", list);
		return "departs/find";
	}
}
