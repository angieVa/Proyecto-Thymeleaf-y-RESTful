package com.computacion.taller.Modelo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Dao.StoryDao;
import com.computacion.taller.Repository.GameRepository;
import com.computacion.taller.Repository.StoryRepository;
import com.computacion.taller.Service.GameServiceImp;
import com.computacion.taller.Service.StoryServiceImp;

@SpringBootTest
class TsscStoryTestU {

	
	@InjectMocks
	@Autowired
	private StoryServiceImp storyService;
	
//	@Mock
	@Autowired
	private GameServiceImp gameService;
	
	@Mock 
	private StoryDao storyRepository;

	
	//duda asociar juego a la historia - error al agregar en el juego por presuntamente lista nula
	//ADD
	
	@BeforeAll
	public static void beforeTest() {
		System.out.println("----------------INICIO-------------------");
	}
	
	@AfterAll
	public static void afterTest() {
		System.out.println("-----------------FIN--------------------");
	}
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void addStoryBusinessValueMenora1() {
			
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			List<TsscStory> st = new ArrayList<TsscStory>();
			game.setTsscStories(st);
			gameService.AddGame(game, 0, false);
			
			Optional<TsscGame> gameN = Optional.of(game);
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(0));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));

//			when(gameService.findById(game.getId())).thenReturn(true);
			when(storyRepository.existsById(story.getId())).thenReturn(false);
			
			storyService.AddStory(story, game.getId());
			
			assertFalse(storyService.findById(story.getId()));
		}
		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void addStoryInitialSprintMenora1() {
		
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			List<TsscStory> st = new ArrayList<TsscStory>();
			game.setTsscStories(st);
			gameService.AddGame(game, 0, false);

			Optional<TsscGame> gameN = Optional.of(game);
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(0));
			story.setPriority(BigDecimal.valueOf(2));
			
//			when(gameService.findById(game.getId())).thenReturn(true);
			when(storyRepository.existsById(story.getId())).thenReturn(false);
			
			storyService.AddStory(story, game.getId());
			
			assertFalse(storyService.findById(story.getId()));
		}
		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void addStoryPriorityMenora1() {
		
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			List<TsscStory> st = new ArrayList<TsscStory>();
			game.setTsscStories(st);
			gameService.AddGame(game, 0, false);
			
			Optional<TsscGame> gameN = Optional.of(game);
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(0));
			
//			when(gameService.findById(game.getId())).thenReturn(true);
			when(storyRepository.existsById(story.getId())).thenReturn(false);
			
			storyService.AddStory(story, game.getId());
			
			assertFalse(storyService.findById(story.getId()));
		}
//		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void addStoryGameIncorrecto() {
		
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(0);
			List<TsscStory> st = new ArrayList<TsscStory>();
			game.setTsscStories(st);
			gameService.AddGame(game, 0, false);
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
//			when(gameService.findById(game.getId())).thenReturn(false);
			when(storyRepository.existsById(story.getId())).thenReturn(false);
			
			storyService.AddStory(story, game.getId());
			
			assertFalse(storyService.findById(story.getId()));
		}
//		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void addStoryCorrecto() {
		
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			List<TsscStory> st = new ArrayList<TsscStory>();
			game.setTsscStories(st);
			gameService.AddGame(game, 0, false);
			
			Optional<TsscGame> gameN = Optional.of(game);
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
			
			
//			when(gameService.findById(game.getId())).thenReturn(true);
//			when(gameService.findByIdR(game.getId())).thenReturn(gameN);
			when(storyRepository.existsById(story.getId())).thenReturn(true);
//			when(gameService.addStory(game, story)).thenReturn(story);
				
			storyService.AddStory(story, game.getId());
			
			assertTrue(storyService.findById(story.getId()));
		}
//		
//		
//		//UPDATE
//		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void UpdateStoryCorrecto() {
			
			String altDescShown = "descripcion 1";
			String altDescripton = "desceripcion 2";
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			List<TsscStory> st = new ArrayList<TsscStory>();
			game.setTsscStories(st);
			gameService.AddGame(game, 0, false);
			
			storyService.AddStory(story, game.getId());
			
			when(storyRepository.existsById(story.getId())).thenReturn(true);
			
			TsscStory storyR = storyService.UpdateStory(story, altDescShown, altDescripton);
			
//			Optional<TsscStory> storyN = Optional.of(storyR);
			when(storyRepository.findById(story.getId())).thenReturn(story);
			
			assertEquals(storyService.findByIdR(story.getId()).get().getAltDescShown(), story.getAltDescShown());
			assertEquals(storyService.findByIdR(story.getId()).get().getAltDescripton(), story.getAltDescripton());
		}
		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void UpdateStoryAltDescShownNull() {
		
			String altDescShown = "descripcion 1";
			String altDescripton = "desceripcion 2";
		
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			List<TsscStory> st = new ArrayList<TsscStory>();
			game.setTsscStories(st);
			gameService.AddGame(game, 0, false);
			
			storyService.AddStory(story, game.getId());
			
			when(storyRepository.existsById(story.getId())).thenReturn(true);
			storyService.UpdateStory(story, null, altDescripton);
			
			Optional<TsscStory> storyN = Optional.of(story);
			when(storyRepository.findById(story.getId())).thenReturn(story);
			
			assertNotEquals(storyService.findByIdR(story.getId()).get().getAltDescShown(), altDescShown);
			assertNotEquals(storyService.findByIdR(story.getId()).get().getAltDescripton(), altDescripton);
		}
//		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void UpdateStoryAltDescShownVacio() {
		
			String altDescShown = "descripcion 1";
			String altDescripton = "desceripcion 2";
		
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			List<TsscStory> st = new ArrayList<TsscStory>();
			game.setTsscStories(st);
			gameService.AddGame(game, 0, false);
			
			storyService.AddStory(story, game.getId());
			
			when(storyRepository.existsById(story.getId())).thenReturn(true);
			storyService.UpdateStory(story, "", altDescripton);
			
			Optional<TsscStory> storyN = Optional.of(story);
			when(storyRepository.findById(story.getId())).thenReturn(story);
			
			assertNotEquals(storyService.findByIdR(story.getId()).get().getAltDescShown(), altDescShown);
			assertNotEquals(storyService.findByIdR(story.getId()).get().getAltDescripton(), altDescripton);
		}
//		
//		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void UpdateStoryAltDescriptonNull() {
		
			String altDescShown = "descripcion 1";
			String altDescripton = "desceripcion 2";
		
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			List<TsscStory> st = new ArrayList<TsscStory>();
			game.setTsscStories(st);
			gameService.AddGame(game, 0, false);
			
			storyService.AddStory(story, game.getId());
			
			when(storyRepository.existsById(story.getId())).thenReturn(true);
			storyService.UpdateStory(story, altDescShown, null);
			
			Optional<TsscStory> storyN = Optional.of(story);
			when(storyRepository.findById(story.getId())).thenReturn(story);
			
			assertNotEquals(storyService.findByIdR(story.getId()).get().getAltDescShown(), altDescShown);
			assertNotEquals(storyService.findByIdR(story.getId()).get().getAltDescripton(), altDescripton);
			
			
		}
//		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void UpdateStoryAltDescriptonVacio() {
		
			String altDescShown = "descripcion 1";
			String altDescripton = "desceripcion 2";
		
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
			
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			List<TsscStory> st = new ArrayList<TsscStory>();
			game.setTsscStories(st);
			gameService.AddGame(game, 0, false);
			
			storyService.AddStory(story, game.getId());
			
			
			when(storyRepository.existsById(story.getId())).thenReturn(true);
			storyService.UpdateStory(story, altDescShown, "");
			
			Optional<TsscStory> storyN = Optional.of(story);
			when(storyRepository.findById(story.getId())).thenReturn(story);
			
			assertNotEquals(storyService.findByIdR(story.getId()).get().getAltDescShown(), altDescShown);
			assertNotEquals(storyService.findByIdR(story.getId()).get().getAltDescripton(), altDescripton);
			
		}
//		


}
