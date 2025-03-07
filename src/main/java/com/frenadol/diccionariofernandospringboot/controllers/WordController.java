package com.frenadol.diccionariofernandospringboot.controllers;

import com.frenadol.diccionariofernandospringboot.exception.WordNotFoundException;
import com.frenadol.diccionariofernandospringboot.models.Definicion;
import com.frenadol.diccionariofernandospringboot.models.Palabra;
import com.frenadol.diccionariofernandospringboot.servicies.DefinitionService;
import com.frenadol.diccionariofernandospringboot.servicies.WordService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Obtener palabra por ID")
    @GetMapping("/{id}")
    public Palabra getPalabraById(@PathVariable Long id) throws WordNotFoundException {
        return wordService.getWordById(id);
    }

    @Operation(summary = "Obtener todas las palabras sin definiciones")
    @GetMapping
    public ResponseEntity<List<Palabra>> getAllPalabras() {
        List<Palabra> palabras = wordService.getAllWords();
        for (Palabra palabra : palabras) {
            palabra.setDefiniciones(new LinkedHashSet<>());
        }
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las palabras con definiciones")
    @GetMapping("/definiciones")
    public ResponseEntity<List<Palabra>> getAllPalabrasWithDefiniciones() {
        List<Palabra> palabras = wordService.getAllWords();
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Crear una nueva palabra")
    @PostMapping
    public ResponseEntity<Palabra> createWord(@RequestBody Palabra word) {
        Palabra createdWord = wordService.createWord(word);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWord);
    }

    @Operation(summary = "Crear una nueva palabra con definiciones")
    @PostMapping("/condefiniciones")
    public ResponseEntity<Palabra> createWordWithDefinitions(@RequestBody Palabra word) {
        Palabra createdWord = wordService.createWordWithDefinitions(word);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWord);
    }

    @Operation(summary = "Actualizar una palabra existente")
    @PutMapping("/{id}")
    public ResponseEntity<Palabra> updateWord(@PathVariable Long id, @RequestBody Palabra updatedWord) throws WordNotFoundException {
        Palabra wordUpdated = wordService.updateWord(id, updatedWord);
        return ResponseEntity.status(HttpStatus.OK).body(wordUpdated);
    }

    @Operation(summary = "Obtener una palabra con sus definiciones mediante su ID")
    @GetMapping("/{id}/condefiniciones")
    public ResponseEntity<Palabra> getWordWithDefinitions(@PathVariable Long id) throws WordNotFoundException {
        Palabra palabra = wordService.getWordWithDefinitions(id);
        return ResponseEntity.status(HttpStatus.OK).body(palabra);
    }

    @Operation(summary = "Obtener palabras por letra inicial")
    @GetMapping("/inicial/{letra}")
    public ResponseEntity<List<Palabra>> getWordsByInitialLetter(@PathVariable String letra) {
        List<Palabra> palabras = wordService.getWordsByInitialLetter(letra);
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Borrar una palabra por su ID")
    @DeleteMapping("/{id}")
    public HttpStatus deleteWordById(@PathVariable Long id) throws WordNotFoundException {
        wordService.deleteWord(id);
        return HttpStatus.ACCEPTED;
    }

    @Operation(summary = "Obtener palabras por categoria gramatical")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> getWordsByCategoriaGramatical(@PathVariable String categoria) {
        List<Palabra> palabras = wordService.getWordsByCategoriaGramatical(categoria);
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

}