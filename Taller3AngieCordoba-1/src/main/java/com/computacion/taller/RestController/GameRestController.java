package com.computacion.taller.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.computacion.taller.Modelo.ConsultaTopic;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Service.GameService;


@RestController
public class GameRestController  implements GameController{
	
	@Autowired
	private GameService service;

	@Override
	@PutMapping("/api/games")
	public void UpdateGame(@RequestBody TsscGame game) {
		service.update(game);
	}	
	
	@Override
	@PostMapping("/api/games")
	public TsscGame AddGame(@RequestBody TsscGame gameOne) {
		
		if(gameOne.getTsscTopic()==null) {
		return service.AddGame(gameOne, 0, false);
		}
		else{
			return service.AddGame(gameOne, gameOne.getTsscTopic().getId(), true);
		}
	}

	@Override
	@GetMapping("/api/games/{id}")
	public TsscGame findGameById(@PathVariable long id) {
	
		return service.findByIdR(id).get();
	}
	
	@Override
	@GetMapping("/api/games")
	public Iterable<TsscGame> findAll() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	
	@Override
	@DeleteMapping("/api/games/{id}")
	public TsscGame DeleteGame(@PathVariable long id) {
		
		TsscGame encontrado = service.findByIdR(id).get();
		service.deleteGame(encontrado);
		
		return encontrado;
	}
	
	@Override
	@GetMapping("/api/games/consulta/{date}")
	public List<TsscGame> ConsultaGame(@PathVariable String date){
		
		LocalDate dateF = LocalDate.parse(date);
		List<TsscGame> lista = service.consulta(dateF);
		return lista;
	}

	@Override
	public Iterable<TsscStory> findHistorias() {
		// TODO Auto-generated method stub
		return null;
	}



}
