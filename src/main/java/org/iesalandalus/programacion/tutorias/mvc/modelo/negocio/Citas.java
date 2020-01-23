package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;

public class Citas {

	private Cita[] coleccionCitas;
	private int capacidad;
	private int tamano;

	// Contructor con parámetros
	public Citas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionCitas = new Cita[capacidad];
		tamano = 0;
	}

	// Getters
	public Cita[] get() {
		return copiaProfundaCitas();
	}

	// Copia profunda citas
	private Cita[] copiaProfundaCitas() {
		Cita[] copiaCita = new Cita[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaCita[i] = new Cita(coleccionCitas[i]);
		}
		return copiaCita;
	}

	// Cita sesion
	public Cita[] get(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: La sesión no puede ser nula.");
		}
		Cita[] citaSesion = new Cita[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionCitas[i].getSesion().equals(sesion)) {
				citaSesion[j++] = new Cita(coleccionCitas[i]);
			}
		}
		return citaSesion;
	}

	// Cita alumno
	public Cita[] get(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		Cita[] citaAlumno = new Cita[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionCitas[i].getAlumno().equals(alumno)) {
				citaAlumno[j++] = new Cita(coleccionCitas[i]);
			}
		}
		return citaAlumno;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	// Insertar citas
	public void insertar(Cita cita) throws OperationNotSupportedException {

		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}

		int indice = buscarIndice(cita);

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		}

		if (tamanoSuperado(indice)) {
			coleccionCitas[indice] = new Cita(cita);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita con esa hora.");
		}

	}

	// Buscamos el indice
	public int buscarIndice(Cita cita) {
		int indice = 0;
		boolean citaEncontrada = false;
		while (!tamanoSuperado(indice) && !citaEncontrada) {
			if (coleccionCitas[indice].equals(cita)) {
				citaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	// Comprobamos tamaño
	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}

	// Comprobamos capacidad
	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	// Buscar citas
	public Cita buscar(Cita cita) {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una cita nula.");
		}

		int indice = buscarIndice(cita);
		if (!tamanoSuperado(indice)) {
			return new Cita(coleccionCitas[indice]);
		} else {
			return null;
		}

	}

	// Desplazar posicion a la izquierda
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionCitas[i] = coleccionCitas[i + 1];
		}

		tamano--;
	}

	// Borrar citas
	public void borrar(Cita cita) throws OperationNotSupportedException {

		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}

		int indice = buscarIndice(cita);

		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita con esa hora.");
		}
	}

}
