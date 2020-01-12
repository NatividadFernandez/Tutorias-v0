package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Tutorias {

	private Tutoria[] coleccionTutorias;
	private int capacidad;
	private int tamano;

	// Constructor con parámetros
	public Tutorias(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionTutorias = new Tutoria[capacidad];
		tamano = 0;
	}

	// Getters
	public Tutoria[] get() {
		return copiaProfundaProfesores();
	}

	// Copia profunda tutorias
	private Tutoria[] copiaProfundaProfesores() {
		Tutoria[] copiaTutoria = new Tutoria[coleccionTutorias.length];
		for (int i = 0; i < coleccionTutorias.length && coleccionTutorias[i] != null; i++) {
			copiaTutoria[i] = new Tutoria(coleccionTutorias[i]);
		}
		return copiaTutoria;
	}

	//DUDAS
	public Tutoria[] get(Profesor profesor) {
		Tutoria[] tutoriaProfesor = new Tutoria[tamano];
		int tutorias = 0;
		for(int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionTutorias[i].getProfesor().equals(profesor)) {
				tutoriaProfesor[tutorias++] = new Tutoria(coleccionTutorias[i]);
			}			
		}		
		return tutoriaProfesor;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	// Insertar tutoria
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {

		if (tutoria == null) {
			throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
		}

		int indice = buscarIndice(tutoria);

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más tutorías.");
		}

		if (tamanoSuperado(indice)) {
			coleccionTutorias[indice] = new Tutoria(tutoria);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una tutoría con ese identificador.");
		}

	}

	// Buscamos el indice
	public int buscarIndice(Tutoria tutoria) {
		int indice = 0;
		boolean tutoriaEncontrada = false;
		while (!tamanoSuperado(indice) && !tutoriaEncontrada) {
			if (coleccionTutorias[indice].equals(tutoria)) {
				tutoriaEncontrada = true;
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
	public Tutoria buscar(Tutoria tutoria) {
		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una tutoría nula.");
		}

		int indice = buscarIndice(tutoria);
		if (!tamanoSuperado(indice)) {
			return new Tutoria(coleccionTutorias[indice]);
		} else {
			return null;
		}

	}

	// Desplazar posicion a la izquierda
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionTutorias[i] = coleccionTutorias[i + 1];

		}
		tamano--;
	}

	// Borrar alumnos
	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {

		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una tutoría nula.");
		}

		int indice = buscarIndice(tutoria);

		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna tutoría con ese identificador.");
		}
	}

}
