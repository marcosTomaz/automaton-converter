/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automata;

/**
 *
 * @author magda
 */
public class Transicion {  
        private Estado origen;
        private Simbolo simbolo;
        private Estado destino;

	public Transicion(){

	}

	public void setEstadoOrigen(Estado estadoOrigen ){
		this.origen = estadoOrigen;
	}

	public Estado getEstadoOrigen(){
		return origen;
	}

	public void setSimbolo(Simbolo simbolo ){
		this.simbolo = simbolo;
	}

	public Simbolo getSimbolo(){
		return simbolo;
	}

        public void setEstadoDestino(Estado estadoDestino ){
		this.destino = estadoDestino;
	}

	public Estado getEstadoDestino(){
		return destino;
	}

	/**
	 * retorna la informacion deseada
	 */
        @Override
	public String toString(){

            StringBuffer salida = new StringBuffer();

            salida.append(getEstadoOrigen());
            salida.append("--");
            salida.append(getSimbolo());
            salida.append("--->");
            salida.append(getEstadoDestino());

            return salida.toString();
            
	}

}
