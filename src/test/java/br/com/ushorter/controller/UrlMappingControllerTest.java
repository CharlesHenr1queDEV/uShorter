package br.com.ushorter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ushorter.dto.OriginalUrlDTO;
import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.repository.UrlMappingRepository;

@AutoConfigureMockMvc
@SpringBootTest
public class UrlMappingControllerTest {

	private final String END_POINT = "/";
	
	@Autowired
	private UrlMappingRepository urlMappingRepository;

	@Autowired
	private MockMvc mock;

	@BeforeEach
	@Test
	public void saveUrlMaping_whenOriginalUrlIsValid_thenSuccess() throws Exception {
		
		OriginalUrlDTO originalUrlDTO = new OriginalUrlDTO("http://google.com");

		ObjectMapper objectMapper = new ObjectMapper();

		String originalUrlObjectString = objectMapper.writeValueAsString(originalUrlDTO);

		mock.perform(MockMvcRequestBuilders.post(END_POINT).content(originalUrlObjectString)
				.contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.originalUrl").value("http://google.com"));
	}

	@Test
	public void saveUrlMapping_whenOriginalUrlIsEmpty_thenError() throws Exception {
		
		OriginalUrlDTO originalUrlDTO = new OriginalUrlDTO("");

		ObjectMapper objectMapper = new ObjectMapper();

		String originalUrlObjectString = objectMapper.writeValueAsString(originalUrlDTO);

		mock.perform(MockMvcRequestBuilders.post(END_POINT).content(originalUrlObjectString)
				.contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void saveUrlMapping_whenOriginalUrlIsNull_thenError() throws Exception {
		
		OriginalUrlDTO originalUrlDTO = new OriginalUrlDTO(null);

		ObjectMapper objectMapper = new ObjectMapper();

		String originalUrlObjectString = objectMapper.writeValueAsString(originalUrlDTO);

		mock.perform(MockMvcRequestBuilders.post(END_POINT).content(originalUrlObjectString)
				.contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void saveUrlMapping_whenOriginalUrlIsNotAcessible_thenError() throws Exception {
		
		OriginalUrlDTO original = new OriginalUrlDTO("uol.rrr");

		ObjectMapper objectMapper = new ObjectMapper();

		String originalUrlObjectString = objectMapper.writeValueAsString(original);

		mock.perform(MockMvcRequestBuilders.post(END_POINT).content(originalUrlObjectString)
				.contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void saveUrlMapping_whenOriginalUrlNotExist_thenError() throws Exception {
		
		mock.perform(MockMvcRequestBuilders.post(END_POINT))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void redirectUrl_whenShortUrlIsValid_thenRedirect() throws Exception {
		
		UrlMapping urlMapping = urlMappingRepository.findAll().get(0);

		mock.perform(MockMvcRequestBuilders.get(END_POINT + "{shortUrl}", urlMapping.getShortUrl())).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.redirectedUrl(urlMapping.getOriginalUrl()))
				.andExpect(MockMvcResultMatchers.status().isFound());
	}
	
	@Test
	public void redirectUrl_whenShortUrlIsBlank_thenError() throws Exception {
		
		mock.perform(MockMvcRequestBuilders.get(END_POINT + "{shortUrl}", ""))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
	}
	
	@Test
	public void redirectUrl_whenShortUrlNotExist_thenError() throws Exception {
		
		mock.perform(MockMvcRequestBuilders.get(END_POINT + "{shortUrl}", "notExist"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
