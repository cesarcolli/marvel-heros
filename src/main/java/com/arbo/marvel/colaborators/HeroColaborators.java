package com.arbo.marvel.colaborators;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "colaborators")
@Getter
@Setter
public class HeroColaborators {
    @Id
    @JsonIgnore
    private String id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)  
    @Field(name = "last_sync")
    private LocalDateTime lastSync;
    private Set<String> editors;
    private Set<String> writers;
    private Set<String> colorists;

    public HeroColaborators(final String id) {
        this.id = id;
        this.editors = new HashSet<>();
        this.writers = new HashSet<>();
        this.colorists = new HashSet<>();
    }

    /**
     * Add creator a the valid lists depends on role
     * @param name
     * @param role
     */
    public void addCreator(String name, String role){
        switch(role.toLowerCase()){
            case "editor" : this.editors.add(name); break;
            case "writer" : this.writers.add(name); break;
            case "colorist" : this.colorists.add(name); break;
        }
    }
}
