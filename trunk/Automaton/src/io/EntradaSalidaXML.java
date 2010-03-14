/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io;

import automata.AFD;
import automata.AFN;
import automata.Alfabeto;
import automata.Estado;
import automata.Simbolo;
import automata.Transicion;
import excepcion.EstadoNoValidoException;
import ioxml.Afn;
import java.io.File;
import ioxml.ObjectFactory;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author magda
 */
public class EntradaSalidaXML extends EntradaSalida{

    private Afn getAfn(Vector<AFD> automatas){
       // throw new UnsupportedOperationException();

        Iterator<AFD> itAFD = automatas.iterator();
        Afn afnOut = new Afn();

        while (itAFD.hasNext()){
            Afn.Automata.Estados estadosAfn;
            Afn.Automata.Alfabeto alfabetoAfn = new Afn.Automata.Alfabeto();
            Afn.Automata.Funcion funcionAfn = new Afn.Automata.Funcion();
            AFD afdActual = itAFD.next();
            Iterator<Estado> itEstados = afdActual.getEstados().iterator();
            estadosAfn = new Afn.Automata.Estados();
            Afn.Automata automataActual = new Afn.Automata();

            while (itEstados.hasNext()){
                Estado estadoActual = itEstados.next();
                Afn.Automata.Estados.Estado estadoAfnActual = new Afn.Automata.Estados.Estado();
                estadoAfnActual.setNombre(estadoActual.getNombre());
                estadoAfnActual.setInicial(estadoActual.isInicial());
                estadoAfnActual.setTipo((estadoActual.isAceptador()) ? "aceptador" : "interno");

                estadosAfn.getEstado().add(estadoAfnActual);
            }

            Alfabeto alfabeto = afdActual.getAlfabeto();
            for (int i = 0;i < alfabeto.getCantidadSimbolos();i++){
                alfabetoAfn.getSimbolo().add(alfabeto.getSimbolo(i).getNombre());
            }

            Iterator<Transicion> itTransiciones = afdActual.getFuncion().iterator();

            while (itTransiciones.hasNext()){
                Transicion transicionActual = itTransiciones.next();
                Afn.Automata.Funcion.Transicion.Estado estadoOrigen;
                Afn.Automata.Funcion.Transicion.Estado estadoDestino;
                String simbolo;
                Afn.Automata.Funcion.Transicion transicionAfnActual;

                estadoOrigen = new Afn.Automata.Funcion.Transicion.Estado();
                estadoDestino = new Afn.Automata.Funcion.Transicion.Estado();
                transicionAfnActual = new Afn.Automata.Funcion.Transicion();

                estadoOrigen.setNombre(transicionActual.getEstadoOrigen().getNombre());
                estadoDestino.setNombre(transicionActual.getEstadoDestino().getNombre());
                simbolo = transicionActual.getSimbolo().getNombre();

                estadoOrigen.setRol("origen");
                estadoDestino.setRol("destino");

                transicionAfnActual.getEstado().add(estadoOrigen);
                transicionAfnActual.getEstado().add(estadoDestino);
                transicionAfnActual.setSimbolo(simbolo);
                
                funcionAfn.getTransicion().add(transicionAfnActual);
            }

            automataActual.setAlfabeto(alfabetoAfn);
            automataActual.setEstados(estadosAfn);
            automataActual.setFuncion(funcionAfn);

//            automataActual.getAlfabeto().getSimbolo().addAll(alfabetoAfn.getSimbolo());
//            automataActual.getEstados().getEstado().addAll(estadosAfn);
//            automataActual.getFuncion().getTransicion().addAll(funcionAfn.getTransicion());

            afnOut.getAutomata().add(automataActual);

        }

        return afnOut;
    }

    public Afn getAfn(File xml,File xsd) throws FileNotFoundException{
        Afn xmlObject = null;
        try {
            ObjectFactory of = new ObjectFactory();
            Afn afn = of.createAfn();
            JAXBContext context = JAXBContext.newInstance(afn.getClass());
            Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xsd);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setSchema(schema);
            xmlObject = afn.getClass().cast(unmarshaller.unmarshal(new FileReader(xml)));
            

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

    private AFN getAFN(Afn.Automata automata) {

        try {

            Alfabeto alfabeto = new Alfabeto();
            Vector<Estado> estados = new Vector<Estado>();
            Vector<Transicion> transiciones = new Vector<Transicion>();
            Estado estadoInicial = new Estado();
            Vector<Estado> estadosFinales = new Vector<Estado>();
            AFN afnOut;

            Afn.Automata afnActual = automata;
            Iterator<String> itSimbolos = afnActual.getAlfabeto().getSimbolo().iterator();
            Iterator<Afn.Automata.Estados.Estado> itEstados = afnActual.getEstados().getEstado().iterator();
            Iterator<Afn.Automata.Funcion.Transicion> itTransiciones = afnActual.getFuncion().getTransicion().iterator();
            while (itSimbolos.hasNext()) {
                alfabeto.agregarSimbolo(new Simbolo(itSimbolos.next()));
            }
            while (itEstados.hasNext()) {
                Afn.Automata.Estados.Estado estadoActual = itEstados.next();
                boolean esAceptador = (estadoActual.getTipo().equals("aceptador"));
                Estado estado = new Estado(0, estadoActual.getNombre(), estadoActual.isInicial(), esAceptador);
                estados.add(estado);
            }
            while (itTransiciones.hasNext()) {
                Afn.Automata.Funcion.Transicion transicionActual = itTransiciones.next();
                Estado estadoOrigen;
                Estado estadoDestino;
                Simbolo simbolo;
                Transicion transicion = new Transicion();
                int origen, destino;

                if (transicionActual.getEstado().get(0).getRol().equals("origen")) {
                    origen = 0;
                    destino = 1;
                } else {
                    origen = 1;
                    destino = 0;
                }

                estadoOrigen = new Estado(transicionActual.getEstado().get(origen).getNombre());
                estadoDestino = new Estado(transicionActual.getEstado().get(destino).getNombre());
                simbolo = new Simbolo(transicionActual.getSimbolo());
                transicion.setEstadoDestino(estadoDestino);
                transicion.setEstadoOrigen(estadoOrigen);
                transicion.setSimbolo(simbolo);

                transiciones.add(transicion);
            }

            Iterator<Estado> itestados = estados.iterator();
            while (itestados.hasNext()) {
                Estado estadoAct = itestados.next();
                if (estadoAct.isInicial()) {
                    estadoInicial = estadoAct;
                }
                if (estadoAct.isAceptador()) {
                    estadosFinales.add(estadoAct);
                }
            }

            afnOut = new AFN(estadoInicial, alfabeto, estadosFinales, estados, transiciones);

            return afnOut;

        } catch (EstadoNoValidoException ex) {
            System.out.println("estado no valido");
            ex.printStackTrace();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
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
    public void saveAutomatas(File xml, Vector<AFD> automatas) throws IOException{
        Afn rawAfn = getAfn(automatas);
        try {

            JAXBContext context = JAXBContext.newInstance(rawAfn.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(rawAfn, new FileWriter(xml));

        } catch (JAXBException ex) {
            Logger.getLogger(EntradaSalidaXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
