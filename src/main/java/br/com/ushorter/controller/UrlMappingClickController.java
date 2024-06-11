package br.com.ushorter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ushorter.exception.UshorterException;
import br.com.ushorter.service.UrlMappingClickService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "url-click")
public class UrlMappingClickController {

	private UrlMappingClickService urlMappingClickService;
	
	public UrlMappingClickController(UrlMappingClickService urlMappingClickService) {
		this.urlMappingClickService = urlMappingClickService;
	}

	@Operation(summary = "Encontrar a quantidade de cliques", description = "Retorna a quantidade de cliques para a URL curta fornecida")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Quantidade de cliques encontrada com sucesso", content = @Content),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) })
	@GetMapping
	public ResponseEntity<?> findQuantityClick(@RequestHeader(name = "language", required = false) String language,
			@RequestHeader(name = "shortUrl", required = false) String shortUrl) {
		
		try {
			int quantityClick = urlMappingClickService.findQuantityClick(shortUrl, language);
			return new ResponseEntity<>(quantityClick, HttpStatus.OK);
		}  catch (UshorterException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
