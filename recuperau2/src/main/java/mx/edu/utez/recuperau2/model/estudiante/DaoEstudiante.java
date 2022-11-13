package mx.edu.utez.recuperau2.model.estudiante;

import mx.edu.utez.recuperau2.connection.MySQLConnection;
import mx.edu.utez.recuperau2.model.docente.BeanDocente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEstudiante {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;


    private String CONSULTAR_ESTUDIANTES="SELECT * FROM estudiantes";
    private String REGISTRAR_ESTUDIANTE="INSERT INTO estudiantes (matricula,nombre,apellidos,fnac,curp) VALUES (?,?,?,?,?)";
    private String ACTUALIZAR_ESTUDIANTE="UPDATE estudiantes SET nombre=?,apellidos=?,fnac=?,curp=? WHERE matricula=?";

    public List<BeanEstudiante> getAllStudent(){
        List<BeanEstudiante> students = new ArrayList<>();
        try {
            con = MySQLConnection.getConnection();
            pstm=con.prepareStatement(CONSULTAR_ESTUDIANTES);
            rs=pstm.executeQuery();
            while (rs.next()){
                BeanEstudiante student = new BeanEstudiante();
                student.setMatricula(rs.getString("matricula"));
                student.setNombre(rs.getString("nombre"));
                student.setApellidos(rs.getString("apellidos"));
                student.setFnac(rs.getString("fnac"));
                student.setCurp(rs.getString("curp"));
                students.add(student);
            }
        }catch(SQLException e){
            System.out.println("Error en getAllStudent() -> "+e.getMessage());
        }finally {
            try{
                con.close();
                pstm.close();
                rs.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar conexiones -> "+e.getMessage());
            }
        }
        return students;
    }

    public BeanEstudiante insertStudent(String matricula,String nombre,String apellidos,String fnac,String curp){
        BeanEstudiante student = new BeanEstudiante();
        try{
            con = MySQLConnection.getConnection();
            pstm= con.prepareStatement(REGISTRAR_ESTUDIANTE);
            //if(validateCurp(curp)){
                pstm.setString(1,matricula);
                pstm.setString(2,nombre);
                pstm.setString(3,apellidos);
                pstm.setString(4,fnac);
                pstm.setString(5,curp);
                int result = pstm.executeUpdate();
                if(result==1){
                    student.setMatricula(matricula);
                    student.setNombre(nombre);
                    student.setApellidos(apellidos);
                    student.setFnac(fnac);
                    student.setCurp(curp);
                }else{
                    throw new SQLException("Error al insertar");
                }
            //}
        }catch (SQLException e){
            System.out.println("Error en insertStudent() -> "+e.getMessage());
        }finally {
            try{
                con.close();
                pstm.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar conexiones -> "+e.getMessage());
            }
        }
        return student;
    }

    //ACTUALIZAR INFORMACIÓN DE ESTUDIANTE
    public String updatestudent(String matricula,String nombre,String apellidos,String fnac,String curp){
        String mensaje="";
        try {
            pstm = con.prepareStatement(ACTUALIZAR_ESTUDIANTE);
            //if(validateCurp(curp)){
                pstm.setString(1,nombre);
                pstm.setString(2,apellidos);
                pstm.setString(3,fnac);
                pstm.setString(4,curp);
                pstm.setString(5,matricula);
                int result = pstm.executeUpdate();
                if(result==1){
                    mensaje="Actualizado con exito";
                }
            //}else{
             //   mensaje="CURP EXISTENTE";
           // }
        }catch (SQLException e) {
            System.out.println("Error en updateStudent() -> "+e.getMessage());
        }finally {
            try {
                con.close();
                pstm.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones -> "+e.getMessage());
            }
        }
        return mensaje;
    }


    public boolean validateCurp(String curp){
        boolean flag=true;
        try {
            con = MySQLConnection.getConnection();
            pstm = con.prepareStatement("SELECT curp FROM estudiantes");
            rs = pstm.executeQuery();
            while (rs.next()){
                BeanEstudiante beanEstudiante = new BeanEstudiante();
                String c = beanEstudiante.getCurp();
                if(curp.equals(c)){
                    flag=false;
                }
            }
        }catch(SQLException e){
            System.out.println("Error en validateCurp() -> "+e.getMessage());
        }finally {
            try {
                con.close();
                pstm.close();
                rs.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones -> "+e.getMessage());
            }
        }
        return flag;
    }

}
