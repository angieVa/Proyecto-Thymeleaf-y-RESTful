package com.computacion.taller.Controller;

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

import com.computacion.taller.Delegate.GameDelegateImpt;
import com.computacion.taller.Delegate.storyDelegateImpt;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Service.GameServiceImp;
import com.computacion.taller.Service.StoryServiceImp;


@Controller
public class StoryController {
	
	private storyDelegateImpt storyService;

	private GameDelegateImpt gameService;

	@Autowired
	public StoryController(storyDelegateImpt storyService, GameDelegateImpt gameService) {
		this.storyService = storyService;
		this.gameService = gameService;
	}

	@GetMapping("/story/")
	public String indexStory(Model model) {
		model.addAttribute("stories", storyService.findAll());
		return "story/index";
	}

	@GetMapping("/story/add")
	public String addStory(Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("games", gameService.findAll());
		return "story/add-story";
	}

	@PostMapping("/story/add")
	public String addStoryPost(@Validated TsscStory tsscStory, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("games", gameService.findAll());
			return "story/add-story";

		} else {
			if (!action.equals("Cancelar")) {
				
				storyService.add(tsscStory);
			}	
			return "redirect:/story/";
		}
	}

	@GetMapping("/story/edit/{id}")
	public String updateStory(@PathVariable("id") long id, Model model) {
		TsscStory tsscStory = storyService.findById(id);

		if (tsscStory == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);

		model.addAttribute("tsscStory", tsscStory);
		model.addAttribute("games", gameService.findAll());

		return "story/update-story";
	}

	@PostMapping("story/edit/{id}")
	public String updateStoryPost(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			@Validated TsscStory tsscStory, BindingResult bindingResult, Model model) {

		if (action.equals("Cancelar")) {

			return "redirect:/story/";
		}

		if (bindingResult.hasErrors()) {

			model.addAttribute("games", gameService.findAll());			
			return "story/update-story";
		}

		if (!action.equals("Cancelar")) {

			
				storyService.update(tsscStory);
		}

		return "redirect:/story/";
	}

	@GetMapping("/story/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		TsscStory tsscStory = storyService.findById(id);
		storyService.delete(tsscStory);
		return "redirect:/story/";
	}


}
