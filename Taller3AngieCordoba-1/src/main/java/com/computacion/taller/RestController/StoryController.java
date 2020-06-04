package com.computacion.taller.RestController;

import com.computacion.taller.Modelo.TsscStory;

public interface StoryController {

	public TsscStory AddStory(TsscStory Story, long id);
	public void UpdateStory(TsscStory Story);
	public TsscStory findStoryById(long id);
//	public boolean existbyId(long id);
	public Iterable<TsscStory> findAll();
	public TsscStory DeleteStory(long id);
}
