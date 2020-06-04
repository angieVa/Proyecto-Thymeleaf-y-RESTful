package com.computacion.taller.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.taller.Modelo.TsscGroup;

@Repository
public interface GroupRepository extends CrudRepository<TsscGroup,Long>{

}
