package com.computacion.taller.Dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;


@SpringBootTest
class StoryDaoImpTest {
	
	@Autowired
	private StoryDao storyDao;
	@Autowired
	private GameDao gameDao;
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void SaveTest() {
		
		TsscStory story = new TsscStory();
		story.setInitialSprint(BigDecimal.valueOf(2));
		story.setBusinessValue(BigDecimal.valueOf(2));
		story.setPriority(BigDecimal.valueOf(2));
		story.setDescription("Description");
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		
		gameDao.save(game);		
		story.setTsscGame(game);	
		
		storyDao.save(story);	
		assertNotNull(storyDao.findById(story.getId()));
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTest() {
		
		TsscStory story = new TsscStory();
		story.setInitialSprint(BigDecimal.valueOf(2));
		story.setBusinessValue(BigDecimal.valueOf(2));
		story.setPriority(BigDecimal.valueOf(2));
		story.setDescription("Description");
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		
		gameDao.save(game);		
		story.setTsscGame(game);			
		storyDao.save(story);	
		
		story.setInitialSprint(BigDecimal.valueOf(3));
		storyDao.merge(story);
		
		assertEquals(storyDao.findById(story.getId()).getInitialSprint(), story.getInitialSprint());

		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void DeleteTest() {
		
		TsscStory story = new TsscStory();
		story.setInitialSprint(BigDecimal.valueOf(2));
		story.setBusinessValue(BigDecimal.valueOf(2));
		story.setPriority(BigDecimal.valueOf(2));
		story.setDescription("Description");
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		
		gameDao.save(game);		
		story.setTsscGame(game);	
		
		storyDao.save(story);	
		assertNotNull(storyDao.findById(story.getId()));
		
		storyDao.delete(story);
		assertNull(storyDao.findById(story.getId()));
		
	}


}
