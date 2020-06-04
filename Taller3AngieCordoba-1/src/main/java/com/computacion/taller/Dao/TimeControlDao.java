package com.computacion.taller.Dao;

import java.util.List;

import com.computacion.taller.Modelo.TsscTimecontrol;

public interface TimeControlDao {
	
	public TsscTimecontrol save(TsscTimecontrol entity);
	public TsscTimecontrol merge(TsscTimecontrol entity);
	public void delete(TsscTimecontrol entity);
	public TsscTimecontrol findById(long id);
	public List<TsscTimecontrol> findAll();

}
