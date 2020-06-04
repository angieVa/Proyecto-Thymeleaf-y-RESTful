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
import com.computacion.taller.Service.GameServiceImp;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class storyDelegateImptTestMock {
	
	final String SERVER="http://localhost:8080/";

	@Mock
	private RestTemplate restTemplate;


	@InjectMocks
	private storyDelegateImpt storyServiceImp;

	@Autowired
	private GameServiceImp gameImp;


	@Autowired
	public storyDelegateImptTestMock(storyDelegateImpt storyServiceImp) {
		this.storyServiceImp = storyServiceImp;

	}

	@Test
	void AddStoryTest() {

		TsscStory story = new TsscStory();
		story.setInitialSprint(BigDecimal.valueOf(2));
		story.setBusinessValue(BigDecimal.valueOf(2));
		story.setPriority(BigDecimal.valueOf(2));
		story.setDescription("Description");
	
		List<TsscStory> st = new ArrayList<TsscStory>();

		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		game.setTsscStories(st);
		gameImp.AddGame(game, 0, false);

		story.setTsscGame(game);

		when(restTemplate.postForObject(SERVER + "api/stories/games/" + story.getTsscGame().getId(), story, TsscStory.class)).thenReturn(story);

		assertNotNull(storyServiceImp.add(story));

	}

	@Test
	public void UpdateStoryTest() {

		TsscStory story = new TsscStory();
		story.setInitialSprint(BigDecimal.valueOf(2));
		story.setBusinessValue(BigDecimal.valueOf(2));
		story.setPriority(BigDecimal.valueOf(2));
		story.setDescription("Description");
	
		List<TsscStory> st = new ArrayList<TsscStory>();

		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setTsscStories(st);
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		gameImp.AddGame(game, 0, false);

		story.setTsscGame(game);
		
		when(restTemplate.postForObject(SERVER + "api/stories/games/"+story.getTsscGame().getId(), story, TsscStory.class)).thenReturn(story);
		assertNotNull(storyServiceImp.add(story));
		
		story.setDescription("Nueva descripción");
		
	//	when(restTemplate.patchForObject(SERVER+"api/stories", story, TsscStory.class)).thenReturn(story);
		storyServiceImp.update(story);
		
		when(restTemplate.getForObject(SERVER+"api/stories/"+story.getId(), TsscStory.class )).thenReturn(story);
		assertEquals(storyServiceImp.findById(story.getId()).getDescription(), "Nueva descripción");

	}
	

	@Test
	void DeleteStoryTest() {
		
		TsscStory story = new TsscStory();
		story.setInitialSprint(BigDecimal.valueOf(2));
		story.setBusinessValue(BigDecimal.valueOf(2));
		story.setPriority(BigDecimal.valueOf(2));
		story.setDescription("Description");
	
		List<TsscStory> st = new ArrayList<TsscStory>();

		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setTsscStories(st);
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		gameImp.AddGame(game, 0, false);

		story.setTsscGame(game);

		when(restTemplate.postForObject(SERVER + "api/stories/games/" + story.getTsscGame().getId(), story, TsscStory.class)).thenReturn(story);

		assertNotNull(storyServiceImp.add(story));

		restTemplate.delete(SERVER + "api/stories/" + story.getId());
		storyServiceImp.delete(story);

		assertNull(storyServiceImp.findById(story.getId()));
	
	
	}
	
	
}
