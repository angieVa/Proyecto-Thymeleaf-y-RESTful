package com.computacion.taller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.computacion.taller.Modelo.TsscTimecontrol;
import com.computacion.taller.Service.TimeControllerServiceImp;

@RestController
public class TimeRestControllerImpt implements TimeRestController{


	@Autowired
	private TimeControllerServiceImp service;
	
	@Override
	@PostMapping("/api/times/games/{id}")
	public TsscTimecontrol AddTimeControl(@RequestBody TsscTimecontrol Time, @PathVariable long id) {
		
		return service.AddTimecontrol(Time, id);
	}

	@Override
	@GetMapping("/api/times/{id}")
	public TsscTimecontrol findTimeControlById(@PathVariable long id) {
		
		return service.findTimeById(id);
	}

	@Override
	@GetMapping("/api/times")
	public Iterable<TsscTimecontrol> findAll() {
		
		return service.findAll();
	}

	@Override
	@PutMapping("/api/times")
	public void Update(@RequestBody TsscTimecontrol time) {
		
		service.UpdateTimeControl(time);
	}

	@Override
	@DeleteMapping("/api/times/{id}")
	public TsscTimecontrol DeleteTimeControl(@PathVariable long id) {
	
		TsscTimecontrol encontrado = service.findTimeById(id);
		service.deleteTime(encontrado);
		return encontrado;
	}

}
