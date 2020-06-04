package com.computacion.taller.Dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Modelo.TsscTimecontrol;

@SpringBootTest
class TimeControlDaoImpTest {
	
	@Autowired
	private TimeControlDao timeControlDao;

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void SaveTest() {
		
		TsscTimecontrol timeC = new TsscTimecontrol();
		timeC.setAutostart("Yess");
		timeC.setIntervalRunning(BigDecimal.valueOf(3));
		timeC.setLastPlayTime(LocalTime.now());
		timeC.setName("controlador");
		timeC.setOrder(BigDecimal.valueOf(2));
		timeC.setState("Active");
		timeC.setTimeInterval(BigDecimal.valueOf(2));
		timeC.setType("Control");
		
		timeControlDao.save(timeC);
		
		assertNotNull(timeControlDao.findById(timeC.getId()));

	
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void UpdateTest() {
		
		TsscTimecontrol timeC = new TsscTimecontrol();
		timeC.setAutostart("Yess");
		timeC.setIntervalRunning(BigDecimal.valueOf(3));
		timeC.setLastPlayTime(LocalTime.now());
		timeC.setName("controlador");
		timeC.setOrder(BigDecimal.valueOf(2));
		timeC.setState("Active");
		timeC.setTimeInterval(BigDecimal.valueOf(2));
		timeC.setType("Control");	
		timeControlDao.save(timeC);

		timeC.setAutostart("No");
		
		assertEquals(timeControlDao.findById(timeC.getId()).getAutostart(), timeC.getAutostart());

	
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void DeleteTest() {
		
		TsscTimecontrol timeC = new TsscTimecontrol();
		timeC.setAutostart("Yess");
		timeC.setIntervalRunning(BigDecimal.valueOf(3));
		timeC.setLastPlayTime(LocalTime.now());
		timeC.setName("controlador");
		timeC.setOrder(BigDecimal.valueOf(2));
		timeC.setState("Active");
		timeC.setTimeInterval(BigDecimal.valueOf(2));
		timeC.setType("Control");	
		
		timeControlDao.save(timeC);
		assertNotNull(timeControlDao.findById(timeC.getId()));
		
		timeControlDao.delete(timeC);
		assertNull(timeControlDao.findById(timeC.getId()));
	
	}
	

}
