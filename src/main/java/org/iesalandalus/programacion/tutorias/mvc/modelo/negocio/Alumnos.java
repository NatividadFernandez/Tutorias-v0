package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public class Alumnos {

	private Alumno[] coleccionAlumnos;
	private int capacidad;
	private int tamano;

	// Constructor con parámetros
	public Alumnos(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionAlumnos = new Alumno[capacidad];
		tamano = 0;

	}

	// Getters
	public Alumno[] get() {
		return copiaProfundaProfesores();
	}

	// Copia profunda alumnos
	private Alumno[] copiaProfundaProfesores() {
		Alumno[] copiaAlumno = new Alumno[coleccionAlumnos.length];
		for (int i = 0; i < coleccionAlumnos.length && coleccionAlumnos[i] != null; i++) {
			copiaAlumno[i] = new Alumno(coleccionAlumnos[i]);

		}

		return copiaAlumno;

	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	// Insertar alumno
	public void insertar(Alumno alumno) throws OperationNotSupportedException {

		if (alumno == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}

		int indice = buscarIndice(alumno);

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
		}

		if (tamanoSuperado(indice)) {
			coleccionAlumnos[indice] = new Alumno(alumno);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese expediente.");
		}

	}

	// Buscamos el indice
	public int buscarIndice(Alumno alumno) {
		int indice = 0;
		boolean alumnoEncontrado = false;
		while (!tamanoSuperado(indice) && !alumnoEncontrado) {
			if (coleccionAlumnos[indice].equals(alumno)) {
				alumnoEncontrado = true;
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
	public Alumno buscar(Alumno alumno) {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}

		int indice = buscarIndice(alumno);
		if (!tamanoSuperado(indice)) {
			return new Alumno(coleccionAlumnos[indice]);
		} else {
			return null;
		}

	}

	// Desplazar posicion a la izquierda
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionAlumnos[i] = coleccionAlumnos[i + 1];
		}
		
		tamano--;
	}

	// Borrar alumnos
	public void borrar(Alumno alumno) throws OperationNotSupportedException {

		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}

		int indice = buscarIndice(alumno);

		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese expediente.");
		}
	}

}
