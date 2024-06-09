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
import br.com.ushorter.model.UrlMappingClick;
import br.com.ushorter.repository.UrlMappingClickRepository;
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
	private UrlMappingClickRepository urlMappingClickRepository;

	@BeforeAll
	public void setup() throws Exception {
		OriginalUrlDTO originalUrlDTO = new OriginalUrlDTO("http://google.com");
		UrlMapping saveUrlMapping = urlMappingService.saveUrlMapping(originalUrlDTO, "en");
		urlMappingService.redirectUrl(saveUrlMapping.getShortUrl(), "en");
	}

	@Test
	public void findQuantityClick_whenShortUrlIsEmpty_thenError() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(END_POINT + "/{shortUrl}", "")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void findQuantityClick_whenShortUrlNull_thenError() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(END_POINT + "/{shortUrl}", "  ")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void findQuantityClick_whenShortUrlNotExist_thenError() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(END_POINT + "/{shortUrl}", "notExist"))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void findQuantityClick_whenShortUrlIsExist_thenSucess() throws Exception {
		UrlMappingClick urlMappingClick = urlMappingClickRepository.findAll().get(0);

		mock.perform(
				MockMvcRequestBuilders.get(END_POINT + "/{shortUrl}", urlMappingClick.getUrlMapping().getShortUrl()))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
