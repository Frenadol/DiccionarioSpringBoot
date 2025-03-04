package com.frenadol.diccionariofernandospringboot.controllers;

import com.frenadol.diccionariofernandospringboot.exception.WordNotFoundException;
import com.frenadol.diccionariofernandospringboot.models.Definicion;
import com.frenadol.diccionariofernandospringboot.models.Palabra;
import com.frenadol.diccionariofernandospringboot.servicies.DefinitionService;
import com.frenadol.diccionariofernandospringboot.servicies.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;

@RestController
@RequestMapping("/definicion")
public class DefinitionController {

    @Autowired
    DefinitionService definitionService;
    @Autowired
    WordService wordService;

    @GetMapping("/{id}")
    public Definicion getDefinicionById(@PathVariable Long id) throws WordNotFoundException {
        return definitionService.getDefinitionById(id);
    }

    @GetMapping
    public ResponseEntity<List<Definicion>> getAllDefiniciones() {
        List<Definicion> definiciones = definitionService.getAllDefinitions();
        return new ResponseEntity<>(definiciones, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/palabra/{id}/definiciones")
    public ResponseEntity<Palabra> getDefinicionesByWordId(@PathVariable Long id) throws WordNotFoundException {
        Palabra palabra = wordService.getWordById(id);
        List<Definicion> definiciones = definitionService.getDefinitionsByWordId(id);
        palabra.setDefiniciones(new LinkedHashSet<>(definiciones));
        return new ResponseEntity<>(palabra, new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Definicion> createDefinition(@RequestBody Definicion definition) {
        Definicion createdDefinition = definitionService.createDefinition(definition);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Definicion> updateDefinition(@PathVariable Long id, @RequestBody Definicion updatedDefinition) throws WordNotFoundException {
        Definicion definitionUpdated = definitionService.updateDefinition(id, updatedDefinition);
        return ResponseEntity.status(HttpStatus.OK).body(definitionUpdated);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteDefinitionById(@PathVariable Long id) throws WordNotFoundException {
        definitionService.deleteDefinition(id);
        return HttpStatus.ACCEPTED;
    }
}