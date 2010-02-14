/**
 * conjunto de estados
 * estado inicial
 * estados finales
 * alfabeto
 * funcion de transicion
 */
package automata;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author Magda
 *
 */
public abstract class Automata {
	private int estadoInicial;
	private Alfabeto alfabeto;
	private Vector<Integer> estadosFinales;
        private Vector<Estado> estados;
	// vector de arreglos de vectores de estado (tabla de 3 dimensiones)
        //
	private Vector<Vector<Estado>[]> tabla;
	
	public Automata(){
		
	}
	
	public void setAlfabeto(Alfabeto alfabeto){
		this.alfabeto = alfabeto;
	}
	
	//TODO continuar getters y setters 
	
	public void mover(Estado origen,Estado destino, Simbolo simbolo){
		
	}

        private int getIndice(Estado estado) throws EstadoNoValidoException{
            // buscar indice del estado!!!!!!!!!!!!!!!!!!!!!

            throw new EstadoNoValidoException();
        }

        public void setEstadoInicial(Estado estado) throws EstadoNoValidoException{
            int indice = getIndice(estado);

            estadoInicial = indice;
        }

        public void setEstadosFinales(Vector<Estado> estados) throws EstadoNoValidoException{
            Iterator<Estado> itEstados = estados.iterator();
            Vector<Integer> aux = new Vector<Integer>();

            while (itEstados.hasNext()){
                aux.add(new Integer(getIndice(itEstados.next())));
            }

            estadosFinales.addAll(aux);
        }

        public void setEstados(Vector<Estado> estados){
            this.estados = estados;
        }

        public void setTransicion(Transicion transicion){
            // agrego la transicion a la tabla
        }

    @Override
        public String toString(){

        }

}
