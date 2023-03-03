package servidor;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Mensaje implements Serializable{

	private Object mensaje;
	private TipoMensaje tipo;
	
	public Mensaje(Object mensaje, TipoMensaje tipo)	{
		this.mensaje = mensaje;
		this.tipo = tipo;
	}
	
	
	public Object getMensaje() {
		return mensaje;
	}



	public void setMensaje(Object mensaje) {
		this.mensaje = mensaje;
	}



	public TipoMensaje getTipo() {
		return tipo;
	}



	public void setTipo(TipoMensaje tipo) {
		this.tipo = tipo;
	}



	public enum TipoMensaje	{
		NUEVAPUNTUACION, INICIARPUNTUACION, INICIOACEPTADO, PUNTUACIONANHADIDA
	}
}
