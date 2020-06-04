package com.computacion.taller.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.computacion.taller.Modelo.TsscAdmin;
import com.computacion.taller.Dao.AdminDao;


@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminDao adminRepository;
	
	@Autowired
	public MyCustomUserDetailsService(AdminDao adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("----------------------------------------------------------------------");
//		System.out.println(adminRepository.count());
//		System.out.println(username);
//		System.out.println("----------------------------------------------------------------------");
		TsscAdmin admin = adminRepository.findByUser(username);
		if (admin != null) {
			
			User.UserBuilder builder = User.withUsername(username).password(admin.getPassword()).roles(admin.getSuperAdmin());
			return builder.build();
			
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}