package com.computacion.taller.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Dao.GameDao;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Modelo.TsscTimecontrol;
import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Repository.GameRepository;
import com.computacion.taller.Repository.TopicRepository;

@Service
public class GameServiceImp implements GameService{
	
//	@Autowired
//	private GameRepository gameRepository;
//	@Autowired
//	private TopicServiceImp topicService;
//	
//	@Autowired
//	public GameServiceImp(GameRepository gameRepository, TopicServiceImp topicService) {
//		this.gameRepository = gameRepository;
//		this.topicService = topicService;
//	}
//	
//	//JUEGOS 2? PUNTO 6
//	//SETTEAR GRUPOS Y SPRINT EN LAS PRUEBAS?
//
//	@Override
//	public TsscGame AddGame(TsscGame game, long idTopic, boolean isTopic) {
//		
//		
//		if(game!=null && game.getNGroups()>0 && game.getNSprints()>0) {
//			
//			if(isTopic) {
//			if(topicService.findById(idTopic)) {
//				TsscTopic topic = topicService.findByIdR(idTopic).get();
//				game.setTsscTopic(topic);
//				gameRepository.save(game);
//				return game;
//			}else {
//				return null;
//			}
//			}else {
//				boolean f = gameRepository.existsById(game.getId());
//				gameRepository.save(game);
//				boolean a = gameRepository.existsById(game.getId());
//				return game;
//			}
//					
//		}
//		
//		return null;
//	}
//
//	@Override
//	public TsscGame UpdateGame(TsscGame game, String name, String adminPassword) {
//		
//		if(name!=null && adminPassword!=null && game!=null && gameRepository.existsById(game.getId()) && !name.equals("") && !adminPassword.equals("")) {
//			game.setName(name);
//			game.setAdminPassword(adminPassword);
//			gameRepository.save(game);
//			return game;
//		}
//		
//		return null;
//	}
//	
//	@Override
//	public Optional<TsscGame> findByIdR(long id) {	
//		return gameRepository.findById(id);
//	}
//
//	@Override
//	public TsscGame AddGame2(TsscGame game, long idTopic) {
//		
//		long g = gameRepository.count();
//		
//		if(game!=null && game.getNGroups()>0 && game.getNSprints()>0) {
//			
//			
//			if(topicService.findById(idTopic)) {
//				
//				TsscTopic topic = topicService.findByIdR(idTopic).get();
//				game.setTsscTopic(topic);
//				game.setTsscStories(topicService.getStories(topic));
//				game.setTsscTimecontrol(topicService.getTimesControl(topic));
//				gameRepository.save(game);
//				return game;
//				
//			}else {
//				return null;
//			}
//			
//					
//		}
//		
//		return null;
//	}
//
//	@Override
//	public boolean findById(long id) {
//		return gameRepository.existsById(id);
//	}
//
//	@Override
//	public TsscStory addStory(TsscGame game, TsscStory story) {
//		// TODO Auto-generated method stub
//		return game.addTsscStory(story);
//	}
//
//	@Override
//	public void deleteAll() {
//		
//		gameRepository.deleteAll();
//		topicService.deleteAll();
//		
//	}
//
//	@Override
//	public void deleteGame(TsscGame game) {
//		gameRepository.delete(game);
//		
//	}
//
//	@Override
//	public Iterable<TsscGame> findAll() {
//		// TODO Auto-generated method stub
//		return gameRepository.findAll();
//	}

	
	@Autowired
	private GameDao gameRepository;
	@Autowired
	private TopicServiceImp topicService;
	
	@Autowired
	public GameServiceImp(GameDao gameRepository, TopicServiceImp topicService) {
		this.gameRepository = gameRepository;
		this.topicService = topicService;
	}
	
	//JUEGOS 2? PUNTO 6
	//SETTEAR GRUPOS Y SPRINT EN LAS PRUEBAS?

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscGame AddGame(TsscGame game, long idTopic, boolean isTopic) {
		
		
		if(game!=null && game.getNGroups()>0 && game.getNSprints()>0) {
			
			if(isTopic) {
			if(topicService.findById(idTopic)) {
				TsscTopic topic = topicService.findByIdR(idTopic).get();
				game.setTsscTopic(topic);
				gameRepository.save(game);
				topic.addTsscGame(game);
				topicService.update(topic);
				return game;
			}else {
				return null;
			}
			}else {
				boolean f = gameRepository.existsById(game.getId());
				gameRepository.save(game);
				boolean a = gameRepository.existsById(game.getId());
				return game;
			}
					
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscGame UpdateGame(TsscGame game, String name, String adminPassword) {
		
		if(name!=null && adminPassword!=null && game!=null && gameRepository.existsById(game.getId()) && !name.equals("") && !adminPassword.equals("")) {
			game.setName(name);
			game.setAdminPassword(adminPassword);
			gameRepository.save(game);
			return game;
		}
		
		return null;
	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Optional<TsscGame> findByIdR(long id) {	
		
		Optional<TsscGame> op = Optional.of(gameRepository.findById(id));	
		return op;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscGame AddGame2(TsscGame game, long idTopic) {
		
		if(game!=null && game.getNGroups()>0 && game.getNSprints()>0) {
			
			
			if(topicService.findById(idTopic)) {
				
				TsscTopic topic = topicService.findByIdR(idTopic).get();
				game.setTsscTopic(topic);
				game.setTsscStories(topicService.getStories(topic));
				game.setTsscTimecontrol(topicService.getTimesControl(topic));
				gameRepository.save(game);
				topic.addTsscGame(game);
				topicService.update(topic);			
				return game;
				
			}else {
				return null;
			}
			
					
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean findById(long id) {
		return gameRepository.existsById(id);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscStory addStory(TsscGame game, TsscStory story) {
		game.addTsscStory(story);
		gameRepository.merge(game);
		return story;
	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscTimecontrol addTimeControl(TsscGame game, TsscTimecontrol time) {
		game.addTimeControl(time);
		gameRepository.merge(game);
		return time;
	}


	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteGame(TsscGame game) {
		gameRepository.delete(game);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Iterable<TsscGame> findAll() {
		// TODO Auto-generated method stub
		return gameRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(TsscGame game) {
		gameRepository.merge(game);
		
	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)	
	public List<TsscGame> consulta(LocalDate date){
		return gameRepository.findByDateStoryTime(date);
	}

}
