/**
 * 
 */
package main;

import automata.AFD;
import automata.AFN;
import io.EntradaSalidaPlano;
import io.EntradaSalidaXML;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            int contador = 0;
            Vector<AFD> afds = new Vector<AFD>();

            while (itAFN.hasNext()) {
                AFN afn = itAFN.next();
                AFD afd = afn.toAFD();
                afds.add(afd);

                System.out.println("AUTOMATA " + ++contador);
                System.out.println("AFN");
                System.out.println();
                System.out.println(afn.toString());
                System.out.println("AFD");
                System.out.println();
                System.out.println(afd.toString());
                System.out.println("-------------------");
            }

            ioXML.saveAutomatas(new File("afd.xml"), afds);

            EntradaSalidaPlano ioPlano = new EntradaSalidaPlano();
            ioPlano.saveAutomatas(new File("afd.dat"), afds);
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}
