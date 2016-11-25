package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movil {

	private StringProperty nombre;
	private StringProperty precio;
	
	public Movil(String nombre,String precio){
		this.nombre = new SimpleStringProperty(nombre);
		this.precio = new SimpleStringProperty(precio);
	}

	public StringProperty getNombre() {
		return nombre;
	}

	public StringProperty getPrecio() {
		return precio;
	}
	
	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	public void setPrecio(StringProperty precio) {
		this.precio =  precio;
	}
	
	
}
