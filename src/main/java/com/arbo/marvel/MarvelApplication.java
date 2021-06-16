package com.arbo.marvel;

import com.arbo.marvel.heros.Hero;
import com.arbo.marvel.heros.HeroRepository;
import com.arbo.marvel.syncronization.SyncronizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MarvelApplication implements CommandLineRunner{
	@Autowired private HeroRepository heroRepository;
	@Autowired private SyncronizationService service;

	public static void main(String[] args) {
		SpringApplication.run(MarvelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if(heroRepository.count()==0){
			heroRepository.save(new Hero("ironman", "Iron Man"));
			heroRepository.save(new Hero("capamerica", "Captain America"));
			service.sync();
		}
		
	}

}
