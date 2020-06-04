package com.computacion.taller.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.computacion.taller.Modelo.TsscTimecontrol;


@Repository
@Scope("singleton")
public class TimeControlDaoImp implements TimeControlDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscTimecontrol save(TsscTimecontrol entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public TsscTimecontrol merge(TsscTimecontrol entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public void delete(TsscTimecontrol entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public TsscTimecontrol findById(long id) {
		return entityManager.find(TsscTimecontrol.class, id);
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		String jpql = "Select t from TsscTimecontrol t";
		return entityManager.createQuery(jpql).getResultList();
	}

}
