/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Vista.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class ModeloBiblio {
    Conexion conexion;
    Agregar adder = new Agregar();
    Vista vist = new Vista();
    AddSoc socio = new AddSoc();
    public ModeloBiblio(){
       conexion = new Conexion();
    }
    
    public String InsertarLibro(String Autor, String Titulo, String LugardEdic, String Editorial, String Serie, String Tomo, String Descripcion)
    {
        String rptaInsert = null;
        try{
                Connection accesDB = conexion.getConexion();
                PreparedStatement ps = accesDB.prepareStatement("INSERT INTO libros (`Autor`, `Titulo`, `LugarEdit`, `Editorial`, `Serie`, `Tomo`, `Descripcion`) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, Autor);
                ps.setString(2, Titulo);
                ps.setString(3, LugardEdic);
                ps.setString(4, Editorial);
                ps.setString(5, Serie);
                ps.setString(6, Tomo);
                ps.setString(7, Descripcion);
                int Filas = ps.executeUpdate();
            
                if (Filas>0){
                    rptaInsert = "Libro agregado exitosamente.";
                    adder.dispose();
                    vist.setEnabled(true);
                }
        }
        catch(Exception e){
            
        }
        return rptaInsert;
    }
    
    public String AlquilarLibro(String dni, String numero, String fecha){
        String alquilar = null;
        try{
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("SELECT Alquilado FROM libros WHERE ID = ?");
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            
            String alquilado;
            while (rs.next()){
                alquilado = rs.getString(1);
                
                if (alquilado.equals("No")){
                    PreparedStatement cs = accesoDB.prepareStatement("INSERT INTO `alquileres`(`DNI`, `Numero`, `Fecha`) VALUES (?, ?, ?)");
                    cs.setString(1, dni);
                    cs.setString(2, numero);
                    cs.setString(3, fecha);
                    int numFAfectadas = cs.executeUpdate(); 

                    if (numFAfectadas>0){
                        PreparedStatement css = accesoDB.prepareStatement("UPDATE libros SET Alquilado = 'Si' WHERE ID = ?");
                        css.setString(1, numero);
                        css.executeUpdate();
                        alquilar = "Libro alquilado.";
                    }
                }
                else{
                    alquilar = "Este libro no se encuentra disponible";
                }
            }
        }catch (Exception e){
            
        }
        return alquilar;
    }
    
    public String DevolverLibro(String Socdni, String libro){
        String devolver = null;
        try{
            Connection accesoDB = conexion.getConexion();
            PreparedStatement cs = accesoDB.prepareStatement("UPDATE `alquileres` SET Alquilado='No' WHERE DNI = ? && Numero = ?");
            cs.setString(1, Socdni);
            cs.setString(2, libro);
            int numFAfectadas = cs.executeUpdate();
            
            if (numFAfectadas>0){
                PreparedStatement css = accesoDB.prepareStatement("UPDATE `libros` SET `Alquilado` = 'No' WHERE ID = ?");
                css.setString(1, libro);
                css.executeUpdate();
                devolver = "Libro devuelto exitosamente.";
            }
        }catch(Exception e){
            
        }
        return devolver;
    }
    
    public String insertPersona(String dni, String apellido, String nombre, String fechaN, String telefono, String mail){
        String rptaRegistro = null;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement cs = accesoDB.prepareStatement("INSERT INTO socios (`DNI`, `Apellido`, `Nombre`, `Fecha`, `Telefono`, `Mail`) VALUES (?, ?, ?, ?, ?, ?)");
            cs.setString(1, dni);
            cs.setString(2, apellido);
            cs.setString(3, nombre);
            cs.setString(4, fechaN);
            cs.setString(5, telefono);
            cs.setString(6, mail);
            
            int numFAfectadas = cs.executeUpdate();
            
            if (numFAfectadas>0){
                // SendMail(mail);
                rptaRegistro = "Registro exitoso.";
            }
        } catch (Exception e){
        }
         return rptaRegistro;
    }
    
    public ArrayList<Libros> listLibros(){
        ArrayList listaLibros = new ArrayList();
        Libros libros;        
        try {
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT * FROM libros");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                libros = new Libros();
                libros.setNumero(rs.getString(1));
                libros.setAutor(rs.getString(2));
                libros.setTitulo(rs.getString(3));
                libros.setLugardEdicion(rs.getString(4));
                libros.setEditorial(rs.getString(5));
                libros.setSerie(rs.getString(6));
                libros.setTomo(rs.getString(7));
                libros.setDescripcion(rs.getString(8));
                libros.setAlquilado(rs.getString(9));
                listaLibros.add(libros);
            }
        } catch (Exception e){
        }
        return listaLibros;
    }
    
    
     public ArrayList<Libros> buscarLibros(String libro){
       ArrayList listaLibros = new ArrayList();
       Libros libros; 
       try{
           Connection acceDB = conexion.getConexion();
           PreparedStatement cs = acceDB.prepareStatement("SELECT * FROM libros WHERE Titulo LIKE concat(p_nombre,'%') AND Alquilado='No'");
           cs.setString(1, libro);
           ResultSet rs = cs.executeQuery();
           while (rs.next()) {
               libros = new Libros();
               libros.setNumero(rs.getString(1));
               libros.setAutor(rs.getString(2));
               libros.setTitulo(rs.getString(3));
               libros.setLugardEdicion(rs.getString(4));
               libros.setEditorial(rs.getString(5));
               libros.setSerie(rs.getString(6));
               libros.setTomo(rs.getString(7));
               libros.setDescripcion(rs.getString(8));
               libros.setAlquilado(rs.getString(9));
               listaLibros.add(libros);
           }
       }
       catch (Exception e){
       }
       return listaLibros;
   }
     
}
