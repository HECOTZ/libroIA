package com.hg.libroia.sistemaexperto.bases;

import java.util.ArrayList;
import java.util.List;

import com.hg.libroia.sistemaexperto.reglas.Regla;

// Clase que gestiona la base de reglas
public class BaseDeReglas {
	
	// Lista de las reglas
	protected List<Regla> reglas;

	// Constructor
	public BaseDeReglas() {
		reglas = new ArrayList<>();
	}

	public List<Regla> getReglas() {
		return reglas;
	}

	public void setReglas(List<Regla> reglas) {
		// Se copia las reglas y se añaden
		for (Regla regla : reglas) {
			Regla copia = new Regla(regla.getNombreRegla(), regla.getPremisas(), regla.getConclusion());
			this.reglas.add(copia);
		}
	}

	// Eliminar las reglas
	public void clearBase() {
		this.reglas.clear();
	}

	// Agregar una regla a la base
	public void agregarRegla(Regla regla) {
		this.reglas.add(regla);
	}

	// Eliminar una regla
	public void eliminar(Regla regla) {
		this.reglas.remove(regla);
	}
	
}