package org.iesalandalus.programacion.tutorias.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.*;

public class Modelo {

	private static final int CAPACIDAD = 5;
	private Alumnos alumnos;
	private Citas citas;
	private Profesores profesores;
	private Sesiones sesiones;
	private Tutorias tutorias;

	public Modelo() {
		alumnos = new Alumnos(CAPACIDAD);
		citas = new Citas(CAPACIDAD);
		profesores = new Profesores(CAPACIDAD);
		sesiones = new Sesiones(CAPACIDAD);
		tutorias = new Tutorias(CAPACIDAD);
	}

	// Alumno
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.insertar(alumno);
	}

	public void buscar(Alumno alumno) {
		alumnos.buscar(alumno);
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.borrar(alumno);
	}

	public Alumno[] getAlumnos() {
		return alumnos.get();
	}

	// Profesor
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}

	public void buscar(Profesor profesor) {
		profesores.buscar(profesor);
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}

	public Profesor[] getProfesores() {
		return profesores.get();
	}

	// Tutoría
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		tutorias.insertar(tutoria);
	}

	public void buscar(Tutoria tutoria) {
		tutorias.buscar(tutoria);
	}

	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		tutorias.borrar(tutoria);
	}

	public Tutoria[] getTutorias() {
		return tutorias.get();
	}

	public Tutoria[] getTutorias(Profesor profesor) {
		return tutorias.get(profesor);
	}

	// Sesion
	public void insertar(Sesion sesion) throws OperationNotSupportedException {
		sesiones.insertar(sesion);
	}

	public void buscar(Sesion sesion) {
		sesiones.buscar(sesion);
	}

	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		sesiones.borrar(sesion);
	}

	public Sesion[] getSesiones() {
		return sesiones.get();
	}

	public Sesion[] getSesiones(Tutoria tutoria) {
		return sesiones.get(tutoria);
	}

	// Cita
	public void insertar(Cita cita) throws OperationNotSupportedException {
		citas.insertar(cita);
	}

	public void buscar(Cita cita) {
		citas.buscar(cita);
	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		citas.borrar(cita);
	}

	public Cita[] getCitas() {
		return citas.get();
	}

	public Cita[] getCitas(Sesion sesion) {
		return citas.get(sesion);
	}

	public Cita[] getCitas(Alumno alumno) {
		return citas.get(alumno);
	}

}
