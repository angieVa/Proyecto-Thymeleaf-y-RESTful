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
import com.computacion.taller.Modelo.ConsultaTopic;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Service.GameServiceImp;
import com.computacion.taller.Service.TopicServiceImp;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TopicDelegateImptTestMock {

	final String SERVER = "http://localhost:8080/";

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private TopicDelegateImpt topicServiceImp;
	
	@Autowired
	private GameServiceImp gameService;

	@Autowired
	public TopicDelegateImptTestMock(TopicDelegateImpt topicServiceImp) {
		this.topicServiceImp = topicServiceImp;
	}

	@Test
	void testAddTopic() {

		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
		when(restTemplate.postForObject(SERVER + "api/topics", topic, TsscTopic.class)).thenReturn(topic);
		when(restTemplate.getForObject(SERVER + "api/topics/" + topic.getId(), TsscTopic.class)).thenReturn(topic);
		
		assertNotNull(topicServiceImp.add(topic));

	}

	@Test
	public void UpdateTopicTest() {

		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
		when(restTemplate.postForObject(SERVER + "api/topics", topic, TsscTopic.class)).thenReturn(topic);
		assertNotNull(topicServiceImp.add(topic));
		
		topic.setName("Nuevo nombre");
		
	//	when(restTemplate.patchForObject(SERVER + "api/topics", topic, TsscTopic.class)).thenReturn(topic);
		topicServiceImp.update(topic);
		
		when(restTemplate.getForObject(SERVER + "api/topics/" + topic.getId(), TsscTopic.class)).thenReturn(topic);
		assertEquals(topicServiceImp.findById(topic.getId()).getName(), "Nuevo nombre");


	}


	@Test
	public void testDeleteTopic() {
		
		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
		when(restTemplate.postForObject(SERVER + "api/topics", topic, TsscTopic.class)).thenReturn(topic);
		assertNotNull(topicServiceImp.add(topic));
		
		restTemplate.delete(SERVER + "api/topics/" + topic.getId());
		
		when(restTemplate.getForObject(SERVER + "api/topics/" + topic.getId(), TsscTopic.class)).thenReturn(null);
		assertNull(topicServiceImp.findById(topic.getId()));

	}


	@Test
	public void testFindByIdTopic() {

		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
		when(restTemplate.postForObject(SERVER + "api/topics", topic, TsscTopic.class)).thenReturn(topic);
		assertNotNull(topicServiceImp.add(topic));
		
		when(restTemplate.getForObject(SERVER + "api/topics/" + topic.getId(), TsscTopic.class)).thenReturn(topic);
		assertNotNull(topicServiceImp.findById(topic.getId()));

	}
	
	
	@Test
	void findTopicsByGameDateTest() {

		TsscTopic topic = new TsscTopic();
		topic.setName("TopicA");
		topic.setDescription("DescripcionTopic");
		topic.setGroupPrefix("Grp");
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		
        when(restTemplate.postForObject("http://localhost:8080/api/topics", topic, TsscTopic.class)).thenReturn(topic);
        when(restTemplate.getForObject(SERVER+"api/topics/"+topic.getId(), TsscTopic.class )).thenReturn(topic);
		assertNotNull(topicServiceImp.add(topic));
		
		
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
         
		gameService.AddGame(game, topic.getId(), true);
			
		
		TsscTopic topic2 = new TsscTopic();
		topic2.setName("TopicB");
		topic2.setDescription("DescripcionTopic");
		topic2.setGroupPrefix("Grp");
		topic2.setDefaultGroups(2);
		topic2.setDefaultSprints(2);
		
        when(restTemplate.postForObject("http://localhost:8080/api/topics", topic2, TsscTopic.class)).thenReturn(topic2);
        when(restTemplate.getForObject(SERVER+"api/topics/"+topic2.getId(), TsscTopic.class )).thenReturn(topic2);
		assertNotNull(topicServiceImp.add(topic2));
	
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
          
		gameService.AddGame(game2, topic2.getId(), true);
        
		ConsultaTopic[] lista= new ConsultaTopic[2];
		
		when(restTemplate.getForObject(SERVER+"api/topic/fecha/"+LocalDate.now(), ConsultaTopic[].class )).thenReturn(lista);
		when(restTemplate.getForObject(SERVER+"api/topics/consulta/" + game.getScheduledDate().toString(), ConsultaTopic[].class )).thenReturn(lista);
		
		Consulta con = new Consulta(game.getScheduledDate().toString());
		
		assertEquals(topicServiceImp.consulta(con).size(),2);	
		

	}

}



