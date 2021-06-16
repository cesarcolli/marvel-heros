package com.arbo.marvel.characters;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeroCharactersRepository extends MongoRepository<HeroCharacters, String> {
    
}
