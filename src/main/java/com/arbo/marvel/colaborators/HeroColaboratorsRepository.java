package com.arbo.marvel.colaborators;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeroColaboratorsRepository extends MongoRepository<HeroColaborators, String> {
}
