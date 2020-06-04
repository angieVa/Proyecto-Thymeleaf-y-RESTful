package com.computacion.taller.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Dao.TopicDao;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Modelo.TsscTimecontrol;
import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Repository.TopicRepository;

@Service
public class TopicServiceImp implements TopicService{
	
//	@Autowired
//	private TopicRepository topicRepository;
//	
//	@Autowired
//	public TopicServiceImp(TopicRepository topicRepository) {
//		this.topicRepository = topicRepository;
//	}
//
//	@Override
//	public TsscTopic AddTopic(TsscTopic topic) {
//		
//		if(topic!=null && topic.getDefaultSprints()>0 && topic.getDefaultGroups()>0) {
//			topicRepository.save(topic);
//			return topic;
//		}
//		
//		return null;
//	}
//
//	@Override
//	public TsscTopic UpdateTopic(TsscTopic topic, String name, String description) {
//
//		
//		if(topic!=null && name!=null && description!=null && topicRepository.existsById(topic.getId()) && !name.equals("") && !description.equals("")) {
//			topic.setName(name);
//			topic.setDescription(description);
//			topicRepository.save(topic);
//			return topic;
//		}
//		
//		return null;
//	}
//
//	@Override
//	public boolean findById(long id) {	
//		return topicRepository.existsById(id);
//	}
//
//	@Override
//	public Optional<TsscTopic> findByIdR(long id) {
//		// TODO Auto-generated method stub
//		return topicRepository.findById(id);
//	}
//
//	@Override
//	public List<TsscStory> getStories(TsscTopic topic) {
//		return topic.getTsscStories();
//	}
//
//	@Override
//	public List<TsscTimecontrol> getTimesControl(TsscTopic topic) {
//		return topic.getTsscCronograma();
//	}
//	
//	@Override
//	public void deleteAll() {
//		topicRepository.deleteAll();
//	}
//
//	@Override
//	public Iterable<TsscTopic> findAll() {
//		// TODO Auto-generated method stub
//		return topicRepository.findAll();
//	}
//
//	@Override
//	public void deleteTopic(TsscTopic tsscTopic) {
//		topicRepository.delete(tsscTopic);
//		
//	}
	
	@Autowired
	private TopicDao topicRepository;
	
	@Autowired
	public TopicServiceImp(TopicDao topicRepository) {
		this.topicRepository = topicRepository;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscTopic AddTopic(TsscTopic topic) {
		
		if(topic!=null && topic.getDefaultSprints()>0 && topic.getDefaultGroups()>0) {
			topicRepository.save(topic);
			return topic;
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscTopic UpdateTopic(TsscTopic topic, String name, String description) {

		
		if(topic!=null && name!=null && description!=null && topicRepository.existsById(topic.getId()) && !name.equals("") && !description.equals("")) {
			topic.setName(name);
			topic.setDescription(description);
			topicRepository.save(topic);
			return topic;
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean findById(long id) {	
		return topicRepository.existsById(id);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Optional<TsscTopic> findByIdR(long id) {	
		Optional<TsscTopic> op = Optional.of(topicRepository.findById(id));	
		return op;
	}

	@Override
	public List<TsscStory> getStories(TsscTopic topic) {
		return topic.getTsscStories();
	}

	@Override
	public List<TsscTimecontrol> getTimesControl(TsscTopic topic) {
		return topic.getTsscCronograma();
	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Iterable<TsscTopic> findAll() {
		// TODO Auto-generated method stub
		return topicRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTopic(TsscTopic tsscTopic) {
		topicRepository.delete(tsscTopic);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(TsscTopic topic) {
		topicRepository.merge(topic);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Object[]> findTopicsByGameDate(LocalDate date) {
		// TODO Auto-generated method stub
		return topicRepository.findTopicsByGameDate(date);
	}

}
