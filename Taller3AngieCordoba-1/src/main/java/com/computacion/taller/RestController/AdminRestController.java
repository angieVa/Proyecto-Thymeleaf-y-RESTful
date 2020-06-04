package com.computacion.taller.RestController;

import com.computacion.taller.Modelo.TsscAdmin;

public interface AdminRestController {
		
	public void UpdateAdmin(TsscAdmin admin);
	public TsscAdmin AddAdmin(TsscAdmin admin) ;
	public TsscAdmin findAdminById(long id);  
	public Iterable<TsscAdmin> findAll();
	public TsscAdmin DeleteAdmin(long id);

}
