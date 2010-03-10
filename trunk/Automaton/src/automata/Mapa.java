/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automata;

import java.util.Vector;

/**
 *
 * @author magda
 */
public class Mapa {
    private Vector<Equivalencia> equivalencias;

    
}

class Equivalencia {
    private Estado estadoAFD;
    private Vector<Estado> estadosAFN;

    public Equivalencia(){

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
