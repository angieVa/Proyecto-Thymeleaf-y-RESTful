package com.computacion.taller.Delegate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.computacion.taller.Modelo.TsscAdmin;
import com.computacion.taller.Modelo.TsscTopic;


@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AdminDelegateImpTestMock {

	final String SERVER = "http://localhost:8080/";

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private AdminDelegateImp adminService;

	@Autowired
	public AdminDelegateImpTestMock(AdminDelegateImp adminService) {
		this.adminService = adminService;
	}

	@Test
	void AddAdminTest() {

		TsscAdmin admin = new TsscAdmin();
		admin.setPassword("{noop}123");
		admin.setUsername("admin");
		admin.setSuperAdmin("admin");

		
		when(restTemplate.postForObject(SERVER + "api/admins", admin, TsscAdmin.class)).thenReturn(admin);
		assertNotNull(adminService.add(admin));

	}

	@Test
	public void UpdateAdminTest() {


		TsscAdmin admin = new TsscAdmin();
		admin.setPassword("{noop}123");
		admin.setUsername("admin");
		admin.setSuperAdmin("admin");

		
		when(restTemplate.postForObject(SERVER + "api/admins", admin, TsscAdmin.class)).thenReturn(admin);
		assertNotNull(adminService.add(admin));
		
		admin.setUsername("Nuevo admin");
		
	//	when(restTemplate.patchForObject(SERVER + "api/topics", topic, TsscTopic.class)).thenReturn(topic);
		adminService.update(admin);
		
		when(restTemplate.getForObject(SERVER+"api/admins/" + admin.getId(), TsscAdmin.class)).thenReturn(admin);
		assertEquals(adminService.findById(admin.getId()).getUsername(), "Nuevo admin");

	}


	@Test
	public void DeleteAdminTest() {
		

		TsscAdmin admin = new TsscAdmin();
		admin.setPassword("{noop}123");
		admin.setUsername("admin");
		admin.setSuperAdmin("admin");

		
		when(restTemplate.postForObject(SERVER + "api/admins", admin, TsscAdmin.class)).thenReturn(admin);
		assertNotNull(adminService.add(admin));
		
		restTemplate.delete(SERVER+"api/admins/" + admin.getId());
		
		when(restTemplate.getForObject(SERVER+"api/admins/" + admin.getId(), TsscAdmin.class)).thenReturn(null);
		assertNull(adminService.findById(admin.getId()));

	}


	@Test
	public void FindByIdTest() {


		TsscAdmin admin = new TsscAdmin();
		admin.setPassword("{noop}123");
		admin.setUsername("admin");
		admin.setSuperAdmin("admin");

		
		when(restTemplate.postForObject(SERVER + "api/admins", admin, TsscAdmin.class)).thenReturn(admin);
		assertNotNull(adminService.add(admin));
		
		when(restTemplate.getForObject(SERVER+"api/admins/" + admin.getId(), TsscAdmin.class)).thenReturn(admin);
		assertNotNull(adminService.findById(admin.getId()));

	}

	
}
