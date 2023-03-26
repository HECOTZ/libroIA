package com.hg.libroia.sistemaexperto.hechos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hg.libroia.sistemaexperto.hechos.IHecho;
import com.hg.libroia.sistemaexperto.motor.MotorInferencias;

// Clase que permite crear los hechos, independientemente de su tipo
public class HechoFactory {

	private static Logger logger = LoggerFactory.getLogger(HechoFactory.class);
	
    // Crea un nuevo hecho rellenando el valor dado por el usuario
    public static IHecho hecho(IHecho iHecho, MotorInferencias motorInferencias) {
        try {
            IHecho nuevoHecho;
            Class<? extends IHecho> clase = iHecho.getClass();
            if (clase.equals(Class.forName("com.hg.libroia.sistemaexperto.hechos.impl.HechoEntero"))) {
                nuevoHecho = crearHechoEntero(iHecho, motorInferencias);
            }
            else {
                nuevoHecho = crearHechoBooleen(iHecho, motorInferencias);
            }
            return nuevoHecho;
        } catch (ClassNotFoundException ex) {
        	logger.error("Error al crear nuevo Hecho", ex);
            return null;
        }
    }
    
    // Crea un hecho entero
    static IHecho crearHechoEntero(IHecho iHecho, MotorInferencias motorInferencias) {
        int valor = motorInferencias.pedirValorEntero(iHecho.getPregunta());
        return new HechoEntero(iHecho.getNombre(), valor, null, 0);
    }

    // Crea un hecho booleano
    static IHecho crearHechoBooleen(IHecho iHecho, MotorInferencias motorInferencias) {    
        boolean valorB = motorInferencias.pedirValorBooleano(iHecho.getPregunta());
        return new HechoBooleen(iHecho.getNombre(), valorB, null, 0);
    }

    // Crea un nuevo hecho a partir de su cadena
    public static IHecho hecho(String hechoStr) {
        hechoStr = hechoStr.trim();
        if (hechoStr.contains("=")) {
            // Existe el símbolo "=", por lo que es un hecho entero
            hechoStr = hechoStr.replaceFirst("^" + "\\(", "");
            String[] nombreValorPregunta = hechoStr.split("[=()]");
            if (nombreValorPregunta.length >= 2) {
                // Tenemos un hecho correcto Nombre=Valor[(pregunta)]
                String pregunta = null;
                if (nombreValorPregunta.length == 3) {
                    pregunta = nombreValorPregunta[2].trim();
                }
                return new HechoEntero(nombreValorPregunta[0].trim(), Integer.parseInt(nombreValorPregunta[1].trim()), pregunta, 0);
            }
        } else {
            // Es un hecho booleano nombre[(pregunta)] o !nombre[(pregunta)]
            boolean valor = true;
            if (hechoStr.startsWith("!")) {
                valor = false;
                hechoStr = hechoStr.substring(1).trim();
            }
            // Split, después de aber eliminado el primer delimitador si es necesario : '('
            hechoStr = hechoStr.replaceFirst("^" + "\\(", "");
            String[] nombrePregunta = hechoStr.split("[()]");
            String pregunta = null;
            if (nombrePregunta.length == 2) {
                pregunta = nombrePregunta[1].trim();
            }
            return new HechoBooleen(nombrePregunta[0].trim(), valor, pregunta, 0);
        }
        // Si llegamos aquí, la sintaxis es incorrecta
        return null;
    }
}
