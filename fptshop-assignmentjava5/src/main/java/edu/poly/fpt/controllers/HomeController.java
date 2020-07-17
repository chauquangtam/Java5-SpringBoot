package edu.poly.fpt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/menu/icon")
	public String icon() {
		return "examples/icons";
	}
	
	@RequestMapping("/menu/google")
	public String google() {
		return "examples/map";
	}

	@RequestMapping("/menu/table")
	public String table() {
		return "examples/tables";
	}

	

	

	@RequestMapping("/menu/upgrade")
	public String upgrade() {
		return "examples/upgrade";
	}

	
}
