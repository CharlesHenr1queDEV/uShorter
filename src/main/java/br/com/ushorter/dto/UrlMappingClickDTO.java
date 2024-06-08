package br.com.ushorter.dto;

import java.time.LocalDateTime;

import br.com.ushorter.model.UrlMappingClick;

public record UrlMappingClickDTO(UrlMappingDTO urlMapping, LocalDateTime urlClickTime) {
    
    public UrlMappingClick parseUrlMappingClick() {
        return new UrlMappingClick(urlMapping.parseUrlMapping(), urlClickTime);
    }
}