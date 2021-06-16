package com.arbo.marvel.syncronization;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.arbo.marvel.characters.HeroCharacters;
import com.arbo.marvel.characters.HeroCharactersRepository;
import com.arbo.marvel.colaborators.HeroColaborators;
import com.arbo.marvel.colaborators.HeroColaboratorsRepository;
import com.arbo.marvel.heros.Hero;
import com.arbo.marvel.heros.HeroRepository;
import com.arbo.marvel.syncronization.marvel.CharactersByNameDTO;
import com.arbo.marvel.syncronization.marvel.ComicsByCharacterIdDTO;
import com.arbo.marvel.syncronization.marvel.MarvelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SyncronizationService {
    @Autowired HeroRepository heroRepository;
    @Autowired HeroColaboratorsRepository colaboratorRepository;
    @Autowired HeroCharactersRepository charactersRepository;
    @Autowired MarvelService marvelService;

    /**
     * Start the data sycronization with marvel api
     */
    public void sync(){
        heroRepository
            .findAll()
            .forEach(hero -> this.syncHero(hero));
    }

    /**
     * Syncronize changes with marvel by one hero
     * @param hero
     */
    private void syncHero(Hero hero){
        log.info("sync hero id: {}", hero.getId());
        final LocalDateTime syncDate = LocalDateTime.now();

        //get the last data syncronizated from hero colaborators
		final HeroColaborators heroColaborators = colaboratorRepository
            .findById(hero.getId())
            .orElse(new HeroColaborators(hero.getId()));
        
        //get the last data syncronizated from hero characters
        final HeroCharacters heroCharacters = charactersRepository
            .findById(hero.getId())
            .orElse(new HeroCharacters(hero.getId()));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String lastSynDate = hero.getLastSync() != null ? hero.getLastSync().format(formatter) : null;

        log.info("last sync {}", lastSynDate);

        //get the changes from Characters since last sync date
        CharactersByNameDTO charactersDTO = marvelService.getCharactersByName(hero.getName(), lastSynDate);

        for(CharactersByNameDTO.Result result : charactersDTO.getData().getResults()  ){
            log.info("character id: {}", result.getId());

            //get the changes from Commics associated to character id since last sync date
            ComicsByCharacterIdDTO comicsHero = marvelService.getComicsByCharacterId(result.getId(), lastSynDate);

            for(ComicsByCharacterIdDTO.Result comic : comicsHero.getData().getResults()){
                for(ComicsByCharacterIdDTO.Creator creator : comic.getCreators().getItems()){
                    heroColaborators.addCreator(creator.getName(), creator.getRole());
                }

                for(ComicsByCharacterIdDTO.Character character : comic.getCharacters().getItems()){
                    // if the hero name is same to character then is ignored
                    if(!character.getName().equals(result.getName())){
                        heroCharacters.addCharacter(character.getName(), comic.getTitle());
                    }
                }
            }
        }

        //update the LastSyncDate
        heroColaborators.setLastSync(syncDate);
        heroCharacters.setLastSync(syncDate);
        hero.setLastSync(syncDate);

        //update the documents
        colaboratorRepository.save(heroColaborators);
        charactersRepository.save(heroCharacters);
        heroRepository.save(hero);
    }

    
}
