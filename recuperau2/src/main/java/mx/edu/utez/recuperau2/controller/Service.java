package mx.edu.utez.recuperau2.controller;

import mx.edu.utez.recuperau2.model.calificacion.BeanCalificacion;
import mx.edu.utez.recuperau2.model.calificacion.DaoCalificacion;
import mx.edu.utez.recuperau2.model.docente.BeanDocente;
import mx.edu.utez.recuperau2.model.docente.DaoDocente;
import mx.edu.utez.recuperau2.model.estudiante.BeanEstudiante;
import mx.edu.utez.recuperau2.model.estudiante.DaoEstudiante;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
public class Service {

    //MOSTRAR LISTA DE TODOS LOS DOCENTES
    @GET
    @Path("/docentes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BeanDocente> getteachers(){
        return new DaoDocente().getAllteacher();
    }

    //INSERTAR-AGREGAR NUEVO DOCENTE
    @POST
    @Path("/docentes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BeanDocente newteacher(BeanDocente beanDocente){
        BeanDocente docente = new DaoDocente().insertTeacher(
          beanDocente.getNombre(),
          beanDocente.getApellidos(),
                beanDocente.getFnac(),
                beanDocente.getCurp()
        );
        return docente;
    }

    //ACTUALIZAR INFORMACIÓN DEL DOCENTE
    @PUT
    @Path("/docentes/{numempleado}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateteacher(BeanDocente beanDocente,@PathParam("numempleado") int numempleado){
        String docente = new DaoDocente().updateteacher(
                numempleado,
                beanDocente.getNombre(),
                beanDocente.getApellidos(),
                beanDocente.getFnac(),
                beanDocente.getCurp());
        return docente;
    }

    //INSERTAR-AGREGAR NUEVA CALIFIACIÓN
    @POST
    @Path("/calif")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BeanCalificacion insertScore(BeanCalificacion beanCalificacion){
        BeanCalificacion calif = new DaoCalificacion().newScore(
                beanCalificacion.getMatriculaestudiante(),
                beanCalificacion.getMateria(),
                beanCalificacion.getCalificacion(),
                beanCalificacion.getId()
        );
        return calif;
    }

    //CONSULTAR TODOS LOS ESTUDIANTES
    @GET
    @Path("/estudiantes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BeanEstudiante> getstudents(){
        return new DaoEstudiante().getAllStudent();
    }

    //CONSULTAR CALIFICACIONES DE TODOS LOS ESTUDIANTES
    @GET
    @Path("/calif")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BeanCalificacion> getScores(){
        return new DaoCalificacion().getAllScores();
    }

    //CONSULTAR PROMEDIO GENERAL DE LOS ESTUDIANTES
    @GET
    @Path("/promedio")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllScore(){
        String prom = new DaoCalificacion().getGeneralScore();
        return prom;
    }

    //INSERTAR-AGREGAR NUEVO ESTUDIANTE
    @POST
    @Path("/estudiantes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BeanEstudiante insertStudent(BeanEstudiante beanEstudiante){
        BeanEstudiante student = new DaoEstudiante().insertStudent(
                beanEstudiante.getMatricula(),
                beanEstudiante.getNombre(),
                beanEstudiante.getApellidos(),
                beanEstudiante.getFnac(),
                beanEstudiante.getCurp()
        );
        return student;
    }

    //ACTUALIZAR ESTUDIANTE
    @PUT
    @Path("/estudiantes/{matricula}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateStudent(BeanEstudiante beanEstudiante,@PathParam("matricula") String matricula){
        return new DaoEstudiante().updatestudent(
                beanEstudiante.getMatricula(),
                beanEstudiante.getNombre(),
                beanEstudiante.getApellidos(),
                beanEstudiante.getFnac(),
                beanEstudiante.getCurp()
        );
    }

}
