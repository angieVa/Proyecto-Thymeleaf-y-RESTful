package com.computacion.taller.Dao;

import java.util.List;

import com.computacion.taller.Modelo.TsscAdmin;


public interface AdminDao {
	
	public TsscAdmin save(TsscAdmin entity);
	public TsscAdmin merge(TsscAdmin entity);
	public void delete(TsscAdmin entity);
	public TsscAdmin findById(long id);
	public List<TsscAdmin> findAll();
	public TsscAdmin findByUser(String user);

}
