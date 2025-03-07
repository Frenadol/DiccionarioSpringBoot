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
@RequestMapping("/definicion")
public class DefinitionController {

    @Autowired
    DefinitionService definitionService;
    @Autowired
    WordService wordService;


    @Operation(summary = "Obtener todas las definiciones de una palabra mediante su ID")
    @GetMapping("/palabra/{id}/definiciones")
    public ResponseEntity<Palabra> getDefinicionesByWordId(@PathVariable Long id) throws WordNotFoundException {
        Palabra palabra = wordService.getWordById(id);
        List<Definicion> definiciones = definitionService.getDefinitionsByWordId(id);
        palabra.setDefiniciones(new LinkedHashSet<>(definiciones));
        return new ResponseEntity<>(palabra, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Crear una nueva definición para una palabra")
    @PostMapping("/palabra/{id}/definiciones")
    public ResponseEntity<Definicion> createNewDefinition(@PathVariable long id, @RequestBody Definicion definition) throws WordNotFoundException {
        Palabra palabra = wordService.getWordById(id);
        definition.setPalabra(palabra);
        Definicion createdDefinition = definitionService.createDefinition(definition);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinition);
        }


    @Operation(summary = "Eliminar una definición de una palabra por su ID")
    @DeleteMapping("/{id}")
    public HttpStatus deleteDefinitionById(@PathVariable Long id) throws WordNotFoundException {
        definitionService.deleteDefinition(id);
        return HttpStatus.ACCEPTED;
    }
}
