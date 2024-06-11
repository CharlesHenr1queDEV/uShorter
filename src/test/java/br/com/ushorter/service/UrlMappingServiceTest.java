package br.com.ushorter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ushorter.dto.OriginalUrlDTO;
import br.com.ushorter.dto.UrlMappingDTO;
import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.repository.UrlMappingRepository;

@SpringBootTest
public class UrlMappingServiceTest {
	
    @Autowired
    private UrlMappingService urlMappingService;

    @Mock
    private UrlMappingRepository urlMappingRepositoryMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveUrlMapping_whenOriginalUrlProvided_shouldReturnValidShortUrl() throws Exception {
        OriginalUrlDTO originalUrlDTO = new OriginalUrlDTO("http://example.com");

        when(urlMappingRepositoryMock.save(any(UrlMapping.class)))
            .thenReturn(new UrlMappingDTO("http://example.com", "abc123").parseUrlMapping());

        UrlMappingDTO result = urlMappingService.saveUrlMapping(originalUrlDTO, "en");

        assertNotNull(result);
        assertEquals("http://example.com", result.originalUrl());
        assertTrue(result.shortUrl().matches("^http://localhost:8080/[a-zA-Z0-9]+$"));
    }
    
    @Test
    public void generateShortUrl_whenOriginalUrlProvided_shouldReturnValidShortUrl() {
        String originalUrl = "http://example.com";

        String shortUrl = urlMappingService.generateShortUrl(originalUrl);

        assertNotNull(shortUrl);
        assertEquals(7, shortUrl.length());
        assertTrue(shortUrl.chars().allMatch(Character::isLetterOrDigit)); 
    }
}
