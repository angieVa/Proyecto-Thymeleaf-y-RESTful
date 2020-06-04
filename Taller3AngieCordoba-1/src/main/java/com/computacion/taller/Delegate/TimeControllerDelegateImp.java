package com.computacion.taller.Delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Modelo.TsscTimecontrol;

@Component
public class TimeControllerDelegateImp implements TimeControllerDelegate  {

	
	private RestTemplate restTemplate;
	final String SERVER="http://localhost:8080/";
	
	public TimeControllerDelegateImp() {
		this.restTemplate = new RestTemplate ();
	}
	
	@Override
	public TsscTimecontrol add(TsscTimecontrol entity) {
		TsscTimecontrol encontrado= restTemplate.postForObject(SERVER +"api/times/games/"+entity.getTsscGame().getId(), entity, TsscTimecontrol.class);
		return encontrado;
	}

	@Override
	public void update(TsscTimecontrol entity) {
		restTemplate.put(SERVER+"api/times", entity, TsscTimecontrol.class);
	}

	@Override
	public void delete(TsscTimecontrol entity) {
		restTemplate.delete(SERVER+"api/times/"+entity.getId());
		
	}

	@Override
	public TsscTimecontrol findById(long id) {
		TsscTimecontrol encontrado= restTemplate.getForObject(SERVER+"api/times/"+id, TsscTimecontrol.class ); 
		return encontrado;
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		TsscTimecontrol[] times = restTemplate.getForObject(SERVER+"api/times", TsscTimecontrol[].class );
		List<TsscTimecontrol> nueva= Arrays.asList(times);
		return nueva;
	}

}
