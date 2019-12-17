/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

public class Libros {
    String dni;
    String apellido;
    String nombre;
    String fechaN;
    String telefono;
    String mail;
    String numero;
    String NombreLibro;
    String Editorial;
    String Autor;
    String Titulo;
    String Descripcion;
    String LugardEdicion;
    String Serie;
    String Tomo;
    String Alquilado;
    
    public Libros()
    {
        Autor = Titulo = Descripcion = LugardEdicion = Serie = Tomo = "";
        dni = apellido = nombre = fechaN = telefono = mail = numero = NombreLibro = Editorial = "";
    }

    public String getDni() {
        return dni;
    }

    public String getAlquilado() {
        return Alquilado;
    }

    public void setAlquilado(String Alquilado) {
        this.Alquilado = Alquilado;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaN() {
        return fechaN;
    }

    public void setFechaN(String fechaN) {
        this.fechaN = fechaN;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombreLibro() {
        return NombreLibro;
    }

    public void setNombreLibro(String NombreLibro) {
        this.NombreLibro = NombreLibro;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String Editorial) {
        this.Editorial = Editorial;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getLugardEdicion() {
        return LugardEdicion;
    }

    public void setLugardEdicion(String LugardEdicion) {
        this.LugardEdicion = LugardEdicion;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public String getTomo() {
        return Tomo;
    }

    public void setTomo(String Tomo) {
        this.Tomo = Tomo;
    }    
}
