package org.iesalandalus.programacion.tutorias.mvc.modelo;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Citas;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Sesiones;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Tutorias;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ModeloTest {
	
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	
	private Alumno alumno;
	private Profesor profesor;
	private Tutoria tutoria;
	private Sesion sesion;
	private Cita cita;
	
	@InjectMocks private static Modelo modelo;

	@Mock private Alumnos alumnosSimulados;
	@Mock private Profesores profesoresSimulados;
	@Mock private Tutorias tutoriasSimuladas;
	@Mock private Sesiones sesionesSimuladas;
	@Mock private Citas citasSimuladas;
	
	@Before
	public void asignaValoresAtributos() {
		MockitoAnnotations.initMocks(this);
		alumno = Alumno.getAlumnoFicticio("bob@gmail.com");
		profesor = Profesor.getProfesorFicticio("11223344B");
		tutoria = new Tutoria(profesor, "Dudas PROG05");
		sesion = Sesion.getSesionFicticia(tutoria, LocalDate.now().plusDays(1));
		cita = new Cita(alumno, sesion, LocalTime.of(16, 0));
	}

	@Test
	public void insertarAlumnoLlamaAlumnosInsertar() {
		try {
			modelo.insertar(alumno);
			verify(alumnosSimulados).insertar(alumno);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarAlumnoLlamaAlumnosBuscar() {
		modelo.buscar(alumno);
		verify(alumnosSimulados).buscar(alumno);
	}
	
	@Test
	public void borrarAlumnoLlamaAlumnosBorrar() {
		try {
			modelo.borrar(alumno);
			verify(alumnosSimulados).borrar(alumno);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getAlumnosLlamaAlumnosGet() {
		modelo.getAlumnos();
		verify(alumnosSimulados).get();
	}
	
	@Test
	public void insertarProfesorLlamaProfesoresInsertar() {
		try {
			modelo.insertar(profesor);
			verify(profesoresSimulados).insertar(profesor);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarProfesorLlamaProfesoresBuscar() {
		modelo.buscar(profesor);
		verify(profesoresSimulados).buscar(profesor);
	}
	
	@Test
	public void borrarProfesorLlamaProfesoresBorrar() {
		try {
			modelo.borrar(profesor);
			verify(profesoresSimulados).borrar(profesor);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getProfesoresLlamaProfesoresGet() {
		modelo.getProfesores();
		verify(profesoresSimulados).get();
	}
	
	@Test
	public void insertarTutoriaLlamaTutoriasInsertar() {
		try {
			modelo.insertar(tutoria);
			verify(tutoriasSimuladas).insertar(tutoria);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarTutoriaLlamaTutoriasBuscar() {
		modelo.buscar(tutoria);
		verify(tutoriasSimuladas).buscar(tutoria);
	}
	
	@Test
	public void borrarTutoriaLlamaTutoriasBorrar() {
		try {
			modelo.borrar(tutoria);
			verify(tutoriasSimuladas).borrar(tutoria);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getTutoriasLlamaTutoriasGet() {
		modelo.getTutorias();
		verify(tutoriasSimuladas).get();
	}
	
	@Test
	public void getTutoriasProfesorLlamaTutoriasGetConParametroProfesor() {
		modelo.getTutorias(profesor);
		verify(tutoriasSimuladas).get(profesor);
	}
	
	@Test
	public void insertarSesionLlamaSesionesInsertar() {
		try {
			modelo.insertar(sesion);
			verify(sesionesSimuladas).insertar(sesion);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarSesionLlamaSesionesBuscar() {
		modelo.buscar(sesion);
		verify(sesionesSimuladas).buscar(sesion);
	}
	
	@Test
	public void borrarSesionLlamaSesionesBorrar() {
		try {
			modelo.borrar(sesion);
			verify(sesionesSimuladas).borrar(sesion);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getSesionesLlamaSesionesGet() {
		modelo.getSesiones();
		verify(sesionesSimuladas).get();
	}
	
	@Test
	public void getSesionesTutoriaLlamaSesionesGetConParametroTutoria() {
		modelo.getSesiones(tutoria);
		verify(sesionesSimuladas).get(tutoria);
	}
	
	@Test
	public void insertarCitaLlamaCitasInsertar() {
		try {
			modelo.insertar(cita);
			verify(citasSimuladas).insertar(cita);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarCitaLlamaCitasBuscar() {
		modelo.buscar(cita);
		verify(citasSimuladas).buscar(cita);
	}
	
	@Test
	public void borrarCitaLlamaCitasBorrar() {
		try {
			modelo.borrar(cita);
			verify(citasSimuladas).borrar(cita);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getCitasLlamaCitasGet() {
		modelo.getCitas();
		verify(citasSimuladas).get();
	}
	
	@Test
	public void getCitasAlumnoLlamaCitasGetConParametroAlumno() {
		modelo.getCitas(alumno);
		verify(citasSimuladas).get(alumno);
	}
	
	@Test
	public void getCitasSesionLlamaCitasGetConParametroSesion() {
		modelo.getCitas(sesion);
		verify(citasSimuladas).get(sesion);
	}
	
}
