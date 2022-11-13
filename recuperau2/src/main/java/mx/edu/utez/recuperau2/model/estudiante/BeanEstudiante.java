package mx.edu.utez.recuperau2.model.estudiante;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="estudianteBean")
public class BeanEstudiante {
    //(matrícula,Nombre, apellidos, fecha de nacimiento, curp)
    @XmlElement
    private String matricula;
    @XmlElement
    private String nombre;
    @XmlElement
    private String apellidos;
    @XmlElement
    private String fnac;
    @XmlElement
    private String curp;

    public BeanEstudiante() {
    }

    public BeanEstudiante(String matricula, String nombre, String apellidos, String fnac, String curp) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fnac = fnac;
        this.curp = curp;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFnac() {
        return fnac;
    }

    public void setFnac(String fnac) {
        this.fnac = fnac;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
}
