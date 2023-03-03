package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	static final int PUERTO = 5506;
	
	public static void main(String[] args) {
		
		ServerSocket socketServidor = null;
		try {
			socketServidor = new ServerSocket(PUERTO);
			System.out.println("SERVIDOR RECIBIENDO CONEXIONES...");
			DatosPuntuacion puntosGuardados = new DatosPuntuacion();
			
			while(true)	{
				Socket socketCliente = socketServidor.accept();
				System.out.println("Cliente con IP " + socketCliente.getInetAddress() + "conectado.");
				
				HiloServidor hilo = new HiloServidor(socketCliente, puntosGuardados);
				hilo.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
