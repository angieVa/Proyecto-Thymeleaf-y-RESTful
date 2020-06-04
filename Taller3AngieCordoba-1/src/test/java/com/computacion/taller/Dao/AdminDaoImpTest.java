package com.computacion.taller.Dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Modelo.TsscAdmin;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscStory;

@SpringBootTest
class AdminDaoImpTest {
	
	@Autowired
	private AdminDao adminDao;

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void SaveTest() {
		
		TsscAdmin admin = new TsscAdmin();
		admin.setUsername("SuperA");
		admin.setSuperAdmin("superadmin");
		admin.setPassword("1234");
		
		adminDao.save(admin);
	
		assertNotNull(adminDao.findById(admin.getId()));
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTest() {
		
		TsscAdmin admin = new TsscAdmin();
		admin.setUsername("SuperA");
		admin.setSuperAdmin("superadmin");
		admin.setPassword("1234");
		
		adminDao.save(admin);
		
		admin.setUsername("SuperAdmin");
	
		assertEquals(adminDao.findById(admin.getId()).getUsername(), admin.getUsername());

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void DeleteTest() {
		
		TsscAdmin admin = new TsscAdmin();
		admin.setUsername("SuperA");
		admin.setSuperAdmin("superadmin");
		admin.setPassword("1234");
		
		adminDao.save(admin);
		assertNotNull(adminDao.findById(admin.getId()));
		
		adminDao.delete(admin);
		assertNull(adminDao.findById(admin.getId()));
	}

}
