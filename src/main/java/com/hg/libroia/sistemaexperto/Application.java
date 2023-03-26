package com.hg.libroia.sistemaexperto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hg.libroia.sistemaexperto.hechos.IHM;
import com.hg.libroia.sistemaexperto.hechos.IHecho;
import com.hg.libroia.sistemaexperto.motor.MotorInferencias;
import com.hg.libroia.sistemaexperto.reglas.Regla;

public class Application implements IHM {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		Application app = new Application();
		app.run();
	}

	// Funcionamiento del programa, con el ejemplo de los polígonos
	public void run() {
		// Creación del motor
		logger.info("** Creación del motor... **");
		MotorInferencias motorInferencia = new MotorInferencias(this);

		// Agregar las reglas
		logger.info("** Agregar las reglas **");
		motorInferencia.agregarRegla("R1 : IF (Orden=3(¿Cuál es el orden?)) THEN  Triángulo");
		motorInferencia.agregarRegla(
				"R2 : IF (Triángulo AND Ángulo Recto(¿La figura tiene al menos un ángulo recto?)) THEN Triángulo Rectángulo");
		motorInferencia.agregarRegla(
				"R3 : IF (Triángulo AND Lados Iguales=2(¿Cuántos lados iguales tiene la figura?)) THEN Triángulo Isósceles");
		motorInferencia.agregarRegla(
				"R4 : IF (Triángulo rectángulo AND Triángulo Isósceles) THEN Triángulo Rectángulo Isósceles");
		motorInferencia.agregarRegla(
				"R5 : IF (Triángulo AND Lados Iguales=3(¿Cuántos lados iguales tiene la figura?)) THEN Triángulo Equilátero");
		motorInferencia.agregarRegla("R6 : IF (Orden=4(¿Cuál es el orden?)) THEN Cuadrilátero");
		motorInferencia.agregarRegla(
				"R7 : IF (Cuadrilátero AND Lados Paralelos=2(¿Cuántos lados paralelos entre sí - 0, 2 o 4?)) THEN Trapecio");
		motorInferencia.agregarRegla(
				"R8 : IF (Cuadrilátero AND Lados Paralelos=4(¿Cuántos lados paralelos entre sí - 0, 2 o 4?)) THEN Paralelogramo");
		motorInferencia.agregarRegla(
				"R9 : IF (Paralelogramo AND Ángulo Recto(¿La figura tiene al menos un ángulo recto?)) THEN Rectángulo");
		motorInferencia.agregarRegla(
				"R10 : IF (Paralelogramo AND Lados Iguales=4(¿Cuántos lados iguales tiene la figura?)) THEN Rombo");
		motorInferencia.agregarRegla("R11 : IF (Rectángulo AND Rombo THEN Cuadrado");

		// Resolución
		int counter = 0;
		while (counter < 3) {
			logger.info("** Resolución **");
			motorInferencia.resolver();
			counter++;
		}
	}

	// Pide una valor entero al usuario, sin verificaciones (0 en caso de problema)
	public int pedirValorEntero(String pregunta) {
		logger.info(pregunta);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return Integer.decode(br.readLine());
		} catch (Exception e) {
			return 0;
		}
	}

	// Solicita un valor booleano, con sí (verdadero) o no.
	// Se ignorarn los errores (devuelve falso)
	public boolean pedirValorBooleano(String pregunta) {
		try {
			logger.info("{} (y, n)", pregunta);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String res = br.readLine();
			return (res.equalsIgnoreCase("y"));
		} catch (IOException e) {
			return false;
		}
	}

	// Muestra la lista de hechos de nivel >0 y por orden decreciente de nivel
	public void mostrarHechos(List<IHecho> hechos) {
		StringBuilder res = new StringBuilder("Solución(es) encontrada(s) : \n");
		Collections.sort(hechos,
				(IHecho hecho1, IHecho hecho2) -> Integer.compare(hecho2.getNivel(), hecho1.getNivel()));
		for (IHecho hecho : hechos) {
			if (hecho.getNivel() != 0) {
				res.append(hecho.toString()).append("\n");
			}
		}

		logger.info("{}", res);
	}

	// Muestra las reglas contenidas en la base
	public void mostrarReglas(List<Regla> reglas) {
		StringBuilder res = new StringBuilder("");
		for (Regla regla : reglas) {
			res.append(regla.toString()).append("\n");
		}
		logger.info("{}", res);
	}
}