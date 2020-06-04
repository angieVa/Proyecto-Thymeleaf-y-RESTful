package com.computacion.taller.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.computacion.taller.Modelo.TsscTopic;
import com.computacion.taller.Service.TopicService;
import com.computacion.taller.Modelo.ConsultaTopic;


@RestController
public class TopicRestController implements TopicController{

	@Autowired
	private TopicService service;

	@Override
	@GetMapping("/api/topics/{id}")
	public TsscTopic findTopicById(@PathVariable long id) {
		return service.findByIdR(id).get();
	}
	
	@Override
	@PostMapping("/api/topics")
	public TsscTopic AddTopic(@RequestBody TsscTopic topic) {
		// TODO Auto-generated method stub
		return service.AddTopic(topic);
	}


//	@Override
//	@GetMapping("/topics")
//	public boolean existeById(@PathVariable long id) {
//		// TODO Auto-generated method stub
//		return service.existeById(id);
//	}

	@Override
	@GetMapping("/api/topics")
	public Iterable<TsscTopic> findAll() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	@PutMapping("/api/topics")
	public void Update(@RequestBody TsscTopic topic) {
		// TODO Auto-generated method stub
		service.update(topic);
	}

	@Override
	@DeleteMapping("/api/topics/{id}")
	public TsscTopic DeleteTopic(@PathVariable long id) {
		// TODO Auto-generated method stub
		TsscTopic encontrado=service.findByIdR(id).get();
		service.deleteTopic(encontrado);
		return encontrado;
	}
	
	@Override
	@GetMapping("/api/topics/consulta/{date}")
	public List<ConsultaTopic> ConsultaTopic(@PathVariable String date){
		
		LocalDate dateF = LocalDate.parse(date);
		List<Object[]> lista =  service.findTopicsByGameDate(dateF);
		List<ConsultaTopic> consulta = new ArrayList<ConsultaTopic>();
		
		for(int i = 0; i< lista.size(); i++) {
			
			TsscTopic topic = (TsscTopic) (lista.get(i)[0]);
			long cant = (long) lista.get(i)[1];
			
			ConsultaTopic con = new ConsultaTopic(topic , cant);
			consulta.add(con);		
		}
		
		return consulta;
	}
	
}
