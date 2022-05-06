package com.dialenga.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class EquilibriumController {

	@GetMapping(value = "/index")
	public String index(ModelMap mw) {
		mw.addAttribute("titulo1", "Dialenga Prueba");
		mw.addAttribute("titulo2", "Indice de equilibrio");
		return "index";
	}


	@GetMapping(value = "/listarTodas")
	public String listarTodas(ModelMap mw) {
		mw.addAttribute("titulo1", "Dialenga Prueba");
		mw.addAttribute("titulo2", "Indice de equilibrio");
		return "index";
	}

}
