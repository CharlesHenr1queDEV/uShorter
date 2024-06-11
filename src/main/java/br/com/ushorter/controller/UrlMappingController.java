package br.com.ushorter.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
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
import br.com.ushorter.dto.UrlMappingDTO;
import br.com.ushorter.exception.UshorterException;
import br.com.ushorter.service.UrlMappingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class UrlMappingController {

	private UrlMappingService urlMappingService;
	
	@Value("${route.url}")
	private String routeUrl;

	public UrlMappingController(UrlMappingService urlMappingService) {
		this.urlMappingService = urlMappingService;
	}

	@Operation(summary = "Salvar um objeto UrlMapping que contém o mapeamento de url original e url curta", description = "Retorna a URL mapeada salva")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "URL mapeada salva com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UrlMappingDTO.class))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) })
	@PostMapping
	public ResponseEntity<?> saveUrlMapping(@RequestBody OriginalUrlDTO originalUrlDTO,
			@Parameter(name = "language", description = "Idioma da solicitação", in = ParameterIn.HEADER, schema = @Schema(type = "string", defaultValue = "br", example = "br"), examples = {
					@ExampleObject(name = "Opção en", value = "en"),
					@ExampleObject(name = "Opção br", value = "br") }) @RequestHeader(name = "language", required = false) String language) {
		try {
			UrlMappingDTO saveUrlMapping = urlMappingService.saveUrlMapping(originalUrlDTO, language);
			return new ResponseEntity<>(saveUrlMapping, HttpStatus.CREATED);
		} catch (UshorterException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Redirecionar para a URL original", description = "Redireciona a URL curta para a URL original correspondente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "302", description = "Redirecionamento bem-sucedido", content = @Content),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content) })
	@GetMapping("/{shortUrl}")
	public ResponseEntity<?> redirectUrl(@PathVariable String shortUrl,
			@Parameter(name = "language", description = "Idioma da solicitação", in = ParameterIn.HEADER, schema = @Schema(type = "string", defaultValue = "br", example = "br"), examples = {
					@ExampleObject(name = "Opção en", value = "en"),
					@ExampleObject(name = "Opção br", value = "br") }) @RequestHeader(name = "language", required = false) String language) {
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
