package br.com.ushorter.dto;

import java.time.LocalDateTime;

import br.com.ushorter.model.UrlMappingClick;

public class UrlMappingClickDTO {

	private UrlMappingDTO urlMapping;

	private LocalDateTime urlClickTime;

	public UrlMappingClickDTO() {

	}

	public UrlMappingDTO getUrlMapping() {
		return urlMapping;
	}

	public void setUrlMapping(UrlMappingDTO urlMapping) {
		this.urlMapping = urlMapping;
	}

	public LocalDateTime getUrlClickTime() {
		return urlClickTime;
	}

	public void setUrlClickTime(LocalDateTime urlClickTime) {
		this.urlClickTime = urlClickTime;
	}
	
	public UrlMappingClick parseUrlMappingClick() {
		UrlMappingClick urlMappingClick = new UrlMappingClick();
		urlMappingClick.setUrlMapping(this.urlMapping.parseUrlMapping());
		urlMappingClick.setUrlClickTime(this.urlClickTime);
		
		return urlMappingClick;
	}

}
