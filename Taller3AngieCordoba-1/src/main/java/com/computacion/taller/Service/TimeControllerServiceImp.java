package com.computacion.taller.Service;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Dao.TimeControlDao;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscTimecontrol;


@Service
public class TimeControllerServiceImp implements TimeControllerService {

	@Autowired
	private TimeControlDao dao;
	@Autowired
	private GameServiceImp gameService;
	
	@Autowired
	public TimeControllerServiceImp(TimeControlDao dao, GameServiceImp gameService) {
		this.dao = dao;
		this.gameService = gameService;
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public TsscTimecontrol AddTimecontrol(TsscTimecontrol time, long id) {
		
		TsscGame game = gameService.findByIdR(id).get();
		
		gameService.addTimeControl(game, time);
		time.setTsscGame(game);
		return dao.save(time);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public TsscTimecontrol UpdateTimeControl(TsscTimecontrol time) {
	
		return dao.merge(time);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public TsscTimecontrol findTimeById(Long id) {
	
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean existsById(Long id) {
		
		return false;
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Iterable<TsscTimecontrol> findAll() {
	
		return dao.findAll();
	}



	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteTime(TsscTimecontrol time) {
		
		dao.delete(time);
		
	}

}
