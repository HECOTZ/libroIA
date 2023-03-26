package com.hg.libroia.sistemaexperto.reglas;

import java.util.ArrayList;
import java.util.StringJoiner;

import com.hg.libroia.sistemaexperto.hechos.IHecho;

// Representa una regla del sistema experto
public class Regla {
    // Lista de las premisas (parte izquierda)
	private ArrayList<IHecho> premisas;
    // Conclusión de la regla (parte derecha)
    private IHecho conclusion;
    // Nombre de la regla
    private String nombreRegla;
    
    // Constructor
    public Regla(String nombreRegla , ArrayList<IHecho> premisas, IHecho conclusion) { 
    	this.nombreRegla = nombreRegla ; 
        this.premisas = premisas; 
        this.conclusion = conclusion; 
    } 
    
    public ArrayList<IHecho> getPremisas() {
        return premisas;
    }
    public void setPremisas(ArrayList<IHecho> premisas) {
        this.premisas = premisas;
    }
    
    public IHecho getConclusion() {
        return conclusion;
    }
    public void setConclusion(IHecho conclusion) {
    	this.conclusion = conclusion;
    }
    
    public String getNombreRegla() {
        return nombreRegla;
    }
    public void setNombreRegla(String nombreRegla ) {
    	this.nombreRegla = nombreRegla ;
    }
    
    // Métodos que muestran la regla
    @Override
    public String toString() { 
        String cadena = this.nombreRegla + " : IF (";
        
        StringJoiner sj = new StringJoiner(" AND ");
        for(IHecho hecho : this.premisas) {
            sj.add(hecho.toString());
        }
        
        cadena += sj.toString() + ") THEN " + this.conclusion.toString();
        return cadena;
   }
}
