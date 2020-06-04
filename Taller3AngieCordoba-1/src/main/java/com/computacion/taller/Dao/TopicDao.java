package com.computacion.taller.Dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.util.Pair;
import com.computacion.taller.Modelo.TsscTopic;


public interface TopicDao {
	
	public TsscTopic save(TsscTopic entity);
	public TsscTopic merge(TsscTopic entity);
	public void delete(TsscTopic entity);
	public TsscTopic findById(long id);
	public List<TsscTopic> findAll();
	
	public boolean existsById(long id);
	
	//1a)
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String description);
	
	//2a)
	public List<Object[]>  findTopicsByGameDate(LocalDate date);
	

}
