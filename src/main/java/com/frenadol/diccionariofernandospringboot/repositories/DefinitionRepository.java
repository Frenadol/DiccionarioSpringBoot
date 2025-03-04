package com.frenadol.diccionariofernandospringboot.repositories;

import com.frenadol.diccionariofernandospringboot.models.Definicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefinitionRepository extends JpaRepository<Definicion, Long> {
    @Query(
            value="SELECT * FROM Definicion AS d WHERE d.palabra_id = ?1",
            nativeQuery = true
    )
    List<Definicion> findDefinitionsByWordId(Long id);

}
