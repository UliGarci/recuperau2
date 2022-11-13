package mx.edu.utez.recuperau2.model.calificacion;

import mx.edu.utez.recuperau2.connection.MySQLConnection;
import mx.edu.utez.recuperau2.model.docente.BeanDocente;
import mx.edu.utez.recuperau2.model.docente.DaoDocente;
import mx.edu.utez.recuperau2.model.estudiante.BeanEstudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCalificacion {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    private String REGISTRAR_CALIFIACION="INSERT INTO calificaciones (matriculaestudiante,materia,calificacion,numdocente)";
    private String CONSULTAR_CALIFICACIONES="SELECT es.nombre,ca.materia,ca.calificacion FROM calificaciones ca INNER JOIN estudiantes es ON es.matricula=ca.matriculaestudiante";
    private String CONSULTAR_PROMEDIO_GENERAL="SELECT calificacion FROM calificaciones";

    //REGISTRAR NUEVA CALIFICACION
    public BeanCalificacion newScore(String matricula, String materia, String calificacion, int numempleado){
        BeanCalificacion cali = new BeanCalificacion();
        BeanDocente beanDocente = new BeanDocente();
        try {
            con = MySQLConnection.getConnection();
            pstm=con.prepareStatement(REGISTRAR_CALIFIACION);
            pstm.setString(1,matricula);
            pstm.setString(2,materia);
            pstm.setString(3,calificacion);
            pstm.setInt(4,numempleado);
            int result = pstm.executeUpdate();
            if(result==1){
                try {
                    ResultSet ultimoId = pstm.getGeneratedKeys();
                    if(ultimoId.next()){
                        cali.setMatriculaestudiante(matricula);
                        cali.setMateria(materia);
                        cali.setCalificacion(calificacion);
                        beanDocente.setNumempleado(numempleado);
                        cali.setNumdocente(beanDocente);
                        cali.setId(ultimoId.getInt(1));
                    }else{
                        System.out.println("No se encontro registro");
                    }
                }catch (SQLException e){
                    System.out.println("Valio ultimo ID -> "+e.getMessage());
                }
            }else{
                throw new SQLException("Error al insertar");
            }
        }catch (SQLException e){
            System.out.println("Error en newScore() -> "+e.getMessage());
        }finally {
            try {
                con.close();
                pstm.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones -> "+e.getMessage());
            }
        }
        return cali;
    }

    //OBTENER LISTADO DE CALIFICACIONES
    public List<BeanCalificacion> getAllScores(){
        List<BeanCalificacion> calificaciones = new ArrayList<>();
        try{
            con=MySQLConnection.getConnection();
            pstm=con.prepareStatement(CONSULTAR_CALIFICACIONES);
            rs=pstm.executeQuery();
            while (rs.next()){
                BeanCalificacion calificacion = new BeanCalificacion();
                BeanEstudiante estudiante = new BeanEstudiante();
                //BeanDocente docente = new BeanDocente();
                //calificacion.setId(rs.getInt("id"));
                //calificacion.setMatriculaestudiante(rs.getString("matriculaestudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                calificacion.setNombre(estudiante);
                calificacion.setMateria(rs.getString("materia"));
                calificacion.setCalificacion(rs.getString("calificacion"));
                //docente.setNumempleado(rs.getInt("numempleado"));
                //calificacion.setNumdocente(docente);
                calificaciones.add(calificacion);
            }
        }catch (SQLException e){
            System.out.println("Error en getAllScores() -> "+e.getMessage());
        }finally {
            try{
                con.close();
                pstm.close();
                rs.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar conexiones -> "+e.getMessage());
            }
        }
        return calificaciones;
    }

    public String getGeneralScore(){
        String generalScore="";
        int sum=0,cant=0;
        try {
            con = MySQLConnection.getConnection();
            pstm=con.prepareStatement(CONSULTAR_PROMEDIO_GENERAL);
            rs=pstm.executeQuery();
            while (rs.next()){
                cant++;
                BeanCalificacion calificacion = new BeanCalificacion();
                if(calificacion.getCalificacion().equals("AU")){
                    //AU=10
                    sum=sum+10;
                }else if(calificacion.getCalificacion().equals("DE")){
                    //DE=9
                    sum=sum+9;
                }else if(calificacion.getCalificacion().equals("SA")){
                    //SA=8
                    sum=sum+8;
                }
            }
            sum=sum/cant;
            if(sum==10){
                generalScore="Promedio general = AU -> 10";
            }else if(sum>=9 && sum<10){
                generalScore="Promedio general = DE -> 9";
            }else if(sum>=8 && sum<9){
                generalScore="Promedio general = SA -> 8";
            }else if(sum<8){
                generalScore="Promedio general = NA";
            }
        }catch (SQLException e){
            System.out.println("Error en getGeneralScore() -> "+e.getMessage());
        }
        return generalScore;
    }
}
