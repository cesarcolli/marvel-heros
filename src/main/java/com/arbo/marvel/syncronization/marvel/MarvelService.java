package com.arbo.marvel.syncronization.marvel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class MarvelService {
    @Autowired Environment env;
    @Autowired RestTemplate restTemplate;

    /**
     * Get characters by name from marvel rest api
     * @param name the hero name
     * @param lastSyncDate the last sync date
     * @return
     */
    public CharactersByNameDTO getCharactersByName(String name, String lastSyncDate){
        return restTemplate.getForObject(
            UriComponentsBuilder.fromPath("/v1/public/characters")
                .queryParam("apikey", env.getRequiredProperty("marvel.apikey"))
                .queryParam("hash", env.getRequiredProperty("marvel.hash"))
                .queryParam("ts", env.getRequiredProperty("marvel.ts"))
                .queryParam("name", name)
                .queryParam("modifiedSince", lastSyncDate)
                .queryParam("limit", env.getRequiredProperty("marvel.result.limit"))
                .build()
                .toUriString()
            , CharactersByNameDTO.class);
    }

    /**
     * Get comics by character id from marvel rest api
     * @param characterId the character id
     * @param lastSyncDate the last sync date
     * @return
     */
    public ComicsByCharacterIdDTO getComicsByCharacterId(long characterId, String lastSyncDate){
        
        return restTemplate.getForObject(
            UriComponentsBuilder.fromPath("/v1/public/characters/{id}/comics")
                .queryParam("apikey", env.getRequiredProperty("marvel.apikey"))
                .queryParam("hash", env.getRequiredProperty("marvel.hash"))
                .queryParam("ts", env.getRequiredProperty("marvel.ts"))
                .queryParam("modifiedSince", lastSyncDate)
                .queryParam("limit", env.getRequiredProperty("marvel.result.limit"))
                .buildAndExpand(characterId)
                .toUriString()
            , ComicsByCharacterIdDTO.class);
    }

}
