/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automata;

import java.util.Vector;
import java.util.Iterator;

/**
 *
 * @author magda
 */
public class Mapa {
   private Vector<Equivalencia> equivalencias;

    public Mapa() {
    }

    public Estado getEstadoAFD(Vector<Estado> estadosAFN) {

        Iterator<Equivalencia> itEstado = equivalencias.iterator();

        while (itEstado.hasNext()) {

            Equivalencia estadoActual = itEstado.next();

            if (estadoActual.getEstadosAFN().equals(estadosAFN)) {
                return estadoActual.estadoAFD;
            }
        }

        return null;
    }

    public Estado agregar(Estado estadoAFD, Vector<Estado> estadosAFN) {

        Estado afd = getEstadoAFD(estadosAFN);


        if (afd == null) {
            this.equivalencias.add(new Equivalencia(estadoAFD, estadosAFN));
            return estadoAFD;
        }

        return afd;
    }
}


    class Equivalencia {
    public Estado estadoAFD;
    public Vector<Estado> estadosAFN;

    public Equivalencia(Estado estadoAFD,Vector<Estado> estadosAFN){
           setEstadoAFD(estadoAFD);
           setEstadosAFN(estadosAFN);
    }

    public Estado getEstadoAFD() {
        return estadoAFD;
    }

    public Vector<Estado> getEstadosAFN() {
        return estadosAFN;
    }

    public void setEstadoAFD(Estado estadoAFD) {
        this.estadoAFD = estadoAFD;
    }

    public void setEstadosAFN(Vector<Estado> estadosAFN) {
        this.estadosAFN = estadosAFN;
    }

    }


