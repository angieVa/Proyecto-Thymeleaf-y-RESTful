package com.computacion.taller.Service;

import java.util.List;

import com.computacion.taller.Modelo.TsscAdmin;

public interface AdminService {

	public TsscAdmin addAdmin(TsscAdmin admin);
	public TsscAdmin updateAdmin(TsscAdmin admin);
	public void deleteAdmin(TsscAdmin admin);
	public TsscAdmin findById(long id);
	public List<TsscAdmin> findAll();
}
