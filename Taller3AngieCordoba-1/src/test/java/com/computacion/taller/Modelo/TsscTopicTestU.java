package com.computacion.taller.Modelo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

import com.computacion.taller.Dao.TopicDao;
import com.computacion.taller.Repository.TopicRepository;
import com.computacion.taller.Service.TopicServiceImp;


@SpringBootTest
class TsscTopicTestU {


	@Autowired
	@InjectMocks
	private TopicServiceImp topicService;
	
	@Mock
	private TopicDao topicRepository;

	
	
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
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addTopicGrupoMenora1() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(0);
		tsstopic.setDefaultSprints(3);
		topicService.AddTopic(tsstopic);
		
		when(topicRepository.existsById(tsstopic.getId())).thenReturn(false);
		
		assertFalse(topicService.findById(tsstopic.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addTopicSpringMenora1() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(0);
		topicService.AddTopic(tsstopic);
		
		when(topicRepository.existsById(tsstopic.getId())).thenReturn(false);
		
		assertFalse(topicService.findById(tsstopic.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void addTopicCorrecto() {
	
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		topicService.AddTopic(tsstopic);
		
		when(topicRepository.existsById(tsstopic.getId())).thenReturn(true);		
		assertTrue(topicService.findById(tsstopic.getId()));
	}
//	
//	//duda metodos update
//	
//	//UPDATE
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTopicCorrecto() {
		
		TsscTopic tsstopic = new TsscTopic();
		
		String name = "ciencia ficción";
		String description ="alienigenas en un viaje al espacio";
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		topicService.AddTopic(tsstopic);
		
		when(topicRepository.existsById(tsstopic.getId())).thenReturn(true);
		
		TsscTopic topicR = topicService.UpdateTopic(tsstopic, name, description);
		
		Optional<TsscTopic> topicN = Optional.of(topicR);
		when(topicRepository.findById(tsstopic.getId())).thenReturn(topicR);
	
		assertEquals(topicService.findByIdR(tsstopic.getId()).get().getName(),  tsstopic.getName());
		assertEquals(topicService.findByIdR(tsstopic.getId()).get().getDescription(), tsstopic.getDescription());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTopicNameNull() {
	
		TsscTopic tsstopic = new TsscTopic();
		
		String name = "ciencia ficción";
		String description ="alienigenas en un viaje al espacio";
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		topicService.AddTopic(tsstopic);
		
		when(topicRepository.existsById(tsstopic.getId())).thenReturn(true);
		
		topicService.UpdateTopic(tsstopic, null, description);
		
		Optional<TsscTopic> topic = Optional.of(tsstopic);
		when(topicRepository.findById(tsstopic.getId())).thenReturn(tsstopic);
		
		assertNotEquals(topicService.findByIdR(tsstopic.getId()).get().getName(), name);
		assertNotEquals(topicService.findByIdR(tsstopic.getId()).get().getDescription(), description);	

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTopicNameVacio() {
	
		TsscTopic tsstopic = new TsscTopic();
		
		String name = "ciencia ficción";
		String description ="alienigenas en un viaje al espacio";
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		topicService.AddTopic(tsstopic);
		
		when(topicRepository.existsById(tsstopic.getId())).thenReturn(true);
		
		topicService.UpdateTopic(tsstopic, "", description);
		
		Optional<TsscTopic> topic = Optional.of(tsstopic);
		when(topicRepository.findById(tsstopic.getId())).thenReturn(tsstopic);
		
		assertNotEquals(topicService.findByIdR(tsstopic.getId()).get().getName(), name);
		assertNotEquals(topicService.findByIdR(tsstopic.getId()).get().getDescription(), description);
	}
//		
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTopicDescripcionNull() {
	
		TsscTopic tsstopic = new TsscTopic();
		
		String name = "ciencia ficción";
		String description ="alienigenas en un viaje al espacio";
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		topicService.AddTopic(tsstopic);
		
		when(topicRepository.existsById(tsstopic.getId())).thenReturn(true);
		
		topicService.UpdateTopic(tsstopic, name, null);
		
		Optional<TsscTopic> topic = Optional.of(tsstopic);
		when(topicRepository.findById(tsstopic.getId())).thenReturn(tsstopic);
		
		assertNotEquals(topicService.findByIdR(tsstopic.getId()).get().getName(), name);
		assertNotEquals(topicService.findByIdR(tsstopic.getId()).get().getDescription(), description);
	}
//	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTopicDescripcionVacio() {
	
		TsscTopic tsstopic = new TsscTopic();
		
		String name = "ciencia ficción";
		String description ="alienigenas en un viaje al espacio";
		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(2);
		topicService.AddTopic(tsstopic);
		
		when(topicRepository.existsById(tsstopic.getId())).thenReturn(true);
		
		topicService.UpdateTopic(tsstopic, name, "");
		
		Optional<TsscTopic> topic = Optional.of(tsstopic);
		when(topicRepository.findById(tsstopic.getId())).thenReturn(tsstopic);
		
		assertNotEquals(topicService.findByIdR(tsstopic.getId()).get().getName(), name);
		assertNotEquals(topicService.findByIdR(tsstopic.getId()).get().getDescription(), description);
	}
	
	

}
