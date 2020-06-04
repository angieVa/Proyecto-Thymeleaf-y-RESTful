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

import com.computacion.taller.Delegate.GameDelegateImpt;
import com.computacion.taller.Delegate.TimeControllerDelegateImp;
import com.computacion.taller.Delegate.TopicDelegateImpt;
import com.computacion.taller.Delegate.storyDelegateImpt;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Modelo.TsscTimecontrol;
import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Service.GameServiceImp;
import com.computacion.taller.Service.StoryServiceImp;
import com.computacion.taller.Service.TopicServiceImp;

@Controller
public class GameController {

	private GameDelegateImpt gameService;

	private TopicDelegateImpt topicService;
	
	private storyDelegateImpt storyService;
	
	private TimeControllerDelegateImp timeService;

	@Autowired
	public GameController(GameDelegateImpt gameService, TopicDelegateImpt topicService, storyDelegateImpt storyService, TimeControllerDelegateImp timeService) {
		this.gameService = gameService;
		this.topicService = topicService;
		this.storyService = storyService;
		this.timeService = timeService;
	}

	@GetMapping("/game/")
	public String indexGame(Model model) {
		model.addAttribute("games", gameService.findAll());
		return "game/index";
	}

	@GetMapping("/game/add")
	public String addGame(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		model.addAttribute("topics", topicService.findAll());
		return "game/add-game";
	}

	@PostMapping("/game/add")
	public String addGamePost(@Validated TsscGame tsscGame, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("topics", topicService.findAll());
			return "game/add-game";

		} else {
			if (!action.equals("Cancelar")) {
				
					gameService.add(tsscGame);
			}	
			return "redirect:/game/";
		}

	}


	@GetMapping("/game/edit/{id}")
	public String updateGame(@PathVariable("id") long id, Model model) {
		TsscGame tsscGame = gameService.findById(id);

		if (tsscGame == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);

		model.addAttribute("tsscGame", tsscGame);
		model.addAttribute("name", tsscGame.getName());
		model.addAttribute("adminPassword", tsscGame.getAdminPassword());
		model.addAttribute("scheduledDate", tsscGame.getScheduledDate());
		model.addAttribute("scheduledTime", tsscGame.getScheduledTime());
		model.addAttribute("nGroups", tsscGame.getNGroups());
		model.addAttribute("nSprints", tsscGame.getNSprints());
		model.addAttribute("userPassword", tsscGame.getUserPassword());
		model.addAttribute("guestPassword", tsscGame.getGuestPassword());
		model.addAttribute("topics", topicService.findAll());

		return "game/update-game";
	}

	@PostMapping("/game/edit/{id}")
	public String updateGamePost(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			@Validated TsscGame tsscGame, BindingResult bindingResult, Model model) {

		if (action.equals("Cancelar")) {

			return "redirect:/game/";
		}

		if (bindingResult.hasErrors()) {
			
			model.addAttribute("topics", topicService.findAll());
			return "game/update-game";
		}

		if (action != null && !action.equals("Cancelar")) {
			
			gameService.update(tsscGame);
		}

		return "redirect:/game/";
	}

	@GetMapping("/game/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		TsscGame tsscGame = gameService.findById(id);
		
//		if(tsscGame.getTsscStories() != null) {
//			for(int i = 0; i < tsscGame.getTsscStories().size(); i++) {
//				storyService.delete(tsscGame.getTsscStories().get(i));
//			}
//		}
//		
//		if(tsscGame.getTsscTimecontrols() != null) {
//			for(int i = 0; i < tsscGame.getTsscTimecontrols().size(); i++) {
//				timeService.delete(tsscGame.getTsscTimecontrols().get(i));
//			}
//		}
//		tsscGame.setTsscStories(null);
		
		gameService.delete(tsscGame);
		return "redirect:/game/";
	}
	
	@GetMapping("/game/topic/{id}")
	public String indexTopic(Model model, @PathVariable("id") long id) {
		List<TsscTopic> list = new ArrayList<TsscTopic>();
		TsscGame tsscGame = gameService.findById(id);
		List<TsscGame> list2 = new ArrayList<TsscGame>();
		list2.add(tsscGame);
		if(tsscGame.getTsscTopic()!=null) {
			list.add(tsscGame.getTsscTopic());
		}
		
		model.addAttribute("game", list2);
		model.addAttribute("topics", list);
		
		return "game/index-topic";
	}
	
	@GetMapping("/game/add-topic/{id}")
	public String ChangeTopic(Model model, @PathVariable("id") long id) {
		
		TsscGame game = gameService.findById(id);
		model.addAttribute("tsscGame", game);
		model.addAttribute("topics", topicService.findAll());
		return "game/add-topic";
	}
	
	@PostMapping("/game/add-topic/{id}")
	public String ChangeTopicPost(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			TsscGame tsscGame, Model model) {
		
		
		if (action.equals("Cancelar")) {

			return "redirect:/game/";
		}

		if (action != null && !action.equals("Cancelar")) {

			System.out.println(id);
			TsscGame game = gameService.findById(id);
			game.setTsscTopic(tsscGame.getTsscTopic());
			//CAMBIO
			gameService.update(game);
		}

		return "redirect:/game/";

	}
	
	@GetMapping("/game/story/{id}")
	public String indexStory(Model model, @PathVariable("id") long id) {
		
		TsscGame game = gameService.findById(id);
		List<TsscStory> st = new ArrayList<TsscStory>();
		game.setTsscStories(st);

		for (int i = 0; i < storyService.findAll().size(); i++) {

			if (storyService.findAll().get(i).getTsscGame().getName().equals(game.getName())) {

				game.addTsscStory(storyService.findAll().get(i));
			}

		}
		
		List<TsscGame> games = new ArrayList<TsscGame>();
		games.add(game);
		
		model.addAttribute("stories", game.getTsscStories());
		model.addAttribute("game", games);
		
		return "game/index-stories";
	}
	
	@GetMapping("/game/time/{id}")
	public String indexTime(Model model, @PathVariable("id") long id) {
		
		TsscGame game = gameService.findById(id);
		List<TsscTimecontrol> times = new ArrayList<TsscTimecontrol>();
		game.setTsscTimecontrol(times);

		for (int i = 0; i < timeService.findAll().size(); i++) {

			if (timeService.findAll().get(i).getTsscGame().getName().equals(game.getName())) {

				game.addTimeControl(timeService.findAll().get(i));
			}

		}
		
		model.addAttribute("times", game.getTsscTimecontrols());
		
		return "game/index-time";
	}
	
	

	@GetMapping("/game/{idg}/story/edit/{id}")
	public String EditStory(Model model, @PathVariable("id") long id, @PathVariable("idg") long idg) {
		
		TsscStory sto = storyService.findById(id);
		TsscGame game = gameService.findById(idg);
		List<TsscGame> games = new ArrayList<>();
		games.add(game);
		
		model.addAttribute("tsscStory", sto);
		model.addAttribute("games", games);
		return "game/edit-story";
	}

	@PostMapping("/game/{idg}/story/edit/{id}")
	public String EditStoryPost(@RequestParam(value = "action", required = true) String action,
	@Validated TsscStory tsscStory, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			
			TsscGame game = gameService.findById(tsscStory.getTsscGame().getId());
			List<TsscGame> games = new ArrayList<>();
			games.add(game);
	
			model.addAttribute("tsscStory", tsscStory);
			model.addAttribute("games", games);
			return "game/edit-story";

		} else {
			if (!action.equals("Cancelar")) {
				
					storyService.update(tsscStory);
			}	
			return "redirect:/game/";
		}

	}
	
	
	@GetMapping("/game/{idg}/time/edit/{id}")
	public String EditTime(Model model, @PathVariable("id") long id, @PathVariable("idg") long idg) {
		
		TsscTimecontrol time = timeService.findById(id);
		TsscGame game = gameService.findById(idg);
		List<TsscGame> games = new ArrayList<>();
		games.add(game);
		
		model.addAttribute("tsscTimecontrol", time);
		model.addAttribute("games", games);
		return "game/edit-time";
	}

	@PostMapping("/game/{idg}/time/edit/{id}")
	public String EditTimePost(@RequestParam(value = "action", required = true) String action,
	@Validated TsscTimecontrol tssctime, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			
			TsscGame game = gameService.findById(tssctime.getTsscGame().getId());
			List<TsscGame> games = new ArrayList<>();
			games.add(game);
	
			model.addAttribute("tsscTimecontrol", tssctime);
			model.addAttribute("games", games);
			return "game/edit-time";

		} else {
			if (!action.equals("Cancelar")) {
				
					timeService.update(tssctime);
			}	
			return "redirect:/game/";
		}

	}

	@GetMapping("/game/{idg}/story/add")
	public String AddStory(Model model, @PathVariable("idg") long idg) {
		
		TsscStory sto = new TsscStory();
		
		TsscGame game = gameService.findById(idg);
		sto.setTsscGame(game);
		
		List<TsscGame> games = new ArrayList<>();
		games.add(game);
		
		model.addAttribute("tsscStory", sto);
		model.addAttribute("games", games);
		return "game/add-story";
	}

	@PostMapping("/game/{idg}/story/add")
	public String AddStoryPost(@RequestParam(value = "action", required = true) String action,
	@Validated TsscStory tsscStory, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			TsscGame game = gameService.findById(tsscStory.getTsscGame().getId());
			List<TsscGame> games = new ArrayList<>();
			games.add(game);
	
			model.addAttribute("tsscStory", tsscStory);
			model.addAttribute("games", games);
			return "game/add-story";

		} else {
			if (!action.equals("Cancelar")) {
				
					storyService.add(tsscStory);
			}	
			return "redirect:/game/";
		}

	}


	
}
