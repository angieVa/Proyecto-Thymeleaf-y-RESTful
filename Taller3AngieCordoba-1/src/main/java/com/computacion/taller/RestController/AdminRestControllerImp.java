package com.computacion.taller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.computacion.taller.Modelo.TsscAdmin;
import com.computacion.taller.Service.AdminService;

@RestController
public class AdminRestControllerImp implements AdminRestController{
	
	
	@Autowired
	private AdminService service;


	@Override
	@PutMapping("/api/admins")
	public void UpdateAdmin(TsscAdmin admin) {
		
		service.updateAdmin(admin);		
	}

	@Override
	@PostMapping("/api/admins")
	public TsscAdmin AddAdmin(TsscAdmin admin) {
		
		return service.addAdmin(admin);
	}

	@Override
	@GetMapping("/api/admins/{id}")
	public TsscAdmin findAdminById(@PathVariable long id) {
		
		return service.findById(id);
	}

	@Override
	@GetMapping("/api/admins")
	public Iterable<TsscAdmin> findAll() {
		return service.findAll();
	}

	@Override
	@DeleteMapping("/api/admins/{id}")
	public TsscAdmin DeleteAdmin(@PathVariable long id) {
		TsscAdmin del = service.findById(id);
		service.deleteAdmin(del);
		return del;
	}

}
