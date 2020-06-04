package com.computacion.taller.Modelo;

public class ConsultaTopic {
	
	private TsscTopic topic;
	private long cantidad;
	
	
	public ConsultaTopic(TsscTopic topic, long cantidad) {
	
		this.topic = topic;
		this.cantidad = cantidad;
	}


	public ConsultaTopic() {
		super();
	}


	public TsscTopic getTopic() {
		return topic;
	}


	public void setTopic(TsscTopic topic) {
		this.topic = topic;
	}


	public long getCantidad() {
		return cantidad;
	}


	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	
}
