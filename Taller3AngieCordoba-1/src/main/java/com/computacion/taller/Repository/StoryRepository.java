package com.computacion.taller.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.taller.Modelo.TsscStory;

@Repository
public interface StoryRepository extends CrudRepository<TsscStory,Long>{

}
