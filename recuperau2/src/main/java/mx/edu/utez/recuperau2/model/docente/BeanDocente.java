package mx.edu.utez.recuperau2.model.docente;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="docenteBean")
public class BeanDocente {
    // (número de empleado,Nombre, apellidos, fecha de nacimiento, curp)
    @XmlElement
    private int numempleado;
    @XmlElement
    private String nombre;
    @XmlElement
    private String apellidos;
    @XmlElement
    private String fnac;
    @XmlElement
    private String curp;

    public BeanDocente() {
    }

    public BeanDocente(int numempleado, String nombre, String apellidos, String fnac, String curp) {
        this.numempleado = numempleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fnac = fnac;
        this.curp = curp;
    }

    public int getNumempleado() {
        return numempleado;
    }

    public void setNumempleado(int numempleado) {
        this.numempleado = numempleado;
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
