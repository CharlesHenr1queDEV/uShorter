package br.com.ushorter.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UrlMappingClick {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private UrlMapping urlMapping;

	private LocalDateTime urlClickTime;

	public UrlMappingClick(Long id, UrlMapping urlMapping, LocalDateTime urlClickTime) {
		this.id = id;
		this.urlMapping = urlMapping;
		this.urlClickTime = urlClickTime;
	}
	
	public UrlMappingClick() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UrlMapping getUrlMapping() {
		return urlMapping;
	}

	public void setUrlMapping(UrlMapping urlMapping) {
		this.urlMapping = urlMapping;
	}

	public LocalDateTime getUrlClickTime() {
		return urlClickTime;
	}

	public void setUrlClickTime(LocalDateTime urlClickTime) {
		this.urlClickTime = urlClickTime;
	}

}
