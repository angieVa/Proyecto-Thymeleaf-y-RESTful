package com.computacion.taller.Controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.computacion.taller.Delegate.GameDelegateImpt;
import com.computacion.taller.Delegate.TopicDelegateImpt;
import com.computacion.taller.Modelo.Consulta;
import com.computacion.taller.Modelo.TsscGame;

@Controller
public class ConsultasController {
	
	private GameDelegateImpt gameService;

	private TopicDelegateImpt topicService;

	@Autowired
	public ConsultasController(GameDelegateImpt gameService, TopicDelegateImpt topicService) {
		
		this.gameService = gameService;
		this.topicService = topicService;
	}
	
	
	@GetMapping("/consulta/")
	public String indexConsulta(Model model) {
		
		model.addAttribute("consulta", new Consulta());
		return "consulta/indexCon";
	}
	
	@PostMapping("/consulta/")
	public ModelAndView PostConsultas(@Validated Consulta consulta, BindingResult bindingResult,
			@RequestParam(value = "action", required = false) String action, @RequestParam(value = "action2", required = false) String action2, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			
			return new ModelAndView("consulta/indexCon", "consulta", consulta);
		

		} else {
			if (action != null && action.equals("Buscar Temas")) {
					
				return new ModelAndView("consulta/consultaTopic", "consultas", topicService.consulta(consulta));
			}else {
				
				return new ModelAndView("consulta/consultaGame", "games", gameService.consulta(consulta));
			}
			
		}
		
		//return new ModelAndView("consulta/indexCon", "consulta", consulta);

	}
	

}
