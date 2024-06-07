package br.com.ushorter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import br.com.ushorter.dto.UrlMappingDTO;
import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.service.UrlMappingService;

@RestController
public class UrlMappingController {

	private UrlMappingService urlMappingService;

	public UrlMappingController(UrlMappingService urlMappingService) {
		this.urlMappingService = urlMappingService;
	}

	@PostMapping
	public ResponseEntity<?> saveUrlMapping(@RequestBody UrlMappingDTO urlMappingDTO) {
		try {
			UrlMapping saveUrlMapping = urlMappingService.saveUrlMapping(urlMappingDTO);
			return new ResponseEntity<>(saveUrlMapping, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{shortUrl}")
	public RedirectView redirectUrl(@PathVariable String shortUrl) {
		UrlMappingDTO redirectUrl = urlMappingService.redirectUrl(shortUrl);

		if (redirectUrl != null) {
			return new RedirectView(redirectUrl.getOriginalUrl());
		}
		return new RedirectView("/error");
	}

}
