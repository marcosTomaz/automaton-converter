/**
 * 
 */
package automata;

/**
 * @author Magda
 *
 */
public class Estado {
	int codigo;
	String nombre;
	
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
	
	/**
	 * retorna la informacion deseada
	 */
	public String toString(){
		return getNombre();
	}

}
