package com.computacion.taller.Delegate;

import java.util.List;

import com.computacion.taller.Modelo.TsscTimecontrol;

public interface TimeControllerDelegate {
	
	
	public TsscTimecontrol add(TsscTimecontrol entity);
	public void update(TsscTimecontrol entity);
	public void delete(TsscTimecontrol entity);
	public TsscTimecontrol findById(long id);
	public List<TsscTimecontrol> findAll();	
		

}
