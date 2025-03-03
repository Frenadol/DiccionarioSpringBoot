package controllers;

import exception.WordNotFoundException;
import models.Palabra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicies.WordService;

import java.util.List;

@RestController
@RequestMapping("/palabra")
public class PalabraServiceController {

    @Autowired
    WordService wordService;


    @GetMapping("/{id}")
    public Palabra getPalabraById(@PathVariable Long id) throws WordNotFoundException {
        return wordService.getWordById(id);
    }

    @GetMapping
    public ResponseEntity<List<Palabra>> getAllPalabras() {
        List<Palabra> palabras = wordService.getAllWords();
        return new ResponseEntity<List<Palabra>>(palabras, new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Palabra> createWord(@RequestBody Palabra word) {
        Palabra createdWord = wordService.createWord(word);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWord);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Palabra> updateWord(@RequestBody Palabra updatedWord) throws WordNotFoundException {
        Palabra wordUpdated = wordService.updateWord(updatedWord.getId(), updatedWord);
        return ResponseEntity.status(HttpStatus.OK).body(wordUpdated);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteWordById(@PathVariable Long id) throws WordNotFoundException {
        wordService.deleteWord(id);
        return HttpStatus.ACCEPTED;
    }
}