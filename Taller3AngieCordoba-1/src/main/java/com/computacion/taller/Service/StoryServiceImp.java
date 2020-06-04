package com.computacion.taller.Service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Dao.StoryDao;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Repository.GameRepository;
import com.computacion.taller.Repository.StoryRepository;

@Service
public class StoryServiceImp implements StoryService{
	
//	@Autowired
//	private StoryRepository storyRepository;
//	@Autowired
//	private GameServiceImp gameService;
//	
//	@Autowired
//	public StoryServiceImp(StoryRepository storyRepository, GameServiceImp gameService) {
//		this.storyRepository = storyRepository;
//		this.gameService = gameService;
//	}
//
//	@Override
//	public TsscStory UpdateStory(TsscStory story, String altDescShown, String altDescripton) {
//		
//		if(altDescShown!=null && altDescripton!=null && story!=null  && storyRepository.existsById(story.getId()) && !altDescShown.equals("") && !altDescripton.equals("")) {
//			story.setAltDescShown(altDescShown);
//			story.setAltDescripton(altDescripton);
//			storyRepository.save(story);
//			return story;
//		}
//		
//		return null;		
//	}
//
//	//duda asociar juego a la historia - error al agregar en el juego por presuntamente lista nula
//	@Override
//	public TsscStory AddStory(TsscStory story, long idGame) {
//		
//
//		if(story!=null && story.getBusinessValue().compareTo(BigDecimal.valueOf(0))==1 
//				&& story.getInitialSprint().compareTo(BigDecimal.valueOf(0))==1
//				&& story.getPriority().compareTo(BigDecimal.valueOf(0))==1 
//				&& gameService.findById(idGame)) {
//			
//			TsscGame game = gameService.findByIdR(idGame).get();
//			System.out.println(game.getNGroups());
//			gameService.addStory(game, story);
//			storyRepository.save(story);
//			
//			return story;
//		}
//		
//		return null;
//	}
//
//	@Override
//	public Optional<TsscStory> findByIdR(long id) {	
//		return storyRepository.findById(id);
//	}
//
//	@Override
//	public boolean findById(long id) {
//		return storyRepository.existsById(id);
//	}
//
//	@Override
//	public void deleteAll() {
//		storyRepository.deleteAll();
//		gameService.deleteAll();
//		
//	}
//
//	@Override
//	public void deleteStory(TsscStory story) {
//		TsscGame game = story.getTsscGame();
//		game.getTsscStories().remove(story);
//		storyRepository.delete(story);
//		
//	}
//
//	@Override
//	public Iterable<TsscStory> findAll() {
//		return storyRepository.findAll();
//	}

	@Autowired
	private StoryDao storyRepository;
	@Autowired
	private GameServiceImp gameService;
	
	@Autowired
	public StoryServiceImp(StoryDao storyRepository, GameServiceImp gameService) {
		this.storyRepository = storyRepository;
		this.gameService = gameService;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscStory UpdateStory(TsscStory story, String altDescShown, String altDescripton) {
		
		if(altDescShown!=null && altDescripton!=null && story!=null  && storyRepository.existsById(story.getId()) && !altDescShown.equals("") && !altDescripton.equals("")) {
			story.setAltDescShown(altDescShown);
			story.setAltDescripton(altDescripton);
			storyRepository.save(story);
			return story;
		}
		
		return null;		
	}

	//duda asociar juego a la historia - error al agregar en el juego por presuntamente lista nula
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscStory AddStory(TsscStory story, long idGame) {
		

		if(story!=null && story.getBusinessValue().compareTo(BigDecimal.valueOf(0))==1 
				&& story.getInitialSprint().compareTo(BigDecimal.valueOf(0))==1
				&& story.getPriority().compareTo(BigDecimal.valueOf(0))==1 
				&& gameService.findById(idGame)) {
			
			TsscGame game = gameService.findByIdR(idGame).get();
			System.out.println(game.getNGroups());
			gameService.addStory(game, story);
			story.setTsscGame(game);
			storyRepository.save(story);
			
			return story;
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Optional<TsscStory> findByIdR(long id) {	
		
		Optional<TsscStory> st = Optional.of(storyRepository.findById(id));
		
		return st;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean findById(long id) {
		return storyRepository.existsById(id);
	}

	//llamar merge

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteStory(TsscStory story) {
		TsscGame game = story.getTsscGame();
		game.getTsscStories().remove(story);
		gameService.update(game);
		storyRepository.delete(story);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Iterable<TsscStory> findAll() {
		return storyRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void Update(TsscStory story) {
		storyRepository.merge(story);
		
	}

}
