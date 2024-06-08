package br.com.ushorter.model;

import java.util.List;

import br.com.ushorter.dto.UrlMappingDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UrlMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String originalUrl;

	private String shortUrl;

	@OneToMany(mappedBy = "urlMapping")
	private List<UrlMappingClick> urlClicks;

	public UrlMapping() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public List<UrlMappingClick> getUrlClicks() {
		return urlClicks;
	}

	public void setUrlClicks(List<UrlMappingClick> urlClicks) {
		this.urlClicks = urlClicks;
	}

	public UrlMappingDTO toUrlMappingDTO() {
		return new UrlMappingDTO(originalUrl, shortUrl);
	}

}
