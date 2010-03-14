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
public class Mapa implements Iterable<Equivalencia> {

    private Vector<Equivalencia> equivalencias;

    public Mapa() {
        equivalencias = new Vector<Equivalencia>();
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

    public Vector<Estado> getEstadosAFN(Estado estadoAFD) {
        Iterator<Equivalencia> itEstado = equivalencias.iterator();

        while (itEstado.hasNext()) {

            Equivalencia estadoActual = itEstado.next();

            if (estadoActual.getEstadoAFD().equals(estadoAFD)) {
                return estadoActual.getEstadosAFN();
            }
        }

        return null;
    }

    public Estado agregar(Vector<Estado> estadosAFN) {
        Estado afd;
        if (estadosAFN.size() > 0) {
            afd = getEstadoAFD(estadosAFN);


            if (afd == null) {
                Estado estadoAFD = new Estado();
                Iterator<Estado> itEstados = estadosAFN.iterator();
                boolean esAceptador = false;
                boolean esInicial = false;

                while (itEstados.hasNext()) {
                    Estado estadoActual = itEstados.next();
                    esAceptador = (estadoActual.isAceptador() || esAceptador);
                    esInicial = (estadoActual.isInicial() || esInicial);

                    estadoAFD.setAceptador(esAceptador);
                    estadoAFD.setInicial(esInicial);
                }

                this.equivalencias.add(new Equivalencia(estadoAFD, estadosAFN));
                return estadoAFD;
            }
        } else {
            afd = new Estado("default");
        }



        return afd;
    }

    public int getCantidadEquivalencias() {
        return equivalencias.size();
    }

    @Override
    public Iterator<Equivalencia> iterator() {
        return equivalencias.iterator();
    }

    public String toString() {
        StringBuffer salida = new StringBuffer();
        Iterator<Equivalencia> itEquivalencias = equivalencias.iterator();

        while (itEquivalencias.hasNext()) {
            salida.append(itEquivalencias.next());
            salida.append("\n");
        }

        return salida.toString();
    }

    public Vector<Estado> getEstadosAFD() {
        Iterator<Equivalencia> itEquivalencias = equivalencias.iterator();
        Vector<Estado> salida = new Vector<Estado>();

        while (itEquivalencias.hasNext()) {
            salida.add(itEquivalencias.next().getEstadoAFD());
        }

        return salida;
    }
}

class Equivalencia {

    public Estado estadoAFD;
    public Vector<Estado> estadosAFN;

    public Equivalencia(Estado estadoAFD, Vector<Estado> estadosAFN) {
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

    public String toString() {
        StringBuffer salida = new StringBuffer();
        Iterator<Estado> itEstados = getEstadosAFN().iterator();

        salida.append(getEstadoAFD());
        salida.append(" <----> ");

        while (itEstados.hasNext()) {
            salida.append(itEstados.next());
            salida.append(", ");
        }

        return salida.toString();
    }
}
