package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import servidor.Mensaje;
import servidor.Puntuacion;

public class HiloCliente extends Thread{
	
	private ObjectInputStream flujoentrada;
	private VentanaCliente interfaz;
	
	public HiloCliente(ObjectInputStream flujoentrada, VentanaCliente interfaz)	{
		this.flujoentrada = flujoentrada;
		this.interfaz = interfaz;
	}
	
	@Override
	public void run() {
		super.run();
		
		while(true)	{
			try {
				Mensaje msgRecibido = (Mensaje) flujoentrada.readObject();
				
				switch(msgRecibido.getTipo())	{
				case INICIOACEPTADO:
					interfaz.asignarLista((ArrayList<Puntuacion>) msgRecibido.getMensaje());
					
				case PUNTUACIONANHADIDA:
					
				}
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
