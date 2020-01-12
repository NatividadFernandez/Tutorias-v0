package org.iesalandalus.programacion.tutorias.mvc.vista;

import java.time.LocalDate;
import java.time.LocalTime;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {

	}

	public static void mostrarMenu() {
		System.out.println("*** MENÚ INICIAL ***");
		for (Opcion opciones : Opcion.values()) {
			System.out.println(opciones.toString());
		}
	}

	public static void mostrarCabecera(String menu) {
		System.out.printf("%n%s%n", menu);
		String decoracionMenu = "%0" + menu.length() + "d%n";
		System.out.println(String.format(decoracionMenu, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {
		int opcion = 0;
		boolean opcionCorrecta = false;
		do {

			System.out.print("Elige una opción del menú: ");
			opcion = Entrada.entero();

			if (opcion >= 0 && opcion <= Opcion.values().length - 1) {
				opcionCorrecta = true;
			}

		} while (!opcionCorrecta);

		return opcion;
	}

	public static Alumno leerAlumno() {
		String nombreAlumno, correoAlumno;
		System.out.println("*** DATOS ALUMNO ***");
		System.out.println("Introduce el nombre del alumno: ");
		nombreAlumno = Entrada.cadena();
		System.out.println("Introduce el correo del alumno: ");
		correoAlumno = Entrada.cadena();
		System.out.println("");

		return new Alumno(nombreAlumno, correoAlumno);
	}

	public static Alumno leerAlumnoFicticio() {
		String correoAlumno;
		System.out.println("Introduce el correo del alumno: ");
		correoAlumno = Entrada.cadena();
		System.out.println("");

		return Alumno.getAlumnoFicticio(correoAlumno);
	}

	public static Profesor leerProfesor() {
		String nombreProfesor, dni, correoProfesor;
		System.out.println("*** DATOS PROFESOR ***");
		System.out.println("Introduce el nombre del profesor: ");
		nombreProfesor = Entrada.cadena();
		System.out.println("Introduce el DNI del prodesor: ");
		dni = Entrada.cadena();
		System.out.println("Introduce el correo del profesor: ");
		correoProfesor = Entrada.cadena();
		System.out.println("");

		return new Profesor(nombreProfesor, dni, correoProfesor);
	}

	public static Profesor leerProfesorFicticio() {
		String dniProfesor;
		System.out.println("Introduce el DNI del profesor: ");
		dniProfesor = Entrada.cadena();
		System.out.println("");

		return Profesor.getProfesorFicticio(dniProfesor);
	}
	
	public static Tutoria leerTutoria() {
		String nombreTutoria;
		Profesor profesor = leerProfesorFicticio();
		System.out.println("*** DATOS TUTORÍA ***");
		System.out.println("Introduce el nombre de la tutoría: ");
		nombreTutoria = Entrada.cadena();
		System.out.println("");
		
		return new Tutoria(profesor,nombreTutoria);
	}
	
	public static Sesion leerSesion() {
		LocalDate fecha;
		LocalTime horaInicioSesion,horaFinSesion;
		int horaInicio,minInicio,horaFin,minFin;
		int annio,mes,dia,minDuracion;
		System.out.println("*** DATOS SESIÓN ***");
		Tutoria tutoria = leerTutoria();
		System.out.println("** Fecha **");
		do {
			System.out.println("Introduce el año");
			annio = Entrada.entero();
		} while (annio < 2020);

		do {
			System.out.println("Introduce el mes");
			mes = Entrada.entero();
		} while (mes < 1 || mes > 12);

		do {
			System.out.println("Introduce el dia");
			dia = Entrada.entero();
		} while (dia < 1 || dia > 31);
		
		fecha = LocalDate.of(annio, mes, dia);
		System.out.println("");
		
		System.out.println("** Hora Inicio Sesión **");
		do {
			System.out.println("Introduce la hora");
			horaInicio = Entrada.entero();
		} while ((horaInicio < 0 || horaInicio > 23));

		do {
			System.out.println("Introduce los minutos");
			minInicio = Entrada.entero();
		} while (minInicio < 0 || minInicio > 59);
		
		horaInicioSesion = LocalTime.of(horaInicio, minInicio);
		System.out.println("");
		
		System.out.println("** Hora Fin Sesión **");
		do {
			System.out.println("Introduce la hora");
			horaFin = Entrada.entero();
		} while ((horaFin < 0 || horaFin > 23));

		do {
			System.out.println("Introduce los minutos");
			minFin = Entrada.entero();
		} while (minFin < 0 || minFin > 59);
		
		horaFinSesion = LocalTime.of(horaFin, minFin);
		System.out.println("");
		
		System.out.println("** Minutos de duración **");
		do {
			System.out.println("Introduce los minutos de duración de la sesión: ");
			minDuracion = Entrada.entero();
		} while (minDuracion < 0 || minDuracion > 59);
		System.out.println("");
		
		return new Sesion(tutoria,fecha,horaInicioSesion,horaFinSesion,minDuracion);
		
	}
	
	public static Sesion leerSesionFicticia() {
		LocalDate fecha;
		int annio,mes,dia;
		System.out.println("*** DATOS SESIÓN ***");
		Tutoria tutoria = leerTutoria();
		System.out.println("** Fecha **");
		do {
			System.out.println("Introduce el año");
			annio = Entrada.entero();
		} while (annio < 2020);

		do {
			System.out.println("Introduce el mes");
			mes = Entrada.entero();
		} while (mes < 1 || mes > 12);

		do {
			System.out.println("Introduce el dia");
			dia = Entrada.entero();
		} while (dia < 1 || dia > 31);
		
		fecha = LocalDate.of(annio, mes, dia);
		System.out.println("");
		
		return Sesion.getSesionFicticia(tutoria, fecha);
	}
	
	public static Cita leerCita() {
		LocalTime horaCita;
		int hora,min;
		Alumno alumno = leerAlumnoFicticio();
		Sesion sesion = leerSesionFicticia();
		
		System.out.println("** Hora Cita **");
		do {
			System.out.println("Introduce la hora");
			hora = Entrada.entero();
		} while ((hora < 0 || hora > 23));

		do {
			System.out.println("Introduce los minutos");
			min = Entrada.entero();
		} while (min < 0 || min > 59);
		
		horaCita = LocalTime.of(hora, min);
		System.out.println("");
		
		return new Cita(alumno,sesion,horaCita);
	}

}
