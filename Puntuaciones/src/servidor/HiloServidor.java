package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import servidor.Mensaje.TipoMensaje;

public class HiloServidor extends Thread{

	private Socket socket;
	private DatosPuntuacion datosLista;
	
	public HiloServidor(Socket socket, DatosPuntuacion datosLista)	{
		this.socket = socket;
		this.datosLista = datosLista;
	}
	
	@Override
	public void run() {
		super.run();
		
		try {
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			datosLista.anhadirSalida(salida);
			boolean fin = false;
			
			while (!fin)	{
				Mensaje msgRecibido = (Mensaje) entrada.readObject();
				Mensaje msgEnviar;
				
				switch (msgRecibido.getTipo())	{
				
				case INICIARPUNTUACION:
					msgEnviar = new Mensaje(datosLista.devolverLista(), TipoMensaje.INICIOACEPTADO); 
					salida.writeObject(msgEnviar);
					salida.flush();
					break;
					
				case NUEVAPUNTUACION:
					datosLista.anhadirPuntuacion((Puntuacion) msgRecibido.getMensaje());
					msgEnviar = new Mensaje("puntuacion anhadida", TipoMensaje.PUNTUACIONANHADIDA);
					salida.writeObject(msgEnviar);
					salida.flush();
					break;
					
				default:
					break;
				}
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
