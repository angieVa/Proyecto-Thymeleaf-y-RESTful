package com.computacion.taller.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.taller.Modelo.TsscGame;

@Repository
public interface GameRepository extends CrudRepository<TsscGame,Long>{

}
