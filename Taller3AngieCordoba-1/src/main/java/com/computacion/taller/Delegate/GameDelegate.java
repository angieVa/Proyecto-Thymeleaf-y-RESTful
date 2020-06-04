package com.computacion.taller.Delegate;

import java.util.List;

import com.computacion.taller.Modelo.Consulta;
import com.computacion.taller.Modelo.TsscGame;

public interface GameDelegate {

	
	public TsscGame add(TsscGame entity);
	public void update(TsscGame entity);
	public void delete(TsscGame entity);
	public TsscGame findById(long id);
	public List<TsscGame> findAll();
	public boolean existsById(Long id);
	
	public List<TsscGame>  consulta(Consulta date);
	
	
}
