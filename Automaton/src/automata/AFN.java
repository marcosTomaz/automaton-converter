/**
 * 
 */
package automata;
import excepcion.SimboloNoExistente;
import java.util.Vector;
import java.util.Iterator;
import excepcion.EstadoNoValidoException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Magda
 *
 */
public class AFN extends Automata {
    

    public AFN(Estado estadoInicial,Alfabeto alfabeto,Vector<Estado> estadosFinales,Vector<Estado> estados,Vector<Transicion> funcion) throws EstadoNoValidoException {
         super(estadoInicial,alfabeto,estadosFinales,estados,funcion);
    }

    public AFD toAFD() {
        Estado.reset();
        // crear lista de mapeo
        // crear lista de transiciones (usando lista de mapeo)
        // obtener lista de equivalencias finales e iniciales(usando lsita de mapeo)
        // crear el afd con los datos obtenidos
        // retornar el afd
        Mapa mapeo = new Mapa();
        Vector<Estado> estadosActuales = new Vector<Estado>();
        Vector<Estado> estados = new Vector<Estado>();
        Iterator<Estado> itEstados = getEstados().iterator();
        Vector<Transicion> transiciones = new Vector<Transicion>();
        Estado estadoInicialAFD = new Estado();
        Vector<Estado> estadosAceptadores = new Vector<Estado>();

        /*
         * Creacion de la lista de mapeo
         */

        while (itEstados.hasNext()){
            Vector<Estado> aux = new Vector<Estado>();
            aux.add(itEstados.next());
            mapeo.agregar(aux);
        }
        
        for (int i = 0; i < mapeo.getCantidadEquivalencias(); i++) {
            // agrego al vector temporal los estados afn que se corresponden al estado equivalente afd
            estadosActuales = mapeo.getEstadosAFN(mapeo.getEstadosAFD().get(i));
            Estado estadoOrigen = mapeo.getEstadosAFD().get(i);
            for (int j = 0; j < getAlfabeto().getCantidadSimbolos(); j++) {
                Simbolo simboloActual = getAlfabeto().getSimbolo(j);
                Estado estadoAFD = cerradura(estadosActuales, simboloActual, mapeo);

                if (!estadoAFD.getNombre().equals("default")) {

                    Estado estadoDestino = estadoAFD;
                    Transicion transicion = new Transicion();
                    transicion.setEstadoDestino(estadoDestino);
                    transicion.setEstadoOrigen(estadoOrigen);
                    transicion.setSimbolo(simboloActual);
                    transiciones.add(transicion);
               }


            }
        }

        estados = mapeo.getEstadosAFD();
  //      System.out.println(estados);
        

//         System.out.println("#############");
//        System.out.println(transiciones);
        

        Iterator<Estado> itEstadosAFD = estados.iterator();

        while (itEstadosAFD.hasNext()){
            Estado estadoAct = itEstadosAFD.next();

            if (estadoAct.isInicial()){
                estadoInicialAFD = estadoAct;
            }

            if (estadoAct.isAceptador()){
                estadosAceptadores.add(estadoAct);
            }
        }

       // estados = Automata.obtenerInalcanzables(transiciones, estadoInicialAFD);
        estados = recorrer(transiciones, getAlfabeto(), estadoInicialAFD);
//        System.out.println("#############");
//        System.out.println(estados);
   //     estadosAceptadores = Automata.obtenerInalcanzables(transiciones, estadoInicialAFD,estadosAceptadores);

        estadosAceptadores = intersect(estados, estadosAceptadores);

//        System.out.println("#############");
//        System.out.println(estadosAceptadores);

        Iterator<Transicion> itTransiciones = transiciones.iterator();
        Vector<Estado> origenes = new Vector<Estado>();
        Vector<Transicion> transicionesAlcanzables = new Vector<Transicion>();

        while (itTransiciones.hasNext()){
            origenes.add(itTransiciones.next().getEstadoOrigen());
        }

 //       origenes = Automata.obtenerInalcanzables(transiciones, estadoInicialAFD, origenes);

        origenes = intersect(estados, origenes);

        itTransiciones = transiciones.iterator();


        while (itTransiciones.hasNext()){
            Transicion tranActual = itTransiciones.next();
            for (int k = 0;k < origenes.size();++k){
                if (tranActual.getEstadoOrigen().equals(origenes.get(k))){
                    transicionesAlcanzables.add(tranActual);
                }
            }
        }

        AFD afd;
        try {
            afd = new AFD(estadoInicialAFD, getAlfabeto(), estadosAceptadores, estados, transicionesAlcanzables);
            return afd;
        } catch (EstadoNoValidoException ex) {
            Logger.getLogger(AFN.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    private Vector<Estado> intersect(Vector<Estado> uno, Vector<Estado> otro){
        Iterator<Estado> itOtro;
        Iterator<Estado> itUno = uno.iterator();
        HashSet<Estado> nuevo = new HashSet<Estado>();

        while (itUno.hasNext()){
            Estado unoActual = itUno.next();
            itOtro = otro.iterator();
            while (itOtro.hasNext()){
                Estado otroActual = itOtro.next();
                if (unoActual.equals(otroActual)){
                    nuevo.add(unoActual);
                }
            }
        }

        return new Vector<Estado>(nuevo);
    }

    private Estado cerradura(Vector<Estado> estadosAfn,Simbolo simbolo,Mapa mapeo) {
        // Para cada estado con el simbolo
        //// obtengo el estado destino par cada uno (en la tabla AFN)
        //// lo agrego a la lista de estados destino (no se debe repetir)
        // Busco si existe el estado equivalente
        // si no existe lo creo y seteo final (si corresponde)
        // devuelvo el estado equivalente
        Iterator<Estado> itEstadosAfn = estadosAfn.iterator();
        HashSet<Estado> destinos = new HashSet<Estado>();
        int indiceEstado = 0;

        while (itEstadosAfn.hasNext()){
            try {
                indiceEstado = getIndice(itEstadosAfn.next());
                Estado origen = getEstados().get(indiceEstado);
                Vector<Estado> destinosAfn = mover(origen, simbolo);
                destinos.addAll(destinosAfn);
            } catch (SimboloNoExistente ex) {
                Logger.getLogger(AFN.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EstadoNoValidoException ex) {
                Logger.getLogger(AFN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Estado afd = mapeo.agregar(new Vector<Estado>(destinos));

        return afd;

    }
}
