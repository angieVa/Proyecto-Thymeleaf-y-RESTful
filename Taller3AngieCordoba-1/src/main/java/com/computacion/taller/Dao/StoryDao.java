package com.computacion.taller.Dao;

import java.util.List;
import com.computacion.taller.Modelo.TsscStory;

public interface StoryDao {
	
	public TsscStory save(TsscStory entity);
	public TsscStory merge(TsscStory entity);
	public void delete(TsscStory entity);
	public TsscStory findById(long id);
	public List<TsscStory> findAll();
	public boolean existsById(long id);

}
