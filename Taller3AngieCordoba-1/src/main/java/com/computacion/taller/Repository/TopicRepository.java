package com.computacion.taller.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.taller.Modelo.TsscTopic;

@Repository
public interface TopicRepository extends CrudRepository<TsscTopic,Long>{

}
