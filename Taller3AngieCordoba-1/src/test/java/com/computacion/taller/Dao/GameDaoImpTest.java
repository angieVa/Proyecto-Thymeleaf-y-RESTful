package com.computacion.taller.Dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Modelo.TsscTimecontrol;
import com.computacion.taller.Modelo.TsscTopic;

@SpringBootTest
class GameDaoImpTest {
	
	@Autowired
	private GameDao gameDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private TimeControlDao timeControlDao;
	@Autowired
	private StoryDao storyDao;

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void SaveTest() {
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
	
		gameDao.save(game);	
		
		assertNotNull(gameDao.findById(game.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTest() {
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		gameDao.save(game);	
		
		game.setName("gameee");
		gameDao.merge(game);
		
		assertEquals(gameDao.findById(game.getId()).getName(), game.getName());

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void DeleteTest() {
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
	
		gameDao.save(game);	
		assertNotNull(gameDao.findById(game.getId()));
		
		gameDao.delete(game);
		assertNull(gameDao.findById(game.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findByNameTopicTest() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
		topicDao.save(topic);
		
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
		
		gameDao.save(game);			
		assertTrue(gameDao.findByNameTopic(topic.getName()).size() == 1);
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findByDescriptionTopicTest() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
		topicDao.save(topic);
		
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
		
		gameDao.save(game);			
		assertTrue(gameDao.findByDescriptionTopic(topic.getDescription()).size() == 1);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findByIdTopicTest() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
		topicDao.save(topic);
		
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
		
		gameDao.save(game);			
		assertTrue(gameDao.findByIdTopic(topic.getId()).size() == 1);
	}

	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findByDatesTest() {
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.of(2020, 3, 4));
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("1234");
		gameDao.save(game);	
		
		TsscGame game2 = new TsscGame();
		game2.setNGroups(3);
		game2.setNSprints(3);
		game2.setName("gameB");
		game2.setAdminPassword("1234");
		game2.setGuestPassword("1234");
		game2.setScheduledDate(LocalDate.of(2020, 2, 1));
		game2.setScheduledTime(LocalTime.now());
		game2.setUserPassword("1234");
		gameDao.save(game2);	
		
		TsscGame game3 = new TsscGame();
		game3.setNGroups(3);
		game3.setNSprints(3);
		game3.setName("gameB");
		game3.setAdminPassword("1234");
		game3.setGuestPassword("1234");
		game3.setScheduledDate(LocalDate.of(2020, 6, 4));
		game3.setScheduledTime(LocalTime.now());
		game3.setUserPassword("1234");
		gameDao.save(game3);	
		
		LocalDate date1 = LocalDate.of(2020, 1, 20);
		LocalDate date2 = LocalDate.of(2020, 4, 20);

		assertTrue(gameDao.findByDates(date1, date2).size() == 2);
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findByDateHoursTest() {
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.of(2020, 3, 4));
		game.setScheduledTime(LocalTime.of(13, 20));
		game.setUserPassword("1234");
		gameDao.save(game);	
		
		TsscGame game2 = new TsscGame();
		game2.setNGroups(3);
		game2.setNSprints(3);
		game2.setName("gameB");
		game2.setAdminPassword("1234");
		game2.setGuestPassword("1234");
		game2.setScheduledDate(LocalDate.of(2020, 3, 4));
		game2.setScheduledTime(LocalTime.of(14, 1));
		game2.setUserPassword("1234");
		gameDao.save(game2);	
		
		TsscGame game3 = new TsscGame();
		game3.setNGroups(3);
		game3.setNSprints(3);
		game3.setName("gameB");
		game3.setAdminPassword("1234");
		game3.setGuestPassword("1234");
		game3.setScheduledDate(LocalDate.of(2020, 3, 4));
		game3.setScheduledTime(LocalTime.of(16, 34));
		game3.setUserPassword("1234");
		gameDao.save(game3);	
		
		LocalDate date = LocalDate.of(2020, 3, 4);
		LocalTime h1 = LocalTime.of(11, 30);
		LocalTime h2 = LocalTime.of(15, 4);
		
		assertTrue(gameDao.findByDateHours(date, h1, h2).size() == 2);
	}
	
	//ERROR
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findByDateStoryTimeTest() {
		
		TsscTimecontrol timeC = new TsscTimecontrol();
		timeC.setAutostart("Yess");
		timeC.setIntervalRunning(BigDecimal.valueOf(3));
		timeC.setLastPlayTime(LocalTime.now());
		timeC.setName("controlador");
		timeC.setOrder(BigDecimal.valueOf(2));
		timeC.setState("Active");
		timeC.setTimeInterval(BigDecimal.valueOf(2));
		timeC.setType("Control");
		
		timeControlDao.save(timeC);
		
		List<TsscTimecontrol> listTime = new ArrayList<TsscTimecontrol>();
		List<TsscStory> listStory = new ArrayList<TsscStory>();
		
		
		
		
		
		TsscGame game = new TsscGame();
		game.setNGroups(3);
		game.setNSprints(3);
		game.setName("gameA");
		game.setAdminPassword("1234");
		game.setGuestPassword("1234");
		game.setScheduledDate(LocalDate.of(2020, 3, 4));
		game.setScheduledTime(LocalTime.of(13, 20));
		game.setUserPassword("1234");
		timeC.setTsscGame(game);
		game.setTsscTimecontrol(listTime);
		game.addTsscTimecontrol(timeC);
		
			
		gameDao.save(game);		
		timeControlDao.merge(timeC);
		
		TsscGame game2 = new TsscGame();
		game2.setNGroups(3);
		game2.setNSprints(3);
		game2.setName("gameB");
		game2.setAdminPassword("1234");
		game2.setGuestPassword("1234");
		game2.setScheduledDate(LocalDate.of(2020, 3, 4));
		game2.setScheduledTime(LocalTime.of(14, 1));
		game2.setUserPassword("1234");
		
		gameDao.save(game2);
		
		TsscStory story = new TsscStory();
		story.setInitialSprint(BigDecimal.valueOf(2));
		story.setBusinessValue(BigDecimal.valueOf(2));
		story.setPriority(BigDecimal.valueOf(2));
		story.setDescription("Description");
		story.setTsscGame(game);		
		
		game2.setTsscStories(listStory);
		game2.addTsscStory(story);
		storyDao.save(story);
		
		gameDao.merge(game2);	
		
		
		TsscGame game3 = new TsscGame();
		game3.setNGroups(3);
		game3.setNSprints(3);
		game3.setName("gameB");
		game3.setAdminPassword("1234");
		game3.setGuestPassword("1234");
		game3.setScheduledDate(LocalDate.of(2020, 3, 4));
		game3.setScheduledTime(LocalTime.of(16, 34));
		game3.setUserPassword("1234");
		gameDao.save(game3);
		game3.setTsscStories(listStory);
		
		TsscStory story2 = new TsscStory();
		story2.setInitialSprint(BigDecimal.valueOf(2));
		story2.setBusinessValue(BigDecimal.valueOf(2));
		story2.setPriority(BigDecimal.valueOf(2));
		story2.setDescription("Description");
		story2.setTsscGame(game3);		
		storyDao.save(story2);
		
		TsscStory story3 = new TsscStory();
		story3.setInitialSprint(BigDecimal.valueOf(2));
		story3.setBusinessValue(BigDecimal.valueOf(2));
		story3.setPriority(BigDecimal.valueOf(2));
		story3.setDescription("Description");
		story3.setTsscGame(game3);		
		storyDao.save(story3);
	
		TsscStory story4 = new TsscStory();
		story4.setInitialSprint(BigDecimal.valueOf(2));
		story4.setBusinessValue(BigDecimal.valueOf(2));
		story4.setPriority(BigDecimal.valueOf(2));
		story4.setDescription("Description");
		story4.setTsscGame(game3);		
		storyDao.save(story4);
		
		TsscStory story5 = new TsscStory();
		story5.setInitialSprint(BigDecimal.valueOf(2));
		story5.setBusinessValue(BigDecimal.valueOf(2));
		story5.setPriority(BigDecimal.valueOf(2));
		story5.setDescription("Description");
		story5.setTsscGame(game3);		
		storyDao.save(story5);
		
		TsscStory story6 = new TsscStory();
		story6.setInitialSprint(BigDecimal.valueOf(2));
		story6.setBusinessValue(BigDecimal.valueOf(2));
		story6.setPriority(BigDecimal.valueOf(2));
		story6.setDescription("Description");
		story6.setTsscGame(game3);		
		storyDao.save(story6);
		
		TsscStory story7 = new TsscStory();
		story7.setInitialSprint(BigDecimal.valueOf(2));
		story7.setBusinessValue(BigDecimal.valueOf(2));
		story7.setPriority(BigDecimal.valueOf(2));
		story7.setDescription("Description");
		story7.setTsscGame(game3);		
		storyDao.save(story7);
		
		TsscStory story8 = new TsscStory();
		story8.setInitialSprint(BigDecimal.valueOf(2));
		story8.setBusinessValue(BigDecimal.valueOf(2));
		story8.setPriority(BigDecimal.valueOf(2));
		story8.setDescription("Description");
		story8.setTsscGame(game3);		
		storyDao.save(story8);
		
		TsscStory story9 = new TsscStory();
		story9.setInitialSprint(BigDecimal.valueOf(2));
		story9.setBusinessValue(BigDecimal.valueOf(2));
		story9.setPriority(BigDecimal.valueOf(2));
		story9.setDescription("Description");
		story9.setTsscGame(game3);		
		storyDao.save(story9);
	
		TsscStory story10 = new TsscStory();
		story10.setInitialSprint(BigDecimal.valueOf(2));
		story10.setBusinessValue(BigDecimal.valueOf(2));
		story10.setPriority(BigDecimal.valueOf(2));
		story10.setDescription("Description");
		story10.setTsscGame(game3);		
		storyDao.save(story10);
		
		TsscStory story11 = new TsscStory();
		story11.setInitialSprint(BigDecimal.valueOf(2));
		story11.setBusinessValue(BigDecimal.valueOf(2));
		story11.setPriority(BigDecimal.valueOf(2));
		story11.setDescription("Description");
		story11.setTsscGame(game3);		
		storyDao.save(story11);
		
		game3.addTsscStory(story11);				
		game3.addTsscStory(story10);			
		game3.addTsscStory(story9);			
		game3.addTsscStory(story8);		
		game3.addTsscStory(story7);				
		game3.addTsscStory(story6);		
		game3.addTsscStory(story5);	
		game3.addTsscStory(story4);
		game3.addTsscStory(story2);
		game3.addTsscStory(story3);
				
		gameDao.merge(game3);		
		
		LocalDate date = LocalDate.of(2020, 3, 4);
		
		assertTrue(gameDao.findByDateStoryTime(date).size() == 2);
	}
	
	
}
