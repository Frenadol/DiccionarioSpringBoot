package com.frenadol.diccionariofernandospringboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "palabra")
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "termino", nullable = false)
    private String termino;

    @Size(max = 50)
    @NotNull
    @Column(name = "categoriaGramatical", nullable = false, length = 50)
    private String categoriaGramatical;
    @JsonManagedReference
    @OneToMany(mappedBy = "palabra")
    private Set<Definicion> definiciones = new LinkedHashSet<>();

    public Palabra(Long id, String termino, String categoriaGramatical, Set<Definicion> definiciones) {
        this.id = id;
        this.termino = termino;
        this.categoriaGramatical = categoriaGramatical;
        this.definiciones = definiciones;
    }

    public Palabra() {

    }

    @Override
    public String toString() {
        return "Palabra{" +
                "id=" + id +
                ", termino='" + termino + '\'' +
                ", categoriaGramatical='" + categoriaGramatical + '\'' +
                ", definicions=" + definiciones +
                '}';
    }
}