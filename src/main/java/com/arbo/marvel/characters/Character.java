package com.arbo.marvel.characters;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Character {
    private String character;
    private Set<String> comics;

    public Character(final String character){
        this.character = character;
        this.comics = new HashSet<>();
    }
}
