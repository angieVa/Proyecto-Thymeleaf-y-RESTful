package com.computacion.taller.Modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Service.GameServiceImp;
import com.computacion.taller.Service.StoryServiceImp;

@SpringBootTest
class ATsscStoryTest {

	@Autowired 
	private StoryServiceImp storyService;
	@Autowired
	private GameServiceImp gameService;

	//ADD
	
	@BeforeAll
	public static void beforeTest() {
		System.out.println("----------------INICIO-------------------");
	}
	
	@AfterAll
	public static void afterTest() {
		System.out.println("-----------------FIN--------------------");
	}
		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void addStoryGameIncorrecto() {
		
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(0);
			gameService.AddGame(game, 0, false);
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
			storyService.AddStory(story, game.getId());
			
			assertFalse(storyService.findById(story.getId()));
		}
		
	
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void addStoryCorrecto() {
			
			List<TsscStory> list = new ArrayList<TsscStory>();
		
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			game.setTsscStories(list);
			gameService.AddGame(game, 0, false);
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
			storyService.AddStory(story, game.getId());
			
			assertTrue(storyService.findById(story.getId()));
			assertNotNull(storyService.findByIdR(story.getId()).get().getTsscGame());
		}
		
		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void addStoryIncorrecto() {
		
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			gameService.AddGame(game, 0, false);
	
			assertNull(storyService.AddStory(null, game.getId()));
		}
		
		//UPDATE
		
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void UpdateStoryCorrecto() {
			
			String altDescShown = "descripcion 1";
			String altDescripton = "descripcion 2";
			
			List<TsscStory> list = new ArrayList<TsscStory>();
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			game.setTsscStories(list);
			gameService.AddGame(game, 0, false);
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
			storyService.AddStory(story, game.getId());
			storyService.UpdateStory(story, altDescShown, altDescripton);
			
			assertEquals(storyService.findByIdR(story.getId()).get().getAltDescShown(), altDescShown);
			assertEquals(storyService.findByIdR(story.getId()).get().getAltDescripton(), altDescripton);
		}
	
		@Test
		@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void UpdateStoryInvalido() {
		
			String altDescShown = "descripcion 1";
			String altDescripton = "descripcion 2";
			
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			gameService.AddGame(game, 0, false);
			
			TsscStory story = new TsscStory();
			story.setBusinessValue(BigDecimal.valueOf(2));
			story.setInitialSprint(BigDecimal.valueOf(2));
			story.setPriority(BigDecimal.valueOf(2));
			
			assertNull(storyService.UpdateStory(story, altDescShown, altDescripton));		
		}

		

}
