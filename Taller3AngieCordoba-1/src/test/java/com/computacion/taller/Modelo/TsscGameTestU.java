package com.computacion.taller.Modelo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

import com.computacion.taller.Repository.GameRepository;
import com.computacion.taller.Service.GameServiceImp;
import com.computacion.taller.Service.TopicServiceImp;

@SpringBootTest
class TsscGameTestU {


	@Autowired
	@InjectMocks
	private GameServiceImp gameService;

	@Autowired
	private TopicServiceImp topicService;
	
	@Mock
	private GameRepository gameRepository;
	
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
	
	//ADD
	//DUDA GAME NULL VERIFICAR
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGameGrupoMenora1() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(0);
		game.setNSprints(3);
		gameService.AddGame(game, 0, false);
		
		when(gameRepository.existsById(game.getId())).thenReturn(false);
		
		assertFalse(gameService.findById(game.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGameSpringMenora1() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(0);
		gameService.AddGame(game, 0, false);
		
		when(gameRepository.existsById(game.getId())).thenReturn(false);
		
		assertFalse(gameService.findById(game.getId()));
	}
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGameCorrecto() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		gameService.AddGame(game, 0, false);
		
		when(gameRepository.existsById(game.getId())).thenReturn(true);	
		assertTrue(gameService.findById(game.getId()));

	}
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGameTopicCorrecto() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		
		List<TsscGame> list = new ArrayList<TsscGame>();
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		tsstopic.setTsscGames(list);
		List<TsscStory> stories = new ArrayList<TsscStory>();
		List<TsscTimecontrol> times = new ArrayList<TsscTimecontrol>();
		tsstopic.setTsscStories(stories);
		tsstopic.setTsscCronograma(times);
		
		Optional<TsscTopic> topic = Optional.of(tsstopic);
		topicService.AddTopic(tsstopic);
//		when(topicService.findById(tsstopic.getId())).thenReturn(true);
//		when(topicService.findByIdR(tsstopic.getId())).thenReturn(topic);
		when(gameRepository.existsById(game.getId())).thenReturn(true);
		
		gameService.AddGame(game, tsstopic.getId(), true);
		
		assertTrue(gameService.findById(game.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGameTopicIncorrecto() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(0);
		tsstopic.setDefaultSprints(2);
		
		topicService.AddTopic(tsstopic);
		Optional<TsscTopic> topic = Optional.of(tsstopic);
		
//		when(topicService.findById(tsstopic.getId())).thenReturn(false);
		when(gameRepository.existsById(game.getId())).thenReturn(false);
		
		gameService.AddGame(game, tsstopic.getId(), true);
		
		assertFalse(gameService.findById(game.getId()));
	}
	
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
		when(gameRepository.existsById(game.getId())).thenReturn(true);
		
		TsscGame gameR = gameService.UpdateGame(game, name, adminPassword);
		
		Optional<TsscGame> gameN = Optional.of(gameR);
		when(gameRepository.findById(game.getId())).thenReturn(gameN);
		
		assertEquals(gameService.findByIdR(game.getId()).get().getName(), game.getName());
		assertEquals(gameService.findByIdR(game.getId()).get().getAdminPassword(), game.getAdminPassword());
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateGameNameNull() {
			
		TsscGame game = new TsscGame();
		
		String adminPassword ="pass";
		String name = "Los vaqueros";
		game.setNGroups(3);
		game.setNSprints(3);
		
		gameService.AddGame(game, 0, false);
		when(gameRepository.existsById(game.getId())).thenReturn(true);
		
		gameService.UpdateGame(game, null, adminPassword);
		
		Optional<TsscGame> gameN = Optional.of(game);
		when(gameRepository.findById(game.getId())).thenReturn(gameN);
		
		assertNotEquals(gameService.findByIdR(game.getId()).get().getName(), name);
		assertNotEquals(gameService.findByIdR(game.getId()).get().getAdminPassword(), adminPassword);	

	}
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateGameNameVacio() {
	
		TsscGame game = new TsscGame();
		
		String adminPassword ="pass";
		String name = "Los vaqueros";
		game.setNGroups(3);
		game.setNSprints(3);
		
		gameService.AddGame(game, 0, false);
		when(gameRepository.existsById(game.getId())).thenReturn(true);
		
		gameService.UpdateGame(game, "", adminPassword);
		
		Optional<TsscGame> gameN = Optional.of(game);
		when(gameRepository.findById(game.getId())).thenReturn(gameN);
		
		assertNotEquals(gameService.findByIdR(game.getId()).get().getName(), name);
		assertNotEquals(gameService.findByIdR(game.getId()).get().getAdminPassword(), adminPassword);	
	}
//	
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateGameAdminPasswordNull() {
	
		TsscGame game = new TsscGame();
		
		String adminPassword ="pass";
		String name = "Los vaqueros";
		game.setNGroups(3);
		game.setNSprints(3);
		
		gameService.AddGame(game, 0, false);
		when(gameRepository.existsById(game.getId())).thenReturn(true);
		
		gameService.UpdateGame(game, name, null);
		
		Optional<TsscGame> gameN = Optional.of(game);
		when(gameRepository.findById(game.getId())).thenReturn(gameN);
		
		assertNotEquals(gameService.findByIdR(game.getId()).get().getName(), name);
		assertNotEquals(gameService.findByIdR(game.getId()).get().getAdminPassword(), adminPassword);	
			
	}
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateGameAdminPasswordVacio() {
	
		TsscGame game = new TsscGame();
		
		String adminPassword ="pass";
		String name = "Los vaqueros";
		game.setNGroups(3);
		game.setNSprints(3);
		
		gameService.AddGame(game, 0, false);
		when(gameRepository.existsById(game.getId())).thenReturn(true);
		
		gameService.UpdateGame(game, name, "");
		
		Optional<TsscGame> gameN = Optional.of(game);
		when(gameRepository.findById(game.getId())).thenReturn(gameN);
		
		assertNotEquals(gameService.findByIdR(game.getId()).get().getName(), name);
		assertNotEquals(gameService.findByIdR(game.getId()).get().getAdminPassword(), adminPassword);	
		
	}
//	
//	//JUEGO DOS
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGame2Correcto() {
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		
		Optional<TsscTopic> topic = Optional.of(tsstopic);
		
		List<TsscGame> list = new ArrayList<TsscGame>();
		List<TsscStory> stories = new ArrayList<TsscStory>();
		List<TsscTimecontrol> times = new ArrayList<TsscTimecontrol>();
		tsstopic.setTsscStories(stories);
		tsstopic.setTsscCronograma(times);
		tsstopic.setTsscGames(list);
		
		topicService.AddTopic(tsstopic);
//		
//		when(topicService.findById(tsstopic.getId())).thenReturn(true);
//		when(topicService.findByIdR(tsstopic.getId())).thenReturn(topic);
//		when(topicService.getStories(tsstopic)).thenReturn(stories);
//		when(topicService.getTimesControl(tsstopic)).thenReturn(times);
		when(gameRepository.existsById(game.getId())).thenReturn(true);
		
		gameService.AddGame2(game, tsstopic.getId());
		
		Optional<TsscGame> gameR = Optional.of(game);
//		when(gameService.findByIdR(game.getId())).thenReturn(gameR);
		
		assertTrue(gameService.findById(game.getId()));
	//	assertNotNull(gameService.findByIdR(game.getId()).get().getTsscStories());
	//	assertNotNull(gameService.findByIdR(game.getId()).get().getTsscTimecontrols());
	}
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGame2GrupoMenora1() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(0);
		game.setNSprints(3);
		
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		
		gameService.AddGame2(game, tsstopic.getId());	
		when(gameRepository.existsById(game.getId())).thenReturn(false);
		
		assertFalse(gameService.findById(game.getId()));
		

	}
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addGame2SpringMenora1() {
	
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(0);
		
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);	
	
		gameService.AddGame2(game, tsstopic.getId());	
		when(gameRepository.existsById(game.getId())).thenReturn(false);
		
		assertFalse(gameService.findById(game.getId()));
		
	}

}
