/**
 * 
 */
package automata;

/**
 * @author Magda
 *
 */
public class AFD extends AFN {

	/**
	 * En caso de invocar este metodo no realizara la
	 * conversion debido a que ya se trata de un AFD
	 */
	public AFD toAFD(){
		return this;
	}
}
