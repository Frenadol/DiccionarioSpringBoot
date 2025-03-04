package com.frenadol.diccionariofernandospringboot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "definicion")
public class Definicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Lob
    @Column(name = "ejemplo", nullable = false)
    private String ejemplo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "palabra_id", nullable = false)
    @JsonBackReference
    private Palabra palabra;

    public Definicion(Long id, Palabra palabra, String ejemplo, String descripcion) {
        this.id = id;
        this.palabra = palabra;
        this.ejemplo = ejemplo;
        this.descripcion = descripcion;
    }

    public Definicion() {

    }

    @Override
    public String toString() {
        return "Definicion{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", ejemplo='" + ejemplo + '\'' +
                ", palabra=" + palabra +
                '}';
    }
}