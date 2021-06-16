package com.arbo.marvel.syncronization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("marvel")
public class SyncronizationController {
    @Autowired private SyncronizationService service;
    
    @PostMapping("/sync")
    public ResponseEntity sync(){
        service.sync();
        return ResponseEntity.ok().build();
    }
}
