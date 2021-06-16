package com.arbo.marvel.syncronization.marvel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharactersByNameDTO {
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
        private String name;
    }
}
