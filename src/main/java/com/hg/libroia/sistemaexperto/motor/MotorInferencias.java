package com.hg.libroia.sistemaexperto.motor;
import java.util.ArrayList;

import com.hg.libroia.sistemaexperto.bases.BaseDeHechos;
import com.hg.libroia.sistemaexperto.bases.BaseDeReglas;
import com.hg.libroia.sistemaexperto.hechos.IHM;
import com.hg.libroia.sistemaexperto.hechos.IHecho;
import com.hg.libroia.sistemaexperto.hechos.impl.HechoFactory;
import com.hg.libroia.sistemaexperto.reglas.Regla;

// Motor de inferencias del sistema experto
public class MotorInferencias {
    private BaseDeHechos bdh;
    private BaseDeReglas bdr;
    private IHM ihm;
    private int nivelMaxRegla;
    
    // Constructor
    public MotorInferencias(IHM ihm) {
        this.ihm = ihm;
        this.bdh = new BaseDeHechos();
        this.bdr = new BaseDeReglas();
    }
    
    // Solicita un valor entero al ihm
    public int pedirValorEntero(String pregunta) {
        return ihm.pedirValorEntero(pregunta);
    }
    
    // Solicita un valor booleano al ihm
    public boolean pedirValorBooleano(String pregunta) {
        return ihm.pedirValorBooleano(pregunta);
    }
    
    // Indica si una regla pasada como argumento ss aplicable. 
    // Si lo es, devuelve su nivel, si no devuelve -1
    public int esAplicable(Regla regla) {
        int nivelMax = -1;
        // Se verifica la veracidad de cada premisa
        for (IHecho hecho : regla.getPremisas()) {
            IHecho hechoEncontrado = bdh.buscar(hecho.getNombre());
            if (hechoEncontrado == null) {
                // Este hecho no existe en base de hechos
                if (hecho.getPregunta() != null) {
                    hechoEncontrado = HechoFactory.hecho(hecho, this);
                    bdh.agregarHecho(hechoEncontrado);
                }
                else {
                    return -1;
                }
            }
            
            // El hecho existe en base (antes o creado), ¿pero con el valor correcto?
            if (!hechoEncontrado.getValor().equals(hecho.getValor())) {
                return -1;
            }
            else {
                nivelMax = Math.max(nivelMax, hechoEncontrado.getNivel());
            }
        }
        return nivelMax;
    }
    
    // Devuelve la primera regla aplicable de la base que se pasa  como argumento
    // Si hay una, rellena también el atributo de la clase (nivelMaxRegla)
    // si no devuelve null
    public Regla BuscadorRegla(BaseDeReglas bdrLocale) {
        for(Regla r : bdrLocale.getReglas()) {
            int nivel = esAplicable(r);
            if (nivel != -1) {
                nivelMaxRegla = nivel;
                return r;
            }
        }
        return null;
    }
    
    // Algoritmo principal que permtite resolver un caso dado
    public void resolver() {
        // Se copian todas las reglas
        BaseDeReglas bdrLocale = new BaseDeReglas();
        bdrLocale.setReglas(bdr.getReglas());
        
        // Se vacía la base de hechos
        bdh.vaciar();
        
        // mientras existan reglas a aplicar
        Regla r = BuscadorRegla(bdrLocale);
        while(r!=null) {
            // Aplicar la regla
            IHecho nuevoHecho = r.getConclusion();
            nuevoHecho.setNivel(nivelMaxRegla + 1);
            bdh.agregarHecho(nuevoHecho);
            // Eliminar la regla de las posibles
            bdrLocale.eliminar(r);
            // Buscar la siguiente regla aplicable
            r = BuscadorRegla(bdrLocale);
        }
        
        // Visualización de los resultados
        ihm.mostrarHechos(bdh.getHechos());
    }
    
    // Agregar una regla a la base a partir de su cadena
    // En forma :
    // Nombre : IF premisas THEN conclusion
    public void agregarRegla(String str) {
        // Separación nombre:regla
        String[] nombreRegla = str.split(":");
        if (nombreRegla.length == 2) {
            String nombre = nombreRegla[0].trim();
            // Separación premisas THEN conclusión
            String regla = nombreRegla[1].trim();
            regla = regla.replaceFirst("^" + "IF", "");
            String[] premisasConclusion = regla.split("THEN");
            if (premisasConclusion.length == 2) {
                // Lectura de las premisas
                ArrayList<IHecho> premisas = new ArrayList();
                String[] premisasStr = premisasConclusion[0].split(" AND ");
                for(String cadena : premisasStr) {
                    IHecho premisa = HechoFactory.hecho(cadena.trim());
                    premisas.add(premisa);
                }
                // Lectura de la conclusión
                String conclusionStr = premisasConclusion[1].trim();
                IHecho conclusion = HechoFactory.hecho(conclusionStr);
                // Creación de la regla y adición a la base
                bdr.agregarRegla(new Regla(nombre, premisas, conclusion));
            }
        }
    }
}