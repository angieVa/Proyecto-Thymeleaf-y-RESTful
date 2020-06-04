package com.computacion.taller.RestController;

import com.computacion.taller.Modelo.TsscTimecontrol;

public interface TimeRestController {
	
	public TsscTimecontrol AddTimeControl(TsscTimecontrol Time, long id);
	//public TsscTopic ActualizarTopic(TsscTopic topic, String name, String Description );
	public TsscTimecontrol findTimeControlById(long id); 
	//public boolean existeById(long id);
	public Iterable<TsscTimecontrol> findAll();
	public void Update(TsscTimecontrol time);
	public TsscTimecontrol DeleteTimeControl(long id);

}
