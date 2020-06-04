package com.computacion.taller.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.computacion.taller.Modelo.TsscAdmin;

@Repository
public interface AdminRepository extends CrudRepository<TsscAdmin, Long>{
	
	List<TsscAdmin> findByUsername(String user);

}
