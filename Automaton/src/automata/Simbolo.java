/**
 * 
 */
package automata;

/**
 * @author Magda
 *
 */
public class Simbolo {
	String nombre;
	int codigo;
	
	public Simbolo(){
		this("default");
	}
	
	public Simbolo(String nombre){
		setNombre(nombre);
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public String toString(){
		return getNombre();
	}
	
	public boolean isEqual(Object simbolo){
		return ((Simbolo)simbolo).nombre.compareTo(nombre) == 0;
	}

}
