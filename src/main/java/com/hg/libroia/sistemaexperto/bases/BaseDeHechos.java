package com.hg.libroia.sistemaexperto.bases;

import java.util.ArrayList;
import java.util.List;

import com.hg.libroia.sistemaexperto.hechos.IHecho;

// Clase que gestiona la base de hechos
public class BaseDeHechos {

	// Lista de los hechos
	protected List<IHecho> hechos;

	// Constructor
	public BaseDeHechos() {
		hechos = new ArrayList<>();
	}

	public List<IHecho> getHechos() {
		return hechos;
	}

	// Vaciar la base
	public void vaciar() {
		hechos.clear();
	}

	// Agregar un hecho
	public void agregarHecho(IHecho hecho) {
		hechos.add(hecho);
	}

	// Buscar un hecho a partir de su nombre, null si no existe
	public IHecho buscar(String nombre) {
		for (IHecho hecho : hechos) {
			if (hecho.getNombre().equals(nombre)) {
				return hecho;
			}
		}
		return null;
	}

	// Busca el valor de un hecho, null si el hecho no existe
	public Object recuperarValorHecho(String nombre) {
		for (IHecho hecho : hechos) {
			if (hecho.getNombre().equals(nombre)) {
				return hecho.getValor();
			}
		}
		return null;
	}
}
