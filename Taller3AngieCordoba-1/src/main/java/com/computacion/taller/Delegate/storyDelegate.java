package com.computacion.taller.Delegate;

import java.util.List;

import com.computacion.taller.Modelo.TsscStory;


public interface storyDelegate {
	
	
	
	public TsscStory add(TsscStory entity);
	public void update(TsscStory entity);
	public void delete(TsscStory entity);
	public TsscStory findById(long id);
	public List<TsscStory> findAll();
	public boolean existsById(Long id);
	
	
	
	
	
	

}
