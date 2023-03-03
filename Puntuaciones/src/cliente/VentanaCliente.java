package cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

import servidor.Puntuacion;
import javax.swing.JScrollPane;

public class VentanaCliente extends JFrame {

	private final static String SERVIDOR = "localhost";
	private final static int PUERTO = 5506;
	
	private JPanel contentPane;
	private JTextField tf_nombre;
	private JTextField tf_puntuacion;
	private DefaultTableModel modeloTabla = new DefaultTableModel(0, 2);
	private ArrayList<Puntuacion> listaPuntuaciones;
	private JTable tablaPuntuaciones;
	private Socket socket;
	private HiloCliente hilo;
	private ObjectInputStream inputServer;
	private ObjectOutputStream outputServer;
	
	public void vaciar_e_inicializar_tabla()	{
		int num_filas = modeloTabla.getRowCount();
		for (int i = 0; i < num_filas; i++) {
			modeloTabla.removeRow(i);
		}
		String columnas[] = { "Nombre", "Puntos"};
		modeloTabla.setColumnIdentifiers(columnas);
		tablaPuntuaciones.setModel(modeloTabla);
	}
	
	public void rellenar_datos_tabla()	{
		String fila[] = new String[2];
		for (Puntuacion puntuacion : listaPuntuaciones) {
			fila[0] = puntuacion.getNombre();
			fila[1] = String.valueOf(puntuacion.getPuntos());
			modeloTabla.addRow(fila);
		}
		tablaPuntuaciones.setModel(modeloTabla);
	}
	
	public void anhadirPuntuacion()	{
		
	}
	
	public void conectar()	{
		try {
			socket = new Socket(SERVIDOR, PUERTO);
			inputServer = new ObjectInputStream(socket.getInputStream());
			outputServer = new ObjectOutputStream(socket.getOutputStream());
			hilo = new HiloCliente(inputServer, this);
			hilo.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCliente frame = new VentanaCliente();
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
	public VentanaCliente() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_nombre.setBounds(10, 11, 97, 19);
		contentPane.add(lbl_nombre);
		
		tf_nombre = new JTextField();
		tf_nombre.setBounds(117, 10, 248, 20);
		contentPane.add(tf_nombre);
		tf_nombre.setColumns(10);
		
		JLabel lbl_puntuacion = new JLabel("Puntuación:");
		lbl_puntuacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntuacion.setBounds(10, 42, 97, 19);
		contentPane.add(lbl_puntuacion);
		
		tf_puntuacion = new JTextField();
		tf_puntuacion.setColumns(10);
		tf_puntuacion.setBounds(117, 41, 97, 20);
		contentPane.add(tf_puntuacion);
		
		JButton btn_anhadir = new JButton("Añadir");
		btn_anhadir.setBounds(276, 41, 89, 23);
		contentPane.add(btn_anhadir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 355, 400);
		contentPane.add(scrollPane);
		
		tablaPuntuaciones = new JTable();
		scrollPane.setViewportView(tablaPuntuaciones);
		conectar();
	}
	
	public void asignarLista(ArrayList<Puntuacion> datosAIntroducir)	{
		listaPuntuaciones = datosAIntroducir;
	}
}
