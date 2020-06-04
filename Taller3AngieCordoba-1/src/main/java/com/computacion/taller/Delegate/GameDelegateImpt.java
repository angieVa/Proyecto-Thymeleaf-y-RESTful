package com.computacion.taller.Delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.computacion.taller.Modelo.Consulta;
import com.computacion.taller.Modelo.ConsultaTopic;
import com.computacion.taller.Modelo.TsscGame;


@Component
public class GameDelegateImpt implements GameDelegate{
	
	
	private RestTemplate restTemplate;
	final String SERVER="http://localhost:8080/";
	

	public GameDelegateImpt() {
		super();
		this.restTemplate = new RestTemplate();
	}

	@Override
	public TsscGame add(TsscGame nuevo) {
		TsscGame encontrado= restTemplate.postForObject(SERVER +"api/games", nuevo, TsscGame.class);
		return encontrado;
	}

	@Override
	public void update(TsscGame entity) {
		// TODO Auto-generated method stub
		restTemplate.put(SERVER+"api/games", entity, TsscGame.class);
	}

	@Override
	public void delete(TsscGame entity) {
		restTemplate.delete(SERVER+"api/games/" + entity.getId());
		
	}

	@Override
	public TsscGame findById(long id) {
		TsscGame encontrado= restTemplate.getForObject(SERVER+"api/games/"+id, TsscGame.class ); 
		return encontrado;
	}

	@Override
	public List<TsscGame> findAll() {
		TsscGame[] games= restTemplate.getForObject(SERVER+"api/games",TsscGame[].class );
		List<TsscGame> nueva= Arrays.asList(games);
		return nueva;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<TsscGame>  consulta(Consulta date){
		
		TsscGame[] list = restTemplate.getForObject(SERVER+"api/games/consulta/" + date.getDate(), TsscGame[].class );
		List<TsscGame> lista = Arrays.asList(list);
		return lista;
	}
	
	
}
