package com.frenadol.diccionariofernandospringboot.servicies;

import com.frenadol.diccionariofernandospringboot.exception.WordNotFoundException;
import com.frenadol.diccionariofernandospringboot.models.Definicion;
import com.frenadol.diccionariofernandospringboot.models.Palabra;
import com.frenadol.diccionariofernandospringboot.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private DefinitionService definitionService;

    public List<Palabra> getAllWords() {
        return wordRepository.findAll();
    }

    public Palabra getWordById(Long id) throws WordNotFoundException {
        Optional<Palabra> palabra = wordRepository.findById(id);
        if (palabra.isPresent()) {
            return palabra.get();
        } else {
            throw new WordNotFoundException("Word not found with id: " + id);
        }
    }

    public Palabra createWord(Palabra word) {
        return wordRepository.save(word);
    }

    public Palabra createWordWithDefinitions(Palabra word) {
        Palabra createdWord = wordRepository.save(word);
        for (Definicion definicion : word.getDefiniciones()) {
            definicion.setPalabra(createdWord);
            definitionService.createDefinition(definicion);
        }
        return createdWord;
    }

    public Palabra updateWord(Long id, Palabra updatedWord) throws WordNotFoundException {
        Optional<Palabra> palabra = wordRepository.findById(id);
        if (palabra.isPresent()) {
            Palabra word = palabra.get();
            word.setTermino(updatedWord.getTermino());
            word.setCategoriaGramatical(updatedWord.getCategoriaGramatical());
            return wordRepository.save(word);
        } else {
            throw new WordNotFoundException("Word not found with id: " + id);
        }
    }
    public List<Palabra> getWordsByCategoriaGramatical(String categoriaGramatical) {
        return wordRepository.findWordsByCategoriaGramatical(categoriaGramatical);
    }
    public List<Palabra> getWordsByInitialLetter(String initialLetter) {
        return wordRepository.findWordsByInitialLetter(initialLetter);
    }
    public Palabra getWordWithDefinitions(Long id) throws WordNotFoundException {
        Palabra palabra = getWordById(id);
        List<Definicion> definiciones = definitionService.getDefinitionsByWordId(id);
        palabra.setDefiniciones(new LinkedHashSet<>(definiciones));
        return palabra;
    }

    public void deleteWord(Long id) throws WordNotFoundException {
        if (wordRepository.existsById(id)) {
            wordRepository.deleteById(id);
        } else {
            throw new WordNotFoundException("Word not found with id: " + id);
        }
    }
}