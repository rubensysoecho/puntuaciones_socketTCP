package servidor;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Puntuacion implements Serializable, Comparable<Puntuacion>{

	private String nombre;
	private int puntos;
	
	public Puntuacion(String nombre, int puntos) {
		super();
		this.nombre = nombre;
		this.puntos = puntos;
	}
	
	public String getNombre()	{
		return nombre;
	}
	
	public int getPuntos()	{
		return puntos;
	}

	@Override
	public int compareTo(Puntuacion o) {
		Integer puntos1 = puntos;
		Integer puntos2 = o.getPuntos();
		return puntos1.compareTo(puntos2);
	}

	
}
