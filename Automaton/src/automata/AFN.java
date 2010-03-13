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
        // crear lista de mapeo
        // crear lista de transiciones (usando lista de mapeo)
        // obtener lista de equivalencias finales e iniciales(usando lsita de mapeo)
        // crear el afd con los datos obtenidos
        // retornar el afd
        Mapa mapeo = new Mapa();

        return null;
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

        Estado afd = new Estado();
        // setear valores a afd
        afd = mapeo.agregar(afd, new Vector<Estado>(destinos));

        return afd;

    }
}
