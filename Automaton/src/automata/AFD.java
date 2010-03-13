/**
 * 
 */
package automata;
import java.util.Vector;
import excepcion.EstadoNoValidoException;
/**
 * @author Magda
 *
 */
public class AFD extends AFN {

	/**
	 * En caso de invocar este metodo no realizara la
	 * conversion debido a que ya se trata de un AFD
	 */


        public AFD (Estado estadoInicial,Alfabeto alfabeto,Vector<Estado> estadosFinales,Vector<Estado> estados,Vector<Transicion> funcion) throws EstadoNoValidoException {
            super(estadoInicial,alfabeto,estadosFinales,estados,funcion);
        }

       
	public AFD toAFD(){
		return this;
	}
}
