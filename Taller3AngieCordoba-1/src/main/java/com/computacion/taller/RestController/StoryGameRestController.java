package com.computacion.taller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Service.StoryService;

@RestController
public class StoryGameRestController implements StoryController{
	
	@Autowired
	private StoryService service;

	@Override
	@PostMapping("/api/stories/games/{id}")
	public TsscStory AddStory(@RequestBody TsscStory Story, @PathVariable long id) {
	
		return service.AddStory(Story, id);
	}

	@Override
	@PutMapping("/api/stories")
	public void UpdateStory(@RequestBody TsscStory Story) {
		service.Update(Story);
	}

	@Override
	@GetMapping("/api/stories/{id}")
	public TsscStory findStoryById(@PathVariable long id) {
		
		return service.findByIdR(id).get();
	}

	@Override
	@GetMapping("/api/stories")
	public Iterable<TsscStory> findAll() {
		
		return service.findAll();
	}

	@Override
	@DeleteMapping("/api/stories/{id}")
	public TsscStory DeleteStory(@PathVariable long id) {
		
		TsscStory encontrado = service.findByIdR(id).get();
		service.deleteStory(encontrado);
		return encontrado;
	}

}
