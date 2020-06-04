package com.computacion.taller.Dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscTopic;

@SpringBootTest
class TopicDaoImpTest {
	
	
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private GameDao gameDao;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void SaveTest() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
		topicDao.save(topic);
		
		assertNotNull(topicDao.findById(topic.getId()));

	
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTest() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topicDao.save(topic);
		
		topic.setName("M");
		topicDao.merge(topic);
		
		assertEquals(topicDao.findById(topic.getId()).getName(), topic.getName());

	
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void DeleteTest() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
		topicDao.save(topic);
		assertNotNull(topicDao.findById(topic.getId()));
		
		topicDao.delete(topic);
		assertNull(topicDao.findById(topic.getId()));

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void FindByNameTest() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topicDao.save(topic);

//		assertNotNull(topicDao.findByName(topic.getName()));
		assertTrue(topicDao.findByName(topic.getName()).size() == 1);
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void FindByDescriptionTest() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topicDao.save(topic);

		
		assertTrue(topicDao.findByDescription(topic.getDescription()).size() == 1);

	}
	

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindTopicsByGameDate() {

	List<TsscGame> gameList = new ArrayList<TsscGame>();

			TsscTopic topic = new TsscTopic();
			topic.setName("TopicA");
			topic.setDescription("DescripcionTopic");
			topic.setGroupPrefix("Grp");
			topic.setDefaultGroups(2);
			topic.setDefaultSprints(2);
			topic.setTsscGames(gameList);
		
			TsscGame game = new TsscGame();
			game.setNGroups(3);
			game.setNSprints(3);
			game.setName("gameA");
			game.setAdminPassword("1234");
			game.setGuestPassword("1234");
			game.setScheduledDate(LocalDate.of(2020, 3, 4));
			game.setScheduledTime(LocalTime.of(13, 20));
			game.setUserPassword("1234");
			game.setTsscTopic(topic);
		
		
			TsscGame game2 = new TsscGame();
			game2.setNGroups(3);
			game2.setNSprints(3);
			game2.setName("gameB");
			game2.setAdminPassword("1234");
			game2.setGuestPassword("1234");
			game2.setScheduledDate(LocalDate.of(2020, 3, 4));
			game2.setScheduledTime(LocalTime.of(14, 1));
			game2.setUserPassword("1234");
			game2.setTsscTopic(topic);
		
		
			TsscGame game3 = new TsscGame();
			game3.setNGroups(3);
			game3.setNSprints(3);
			game3.setName("gameC");
			game3.setAdminPassword("1234");
			game3.setGuestPassword("1234");
			game3.setScheduledDate(LocalDate.of(2020, 3, 5));
			game3.setScheduledTime(LocalTime.of(16, 34));
			game3.setUserPassword("1234");
			game3.setTsscTopic(topic);
		
		
			topic.addTsscGame(game);
			topic.addTsscGame(game2);
			topic.addTsscGame(game3);
		
			gameDao.save(game);
			gameDao.save(game2);
			gameDao.save(game3);
		
			topicDao.save(topic);
		
			gameList = new ArrayList<TsscGame>();
		
			TsscTopic topic2 = new TsscTopic();
			topic2.setName("TopicB");
			topic2.setDescription("DescripcionTopic");
			topic2.setGroupPrefix("Grp");
			topic2.setDefaultGroups(2);
			topic2.setDefaultSprints(2);
			topic2.setTsscGames(gameList);
		
			TsscGame game4 = new TsscGame();
			game4.setNGroups(3);
			game4.setNSprints(3);
			game4.setName("gameD");
			game4.setAdminPassword("1234");
			game4.setGuestPassword("1234");
			game4.setScheduledDate(LocalDate.of(2020, 3, 4));
			game4.setScheduledTime(LocalTime.of(16, 34));
			game4.setUserPassword("1234");
			game4.setTsscTopic(topic2);
			gameDao.save(game4);
		
			TsscGame game5 = new TsscGame();
			game5.setNGroups(3);
			game5.setNSprints(3);
			game5.setName("game5");
			game5.setAdminPassword("1234");
			game5.setGuestPassword("1234");
			game5.setScheduledDate(LocalDate.of(2020, 3, 4));
			game5.setScheduledTime(LocalTime.of(16, 34));
			game5.setUserPassword("1234");
			game5.setTsscTopic(topic2);
			gameDao.save(game5);
		
			topic2.addTsscGame(game5);
			topic2.addTsscGame(game4);
			game3.setTsscTopic(topic2);
			topic2.addTsscGame(game3);
		
			topicDao.save(topic2);
		
			TsscTopic topic3 = new TsscTopic();
			topic3.setName("TopicC");
			topic3.setDescription("DescripcionTopic");
			topic3.setGroupPrefix("Grp");
			topic3.setDefaultGroups(2);
			topic3.setDefaultSprints(2);
		
			topicDao.save(topic3);
		
			LocalDate date = LocalDate.of(2020, 3, 4);
		
			System.out.println("**********************");
			List<Object[]> resultado = topicDao.findTopicsByGameDate(date);
		
		
		
			System.out.println("TOPIC");
			System.out.println(((TsscTopic) (resultado.get(0)[0])).getName());
			System.out.println(resultado.get(0)[1]);
			System.out.println("------------------------------------------------");
			System.out.println(((TsscTopic) (resultado.get(1)[0])).getName());
			System.out.println(resultado.get(1)[1]);




			assertTrue(topicDao.findTopicsByGameDate(date).size() == 2);
			assertEquals(((TsscTopic) (resultado.get(0)[0])).getName(), "TopicA");
			assertTrue((Long)resultado.get(0)[1] == 2);
			assertEquals(((TsscTopic) (resultado.get(1)[0])).getName(), "TopicB");
			assertTrue((Long)resultado.get(1)[1] == 2);

	}
	
	
	

}
