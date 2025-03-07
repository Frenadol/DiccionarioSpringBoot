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



    public Definicion createDefinition(Definicion definition) {
        return definitionRepository.save(definition);
    }


    public void deleteDefinition(Long id) throws WordNotFoundException {
        if (definitionRepository.existsById(id)) {
            definitionRepository.deleteById(id);
        } else {
            throw new WordNotFoundException("Definition not found with id: " + id);
        }
    }
}