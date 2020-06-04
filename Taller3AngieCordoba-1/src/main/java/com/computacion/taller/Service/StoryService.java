package com.computacion.taller.Service;

import java.util.Optional;

import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;


public interface StoryService {
	
	public TsscStory AddStory(TsscStory story, long idGame);
	public TsscStory UpdateStory(TsscStory story, String altDescShown, String altDescripton);
	public Optional<TsscStory> findByIdR(long id);
	public boolean findById(long id);
	public void deleteStory(TsscStory story);
	public Iterable<TsscStory> findAll();
	public void Update(TsscStory story);

}
