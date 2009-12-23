/**
 * conjunto de estados
 * estado inicial
 * estados finales
 * alfabeto
 * funcion de transicion
 */
package automata;

import java.util.Vector;

/**
 * @author Magda
 *
 */
public abstract class Automata {
	private Estado estadoInicial;
	private Alfabeto alfabeto;
	private Vector<Estado> estadosFinales;
	// vector de arreglos de vectores de estado (tabla de 3 dimensiones)
	private Vector<Vector<Estado>[]> tabla;
	
	public Automata(){
		
	}
	
	public void setAlfabeto(Alfabeto alfabeto){
		this.alfabeto = alfabeto;
	}
	
	//TODO continuar getters y setters 
	
	public void mover(Estado origen,Estado destino, Simbolo simbolo){
		
	}

}
