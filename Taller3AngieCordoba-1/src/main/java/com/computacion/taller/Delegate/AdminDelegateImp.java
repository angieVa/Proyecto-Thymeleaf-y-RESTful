package com.computacion.taller.Delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.computacion.taller.Modelo.TsscAdmin;
import com.computacion.taller.Modelo.TsscTopic;

@Component
public class AdminDelegateImp implements AdminDelegate{
	
	private RestTemplate restTemplate;
	final String SERVER="http://localhost:8080/";
	
	
	public AdminDelegateImp() {
		this.restTemplate = new RestTemplate();
	}


	@Override
	public TsscAdmin add(TsscAdmin entity) {
		
		TsscAdmin admin = restTemplate.postForObject(SERVER +"api/admins", entity, TsscAdmin.class);	
		return admin;
	}

	@Override
	public void update(TsscAdmin entity) {
		
		restTemplate.put(SERVER+"api/admins", entity, TsscAdmin.class);		
	}

	@Override
	public void delete(TsscAdmin entity) {
		restTemplate.delete(SERVER+"api/admins/"+entity.getId());
		
	}

	@Override
	public TsscAdmin findById(long id) {

		TsscAdmin admin = restTemplate.getForObject(SERVER+"api/admins/"+id, TsscAdmin.class); 
		return admin;
	}

	@Override
	public Iterable<TsscAdmin> findAll() {
	
		TsscAdmin[] admins = restTemplate.getForObject(SERVER+"api/admins",TsscAdmin[].class );
		List<TsscAdmin> nueva = Arrays.asList(admins);
		
		return nueva;
	}

}
