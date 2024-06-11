package br.com.ushorter.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.ushorter.dto.OriginalUrlDTO;
import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.repository.UrlMappingRepository;
import br.com.ushorter.service.UrlMappingService;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UrlMappingClickControllerTest {

	private final String END_POINT = "/url-click";

	@Autowired
	private MockMvc mock;

	@Autowired
	private UrlMappingService urlMappingService;

	@Autowired
	private UrlMappingRepository urlMappingRepository;

	@BeforeAll
	public void setup() throws Exception {
		OriginalUrlDTO originalUrlDTO = new OriginalUrlDTO("http://google.com");
		urlMappingService.saveUrlMapping(originalUrlDTO, "en");
	}

	@Test
	public void findQuantityClick_whenShortUrlIsEmpty_thenError() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(END_POINT + "/{shortUrl}", "")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void findQuantityClick_whenShortUrlNull_thenError() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(END_POINT)
                .header("shortUrl", ""))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void findQuantityClick_whenShortUrlNotExist_thenError() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(END_POINT)
				.header("shortUrl", "notExist"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void findQuantityClick_whenShortUrlIsExist_thenSucess() throws Exception {
		UrlMapping urlMapping = urlMappingRepository.findAll().get(0);
		
		mock.perform(MockMvcRequestBuilders.get(END_POINT)
                .header("shortUrl", urlMapping.getShortUrl()))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
	}

}
