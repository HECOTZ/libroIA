package com.hg.libroia.sistemaexperto.hechos;

import java.util.List;

import com.hg.libroia.sistemaexperto.reglas.Regla;

public interface IHM {
    int pedirValorEntero(String pregunta); 
    boolean pedirValorBooleano(String pregunta); 
    void mostrarHechos(List<IHecho> hechos); 
    void mostrarReglas(List<Regla> reglas); 
}
