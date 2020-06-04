package com.computacion.taller.Dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import com.computacion.taller.Modelo.TsscTopic;


@Repository
@Scope("singleton")
public class TopicDaoImp implements TopicDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscTopic save(TsscTopic entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public TsscTopic merge(TsscTopic entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public void delete(TsscTopic entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public TsscTopic findById(long id) {
		return entityManager.find(TsscTopic.class, id);
	}

	@Override
	public List<TsscTopic> findAll() {
		String jpql = "Select t from TsscTopic t";
		return entityManager.createQuery(jpql).getResultList();
	}

	
	//Punto 1a)
	
	@Override
	public List<TsscTopic> findByName(String name) {
		String q = "Select t from TsscTopic t where t.name = :name";
		Query query = entityManager.createQuery(q);
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		String q = "Select t from TsscTopic t where t.description = :description";
		Query query = entityManager.createQuery(q);
		query.setParameter("description", description);

		return query.getResultList();
	}

	@Override
	public boolean existsById(long id) {
		
		TsscTopic topic = entityManager.find(TsscTopic.class, id);
		if(topic == null) {
			return false;
		}else {
			return true;
		}				
	}
	
	

	//2a)

	@Override
	public List<Object[]> findTopicsByGameDate(LocalDate date) {


	String q = "SELECT b.tsscTopic , count(b.tsscTopic) FROM TsscGame b WHERE b.id IN (SELECT a.id from TsscGame a WHERE a.scheduledDate = :date ORDER BY a.scheduledTime DESC) GROUP BY b.tsscTopic";
	
	Query query = entityManager.createQuery(q);
	query.setParameter("date", date);
	return query.getResultList();

	}


	


}
