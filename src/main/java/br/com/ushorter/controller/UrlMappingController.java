package br.com.ushorter.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.ushorter.dto.OriginalUrlDTO;
import br.com.ushorter.exception.UshorterException;
import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.service.UrlMappingService;

@RestController
public class UrlMappingController {

	private UrlMappingService urlMappingService;

	public UrlMappingController(UrlMappingService urlMappingService) {
		this.urlMappingService = urlMappingService;
	}

	@PostMapping
	public ResponseEntity<?> saveUrlMapping(@RequestBody OriginalUrlDTO originalUrlDTO,
			@RequestHeader(name = "language", required = false) String language) {
		try {
			UrlMapping saveUrlMapping = urlMappingService.saveUrlMapping(originalUrlDTO, language);
			return new ResponseEntity<>(saveUrlMapping, HttpStatus.CREATED);
		} catch (UshorterException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{shortUrl}")
	public ResponseEntity<?> redirectUrl(@PathVariable String shortUrl,
	        @RequestHeader(name = "language", required = false) String language) {
	    try {
	        String originalUrl = urlMappingService.redirectUrl(shortUrl, language);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(new URI(originalUrl));
	        return new ResponseEntity<>(headers, HttpStatus.FOUND);
	    } catch (UshorterException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

}
