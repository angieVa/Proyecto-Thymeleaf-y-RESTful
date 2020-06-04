package com.computacion.taller.Modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Service.GameServiceImp;
import com.computacion.taller.Service.TopicServiceImp;


@SpringBootTest
class ATsscGameTest {

	@Autowired
	private GameServiceImp gameService;
	@Autowired
	private TopicServiceImp topicService;
	
	@BeforeAll
	public static void beforeTest() {
		System.out.println("----------------INICIO-------------------");
	}
	
	@AfterAll
	public static void afterTest() {
		System.out.println("-----------------FIN--------------------");
	}
	
	//ADD
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGameCorrecto() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		gameService.AddGame(game, 0, false);
		
		assertTrue(gameService.findById(game.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGameIncorrecto() {
		
		assertNull(gameService.AddGame(null, 0, false));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGameIncorrectoConTopic() {
		
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		
		topicService.AddTopic(tsstopic);
		
		assertNull(gameService.AddGame(null, tsstopic.getId(), true));
	}
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGameTopicCorrecto() {
		
		List<TsscGame> list = new ArrayList<TsscGame>();
	
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		
		
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		tsstopic.setTsscGames(list);
		
		topicService.AddTopic(tsstopic);
		gameService.AddGame(game, tsstopic.getId(), true);
		
		assertTrue(gameService.findById(game.getId()));
	}
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGameTopicIncorrecto() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(0);
		tsstopic.setDefaultSprints(2);

		gameService.AddGame(game, tsstopic.getId(), true);
		
		assertFalse(gameService.findById(game.getId()));
	}
//	
//	//duda metodos update
//	
//	//UPDATE
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateGameCorrecto() {
	
		TsscGame game = new TsscGame();
		
		String name = "Los vaqueros";
		String adminPassword ="pass";
		game.setNGroups(3);
		game.setNSprints(3);
		
		gameService.AddGame(game, 0, false);
		gameService.UpdateGame(game, name, adminPassword);
		
		assertEquals(gameService.findByIdR(game.getId()).get().getName(), name);
		assertEquals(gameService.findByIdR(game.getId()).get().getAdminPassword(), adminPassword);
	}
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateGameInvalido() {
	
		TsscGame game = new TsscGame();
		
		String name = "Los vaqueros";
		String adminPassword ="pass";
		game.setNGroups(3);
		game.setNSprints(3);
		
		assertNull(gameService.UpdateGame(game, name, adminPassword));		
	}
	
	//JUEGO DOS
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGame2Correcto() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		
		List<TsscGame> list = new ArrayList<TsscGame>();
		List<TsscStory> listS = new ArrayList<TsscStory>();
		List<TsscTimecontrol> listT = new ArrayList<TsscTimecontrol>();
		
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		tsstopic.setTsscGames(list);
		tsstopic.setTsscStories(listS);
		tsstopic.setTsscCronograma(listT);
		
		topicService.AddTopic(tsstopic);
		gameService.AddGame2(game, tsstopic.getId());
		
		assertTrue(gameService.findById(game.getId()));
		assertNotNull(gameService.findByIdR(game.getId()).get().getTsscStories());
		assertNotNull(gameService.findByIdR(game.getId()).get().getTsscTimecontrols());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGame2TopicIncorrecto() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(0);
		tsstopic.setDefaultSprints(2);

		gameService.AddGame2(game, tsstopic.getId());
		
		assertFalse(gameService.findById(game.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGame2Incorrecto() {
		
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(2);
		tsstopic.setDefaultSprints(2);
		topicService.AddTopic(tsstopic);
		
		assertNull(gameService.AddGame2(null, tsstopic.getId()));
	}
	

}
