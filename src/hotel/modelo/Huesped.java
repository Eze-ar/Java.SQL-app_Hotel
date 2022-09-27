package hotel.modelo;

public class Huesped {
private Integer id;
private String nombre;
private String apellido;
private String fechaNacimiento;
private String nacionalidad;
private String telefono;
private Integer idReserva;

public Huesped(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono,Integer idReserva) {
	this.nombre = nombre;
	this.apellido = apellido;
	this.fechaNacimiento = fechaNacimiento;
	this.nacionalidad = nacionalidad;
	this.telefono = telefono;
	this.idReserva = idReserva;
}

public Huesped(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, int idReserva) {
	this.id = id;
	this.nombre = nombre;
	this.apellido = apellido;
	this.fechaNacimiento = fechaNacimiento;
	this.nacionalidad = nacionalidad;
	this.telefono = telefono;
	this.idReserva = idReserva;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Integer getIdReserva() {
	return idReserva;
}

public void setIdReserva(Integer idReserva) {
	this.idReserva = idReserva;
}

public String getNombre() {
	return nombre;
}

public String getApellido() {
	return apellido;
}

public String getFechaNacimiento() {
	return fechaNacimiento;
}

public String getNacionalidad() {
	return nacionalidad;
}

public String getTelefono() {
	return telefono;
}

}
