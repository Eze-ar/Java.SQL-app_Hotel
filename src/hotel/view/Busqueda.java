package hotel.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import hotel.controller.HuespedController;
import hotel.controller.ReservaController;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes, tbReservas;
	private DefaultTableModel modeloHuesped;
	private DefaultTableModel modeloReserva;
	private ReservaController reservaController;
	private HuespedController huespedController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {

		reservaController = new ReservaController();
		huespedController = new HuespedController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		contentPane.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (tbHuespedes.isVisible()) {

					buscarTablaHuespedes(txtBuscar.getText().toString());
					
				}
				
				if (tbReservas.isVisible()) {
					buscarTablaReservas(txtBuscar.getText().toString());
				}
				
			}
		});

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				if (tbHuespedes.isVisible()) {
					modificarHuesped();
				}
				
				if(tbReservas.isVisible()) {
					modificarReserva();
				}
				
				limpiarTablas();
				cargarTablaHuespedes();
				cargarTablaReservas();

			}

		});

		JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 258, 42);
		contentPane.add(lblNewLabel_4);

		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(815, 416, 54, 41);
		contentPane.add(btnSalir);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);

		tbHuespedes = new JTable();
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		 
		modeloHuesped.addColumn("Identificador del cliente");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Identificador reserva");
		cargarTablaHuespedes();
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes,
				null);

		tbReservas = new JTable();
		modeloReserva = (DefaultTableModel) tbReservas.getModel();
		modeloReserva.addColumn("Identificador de la reserva");
		modeloReserva.addColumn("Fecha entrada");
		modeloReserva.addColumn("fecha salida");
		modeloReserva.addColumn("valor");
		modeloReserva.addColumn("forma pago");
		cargarTablaReservas();

		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas,
				null);

		JButton btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.setBackground(SystemColor.menu);
		btnEliminar.setBounds(651, 416, 54, 41);
		contentPane.add(btnEliminar);

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				if (tbHuespedes.isVisible()) {
					eliminarHuesped();
				}
				
				if(tbReservas.isVisible()) {
					eliminarReserva();
				}
				
			
				
				limpiarTablas();
				cargarTablaHuespedes();
				cargarTablaReservas();

			}

		});

		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);
		setResizable(false);

	}

	private void limpiarTablas() {
		modeloHuesped.setRowCount(0);
		modeloReserva.setRowCount(0);
	}

	private void cargarTablaHuespedes() {
		var huesped = this.huespedController.listar();
		modeloHuesped.addRow(new Object[] { "Id", "Nombre", "Apellido",
				 "Fecha Nacimiento", "Nacionalidad", "Telefono","Id Reserva" });

		huesped.forEach(h -> modeloHuesped.addRow(new Object[] { h.getId(), h.getNombre(), h.getApellido(),
				h.getFechaNacimiento(), h.getNacionalidad(), h.getTelefono(), h.getIdReserva() }));
	}

	
	private void buscarTablaHuespedes(String lastname) {
		limpiarTablas();
		var huesped = this.huespedController.buscarHuesped(lastname);
		modeloHuesped.addRow(new Object[] { "Id", "Nombre", "Apellido",
				 "Fecha Nacimiento", "Nacionalidad", "Telefono","Id Reserva" });
		
		if(lastname == "") {
			cargarTablaHuespedes();
		}

		huesped.forEach(h -> modeloHuesped.addRow(new Object[] { h.getId(), h.getNombre(), h.getApellido(),
				h.getFechaNacimiento(), h.getNacionalidad(), h.getTelefono(), h.getIdReserva() }));
	}
	
	private void cargarTablaReservas() {
		var reserva = this.reservaController.listar();
		
		modeloReserva.addRow(new Object[] { "Id", "Fecha Entrada", "Fecha Salida",
				 "Valor", "Forma de Pago"});

		reserva.forEach(r -> modeloReserva.addRow(
				new Object[] { r.getId(), r.getFechaEntrada(), r.getFechaSalida(), r.getValor(), r.getFormaPago() }));
	}

	private void buscarTablaReservas(String numero) {
		limpiarTablas();
		var reserva = this.reservaController.buscarReserva(numero);
		modeloReserva.addRow(new Object[] { "Id", "Fecha Entrada", "Fecha Salida",
				 "Valor", "Forma de Pago"});
		
		if(numero == "") {
			cargarTablaReservas();
		}

		reserva.forEach(r -> modeloReserva.addRow(
				new Object[] { r.getId(), r.getFechaEntrada(), r.getFechaSalida(), r.getValor(), r.getFormaPago() }));
	}
	

	/**
	 * 
	 */
	private void eliminarReserva() {
		

		Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresent(fila -> {
					Integer id = Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());

					var filasModificadas = this.reservaController.eliminar(id);

					modeloReserva.removeRow(tbReservas.getSelectedRow());

					JOptionPane.showMessageDialog(this,
							String.format("%d item eliminado con éxito!", filasModificadas));
				});
	}

	private void eliminarHuesped() {
		
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresent(fila -> {
					Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());

					var filasModificadas = this.reservaController.eliminar(id);

					modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

					JOptionPane.showMessageDialog(this,
							String.format("%d item eliminado con éxito!", filasModificadas));
				});
	}
	
	  private void modificarHuesped() {
	       

	        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
	                .ifPresent(fila -> {
	                	 Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
	                    String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
	                    String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
	                    String fechaNacimiento = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3);
	         
	                    String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
	                    String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
	                    
	                    var filasModificadas = this.huespedController.modificar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, id);
	                    
	                    JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	                });
	    }
	  
	  private void modificarReserva() {
	      

	        Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	                .ifPresent(fila -> {
	                	
	                	 Integer id = Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	                    String fecha_entrada = (String) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 1);
	                    String fecha_salida = (String) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 2);
	                    Integer valor =Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 3).toString());	         
	                    String forma_pago = (String) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 4);
	                   
	                    
	                    var filasModificadas = this.reservaController.modificar(fecha_entrada, fecha_salida, valor, forma_pago, id);
	                    
	                    JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	                });
	    }
}
