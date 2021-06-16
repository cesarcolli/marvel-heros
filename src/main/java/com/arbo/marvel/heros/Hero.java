package com.arbo.marvel.heros;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "heros")
@Getter
@Setter
public class Hero {
    @Id
    private String id;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)  
    @Field(name = "last_sync")
    private LocalDateTime lastSync;

    public Hero(final String id, final String name){
        this.id = id;
        this.name = name;
    }
  
}
