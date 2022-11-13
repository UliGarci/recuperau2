package mx.edu.utez.recuperau2.model.calificacion;

import mx.edu.utez.recuperau2.model.docente.BeanDocente;
import mx.edu.utez.recuperau2.model.estudiante.BeanEstudiante;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeanCalificacion {
    @XmlElement
    private int id;
    @XmlElement
    private String matriculaestudiante;
    @XmlElement
    private String materia;
    @XmlElement
    private String calificacion;
    @XmlElement
    private BeanDocente numdocente;
    @XmlElement
    private BeanEstudiante nombre;

    public BeanCalificacion() {
    }

    public BeanCalificacion(int id, String matriculaestudiante, String materia, String calificacion, BeanDocente numdocente, BeanEstudiante nombre) {
        this.id = id;
        this.matriculaestudiante = matriculaestudiante;
        this.materia = materia;
        this.calificacion = calificacion;
        this.numdocente = numdocente;
        this.nombre = nombre;
    }

    public BeanEstudiante getNombre() {
        return nombre;
    }

    public void setNombre(BeanEstudiante nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatriculaestudiante() {
        return matriculaestudiante;
    }

    public void setMatriculaestudiante(String matriculaestudiante) {
        this.matriculaestudiante = matriculaestudiante;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public BeanDocente getNumdocente() {
        return numdocente;
    }

    public void setNumdocente(BeanDocente numdocente) {
        this.numdocente = numdocente;
    }
}
