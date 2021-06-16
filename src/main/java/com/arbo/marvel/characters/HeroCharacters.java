package com.arbo.marvel.characters;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "characters")
@Getter
@Setter
public class HeroCharacters {
    @JsonIgnore
    @Id
    private String id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)  
    @Field(name = "last_sync")
    private LocalDateTime lastSync;
    private List<Character> characters;

    @JsonIgnore
    @Transient
    private Map<String, Character> mapCharacters;

    public HeroCharacters(final String id){
        this.id = id;
        this.characters = new ArrayList<>();
    }

    /**
     * Add new character and comic associated to hero id
     * @param characterName
     * @param comic
     */
    public void addCharacter(final String characterName, final String comic){
        Character character;

        if(characterName == null || comic == null){
            return;
        }   

        if(mapCharacters==null){
            buildMap();
        }

        if(!mapCharacters.containsKey(characterName)){
            character = new Character(characterName);
            this.mapCharacters.put(characterName, character);
            this.characters.add(character);
        }else{
            character = mapCharacters.get(characterName);
        }

        if(character!=null){
            character.getComics().add(comic);
        }
        
    }


    /**
     * Build the map of characters
     */
    private void buildMap(){
        this.mapCharacters = new HashMap<>();
        for(Character character : this.getCharacters()){
            mapCharacters.putIfAbsent(character.getCharacter(), character);
        }
    }
}
