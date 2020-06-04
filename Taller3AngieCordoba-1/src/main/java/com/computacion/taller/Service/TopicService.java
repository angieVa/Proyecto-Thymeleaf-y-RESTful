package com.computacion.taller.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Modelo.TsscTimecontrol;
import com.computacion.taller.Modelo.TsscTopic;

public interface TopicService {
	
	public TsscTopic AddTopic(TsscTopic topic);
	public TsscTopic UpdateTopic(TsscTopic topic, String name, String description);
	public boolean findById(long id);
	public Optional<TsscTopic> findByIdR(long id);
	public List<TsscStory> getStories(TsscTopic topic);
	public List<TsscTimecontrol> getTimesControl(TsscTopic topic);
	public Iterable<TsscTopic> findAll();
	public void deleteTopic(TsscTopic tsscTopic);
	
	public void update(TsscTopic topic);
	public List<Object[]>  findTopicsByGameDate(LocalDate date);

}
