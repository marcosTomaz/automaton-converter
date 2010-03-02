/**
 * 
 */
package io;

import java.io.File;

import automata.AFD;
import automata.AFN;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Magda
 *
 */
public abstract class EntradaSalida {

	public EntradaSalida(){
		
	}
	
	abstract public Vector<AFN> getAutomatas(File xml, File xsd) throws FileNotFoundException;
	
	abstract public void saveAutomatas(File xml, Vector<AFD> automatas) throws IOException;
}
