package com.computacion.taller.Delegate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.computacion.taller.Modelo.Consulta;
import com.computacion.taller.Modelo.ConsultaTopic;
import com.computacion.taller.Modelo.TsscTopic;


@Component
public class TopicDelegateImpt implements TopicDelegate {
	
	
	private RestTemplate restTemplate;
	final String SERVER="http://localhost:8080/";

	public TopicDelegateImpt() {
		this.restTemplate = new RestTemplate ();
	}

	@Override
	public TsscTopic add(TsscTopic nuevo) {
		TsscTopic encontrado= restTemplate.postForObject(SERVER +"api/topics", nuevo, TsscTopic.class);
		return encontrado;
	}

	@Override
	public void update(TsscTopic entity) {
		restTemplate.put(SERVER+"api/topics", entity, TsscTopic.class);
	}

	@Override
	public void delete(TsscTopic encontrado) {
		restTemplate.delete(SERVER+"api/topics/"+encontrado.getId());
		
	}

	@Override
	public TsscTopic findById(long id) {
		TsscTopic encontrado= restTemplate.getForObject(SERVER+"api/topics/"+id, TsscTopic.class ); 
		return encontrado;
	}

	@Override
	public Iterable<TsscTopic> findAll() {

		
		TsscTopic[] topics= restTemplate.getForObject(SERVER+"api/topics",TsscTopic[].class );
		List<TsscTopic> nueva= Arrays.asList(topics);
		
		return nueva;
	}

	@Override
	public List<ConsultaTopic>  consulta(Consulta date){
		
		ConsultaTopic[] list = restTemplate.getForObject(SERVER+"api/topics/consulta/" + date.getDate(), ConsultaTopic[].class );
		List<ConsultaTopic> lista = Arrays.asList(list);
		return lista;
	}

}
