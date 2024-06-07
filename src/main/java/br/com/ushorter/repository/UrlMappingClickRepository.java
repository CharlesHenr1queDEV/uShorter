package br.com.ushorter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ushorter.model.UrlMappingClick;

@Repository
public interface UrlMappingClickRepository extends JpaRepository<UrlMappingClick, Long>{

   public List<UrlMappingClick> findByUrlMapping_ShortUrl(String shortUrl);

}
