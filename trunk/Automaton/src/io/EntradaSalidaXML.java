/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io;

import automata.AFD;
import automata.AFN;
import ioxml.Afn;
import java.io.File;
import ioxml.ObjectFactory;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author magda
 */
public class EntradaSalidaXML extends EntradaSalida{

    private Afn getAfn(File xml,File xsd) throws FileNotFoundException{
        Afn xmlObject = null;
        try {
            ObjectFactory of = new ObjectFactory();
            Afn afn = of.createAfn();
            JAXBContext context = JAXBContext.newInstance(afn.getClass());
            Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xsd);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setSchema(schema);
            xmlObject = afn.getClass().cast(unmarshaller.unmarshal(xml));
            

        } catch (JAXBException ex) {
            Logger.getLogger(EntradaSalidaXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SAXException ex) {
            Logger.getLogger(EntradaSalidaXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            return xmlObject;
        }
    }

    private AFN getAFN(Afn.Automata automata){
        
    }

    @Override
    public Vector<AFN> getAutomatas(File xml,File xsd) throws FileNotFoundException{

        // se leera el archivo xml con todos los automatas
        Afn rawAfn = getAfn(xml, xsd);
        Iterator<Afn.Automata> itAutomata = rawAfn.getAutomata().iterator();
        Vector<AFN> salida = new Vector<AFN>();

        while (itAutomata.hasNext()){
            salida.add(getAFN(itAutomata.next()));
        }

        return salida;
    }

    @Override
    public void saveAutomatas(File xml, Vector<AFD> automatas) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
