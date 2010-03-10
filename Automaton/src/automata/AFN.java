/**
 * 
 */
package automata;
import java.util.Vector;
import java.util.Iterator;
import excepcion.EstadoNoValidoException;

/**
 * @author Magda
 *
 */
public class AFN extends Automata {

    Vector<Vector<Estado>> mapa;

    public AFN(Estado estadoInicial,Alfabeto alfabeto,Vector<Estado> estadosFinales,Vector<Estado> estados,Vector<Transicion> funcion) throws EstadoNoValidoException {
         super(estadoInicial,alfabeto,estadosFinales,estados,funcion);
    }

    public AFD toAFD(AFN tablaAFN) {
        //sabiendo q un automata, AFn o AFD esta formado por:
        //quintupla=(estadoInicial, Alfabeto alfabeto, Vector<Integer> estadosFinales, Vector<Estado> estados)
	// este metodo recibe un AFN y regresa un AFD...

        // con tablaAFN:
        // recorro el vector de estados origen, por estado
        //// recorro el arreglo de simbolos, por simbolo
        
        ////// recorro el vector de estados destino y genero el vector de estadosAfn(para cada simbolo)
        //////// aplico cerradura(vector de estadosAfn,simbolo)--->estadoAfd

        ///////// (estadoAfd)si no existe, lo agrego al vector de estados origen y al vector de estados con la posicion en estado
        ///////// estadoDestino = buscar en el vector funcion,al estadoAfn en estadoOrigen y regresar el estadoDestino
        ///////// agrego al vector funcion(estadoAfd, simbolo,estadoDestino)
        ///////// mapeo(vector <Estado> estadoAfn,estado Afn)


        return new AFD();
    }

    private Estado cerradura(Vector<Estado> estadosAfn, Simbolo simbolo) {
        // recibe el vector, estadosAfn, de estadosDestino de la tablaAFN asociada a cada simbolo y regresera:
        // un nuevo estado del Afd
        // si hay mas de un estado Afn, regresa un nuevo estado Afd
        // sino regresa el mismo estado Afn (como estado Afd)


        if (estadoAfn.size() == 1) {
            return estadosAfn;
        }
        else {
            // codigo
        }
    }

  
    private void mapeo(Vector<Estado> estadosAfn,Estado estadoAfd) { // estadosAfn = {q1,q2,q3}; estadoAfd = {qnuevo}
                                                                     // o estadosAfn = {qn}; estadoAfd = {qn}
        //Vector<Vector<Estado>> mapa;  creo q esta mal, quiero hacer un vector de estadoAfd de vectores de estadosAfn
        // recorro con mapa el vector de estadoAfd
        //si e estadoAfd no existe se lo agrego al vector
        //sino le agrego el vector de estadosAfn
        
    //   me gustaria crear la clase Mapa...
     //  entonces tendria algo asi como:
     //      atributos:
               private Estado estadoAfd;
               private Vector<Estado> estadosAfn;
               con todas sus funcionalidades....

    }

}
