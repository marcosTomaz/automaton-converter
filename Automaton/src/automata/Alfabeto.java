/**
 * 
 */
package automata;

import java.util.Iterator;
import java.util.Vector;
import excepcion.SimboloNoExistente;

/**
 * @author Magda
 *
 */
public class Alfabeto {
	Vector<Simbolo> simbolos;
	
	public Alfabeto(){
		simbolos = new Vector<Simbolo>();
	}
	
	public Alfabeto(Simbolo[] simbolos){
		this();
		for (int i = 0;i < simbolos.length;i++){
			agregarSimbolo(simbolos[i]);
		}
	}
	
	public Alfabeto(Vector<Simbolo> simbolos){
		this(simbolos.toArray(new Simbolo[0]));
	}
	
	public void agregarSimbolo(Simbolo simb){
		try {
			getSimbolo(simb.getNombre());
		}
		catch (SimboloNoExistente ex){
			simbolos.add(simb);
			simb.setCodigo(simbolos.indexOf(simb));
		}
	}
	
	public Simbolo getSimbolo(String nombre) throws SimboloNoExistente{
		
		for (int i=0;i < simbolos.size();i++){
			Simbolo temp = new Simbolo();
			temp.setNombre(nombre);
			
			if (simbolos.get(i).isEqual(temp)){
				return simbolos.get(i);
			}
		}
		
		throw new SimboloNoExistente("El simbolo no pertenece al Alfabeto");
	}
	
	public String toString(){
		Iterator<Simbolo> itSimb = simbolos.iterator();
		StringBuffer salida = new StringBuffer();
		
		while (itSimb.hasNext()){
			salida.append(",");
			salida.append(itSimb.next());
		}
		salida.deleteCharAt(0); // elimina la primera coma
		
		return salida.toString();
	}
	
	public int getCantidadSimbolos(){
		return simbolos.size();
	}
}
