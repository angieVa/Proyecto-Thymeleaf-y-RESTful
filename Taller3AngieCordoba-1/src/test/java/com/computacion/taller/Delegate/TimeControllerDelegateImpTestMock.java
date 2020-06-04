package com.computacion.taller.Delegate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Modelo.TsscTimecontrol;
import com.computacion.taller.Service.GameServiceImp;

import org.junit.jupiter.api.Test;


@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TimeControllerDelegateImpTestMock {

	final String SERVER="http://localhost:8080/";

	@Mock
	private RestTemplate restTemplate;


	@InjectMocks
	private TimeControllerDelegateImp timeControlDelegate;
	
	@Autowired
	private GameServiceImp gameImp;

	@Autowired
	public TimeControllerDelegateImpTestMock(TimeControllerDelegateImp timeControlDelegate, GameServiceImp gameImp) {
		this.timeControlDelegate = timeControlDelegate;
		this.gameImp = gameImp;
	}


	@Test
	void AddTimeControlTest() {
		
		TsscTimecontrol timeC = new TsscTimecontrol();
		timeC.setAutostart("Yess");
		timeC.setIntervalRunning(BigDecimal.valueOf(3));
		timeC.setLastPlayTime(LocalTime.now());
		timeC.setName("controlador");
		timeC.setOrder(BigDecimal.valueOf(2));
		timeC.setState("Active");
		timeC.setTimeInterval(BigDecimal.valueOf(2));
		timeC.setType("Control");
		
		List<TsscTimecontrol> times = new ArrayList<TsscTimecontrol>();

		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		game.setTsscTimecontrol(times);
		gameImp.AddGame(game, 0, false);
		
		timeC.setTsscGame(game);
	
	when(restTemplate.postForObject(SERVER +"api/times/games/"+timeC.getTsscGame().getId(), timeC, TsscTimecontrol.class)).thenReturn(timeC);
	assertNotNull(timeControlDelegate.add(timeC));

	}
	
	//MIRAR DESPUES DE CREAR VISTASSSSSS

	@Test
	void UpdateTimeControlTest() {

		TsscTimecontrol timeC = new TsscTimecontrol();
		timeC.setAutostart("Yess");
		timeC.setIntervalRunning(BigDecimal.valueOf(3));
		timeC.setLastPlayTime(LocalTime.now());
		timeC.setName("controlador");
		timeC.setOrder(BigDecimal.valueOf(2));
		timeC.setState("Active");
		timeC.setTimeInterval(BigDecimal.valueOf(2));
		timeC.setType("Control");
	
		List<TsscTimecontrol> times = new ArrayList<TsscTimecontrol>();

		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		game.setTsscTimecontrol(times);
		gameImp.AddGame(game, 0, false);
		
		timeC.setTsscGame(game);
	
		when(restTemplate.postForObject(SERVER +"api/times/games/"+timeC.getTsscGame().getId(), timeC, TsscTimecontrol.class)).thenReturn(timeC);
		assertNotNull(timeControlDelegate.add(timeC));
		
		timeC.setName("Nuevo nombre");
		
		when(restTemplate.patchForObject(SERVER + "api/times", timeC, TsscTimecontrol.class)).thenReturn(timeC);
		when(restTemplate.getForObject(SERVER+"api/times/"+timeC.getId(), TsscTimecontrol.class )).thenReturn(timeC);
		
		assertEquals(timeControlDelegate.findById(timeC.getId()).getName(), "Nuevo nombre");

	}

	@Test
	void DeleteTimeControlTest() {

		TsscTimecontrol timeC = new TsscTimecontrol();
		timeC.setAutostart("Yess");
		timeC.setIntervalRunning(BigDecimal.valueOf(3));
		timeC.setLastPlayTime(LocalTime.now());
		timeC.setName("controlador");
		timeC.setOrder(BigDecimal.valueOf(2));
		timeC.setState("Active");
		timeC.setTimeInterval(BigDecimal.valueOf(2));
		timeC.setType("Control");
		
		List<TsscTimecontrol> times = new ArrayList<TsscTimecontrol>();

		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		game.setTsscTimecontrol(times);
		gameImp.AddGame(game, 0, false);
		
		timeC.setTsscGame(game);
	
		when(restTemplate.postForObject(SERVER +"api/times/games/"+timeC.getTsscGame().getId(), timeC, TsscTimecontrol.class)).thenReturn(timeC);
		assertNotNull(timeControlDelegate.add(timeC));
		
		restTemplate.delete(SERVER + "api/times/" + timeC.getId());
		
		timeControlDelegate.delete(timeC);
		assertNull(timeControlDelegate.findById(timeC.getId()));

	}


}
