package com.computacion.taller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import com.computacion.taller.Modelo.TsscAdmin;
import com.computacion.taller.Modelo.TsscGame;
import com.computacion.taller.Modelo.TsscGameAdmin;
import com.computacion.taller.Modelo.TsscStory;
import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Service.AdminServiceImp;
import com.computacion.taller.Service.GameService;
import com.computacion.taller.Service.StoryService;
import com.computacion.taller.Service.TopicService;


@SpringBootApplication
public class Taller1AngieCordoba1Application {
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
//	spring.datasource.platform=postgres
//			spring.datasource.url=jdbc:postgresql://localhost:5432/Computacion_final
//			spring.datasource.username=postgres
//			spring.datasource.password=1006189874
//			spring.jpa.show-sql=true
//			spring.jpa.generate-ddl=true
//			spring.jpa.hibernate.ddl-auto=create-drop
//			spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
//			spring.datasource.driver-class-name=org.postgresql.Driver
	
	//------------------------------------------------------
	//INTEGRANTES
	//Alejandra González Veléz 
	//Angie Valentina Córdoba Pinzón
	//------------------------------------------------------

	public static void main(String[] args) {
//		SpringApplication.run(Taller1AngieCordoba1Application.class, args);
		
		ConfigurableApplicationContext c = SpringApplication.run(Taller1AngieCordoba1Application.class, args);
		AdminServiceImp adminService = c.getBean(AdminServiceImp.class);

		TsscAdmin nuevo= new TsscAdmin();
		nuevo.setPassword("{noop}123");
		nuevo.setUsername("admin");
		nuevo.setSuperAdmin("admin");

		adminService.addAdmin(nuevo);

		TsscAdmin superAdmin= new TsscAdmin();
		superAdmin.setPassword("{noop}123");
		superAdmin.setUsername("superadmin");
		superAdmin.setSuperAdmin("superadmin");

		adminService.addAdmin(superAdmin);

		TopicService topicService = c.getBean(TopicService.class);
		GameService gameService = c.getBean(GameService.class);
		StoryService storyserv = c.getBean(StoryService.class);


		TsscTopic topic = new TsscTopic();
		topic.setName("Aliens");
		topic.setDescription("Viaje Espacial");
		topic.setDefaultGroups(12);
		topic.setDefaultSprints(20);
		topic.setGroupPrefix("VE");


		TsscTopic topic2 = new TsscTopic();
		topic2.setName("Aliens 2");
		topic2.setDescription("Viaje Espacial 2");
		topic2.setDefaultGroups(20);
		topic2.setDefaultSprints(20);
		topic2.setGroupPrefix("VE2");


		TsscTopic topic3 = new TsscTopic();
		topic3.setName("Aliens 3");
		topic3.setDescription("Viaje Espacial 3");
		topic3.setDefaultGroups(30);
		topic3.setDefaultSprints(30);
		topic3.setGroupPrefix("VE3");

		topicService.AddTopic(topic);
		topicService.AddTopic(topic2);

		TsscGame game = new TsscGame();

		ArrayList<TsscGame> lista= new ArrayList<TsscGame>();

		game.setNGroups(1);
		game.setNSprints(1);
		game.setName("Juego");
		game.setAdminPassword("pass");
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("pass");
		game.setGuestPassword("pass");

		TsscTopic topic4 = new TsscTopic();
		topic4.setDefaultGroups(1);
		topic4.setDefaultSprints(1);
		topic4.setTsscGames(lista);

		gameService.AddGame(game, 0, false);

		List<TsscStory> st = new ArrayList<TsscStory>();
		game.setTsscStories(st);
		
		TsscStory story = new TsscStory();
		story.setBusinessValue(BigDecimal.valueOf(2));
		story.setInitialSprint(BigDecimal.valueOf(1));
		story.setPriority(BigDecimal.valueOf(1));
		story.setTsscGame(game);
		story.setDescription("Descripcion");

		storyserv.AddStory(story, game.getId());

		ArrayList<TsscGameAdmin> lista2= new ArrayList<TsscGameAdmin>();
		
		TsscGame game2 = new TsscGame();
		
		game2.setName("Juego 2");
		game2.setAdminPassword("pass");
		game2.setScheduledDate(LocalDate.now());
		game2.setScheduledTime(LocalTime.now());
		game2.setUserPassword("pass");
		game2.setGuestPassword("pass");
		game2.setTsscGameAdmins(lista2);
		gameService.AddGame(game2, 0, false);
		
	}
	

}
