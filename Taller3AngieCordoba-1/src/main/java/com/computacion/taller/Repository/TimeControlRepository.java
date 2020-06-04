package com.computacion.taller.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.taller.Modelo.TsscTimecontrol;

@Repository
public interface TimeControlRepository extends CrudRepository<TsscTimecontrol,Long>{

}
