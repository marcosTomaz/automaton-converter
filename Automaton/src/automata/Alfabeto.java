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
	private Vector<Simbolo> simbolos;
	
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

        public int getIndice(Simbolo simbolo) throws SimboloNoExistente{
            return getSimbolo(simbolo.getNombre()).getCodigo();
        }
	
	public Simbolo getSimbolo(String nombre) throws SimboloNoExistente{
		
		for (int i=0;i < simbolos.size();i++){
			Simbolo temp = new Simbolo();
			temp.setNombre(nombre);
			
			if (simbolos.get(i).isEqual(temp)){
                            simbolos.get(i).setCodigo(i);
				return simbolos.get(i);
			}
		}
		
		throw new SimboloNoExistente("El simbolo no pertenece al Alfabeto");
	}

        public Simbolo getSimbolo(int codigo){
            return simbolos.get(codigo);
        }
	
        @Override
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
