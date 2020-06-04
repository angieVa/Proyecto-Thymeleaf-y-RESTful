package com.computacion.taller.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;


public interface GameController {
	
	public void UpdateGame(TsscGame game);
	public TsscGame AddGame(TsscGame gameOne) ;
	public TsscGame findGameById(long id);  
	public Iterable<TsscGame> findAll();
	public Iterable<TsscStory> findHistorias();
	public TsscGame DeleteGame(long id);
	
	public List<TsscGame> ConsultaGame(@RequestBody String date);
	

}
