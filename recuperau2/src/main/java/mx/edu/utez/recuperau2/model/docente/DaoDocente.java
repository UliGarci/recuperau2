package mx.edu.utez.recuperau2.model.docente;

import mx.edu.utez.recuperau2.connection.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoDocente {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    private String CONSULTAR_DOCENTE="SELECT numempleado FROM docentes WHERE numempleado=?";
    private String CONSULTAR_DOCENTES="SELECT * FROM docentes";
    private String REGISTRAR_DOCENTE="INSERT INTO docentes (numempleado,nombre,apellidos,fnac,curp) VALUES (0,?,?,?,?)";
    private String ACTUALIZAR_DOCENTE="UPDATE docentes SET nombre=?,apellidos=?,fnac=?,curp=? WHERE numempleado=?";


    public List<BeanDocente> getAllteacher(){
        List<BeanDocente> docentes = new ArrayList<>();
        try{
            con = MySQLConnection.getConnection();
            pstm = con.prepareStatement(CONSULTAR_DOCENTES);
            rs=pstm.executeQuery();
            while (rs.next()){
                BeanDocente docente = new BeanDocente();
                docente.setNumempleado(rs.getInt("numempleado"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellidos(rs.getString("apellidos"));
                docente.setFnac(rs.getString("fnac"));
                docente.setCurp(rs.getString("curp"));
                docentes.add(docente);
            }
        }catch(SQLException e){
            System.out.println("Error en getAllteacher() de DaoDocente -> "+e.getMessage());
        }finally {
            try {
                con.close();
                pstm.close();
                rs.close();
            }catch(SQLException e) {
                System.out.println("Error al cerrar conexiones -> "+e.getMessage());
            }
        }
        return docentes;
    }

    public BeanDocente insertTeacher(String nombre,String apellidos, String fnac, String curp){
        BeanDocente docente = new BeanDocente();
        try {
            con = MySQLConnection.getConnection();
            pstm = con.prepareStatement(REGISTRAR_DOCENTE);
            //if(validateCurp(curp)){
                pstm.setString(1,nombre);
                pstm.setString(2,apellidos);
                pstm.setString(3,fnac);
                pstm.setString(4,curp);
                int result = pstm.executeUpdate();
                if(result==1){
                    docente.setNombre(nombre);
                    docente.setApellidos(apellidos);
                    docente.setFnac(fnac);
                    docente.setCurp(curp);

                }else{
                    throw new SQLException("Error al insertar");
                }
            //}else{
              //  System.out.println("CURP EXISTENTE");
            //}
        }catch(SQLException e){
            System.out.println("Error en insertTeacher() en DaoDocente -> "+e.getMessage());
        }finally {
            try {
                con.close();
                pstm.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones -> "+e.getMessage());
            }
        }
        return docente;
    }

    public String updateteacher(int numempleado,String nombre,String apellidos,String fnac,String curp){
        String mensaje="";
        try {
            pstm = con.prepareStatement(ACTUALIZAR_DOCENTE);
            //if(validateCurp(curp)){
                pstm.setString(1,nombre);
                pstm.setString(2,apellidos);
                pstm.setString(3,fnac);
                pstm.setString(4,curp);
                pstm.setInt(5,numempleado);
                int result = pstm.executeUpdate();
                if(result==1){
                    mensaje="Actualizado con exito";
                }
            //}else{
             //   mensaje="CURP EXISTENTE";
           // }
        }catch (SQLException e) {
            System.out.println("Error en updateteacher() -> "+e.getMessage());
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

    /*
    public BeanDocente searchteacher(int id){
        BeanDocente docente = new BeanDocente();
        try {
            con=MySQLConnection.getConnection();
            pstm=con.prepareStatement(CONSULTAR_DOCENTE);
            pstm.setInt(1,id);
            rs=pstm.executeQuery();
            while (rs.next()){
                docente.setNumempleado(rs.getInt("numempleado"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellidos(rs.getString("apellidos"));
                docente.setFnac(rs.getString("fnac"));
                docente.setCurp(rs.getString("curp"));
            }
        }catch(SQLException e){
            System.out.println("Error en searchteacher() -> "+e.getMessage());
        }finally {
            try{
                rs.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones -> "+e.getMessage());
            }
        }
        return docente;
    }
    */
    public boolean validateCurp(String curp){
        boolean flag=true;
        try {
            con = MySQLConnection.getConnection();
            pstm = con.prepareStatement("SELECT curp FROM docentes");
            rs = pstm.executeQuery();
            while (rs.next()){
                BeanDocente beanDocente = new BeanDocente();
                String c = beanDocente.getCurp();
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
