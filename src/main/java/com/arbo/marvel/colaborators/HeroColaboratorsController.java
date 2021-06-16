package com.arbo.marvel.colaborators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("marvel/colaborators")
public class HeroColaboratorsController {

    @Autowired private HeroColaboratorsRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) {
        Optional colaborator = repository.findById(id);

        if(!colaborator.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(colaborator.get());
    }    
}
