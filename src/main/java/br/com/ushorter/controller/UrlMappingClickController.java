package br.com.ushorter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ushorter.service.UrlMappingClickService;

@RestController
@RequestMapping(value = "url-click")
public class UrlMappingClickController {

	private UrlMappingClickService urlMappingClickService;

	public UrlMappingClickController(UrlMappingClickService urlMappingClickService) {
		this.urlMappingClickService = urlMappingClickService;
	}

	@GetMapping(value = "/{shortUrl}")
	public ResponseEntity<?> findQuantityClick(@PathVariable String shortUrl) {
		int quantityClick = urlMappingClickService.findQuantityClick(shortUrl);
		return new ResponseEntity<>(quantityClick, HttpStatus.OK);
	}
}
