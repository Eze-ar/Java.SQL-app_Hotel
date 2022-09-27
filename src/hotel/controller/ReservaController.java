package hotel.controller;

import java.util.List;

import hotel.dao.ReservaDAO;
import hotel.factory.ConnectionFactory;
import hotel.modelo.Reserva;



public class ReservaController {
private ReservaDAO reservaDAO;
	
public ReservaController() {
	var factory = new ConnectionFactory();
	this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
}

public void guardar(Reserva reserva) {
	this.reservaDAO.guardar(reserva);
}

public List<Reserva> listar() {
    return reservaDAO.listar();
}

public int eliminar(Integer id) {
    return reservaDAO.eliminar(id);
}

public int modificar(String fecha_entrada, String fecha_salida, Integer valor, String forma_pago, Integer id) {
    return reservaDAO.modificar(fecha_entrada, fecha_salida, valor, forma_pago, id);
}

public List<Reserva> buscarReserva(String numero) {
    return reservaDAO.buscarReserva(numero);
}


}
