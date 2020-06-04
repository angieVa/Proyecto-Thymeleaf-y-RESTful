package com.computacion.taller.Delegate;

import com.computacion.taller.Modelo.TsscAdmin;

public interface AdminDelegate {
	
	public TsscAdmin add(TsscAdmin entity);
	public void update(TsscAdmin entity);
	public void delete(TsscAdmin entity);
	public TsscAdmin findById(long id);
	public Iterable<TsscAdmin> findAll();

}
