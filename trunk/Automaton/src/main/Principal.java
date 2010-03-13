/**
 * 
 */
package main;

import automata.AFD;
import automata.AFN;
import io.EntradaSalidaXML;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Vector;
/**
 * @author Magda
 *
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        try {
            EntradaSalidaXML ioXML = new EntradaSalidaXML();
            File xml = new File("afn.xml");
            File xsd = new File("afn.xsd");
            Vector<AFN> automatas;
            Iterator<AFN> itAFN;
            automatas = ioXML.getAutomatas(xml, xsd);
            itAFN = automatas.iterator();
            System.out.println(automatas.size());
            while (itAFN.hasNext()) {
                AFN afn = itAFN.next();
                AFD afd = afn.toAFD();
                System.out.println("-------------------");
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
	}

}
