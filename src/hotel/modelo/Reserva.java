package hotel.modelo;

public class Reserva {

private Integer id;
private String fechaEntrada,fechaSalida;
private String formaPago,valor;

public Reserva(String fechaEntrada, String fechaSalida, String valor, String formaPago) {
	
	this.valor = valor;
	this.fechaEntrada = fechaEntrada;
	this.fechaSalida = fechaSalida;
	this.formaPago = formaPago;
}

public Reserva(String fechaEntrada, String fechaSalida, String valor, String formaPago, int id) {
	this.valor = valor;
	this.fechaEntrada = fechaEntrada;
	this.fechaSalida = fechaSalida;
	this.formaPago = formaPago;
	this.id = id;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getValor() {
	return valor;
}

public String getFechaEntrada() {
	return fechaEntrada;
}

public String getFechaSalida() {
	return fechaSalida;
}

public String getFormaPago() {
	return formaPago;
}



}
