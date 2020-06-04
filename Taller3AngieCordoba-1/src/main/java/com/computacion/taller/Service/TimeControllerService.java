package com.computacion.taller.Service;

import com.computacion.taller.Modelo.TsscTimecontrol;

public interface TimeControllerService {
	
	
	public TsscTimecontrol AddTimecontrol(TsscTimecontrol time, long id);
	public TsscTimecontrol UpdateTimeControl(TsscTimecontrol time);
	public TsscTimecontrol findTimeById(Long id); 
	public boolean existsById(Long id);
	public Iterable<TsscTimecontrol> findAll();
	public void deleteTime(TsscTimecontrol time);
	

}
