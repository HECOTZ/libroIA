package com.hg.libroia.sistemaexperto.hechos;

import java.util.ArrayList;

import com.hg.libroia.sistemaexperto.reglas.Regla;

public interface IHM {
    int PedirValorEntero(String pregunta); 
    boolean PedirValorBooleano(String pregunta); 
    void MostrarHechos(ArrayList<IHecho> hechos); 
    void MostrarReglas(ArrayList<Regla> reglas); 
}
