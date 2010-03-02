/**
 * 
 */
package automata;

/**
 * @author Magda
 *
 */
public class Estado {
	private int codigo;
	private String nombre;
	
	/**
	 *  constructor nulo
	 */
	public Estado(){
		this(0,"default");
	}
	
	/**
	 * 
	 * @param codigo
	 * @param nombre
	 */
	public Estado(int codigo, String nombre){
		setCodigo(codigo);
		setNombre(nombre);
	}
	
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre(){
		return nombre;
	}

        public boolean equals(Object otro){
            Estado otroEstado = (Estado) otro;
            return (otroEstado.getNombre().compareTo(getNombre()) == 0);
        }
	
	/**
	 * retorna la informacion deseada
	 */
        @Override
	public String toString(){
		return getNombre();
	}

}
