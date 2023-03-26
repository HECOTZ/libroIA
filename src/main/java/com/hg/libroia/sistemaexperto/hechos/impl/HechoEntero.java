package com.hg.libroia.sistemaexperto.hechos.impl;

import com.hg.libroia.sistemaexperto.hechos.IHecho;

// Clase para los hechos enteros (como el número de lados)
class HechoEntero implements IHecho {

    // Nombre del hecho
    protected String nombre;
    // Valor entero asociado
    protected int valor;
    // Nivel (0 para los hechos como entrada)
    protected int nivel;
    // Pregunta que hay que hacer al usuario si es necesario
    protected String pregunta = "";

    // Constructor
    public HechoEntero(String nombre , int valor, String pregunta, int nivel) {
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
    
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public String getPregunta() {
        return pregunta;
    }
    
    // Métodos toString (para la visualización)
    @Override
    public String toString() {
        return nombre + "=" + valor + " (" + nivel + ")";
    }}
