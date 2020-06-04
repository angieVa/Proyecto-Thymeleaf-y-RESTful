package com.computacion.taller.Modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Service.TopicServiceImp;


@SpringBootTest
class ATsscTopicTest {
	
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
	void addTopicCorrecto() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(3);
		topicService.AddTopic(tsstopic);
		assertTrue(topicService.findById(tsstopic.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addTopicIncorrecto() {
		
		assertNull(topicService.AddTopic(null));
	}
	
	//UPDATE
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTopicCorrecto() {
	
		TsscTopic tsstopic = new TsscTopic();
		
		String name = "ciencia ficción";
		String description ="alienigenas en un viaje al espacio";
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		
		topicService.AddTopic(tsstopic);
		topicService.UpdateTopic(tsstopic, name, description);
		
		assertEquals(topicService.findByIdR(tsstopic.getId()).get().getName(),  tsstopic.getName());
		assertEquals(topicService.findByIdR(tsstopic.getId()).get().getDescription(),  tsstopic.getDescription());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTopicInvalido() {
	
		TsscTopic tsstopic = new TsscTopic();
		
		String name = "ciencia ficción";
		String description ="alienigenas en un viaje al espacio";
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		
		assertNull(topicService.UpdateTopic(tsstopic, name, description));
		
	}
	
}
