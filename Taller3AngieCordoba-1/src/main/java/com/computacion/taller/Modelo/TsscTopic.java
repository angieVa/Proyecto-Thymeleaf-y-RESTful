package com.computacion.taller.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the TSSC_TOPIC database table.
 * 
 */
@Entity
@Table(name = "TSSC_TOPIC")
@NamedQuery(name = "TsscTopic.findAll", query = "SELECT t FROM TsscTopic t")
public class TsscTopic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_TOPIC_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_TOPIC_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_TOPIC_ID_GENERATOR")
	private long id;

	@NotBlank(message = "Descripción inválida")
	private String description;

	@NotBlank(message = "Nombre inválido")
	private String name;

	@Min(value = 1, message = "Debe haber uno o más sprints")
	@Column(name = "DEFAULT_SPRINTS")
	private long defaultSprints;

	@Min(value = 1, message = "Debe haber uno o más grupos")
	@Column(name = "DEFAULT_GROUPS")
	private long defaultGroups;

	@NotBlank(message = "Prefijo de grupo inválido")
	@Column(name = "GROUP_PREFIX")
	private String groupPrefix;
	
	// bi-directional many-to-one association to TsscStory
	@OneToMany(mappedBy = "tsscTopic")
	@JsonIgnore
	private List<TsscGame> tsscGames;

	// bi-directional many-to-one association to TsscStory
	@OneToMany(mappedBy = "tsscTopic")
	@JsonIgnore
	private List<TsscStory> tsscStories;
	
	@OneToMany(mappedBy = "tsscTopic")
	@JsonIgnore
	private List<TsscTimecontrol> tsscCronograma;
	

	public List<TsscTimecontrol> getTsscCronograma() {
		return tsscCronograma;
	}

	public void setTsscCronograma(List<TsscTimecontrol> tsscCronograma) {
		this.tsscCronograma = tsscCronograma;
	}

	public TsscTopic() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDefaultSprints() {
		return defaultSprints;
	}

	public void setDefaultSprints(long defaultSprints) {
		this.defaultSprints = defaultSprints;
	}

	public long getDefaultGroups() {
		return defaultGroups;
	}

	public void setDefaultGroups(long defaultGroups) {
		this.defaultGroups = defaultGroups;
	}

	public String getGroupPrefix() {
		return groupPrefix;
	}

	public void setGroupPrefix(String groupPrefix) {
		this.groupPrefix = groupPrefix;
	}

	public List<TsscStory> getTsscStories() {
		return this.tsscStories;
	}

	public void setTsscStories(List<TsscStory> tsscStories) {
		this.tsscStories = tsscStories;
	}

	public TsscStory addTsscStory(TsscStory tsscStory) {
		getTsscStories().add(tsscStory);
		tsscStory.setTsscTopic(this);

		return tsscStory;
	}

	public TsscStory removeTsscStory(TsscStory tsscStory) {
		getTsscStories().remove(tsscStory);
		tsscStory.setTsscTopic(null);

		return tsscStory;
	}

	public List<TsscGame> getTsscGames() {
		return tsscGames;
	}

	public void setTsscGames(List<TsscGame> tsscGames) {
		this.tsscGames = tsscGames;
	}
	
	public TsscGame addTsscGame(TsscGame game) {
		game.setTsscTopic(this);
		getTsscGames().add(game);			
		return game;
	}
	
}