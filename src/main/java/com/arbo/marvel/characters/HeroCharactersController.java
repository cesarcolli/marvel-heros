package com.arbo.marvel.characters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("marvel/characters")
public class HeroCharactersController {
    @Autowired private HeroCharactersRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) {
        Optional heroCharacters = repository.findById(id);

        if(!heroCharacters.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(heroCharacters.get());
    }
}
