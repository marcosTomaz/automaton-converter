/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io;

import automata.AFD;
import automata.AFN;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author magda
 */
public class EntradaSalidaPlano extends EntradaSalida{

    @Override
    public Vector<AFN> getAutomatas(File xml, File xsd) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveAutomatas(File nombre, Vector<AFD> automatas) throws IOException {
        FileWriter writer = new FileWriter(nombre);
        Iterator<AFD> itAutomatas = automatas.iterator();
        int contador = 0;
        StringBuffer salida = new StringBuffer();

        while (itAutomatas.hasNext()){
            salida.append("AUTOMATA " + ++contador);
            salida.append("\n");
            salida.append(itAutomatas.next().toString());
            salida.append("\n");
            salida.append("----------");
            salida.append("\n");
        }

        writer.write(salida.toString());
        writer.close();
    }

}
