package com.computacion.taller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.computacion.taller.Delegate.GameDelegateImpt;
import com.computacion.taller.Delegate.TopicDelegateImpt;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Service.GameServiceImp;
import com.computacion.taller.Service.TopicServiceImp;


@Controller
public class TopicController {
	
	private TopicDelegateImpt topicService;
	
	private GameDelegateImpt gameService;

	@Autowired
	public TopicController(TopicDelegateImpt topicService, GameDelegateImpt gameService) {
		this.topicService = topicService;
		this.gameService = gameService;
	}

	@GetMapping("/topic/")
	public String indexTopic(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "topic/index";
	}

	@GetMapping("/topic/add")
	public String addTopic(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "topic/add-topic";
	}

	@PostMapping("/topic/add")
	public String AddTopicPost(@Validated TsscTopic tsscTopic, BindingResult bindingResult, 
			Model model, @RequestParam(value = "action", required = true) String action) {

		if (bindingResult.hasErrors()) {
			
			return "topic/add-topic";

		} else {
			if (!action.equals("Cancelar")) {
					topicService.add(tsscTopic);
			}	
			return "redirect:/topic/";
		}
	}

	
	@GetMapping("/topic/edit/{id}")
	public String updateTopic(@PathVariable("id") long id, Model model) {
		TsscTopic tsscTopic = topicService.findById(id);

		if (tsscTopic == null)
			throw new IllegalArgumentException("Invalid topic Id:" + id);

		model.addAttribute("tsscTopic", tsscTopic);
		
		return "topic/update-topic";
	}

	@PostMapping("/topic/edit/{id}")
	public String updateTopicPost(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			@Validated TsscTopic tsscTopic, BindingResult bindingResult, Model model) {

		if (action.equals("Cancelar")) {

			return "redirect:/topic/";
		}

		if (bindingResult.hasErrors()) {

			return "topic/update-topic";
		}

		if (action != null && !action.equals("Cancelar")) {
			
				topicService.update(tsscTopic);	
		}

		return "redirect:/topic/";
	}

	
	@GetMapping("/topic/del/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		
		TsscTopic tsscTopic = topicService.findById(id);
		
		for (TsscGame juegos : gameService.findAll()) {
	        if (juegos.getTsscTopic() != null && juegos.getTsscTopic().getName().equals(tsscTopic.getName())) {
	            juegos.setTsscTopic(null);
	            gameService.update(juegos);
	        }

	    }

	        tsscTopic.setTsscGames(null);
	        topicService.update(tsscTopic);
	        topicService.delete(tsscTopic);
	        
		return "redirect:/topic/";
	}

	

}
