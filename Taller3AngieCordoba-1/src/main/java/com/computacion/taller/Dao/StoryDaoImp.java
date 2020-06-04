package com.computacion.taller.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.computacion.taller.Modelo.TsscStory;

@Repository
@Scope("singleton")
public class StoryDaoImp implements StoryDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscStory save(TsscStory entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public TsscStory merge(TsscStory entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public void delete(TsscStory entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public TsscStory findById(long id) {
		return entityManager.find(TsscStory.class, id);
	}

	@Override
	public List<TsscStory> findAll() {
		String jpql = "SELECT t FROM TsscStory t";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public boolean existsById(long id) {
		
		TsscStory st = entityManager.find(TsscStory.class, id);
		if(st == null) {
			return false;
		}else {
			return true;
		}
		
	
	}
	
	

}
