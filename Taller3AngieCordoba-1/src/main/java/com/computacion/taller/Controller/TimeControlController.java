package com.computacion.taller.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.computacion.taller.Delegate.GameDelegateImpt;
import com.computacion.taller.Delegate.TimeControllerDelegateImp;
import com.computacion.taller.Delegate.TopicDelegateImpt;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscTimecontrol;
import com.computacion.taller.Modelo.TsscTopic;


@Controller
public class TimeControlController {
	
	private TimeControllerDelegateImp timeService;
	
	private GameDelegateImpt gameService;

	@Autowired
	public TimeControlController(TimeControllerDelegateImp timeService, GameDelegateImpt gameService) {
		this.timeService = timeService;
		this.gameService = gameService;
	}

	@GetMapping("/time/")
	public String indexTimeControl(Model model) {
		model.addAttribute("times", timeService.findAll());
		return "time/index";
	}

	@GetMapping("/time/add")
	public String addTimeControl(Model model) {
		model.addAttribute("tsscTimecontrol", new TsscTimecontrol());
		model.addAttribute("games", gameService.findAll());
		return "time/add-time";
	}

	@PostMapping("/time/add")
	public String AddTimePost(@Validated TsscTimecontrol tsscTime, BindingResult bindingResult, 
			Model model, @RequestParam(value = "action", required = true) String action) {

		if (bindingResult.hasErrors()) {
			
			model.addAttribute("games", gameService.findAll());
			return "time/add-time";

		} else {
			if (!action.equals("Cancelar")) {
					timeService.add(tsscTime);
			}	
			return "redirect:/time/";
		}
	}

	
	@GetMapping("/time/edit/{id}")
	public String updateTime(@PathVariable("id") long id, Model model) {
		TsscTimecontrol tsscTime = timeService.findById(id);

		if (tsscTime == null)
			throw new IllegalArgumentException("Invalid Time Control Id:" + id);

		model.addAttribute("tsscTimecontrol", tsscTime);
		model.addAttribute("games", gameService.findAll());
		
		return "time/update-time";
	}

	@PostMapping("/time/edit/{id}")
	public String updateTime(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			@Validated TsscTimecontrol tsscTime, BindingResult bindingResult, Model model) {

		if (action.equals("Cancelar")) {
			
			return "redirect:/time/";
		}

		if (bindingResult.hasErrors()) {
			
			model.addAttribute("games", gameService.findAll());
			return "time/update-time";
		}

		if (action != null && !action.equals("Cancelar")) {
			
				timeService.update(tsscTime);	
		}

		return "redirect:/time/";
	}

	
	@GetMapping("/time/del/{id}")
	public String deleteTime(@PathVariable("id") long id) {
		
		TsscTimecontrol time = timeService.findById(id);
	
//		for(TsscGame game : gameService.findAll()) {
//			
//			if(game.getTsscTopic() != null) {
//				
//				if(game.getTsscTopic().equals(tsscTopic)) {
//					game.setTsscTopic(null);
//				}
//			}
//			
//		}
		
		timeService.delete(time);
		return "redirect:/time/";
	}

}
