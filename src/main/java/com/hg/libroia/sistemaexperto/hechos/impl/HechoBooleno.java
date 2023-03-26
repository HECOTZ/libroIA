package com.hg.libroia.sistemaexperto.hechos.impl;

import com.hg.libroia.sistemaexperto.hechos.IHecho;

// Clase para los hechos booleanos (como el hecho de si es o no un triangulo)
class HechoBooleen implements IHecho {

    // Nombre del hecho
    private String nombre;
    // Valor booleano del hecho
    private boolean valor;
    // Nivel (0 para los hechos como entrada)
    private int nivel;
    // Pregunta que se debe hacer al usuario si es necesario
    private String pregunta;

    // Constructor
    public HechoBooleen(String nombre , boolean valor, String pregunta, int nivel) {
        this.nombre = nombre ;
        this.valor = valor;
        this.pregunta = pregunta;
        this.nivel = nivel;
    }
    
    public String getNombre() {
        return nombre;
    }

    public Object getValor() {
        return valor;
    }

    public int getNivel() {
        return nivel;
    }
    public void setNivel(int n) {
        nivel = n;
    }
    
    public String getPregunta() {
        return pregunta;
    }

    // Métodos toString (para la visualización)
    // de la forma Hecho(nivel) o !Hecho(nivel)
    @Override
    public String toString() { 
        String res = ""; 
        if (!this.valor) { 
            res += "!"; 
        } 
        res += this.nombre + " (" + nivel + ")"; 
        return res; 
    } }
