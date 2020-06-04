package com.computacion.taller.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.taller.Modelo.TsscSprint;

@Repository
public interface SprintRepository extends CrudRepository<TsscSprint,Long>{

}
