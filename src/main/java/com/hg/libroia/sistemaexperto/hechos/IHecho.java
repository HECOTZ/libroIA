package com.hg.libroia.sistemaexperto.hechos;

// Interface para todos los hechos que se debe implementar
public interface IHecho {
    String getNombre();
    Object getValor();
    int getNivel();
    String getPregunta();
    
    void setNivel(int nivel); // Permite modificar el nivel de un hecho
}
