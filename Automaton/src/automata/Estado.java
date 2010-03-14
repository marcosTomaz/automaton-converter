/**
 * 
 */
package automata;

/**
 * @author Magda
 *
 */
public class Estado {
    private static long cantidad = 0;

    private int codigo;
    private String nombre;
    private boolean inicial;
    private boolean aceptador;

    /**
     *  constructor nulo
     */
    public Estado() {
        this(0, "q" + Estado.cantidad,false,false);
        ++Estado.cantidad;
    }

    /**
     *
     * @param codigo
     * @param nombre
     */
    public Estado(int codigo, String nombre,boolean inicial,boolean aceptador) {
        setCodigo(codigo);
        setNombre(nombre);
        setInicial(inicial);
        setAceptador(aceptador);
    }

    public Estado(String nombre){
        this(0, nombre,false,false);
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean equals(Object otro) {
        Estado otroEstado = (Estado) otro;
        return otroEstado.getNombre().equals(this.getNombre());
    }

    public boolean isAceptador() {
        return aceptador;
    }

    public boolean isInicial() {
        return inicial;
    }

    public void setAceptador(boolean aceptador) {
        this.aceptador = aceptador;
    }

    public void setInicial(boolean inicial) {
        this.inicial = inicial;
    }

    /**
     * retorna la informacion deseada
     */
    @Override
    public String toString() {
        return getNombre();
    }

    public static void reset(){
        cantidad = 0;
    }
}
