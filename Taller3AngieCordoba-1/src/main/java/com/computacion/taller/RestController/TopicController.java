package com.computacion.taller.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Modelo.Consulta;
import com.computacion.taller.Modelo.ConsultaTopic;;

public interface TopicController {
	
	public TsscTopic AddTopic(TsscTopic topic);
	//public TsscTopic ActualizarTopic(TsscTopic topic, String name, String Description );
	public TsscTopic findTopicById(long id); 
	//public boolean existeById(long id);
	public Iterable<TsscTopic> findAll();
	public void Update(TsscTopic topic);
	public TsscTopic DeleteTopic(long id);
	
	public List<ConsultaTopic> ConsultaTopic(@PathVariable String date);
	
	
	

}
