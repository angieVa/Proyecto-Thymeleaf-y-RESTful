package com.computacion.taller.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.taller.Dao.AdminDao;
import com.computacion.taller.Modelo.TsscAdmin;
import com.computacion.taller.Repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService{
	
//	@Autowired
//	private AdminRepository adminRepository;
//	
//	@Autowired
//	public AdminServiceImp(AdminRepository adminRepository) {
//		this.adminRepository = adminRepository;
//	}
//
//	@Override
//	public TsscAdmin addAdmin(TsscAdmin admin) {
//		return adminRepository.save(admin);
//	}
//
//	@Override
//	public TsscAdmin updateAdmin(TsscAdmin admin) {
//		return adminRepository.save(admin);
//	}
//
//	@Override
//	public void deleteAdmin(TsscAdmin admin) {
//		adminRepository.delete(admin);
//		
//	}
	
	
	@Autowired
	private AdminDao adminRepository;
	
	@Autowired
	public AdminServiceImp(AdminDao adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscAdmin addAdmin(TsscAdmin admin) {
		return adminRepository.save(admin);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscAdmin updateAdmin(TsscAdmin admin) {
		return adminRepository.merge(admin);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAdmin(TsscAdmin admin) {
		adminRepository.delete(admin);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscAdmin findById(long id) {
		return adminRepository.findById(id);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<TsscAdmin> findAll() {
		return adminRepository.findAll();
	}

}
