package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Persona;

@Controller
public class ControladorFormulario {

	@RequestMapping("/formulario")
	public ModelAndView irAForm() {

		ModelMap modelo = new ModelMap();
		
		Persona persona= new Persona();
		modelo.put("persona", persona);
		
		return new ModelAndView("formulario1", modelo);		
		
	}
	
	@RequestMapping(path = "/enviar-formulario", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("persona") Persona persona, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		model.put("datos", persona.toString());
		return new ModelAndView("formulario1", model);
	}
}