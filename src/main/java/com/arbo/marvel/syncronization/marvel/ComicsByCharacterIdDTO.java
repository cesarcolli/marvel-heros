package com.arbo.marvel.syncronization.marvel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComicsByCharacterIdDTO {
    private long code;
    private String status;
    private Data data;

    @Getter
    @Setter
    public static class Data {
        private long offset;
        private long limit;
        private long total;
        private long count;
        private Result[] results;
    }    

    @Getter
    @Setter
    public static class Result {
        private long id;
        private String title;
        private String modified;
        private Characters characters;
        private Creators creators;
    }

    @Getter
    @Setter
    public static class Characters {
        private Character[] items;

        
    }

    @Getter
    @Setter
    public static class Character {
        private String name;
    }

    @Getter
    @Setter
    public static class Creators {
        private Creator[] items;

        
    }

    @Getter
    @Setter
    public static class Creator {
        private String name;
        private String role;
    }
}
