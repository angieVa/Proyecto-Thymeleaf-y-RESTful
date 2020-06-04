package com.computacion.taller.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Modelo.TsscTimecontrol;
import com.computacion.taller.Modelo.TsscTopic;

public interface GameService {

	public TsscGame UpdateGame(TsscGame game, String name, String adminPassword);
	public TsscGame AddGame(TsscGame game, long idTopic, boolean isTopic);
	public TsscGame AddGame2(TsscGame game, long idTopic);
	public boolean findById(long id);
	public Optional<TsscGame> findByIdR(long id);
	public TsscStory addStory(TsscGame game, TsscStory story);
	public TsscTimecontrol addTimeControl(TsscGame game, TsscTimecontrol time);
	public void deleteGame(TsscGame game);
	public Iterable<TsscGame> findAll();
	public void update(TsscGame game);
	
	public List<TsscGame> consulta(LocalDate date);
	
}
