package com.frenadol.diccionariofernandospringboot.repositories;

import com.frenadol.diccionariofernandospringboot.models.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Palabra, Long> {
    @Query(
            value="SELECT * FROM Palabra AS p WHERE p.categoria_gramatical = ?1",
            nativeQuery = true
    )
    List<Palabra> findWordsByCategoriaGramatical(String categoriaGramatical);
    @Query(
            value="SELECT * FROM Palabra AS p WHERE p.termino LIKE ?1%",
            nativeQuery = true
    )
    List<Palabra> findWordsByInitialLetter(String initialLetter);
}