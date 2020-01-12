package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public class Profesores {

	private Profesor[] coleccionProfesores;
	private int capacidad;
	private int tamano;

	// Constructor con parámetros
	public Profesores(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionProfesores = new Profesor[capacidad];
		tamano = 0;
	}

	// Getters
	public Profesor[] get() {
		return copiaProfundaProfesores();
	}

	// Copia profunda de Profesores
	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaProfesor = new Profesor[coleccionProfesores.length];
		for (int i = 0; i < coleccionProfesores.length && coleccionProfesores[i] != null; i++) {
			copiaProfesor[i] = new Profesor(coleccionProfesores[i]);
		}
		return copiaProfesor;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	// Insertar profesor
	public void insertar(Profesor profesor) throws OperationNotSupportedException {

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}

		int indice = buscarIndice(profesor);

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		}

		if (tamanoSuperado(indice)) {
			coleccionProfesores[indice] = new Profesor(profesor);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese DNI.");
		}

	}

	// Buscamos el indice
	public int buscarIndice(Profesor profesor) {
		int indice = 0;
		boolean profesorEncontrado = false;
		while (!tamanoSuperado(indice) && !profesorEncontrado) {
			if (coleccionProfesores[indice].equals(profesor)) {
				profesorEncontrado = true;
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

	// Buscar alumnos
	public Profesor buscar(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un profesor nulo.");
		}

		int indice = buscarIndice(profesor);
		if (!tamanoSuperado(indice)) {
			return new Profesor(coleccionProfesores[indice]);
		} else {
			return null;
		}

	}

	// Desplazar posicion a la izquierda
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionProfesores[i] = coleccionProfesores[i + 1];

		}
		tamano--;
	}

	// Borrar alumnos
	public void borrar(Profesor profesor) throws OperationNotSupportedException {

		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un profesor nulo.");
		}

		int indice = buscarIndice(profesor);

		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese DNI.");
		}
	}

}
