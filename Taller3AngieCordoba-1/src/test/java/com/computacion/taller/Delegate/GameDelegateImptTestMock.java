package com.computacion.taller.Delegate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.computacion.taller.Modelo.Consulta;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Service.TopicServiceImp;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class GameDelegateImptTestMock {


	final String SERVER = "http://localhost:8080/";

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private GameDelegateImpt gameServiceImp;

	@Autowired
	private TopicServiceImp topiService;

	@Autowired
	public GameDelegateImptTestMock(GameDelegateImpt gameServiceImp) {
		super();
		this.gameServiceImp = gameServiceImp;
	}

	@Test
	void testAddGameconTema() {

		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topiService.AddTopic(topic);
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		game.setTsscTopic(topic);

		when(restTemplate.postForObject(SERVER + "api/games", game, TsscGame.class)).thenReturn(game);
		assertNotNull(gameServiceImp.add(game));

	}
	
	@Test
	void testAddGameSinTema() {
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");

		when(restTemplate.postForObject(SERVER + "api/games", game, TsscGame.class)).thenReturn(game);
		assertNotNull(gameServiceImp.add(game));
	}

	@Test
	void testUpdateGameconTema() {

		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topiService.AddTopic(topic);
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		game.setTsscTopic(topic);

		when(restTemplate.postForObject(SERVER + "api/games", game, TsscGame.class)).thenReturn(game);

		assertNotNull(gameServiceImp.add(game));
		game.setName("Nuevo nombre");
		game.setNSprints(25);
		
		when(restTemplate.patchForObject(SERVER + "api/games", game, TsscGame.class)).thenReturn(game);
		gameServiceImp.update(game);
		
		when(restTemplate.getForObject(SERVER + "api/games/" + game.getId(), TsscGame.class)).thenReturn(game);
		assertEquals(gameServiceImp.findById(game.getId()).getName(), "Nuevo nombre");
		assertEquals(gameServiceImp.findById(game.getId()).getNSprints(), 25);

	}

	@Test
	void testDeleteGameconTema() {

		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topiService.AddTopic(topic);
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		game.setTsscTopic(topic);

		when(restTemplate.postForObject(SERVER + "api/games", game, TsscGame.class)).thenReturn(game);
		assertNotNull(gameServiceImp.add(game));
		
		restTemplate.delete(SERVER + "api/games/" + game.getId());
		
		gameServiceImp.delete(game);
		assertNull(gameServiceImp.findById(game.getId()));

	}
	
	@Test
	void findByDateStoryTimeTest() {

	
		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topiService.AddTopic(topic);
	
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		game.setName("Game A");
		game.setAdminPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setStartTime(LocalTime.NOON);
		game.setUserPassword("1234");
		game.setGuestPassword("1234");
		game.setTsscTopic(topic);
        
        
		when(restTemplate.postForObject(SERVER + "api/games", game, TsscGame.class)).thenReturn(game);
		assertNotNull(gameServiceImp.add(game));
		
		
		
		TsscTopic topic2 = new TsscTopic();
		topic2.setName("TopicA");
		topic2.setDescription("DescripcionTopic");
		topic2.setGroupPrefix("Grp");
		topic2.setDefaultGroups(2);
		topic2.setDefaultSprints(2);
		topiService.AddTopic(topic2);
	
		TsscGame game2 = new TsscGame();
		game2.setNGroups(1);
		game2.setNSprints(1);
		game2.setName("Game B");
		game2.setAdminPassword("1234");
		game2.setScheduledDate(LocalDate.now());
		game2.setStartTime(LocalTime.NOON);
		game2.setUserPassword("1234");
		game2.setGuestPassword("1234");
		game2.setTsscTopic(topic2);
        
        
		when(restTemplate.postForObject(SERVER + "api/games", game2, TsscGame.class)).thenReturn(game2);
		assertNotNull(gameServiceImp.add(game2));
		
		TsscGame[] games = new TsscGame[2];
		games[0]= game;
		games[1]= game2;
		
		when(restTemplate.getForObject(SERVER+"api/games/consulta/" + game.getScheduledDate().toString(), TsscGame[].class )).thenReturn(games);
		
		Consulta con = new Consulta(game.getScheduledDate().toString());
		assertEquals(gameServiceImp.consulta(con).size(), 2);

	}
	


}
