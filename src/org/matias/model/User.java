package org.matias.model;

public class User {
    private int id;
    private String nombre;
    private String apellido;
    private String rut;
    private boolean debe;
    private String clave;
    
    
    
	public User() {
		super();
	}

	public User(int id, String nombre, String apellido, String rut, boolean debe, String clave) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rut = rut;
		this.debe = debe;
		this.clave = clave;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public boolean isDebe() {
		return debe;
	}

	public void setDebe(boolean debe) {
		this.debe = debe;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

    // Constructor, getters y setters
    
}
