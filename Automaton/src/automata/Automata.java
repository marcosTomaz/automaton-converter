/**
 * conjunto de estados
 * estado inicial
 * estados finales
 * alfabeto
 * funcion de transicion
 */
package automata;

import com.inamik.utils.SimpleTableFormatter;
import com.inamik.utils.TableFormatter;
import excepcion.SimboloNoExistente;
import java.util.Iterator;
import java.util.Vector;
import excepcion.EstadoNoValidoException;
import ioxml.Afn.Automata.Estados;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Magda
 *
 */
public abstract class Automata {
	private int estadoInicial;
	private Alfabeto alfabeto;
        private Vector<Integer> estadosFinales;
        private Vector<Estado> estados;
	// vector de arreglos de vectores de estado (tabla de 3 dimensiones)
        //
	private Vector<Vector<Estado>[]> tabla;
	
	public Automata(Estado estadoInicial,Alfabeto alfabeto,Vector<Estado> estadosFinales,Vector<Estado> estados,Vector<Transicion> funcion) throws EstadoNoValidoException {
             try {

                 this.estadosFinales = new Vector<Integer>();
                    this.alfabeto = alfabeto;
                    this.estados = estados;

                   // eliminarInalcanzables(funcion,estadoInicial);

                    // creo tabla
                    crearTabla();
                    // seteo estado inicial
                    setEstadoInicial(estadoInicial);
                    // inicializo estados finales
                    setEstadosFinales(estadosFinales);

                    // inicilizo tabla (cargo transiciones)
                    Iterator<Transicion> itTransicion = funcion.iterator();

                    while(itTransicion.hasNext()){
                        Transicion transicion = itTransicion.next();
                        addTransicion(transicion);
                    }
            } catch (SimboloNoExistente ex) {
                    Logger.getLogger(Automata.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }

	
	public void setAlfabeto(Alfabeto alfabeto){
            this.alfabeto = alfabeto;
	}
	
	public Alfabeto getAlfabeto(){
            return alfabeto;
        }

	public Vector<Estado> mover(Estado origen, Simbolo simbolo) throws SimboloNoExistente{
             return tabla.get(origen.getCodigo())[alfabeto.getIndice(simbolo)];

	}

        protected  int getIndice(Estado estado) throws EstadoNoValidoException{
            Estado estadoValido = checkEstado(estado);
            return estadoValido.getCodigo();

        }
         public void addTransicion(Transicion transicion) throws EstadoNoValidoException, SimboloNoExistente {

        // recupero estado origen
        // busco el estado origen (XO) en el vector de estados
        /////// A
        /////// si no lo encuentro lanzo exception
        /////// B
        /////// si lo encuentro
        /////// recupero el estado encontrado (YO)
        /////// descarto el estado origen XO
        /////// obtengo el codigo de YO (CYO)
        /////// busco el estado destino XD
        ////////// A
        ////////// si no lo encuentro lanzo la excepcion
        ////////// B
        ////////// si lo encuentro
        ////////// recupero el estado encontrado (YD)
        ////////// descarto el estado destino (YD)
        ////////// obtengo el codigo de YD (CYD)
        ////////// obtengo el indice del simbolo (IS)
        ////////// agrego el estado YD en la posicion CYO-IS de la tabla
        Estado estadoOrigen = null;
        Estado estadoDestino = null;
        Simbolo simbolo = null;

        estadoOrigen = checkEstado(transicion.getEstadoOrigen());
        estadoDestino = checkEstado(transicion.getEstadoDestino());
        simbolo = transicion.getSimbolo();

        Vector<Estado> destinos = mover(estadoOrigen, simbolo);
        Iterator<Estado> itDestinos = destinos.iterator();

        while (itDestinos.hasNext()){
            if (itDestinos.next().equals(transicion.getEstadoDestino())){
                return;
            }
        }

        destinos.add(estadoDestino);
        }

        private Estado checkEstado(Estado estado) throws EstadoNoValidoException {
        Iterator<Estado> itEstado = estados.iterator();
        Estado estadoValido = null;

        while (itEstado.hasNext()) {

            Estado estadoActual = itEstado.next();
            if (estadoActual.equals(estado)) {
                estadoValido = estadoActual;
                break;
            }
        }

        if (estadoValido == null) {
            throw new EstadoNoValidoException("Estado no valido");
        }
        return estadoValido;
    }

        public void setEstadoInicial(Estado estado) throws EstadoNoValidoException{
            estadoInicial = getIndice(estado);
        }

        public int getEstadoInicial(){
            return estadoInicial;
        }

        public void setEstadosFinales(Vector<Estado> estados) throws EstadoNoValidoException{
            Iterator<Estado> itEstados = estados.iterator();
            Vector<Integer> aux = new Vector<Integer>();

            while (itEstados.hasNext()){
                aux.add(new Integer(getIndice(itEstados.next())));
            }

            estadosFinales.addAll(aux);
        }

        public Vector<Integer> getEstadosFinales(){
            return estadosFinales;
        }

        public void setEstados(Vector<Estado> estados){
            this.estados = estados;
        }

        public void crearTabla(){
            // creo el vector de estados origen (Y)
            // recorro el vector de estados (x)
            // por cada estado
            ////// obtengo la cantidad de simbolos que tiene el alfabeto
            ////// creo el arreglo de longitud = a la cantidad de simbolos
            ////// por cada posicion del arreglo
            /////////// creo un nuevo vector de destinos (Z)
            ////// agrego el arreglo al vector estados origen (Y)
            ////// al estado actual del vector X le asigo el codigo = a la posicion actual del vector Y

            Iterator<Estado> itEstados = estados.iterator();
            tabla = new Vector<Vector<Estado>[]>();
            int cantSimbolos = alfabeto.getCantidadSimbolos();

            while (itEstados.hasNext()){
                Vector simbolos[] = new Vector[cantSimbolos];
                for (int i = 0;i < simbolos.length; i++){
                    simbolos[i] = new Vector<Estado>();
                }
                tabla.add(simbolos);
                //itEstados.next().setCodigo(tabla.indexOf(simbolos));
                itEstados.next().setCodigo(tabla.size()-1);
            }
        }

        public Vector<Estado> getEstados(){
            return estados;
        }

        private Estado getEstado(int codigo){
            // recorro el vector de estados
            //// busco el estado que tiene el codigo correspondiente
            //// retorno el estado

            return estados.get(codigo);

//            Iterator<Estado> itEstado = estados.iterator() ;
//
//            while (itEstado.hasNext()){
//                Estado estadoActual = itEstado.next();
//                if (estadoActual.getCodigo() == codigo){
//                    return estadoActual;
//                }
//            }
//            return null;
        }

        /**
         *
         * @return la funcion de transicion del automata
         */
    public Vector<Transicion> getFuncion() {
        Iterator<Estado> itEstado = getEstados().iterator();
        Vector<Transicion> transiciones = new Vector<Transicion>();

        while (itEstado.hasNext()) {
            Estado estadoOrigen = itEstado.next();
            for (int i = 0; i < alfabeto.getCantidadSimbolos(); ++i) {
                try {
                    Vector<Estado> estadosDestino;
                    Simbolo simbolo = alfabeto.getSimbolo(i);
                    estadosDestino = mover(estadoOrigen, simbolo);
                    for (int j = 0; j < estadosDestino.size(); j++) {
                        Transicion transicionActual = new Transicion();
                        transicionActual.setEstadoDestino(estadosDestino.get(j));
                        transicionActual.setEstadoOrigen(estadoOrigen);
                        transicionActual.setSimbolo(simbolo);
                        transiciones.add(transicionActual);
                    }
                } catch (SimboloNoExistente ex) {
                    ex.printStackTrace();
                }
            }
        }

        return transiciones;
    }

      /**
	 * retorna la informacion deseada
	 */
        @Override
	public String toString(){
//            //return  getEstadoOrigen() + "--" + getSimbolo() + "------>" + getEstadoOrigen();
//
//            StringBuffer salida = new StringBuffer();
//            int indEstOrigen,indSimbolos,indEstDestino;
//
//            int lenEstOrigen = tabla[0][0].length;
//            int lenSimbolos = tabla[0].length;
//            int lenEstDestino = tabla.length;
//
//            // Recorrido de las filas de la matriz
//            for (indEstOrigen=0; indEstOrigen<lenEstOrigen; indEstOrigen++){
//            // Recorrido de las celdas de una fila
//                for (indSimbolos=0; indSimbolos<lenSimbolos; indSimbolos++){
//                    for (indEstDestino=0; indEstDestino<lenEstDestino; indEstDestino++){
//                        salida.append("Estado Origen: ");
//                        salida.append(indEstOrigen);
//                        salida.append("Simbolo: ");
//                        salida.append(tabla.get(getIndice(estados)));
//                        salida.append("Estado Destino: ");
//                        salida.append(tabla[indEstadoOrigen][indSimbolo][indEstadoDestino]);
//                    }
//                 }
//            }
//            return salida.toString();

            TableFormatter tf = new SimpleTableFormatter(true);
            TableFormatter encabezado = tf.nextRow();
            encabezado = encabezado.nextCell().addLine("Estado");
            encabezado = encabezado.nextCell().addLine("->");
            encabezado = encabezado.nextCell().addLine("F");

            for (int i = 0;i < alfabeto.getCantidadSimbolos();i++){
                encabezado = encabezado.nextCell().addLine(alfabeto.getSimbolo(i).getNombre());
            }

            TableFormatter fila = encabezado;
            TableFormatter celda;
            TableFormatter linea = new SimpleTableFormatter();
            for (int i = 0; i < tabla.size(); ++i){
                fila = fila.nextRow();
                celda = fila.nextCell();
                celda = celda.addLine(getEstado(i).getNombre());
                celda = fila.nextCell();
                celda = celda.addLine((getEstado(i).isInicial()) ? "Si" : "No");
                celda = fila.nextCell();
                celda = celda.addLine((getEstado(i).isAceptador()) ? "Si" : "No");
                for (int j = 0;j < tabla.get(i).length;j++){
                    celda = celda.nextCell();

                    for (int k = 0;k < tabla.get(i)[j].size();k++){
                        linea = celda.addLine(tabla.get(i)[j].get(k).getNombre());
                        celda = linea;
                    }
                }
                fila = celda;
            }

            String[] table = tf.getFormattedTable();
            StringBuffer salida = new StringBuffer();

		for (int i = 0, size = table.length; i < size; i++)
		{
			salida.append(table[i] + "\n");
		}

            return salida.toString();

        }

        /*

        private static boolean tienePredecesor(Estado estado, Vector<Transicion> transiciones, Estado inicial,HashSet<Estado> alcanzables){
            if (estado.equals(inicial)){
                alcanzables.add(estado);
                return true;
            }
            boolean cortar = false;
            Iterator<Transicion> itTransiciones = transiciones.iterator();

            while(!cortar && itTransiciones.hasNext()){
                Transicion transicion = itTransiciones.next();
                if (estado.equals(transicion.getEstadoDestino()) && !alcanzables.contains(estado)){
                    cortar = tienePredecesor(transicion.getEstadoOrigen(), transiciones, inicial,alcanzables);
                    if (cortar){
                        alcanzables.add(estado);
                    }
                }
            }

            return cortar;

        }
        
        public static Vector<Estado> obtenerInalcanzables(Vector<Transicion> transiciones,Estado inicial){
            HashSet<Estado> estadosAlcanzables = new HashSet<Estado>();
            Iterator<Transicion> itTransiciones;
            //Iterator<Estado> itEstados = estados.iterator();

        //    HashSet<Estado> estadosInalcanzables = new HashSet<Estado>();
            itTransiciones = transiciones.iterator();
         //   Vector<Estado> salida = new Vector<Estado>();

            while(itTransiciones.hasNext()){
                Transicion tranActual = itTransiciones.next();
                tienePredecesor(tranActual.getEstadoOrigen(), transiciones, inicial,estadosAlcanzables);
//                if (!estadosAlcanzables.contains(tranActual.getEstadoOrigen()) && !estadosInalcanzables.contains(tranActual.getEstadoOrigen())){
//                    if (tienePredecesor(tranActual.getEstadoOrigen(), transiciones, inicial,estadosAlcanzables)){
//                        estadosAlcanzables.add(tranActual.getEstadoOrigen());
//                    }
//                    else {
//                        estadosInalcanzables.add(tranActual.getEstadoOrigen());
//                    }
//                }
            }

//            estadosAlcanzables.add(inicial);
//            while (itEstados.hasNext()){
//                Estado actual = itEstados.next();
//                itTransiciones = transiciones.iterator();
//                while(itTransiciones.hasNext()){
//                    if (itTransiciones.next().getEstadoDestino().equals(actual)){
//                        estadosAlcanzables.add(actual);
//                    }
//                }
//            }




//            while (itTransiciones.hasNext()){
//                Transicion transicionActual = itTransiciones.next();
//                Estado destino = transicionActual.getEstadoDestino();
//                estadosAlcanzables.add(destino);
//            }

            return new Vector<Estado>(estadosAlcanzables);
        }

*/
        	/**
	 * Recorre el automata.
	 * @param salida cadena que representa la funcion de transicion
	 * @return los estados alcanzables del automata
	 */
	public static Vector<Estado> recorrer(Vector<Transicion> transiciones,Alfabeto alfabeto, Estado inicial){
		Stack<Estado> pila = new Stack<Estado>();
		Estado estadoActual;
		Simbolo simboloActual;
		Vector<Estado> destinos;
		HashSet<Estado> visitados = new HashSet<Estado>();

			estadoActual = inicial;

			pila.push(estadoActual);	//actua como marca, evitando que la pila quede vacia antes de lo previsto

			do {

				visitados.add(estadoActual);

				for (int i = 0;i < alfabeto.getCantidadSimbolos();++i){
					simboloActual = alfabeto.getSimbolo(i);
                                        Iterator<Transicion> itTransicion = transiciones.iterator();

                                        destinos = new Vector<Estado>();
                                        while (itTransicion.hasNext()){
                                            Transicion tActual = itTransicion.next();
                                            if (tActual.getSimbolo().equals(simboloActual)){
                                                destinos.add(tActual.getEstadoDestino());
                                            }
                                        }

					//destinos = mover(estadoActual,simboloActual);

					for (int j = 0;j < destinos.size();++j){

						pila.push(destinos.get(j));
					}
				}

				if (!pila.empty()){
					do {

						estadoActual = pila.pop();

					}while (!pila.empty() && visitados.contains(estadoActual)); //solo elige estados no visitados
				}

			} while (!pila.empty());


		return new Vector<Estado>(visitados);
	}
}

