package servidor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DatosPuntuacion {

	private ArrayList<ObjectOutputStream> listaSalidas;
	private ArrayList<Puntuacion> listaPuntuaciones;
	private BufferedReader entrada;
	
	public DatosPuntuacion()	{
		listaPuntuaciones = new ArrayList<Puntuacion>();
		try {
			entrada = new BufferedReader(new FileReader("puntuaciones.csv"));
			String linea = entrada.readLine();
			Scanner in = null;
			while(linea != null)	{
				in = new Scanner(linea);
				in.useDelimiter("\\s*;\\s*");
				int puntos = in.nextInt();
				String nombre = in.next();
				listaPuntuaciones.add(new Puntuacion(nombre, puntos));
				linea = entrada.readLine();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Puntuacion> devolverLista()	{
		return listaPuntuaciones;
	}
	
	public synchronized void anhadirSalida(ObjectOutputStream salida)	{
		listaSalidas.add(salida);
	}
	
	public synchronized void anhadirPuntuacion(Puntuacion puntuacion_anhadir)	{
		listaPuntuaciones.add(puntuacion_anhadir);
		Collections.sort(listaPuntuaciones);
	}
}
