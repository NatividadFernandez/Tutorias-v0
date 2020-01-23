package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Sesiones {

	private Sesion[] coleccionSesiones;
	private int capacidad;
	private int tamano;

	// Constructor con parámetros
	public Sesiones(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionSesiones = new Sesion[capacidad];
		tamano = 0;
	}

	// Getters
	public Sesion[] get() {
		return copiaProfundaSesiones();
	}

	// Copia profunda sesiones
	private Sesion[] copiaProfundaSesiones() {
		Sesion[] copiaSesion = new Sesion[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaSesion[i] = new Sesion(coleccionSesiones[i]);
		}
		return copiaSesion;
	}

	// Sesion tutoria
	public Sesion[] get(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
		}
		Sesion[] sesionTutoria = new Sesion[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionSesiones[i].getTutoria().equals(tutoria)) {
				sesionTutoria[j++] = new Sesion(coleccionSesiones[i]);
			}
		}
		return sesionTutoria;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	// Insertar sesion
	public void insertar(Sesion sesion) throws OperationNotSupportedException {

		if (sesion == null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}

		int indice = buscarIndice(sesion);

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más sesiones.");
		}

		if (tamanoSuperado(indice)) {
			coleccionSesiones[indice] = new Sesion(sesion);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una sesión con esa fecha.");
		}

	}

	// Buscamos el indice
	public int buscarIndice(Sesion sesion) {
		int indice = 0;
		boolean sesionEncontrada = false;
		while (!tamanoSuperado(indice) && !sesionEncontrada) {
			if (coleccionSesiones[indice].equals(sesion)) {
				sesionEncontrada = true;
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

	// Buscar sesiones
	public Sesion buscar(Sesion sesion) {
		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una sesión nula.");
		}

		int indice = buscarIndice(sesion);
		if (!tamanoSuperado(indice)) {
			return new Sesion(coleccionSesiones[indice]);
		} else {
			return null;
		}

	}

	// Desplazar posicion a la izquierda
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionSesiones[i] = coleccionSesiones[i + 1];
		}

		tamano--;
	}

	// Borrar sesiones
	public void borrar(Sesion sesion) throws OperationNotSupportedException {

		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una sesión nula.");
		}

		int indice = buscarIndice(sesion);

		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna sesión con esa fecha.");
		}
	}

}
