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
@RequestMapping("/palabra")
public class WordController {

    @Autowired
    WordService wordService;
    @Autowired
    DefinitionService definitionService;

    @GetMapping("/{id}")
    public Palabra getPalabraById(@PathVariable Long id) throws WordNotFoundException {
        return wordService.getWordById(id);
    }

    @GetMapping
    public ResponseEntity<List<Palabra>> getAllPalabras() {
        List<Palabra> palabras = wordService.getAllWords();
        for (Palabra palabra : palabras) {
            palabra.setDefiniciones(new LinkedHashSet<>());
        }
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/definiciones")
    public ResponseEntity<List<Palabra>> getAllPalabrasWithDefiniciones() {
        List<Palabra> palabras = wordService.getAllWords();
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Palabra> createWord(@RequestBody Palabra word) {
        Palabra createdWord = wordService.createWord(word);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWord);
    }

    @PostMapping("/condefiniciones")
    public ResponseEntity<Palabra> createWordWithDefinitions(@RequestBody Palabra word) {
        Palabra createdWord = wordService.createWordWithDefinitions(word);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Palabra> updateWord(@PathVariable Long id, @RequestBody Palabra updatedWord) throws WordNotFoundException {
        Palabra wordUpdated = wordService.updateWord(id, updatedWord);
        return ResponseEntity.status(HttpStatus.OK).body(wordUpdated);
    }

    @GetMapping("/{id}/condefiniciones")
    public ResponseEntity<Palabra> getWordWithDefinitions(@PathVariable Long id) throws WordNotFoundException {
        Palabra palabra = wordService.getWordWithDefinitions(id);
        return ResponseEntity.status(HttpStatus.OK).body(palabra);
    }
    @PostMapping("/{id}/definiciones")
    public ResponseEntity<Definicion> createNewDefinition(@PathVariable long id, @RequestBody Definicion definition) throws WordNotFoundException {
        Palabra palabra = wordService.getWordById(id);
        definition.setPalabra(palabra);
        Definicion createdDefinition = definitionService.createDefinition(definition);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinition);
    }
    @GetMapping("/inicial/{letra}")
    public ResponseEntity<List<Palabra>> getWordsByInitialLetter(@PathVariable String letra) {
        List<Palabra> palabras = wordService.getWordsByInitialLetter(letra);
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteWordById(@PathVariable Long id) throws WordNotFoundException {
        wordService.deleteWord(id);
        return HttpStatus.ACCEPTED;
    }
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> getWordsByCategoriaGramatical(@PathVariable String categoria) {
        List<Palabra> palabras = wordService.getWordsByCategoriaGramatical(categoria);
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }
}