package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Familia implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String nombre;
    private boolean subFamilia;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Familia familiaPadre;

    @ElementCollection
    private List<Long> diasAlquilados;

    public Familia() {

    }

    public Familia(String nombre, boolean subFamilia) {
        this.nombre = nombre;
        this.subFamilia = subFamilia;
    }

    public Familia(String nombre, boolean subFamilia, Familia familiaPadre) {
        this.nombre = nombre;
        this.subFamilia = subFamilia;
        if (subFamilia) {
            this.familiaPadre = familiaPadre;
        }
    }

    public long getPromedio() {

        if (this.diasAlquilados.size() == 0) {

            return 0;

        }

        long suma = 0;
        for (long dias: this.diasAlquilados) {

            suma += dias;

        }
        return suma/this.diasAlquilados.size();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSubFamilia() {
        return subFamilia;
    }

    public void setSubFamilia(boolean subFamilia) {
        this.subFamilia = subFamilia;
    }

    public Familia getFamiliaPadre() {
        return familiaPadre;
    }

    public void setFamiliaPadre(Familia familiaPadre) {
        this.familiaPadre = familiaPadre;
    }

    public List<Long> getDiasAlquilados() {
        return diasAlquilados;
    }

    public void setDiasAlquilados(List<Long> diasAlquilados) {
        this.diasAlquilados = diasAlquilados;
    }
}
