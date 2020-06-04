package com.computacion.taller.Delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.computacion.taller.Modelo.TsscStory;


@Component
public class storyDelegateImpt implements storyDelegate {

	
	
	private RestTemplate restTemplate;
	final String SERVER="http://localhost:8080/";
	

	public storyDelegateImpt() {
		this.restTemplate = new RestTemplate();
	}
	
	
	@Override
	public TsscStory add(TsscStory nuevo) {
		TsscStory encontrado= restTemplate.postForObject(SERVER +"api/stories/games/"+nuevo.getTsscGame().getId(), nuevo, TsscStory.class);
		return encontrado;
	}

	@Override
	public void update(TsscStory entity) {
		// TODO Auto-generated method stub
		restTemplate.put(SERVER+"api/stories", entity, TsscStory.class);
	}

	@Override
	public void delete(TsscStory nuevo) {
		restTemplate.delete(SERVER+"api/stories/"+nuevo.getId());
		
		
	}

	@Override
	public TsscStory findById(long id) {
		TsscStory encontrado=restTemplate.getForObject(SERVER+"api/stories/"+id, TsscStory.class ); 
		return encontrado;
	}

	@Override
	public List<TsscStory> findAll() {
		TsscStory[] stories= restTemplate.getForObject(SERVER+"api/stories",TsscStory[].class );
		List<TsscStory> nueva= Arrays.asList(stories);
		return nueva;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
