package com.computacion.taller.Delegate;

import java.time.LocalDate;
import java.util.List;

import com.computacion.taller.Modelo.Consulta;
import com.computacion.taller.Modelo.ConsultaTopic;
import com.computacion.taller.Modelo.TsscTopic;


public interface TopicDelegate {

	public TsscTopic add(TsscTopic entity);
	public void update(TsscTopic entity);
	public void delete(TsscTopic entity);
	public TsscTopic findById(long id);
	public Iterable<TsscTopic> findAll();
	public List<ConsultaTopic> consulta(Consulta date);
	
}
