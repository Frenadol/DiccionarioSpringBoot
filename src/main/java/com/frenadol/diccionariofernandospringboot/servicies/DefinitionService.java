package com.frenadol.diccionariofernandospringboot.servicies;

import com.frenadol.diccionariofernandospringboot.exception.WordNotFoundException;
import com.frenadol.diccionariofernandospringboot.models.Definicion;
import com.frenadol.diccionariofernandospringboot.repositories.DefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefinitionService {

    @Autowired
    private DefinitionRepository definitionRepository;

    public List<Definicion> getDefinitionsByWordId(Long id) {
        return definitionRepository.findDefinitionsByWordId(id);
    }

    public List<Definicion> getAllDefinitions() {
        return definitionRepository.findAll();
    }

    public Definicion getDefinitionById(Long id) throws WordNotFoundException {
        return definitionRepository.findById(id)
                .orElseThrow(() -> new WordNotFoundException("Definition not found with id: " + id));
    }

    public Definicion createDefinition(Definicion definition) {
        return definitionRepository.save(definition);
    }

    public Definicion updateDefinition(Long id, Definicion updatedDefinition) throws WordNotFoundException {
        if (definitionRepository.existsById(id)) {
            updatedDefinition.setId(id);
            return definitionRepository.save(updatedDefinition);
        } else {
            throw new WordNotFoundException("Definition not found with id: " + id);
        }
    }

    public void deleteDefinition(Long id) throws WordNotFoundException {
        if (definitionRepository.existsById(id)) {
            definitionRepository.deleteById(id);
        } else {
            throw new WordNotFoundException("Definition not found with id: " + id);
        }
    }
}