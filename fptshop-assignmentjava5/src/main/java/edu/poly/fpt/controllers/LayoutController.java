package edu.poly.fpt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.fpt.models.Depart;
import edu.poly.fpt.service.DepartService;

@Controller
public class LayoutController {
	@Autowired
	private DepartService departService;

	

}
