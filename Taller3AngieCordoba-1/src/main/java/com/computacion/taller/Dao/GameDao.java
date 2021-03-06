package com.computacion.taller.Dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.computacion.taller.Modelo.TsscGame;


public interface GameDao {

	public TsscGame save(TsscGame entity);
	public TsscGame merge(TsscGame entity);
	public void delete(TsscGame entity);
	public TsscGame findById(long id);
	public List<TsscGame> findAll();
	
	public boolean existsById(long id);
	
	//1b)nombre, descripcion , id del tema PREGUNTAR
	//*****************************************
	public List<TsscGame> findByNameTopic(String name);
	public List<TsscGame> findByDescriptionTopic(String description);
	public List<TsscGame> findByIdTopic(long idTopic);
	
	//1c) juntos o separados??
	public List<TsscGame> findByDates(LocalDate date1, LocalDate date2);
	public List<TsscGame> findByDateHours(LocalDate date, LocalTime h1, LocalTime h2);
	
	
	//2B)
	
	public List<TsscGame> findByDateStoryTime(LocalDate date);
	
	//3??
}
