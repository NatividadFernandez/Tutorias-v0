package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Sesion {

	private Tutoria tutoria;
	private static final LocalTime HORA_COMIENZO_CLASES = LocalTime.of(16, 00);
	private static final LocalTime HORA_FIN_CLASES = LocalTime.of(22, 15);
	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("hh:mm");
	private LocalDate fecha;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private int minutosDuracion;

	// Constructor con parámetros
	public Sesion(Tutoria tutoria, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, int minutosDuracion) {
		setTutoria(tutoria);
		setFecha(fecha);
		setHoraInicio(horaInicio);
		setHoraFin(horaFin);
		setMinutosDuracion(minutosDuracion);
		comprobarValidezSesion();

	}

	// Constructor de copia
	public Sesion(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: No es posible copiar una sesión nula.");
		}

		setTutoria(sesion.tutoria);
		setFecha(sesion.fecha);
		setHoraInicio(sesion.horaInicio);
		setHoraFin(sesion.horaFin);
		setMinutosDuracion(sesion.minutosDuracion);
	}

	// Getters y Setters
	public Tutoria getTutoria() {
		return new Tutoria(tutoria);
	}

	private void setTutoria(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
		}

		this.tutoria = new Tutoria(tutoria);
	}

	public LocalDate getFecha() {
		return fecha;
	}

	private void setFecha(LocalDate fecha) {
		if (fecha == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		} else if (fecha.equals(fecha.now())) {
			throw new IllegalArgumentException("ERROR: Las sesiones de deben planificar para fechas futuras.");
		}

		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	private void setHoraInicio(LocalTime horaInicio) {
		if (horaInicio == null) {
			throw new NullPointerException("ERROR: La hora de inicio no puede ser nula.");
		} /*
			 * else if (horaInicio.isBefore(HORA_COMIENZO_CLASES) ||
			 * horaInicio.equals(HORA_FIN_CLASES) || horaInicio.isAfter(HORA_FIN_CLASES)) {
			 * throw new IllegalArgumentException("ERROR: La hora de inicio no es válida.");
			 * }
			 */

		// comprobarValidezSesion();

		this.horaInicio = horaInicio;

	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	private void setHoraFin(LocalTime horaFin) {
		if (horaFin == null) {
			throw new NullPointerException("ERROR: La hora de fin no puede ser nula.");
		} /*
			 * else if (horaFin.isBefore(HORA_COMIENZO_CLASES) ||
			 * horaFin.isAfter(HORA_FIN_CLASES)) { throw new
			 * IllegalArgumentException("ERROR: La hora de fin no es válida."); } else if
			 * (horaFin.equals(this.horaInicio) ||
			 * horaFin.isBefore(this.horaInicio.plusMinutes(1))) { throw new
			 * IllegalArgumentException("ERROR: Las hora para establecer la sesión no son válidas."
			 * ); }
			 */

		// comprobarValidezSesion();

		this.horaFin = horaFin;
	}

	public int getMinutosDuracion() {
		return minutosDuracion;
	}

	private void setMinutosDuracion(int minutosDuracion) {
		if (minutosDuracion == 0) {
			throw new IllegalArgumentException("ERROR: Los minutos de duración no son válidos.");
		}

		/*
		 * int operacion; operacion = this.horaFin.getHour() -
		 * this.horaInicio.getHour();
		 * 
		 * int resto; resto = minutosDuracion % operacion;
		 * 
		 * if (resto == 0) { this.minutosDuracion = minutosDuracion; } else { throw new
		 * IllegalArgumentException(
		 * "ERROR: Los minutos de duración no es divisor de los minutos establecidos para toda la sesión."
		 * ); }
		 */

		this.minutosDuracion = minutosDuracion;

	}

	// Validez Sesion
	private void comprobarValidezSesion() {

		if (this.horaInicio.isBefore(HORA_COMIENZO_CLASES) || this.horaInicio.equals(HORA_FIN_CLASES)
				|| this.horaInicio.isAfter(HORA_FIN_CLASES)) {
			throw new IllegalArgumentException("ERROR: La hora de inicio no es válida.");
		}

		if (this.horaFin.isBefore(HORA_COMIENZO_CLASES) || this.horaFin.equals(HORA_COMIENZO_CLASES)
				|| this.horaFin.isAfter(HORA_FIN_CLASES)) {
			throw new IllegalArgumentException("ERROR: La hora de fin no es válida.");
		}

		if (this.horaFin.equals(this.horaInicio) || this.horaFin.isBefore(this.horaInicio.plusMinutes(1))) {
			throw new IllegalArgumentException("ERROR: Las hora para establecer la sesión no son válidas.");
		}

		//DUDAS
	/*	int operacion, resto;
		operacion = this.horaFin.getHour() - this.horaInicio.getHour();

		resto = minutosDuracion % operacion;

		if (resto != 0) {
			throw new IllegalArgumentException(
					"ERROR: Los minutos de duración no es divisor de los minutos establecidos para toda la sesión.");

		}*/
		
		if (((this.horaFin.toSecondOfDay() - this.horaInicio.toSecondOfDay()) / 60) % this.minutosDuracion != 0) {
            throw new IllegalArgumentException("ERROR: Los minutos de duración no es divisor de los minutos establecidos para toda la sesión.");
        }

	}

	// Sesion ficticia
	public static Sesion getSesionFicticia(Tutoria tutoria, LocalDate fecha) {
		return new Sesion(tutoria, fecha, LocalTime.of(16, 0), LocalTime.of(19, 0), 30);
	}

	// hasCode y Equals
	@Override
	public int hashCode() {
		return Objects.hash(fecha, tutoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Sesion)) {
			return false;
		}
		Sesion other = (Sesion) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(tutoria, other.tutoria);
	}

	// toString
	@Override
	public String toString() {
		return String.format("tutoria=%s, fecha=%s, horaInicio=%s, horaFin=%s, minutosDuracion=%d", tutoria,
				fecha.format(Sesion.FORMATO_FECHA), horaInicio.format(Sesion.FORMATO_HORA),
				horaFin.format(Sesion.FORMATO_HORA), minutosDuracion);
	}

}
