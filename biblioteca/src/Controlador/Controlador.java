/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import Modelo.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Lidia
 */
public class Controlador implements ActionListener, KeyListener, WindowListener{
    Vista vista = new Vista();
    Agregar agregar = new Agregar();
    ModeloBiblio model = new ModeloBiblio();
    AddAlq alquiler = new AddAlq();
    AddSoc socio = new AddSoc();
    Devolver devolver = new Devolver();
    
    public Controlador (Vista vista, ModeloBiblio model, Agregar adder, AddAlq alquilo, AddSoc soc, Devolver dev){
        this.model = model;
        this.vista = vista;
        this.agregar = adder;
        this.alquiler = alquilo;
        this.socio = soc;
        this.devolver = dev;
        
        // agregar libro botones
        this.agregar.BtnAddAgregar.addActionListener(this);
        this.agregar.BtnAddCancel.addActionListener(this);
        
        // agregar socio botones
        this.socio.SocAgregar.addActionListener(this);
        this.socio.SocCancel.addActionListener(this);
        
        // ventana principal botones
        this.vista.BtnAdd.addActionListener(this);
        this.vista.BtnAddSoc.addActionListener(this);
        this.vista.BtnAlquiler.addActionListener(this);
        this.vista.BtnDevolucion.addActionListener(this);
        this.vista.BtnListar.addActionListener(this);
        
        // devolver libro botones
        this.devolver.DevAceptar.addActionListener(this);
        this.devolver.DevCancelar.addActionListener(this);
        
        // alquilar botones
        this.alquiler.Alquilar.addActionListener(this);
        
        // Texto de buscar
        this.vista.jTextField1.addKeyListener(this);
        
        // Texto de dni
        this.socio.SocDNI.addKeyListener(this);
        
        // accion al apretar boton 'cancelar' o al cerrar ventana
        agregar.setDefaultCloseOperation(Agregar.DISPOSE_ON_CLOSE);
        socio.setDefaultCloseOperation(AddSoc.DISPOSE_ON_CLOSE);
        devolver.setDefaultCloseOperation(Devolver.DISPOSE_ON_CLOSE);
        alquiler.setDefaultCloseOperation(AddAlq.DISPOSE_ON_CLOSE);
    }
    
    public void Iniciar(){
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == vista.BtnAdd){
           this.agregar.setVisible(true);
           this.agregar.setEnabled(true);
           this.agregar.setLocationRelativeTo(null);
        }
        if (e.getSource() == vista.BtnAddSoc){
            this.socio.setVisible(true);
            this.socio.setEnabled(true);
            this.socio.setLocationRelativeTo(null);
        }
        if (e.getSource() == vista.BtnDevolucion){
            this.devolver.setVisible(true);
            this.devolver.setEnabled(true);
            this.devolver.setLocationRelativeTo(null);
        }
        if (e.getSource() == vista.BtnAlquiler){
            int filaEditar = vista.jTable1.getSelectedRow();
            int numFS = vista.jTable1.getSelectedRowCount();
            
            if (filaEditar>=0 && numFS == 1){
                this.alquiler.setVisible(true);
                this.alquiler.setEnabled(true);
                this.alquiler.setLocationRelativeTo(null);
                alquiler.AlqLibro.setText(String.valueOf(vista.jTable1.getValueAt(filaEditar, 0)));
                alquiler.AlqLibro.setEditable(false);
                
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar un libro.");
            }
        }
        if (e.getSource() == vista.BtnListar){
            LlenarTabla(vista.jTable1);
        }
        if (e.getSource() == agregar.BtnAddAgregar){
            String Autor = agregar.AddAut.getText();
            String Titulo = agregar.AddTitle.getText();
            String LugardEdic = agregar.AddLugar.getText();
            String Editorial = agregar.AddEditorial.getText();
            String Serie = agregar.AddSerie.getText();
            String Tomo = agregar.AddTomo.getText();
            String Descripcion = agregar.AddDescrition.getText();
            
            String rptaInsert = model.InsertarLibro(Autor, Titulo, LugardEdic, Editorial, Serie, 
                    Tomo, Descripcion);
            
            if (rptaInsert != null){
                JOptionPane.showMessageDialog(null, rptaInsert);
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro erroneo");
            }
        }
        if (e.getSource() == agregar.BtnAddCancel)
        {
            this.agregar.dispose();
        }
        if (e.getSource() == socio.SocAgregar){
            String dni = socio.SocDNI.getText();
            String nombre = socio.SocNombre.getText();
            String apellido = socio.SocApellido.getText();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formatoFecha.format(socio.SocFechaN.getDate());
            String telefono = socio.SocTelefono.getText();
            String mail = socio.SocMail.getText();
            
            String rptaSocio = model.insertPersona(dni, apellido, nombre, fecha, telefono, mail);
            
            if (rptaSocio != null){
                JOptionPane.showMessageDialog(null, rptaSocio);
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro erroneo");
            }
        }
        if (e.getSource() == socio.SocCancel){
            this.socio.dispose();
        }
        if(e.getSource() == devolver.DevAceptar){
            String socdni = devolver.DevSoc.getText();
            String libro = devolver.DevNumero.getText();
            
            String devolver = model.DevolverLibro(socdni, libro);
            
            if(devolver != null){
                JOptionPane.showMessageDialog(null, devolver);
            }
            else{
                JOptionPane.showMessageDialog(null, "No hay registro de este alquiler.");
            }
        }
        if (e.getSource() == devolver.DevCancelar){
            this.devolver.dispose();
        }
        if (e.getSource() == alquiler.Alquilar){
            String dni = alquiler.AlqDNI.getText();
            String libroN = alquiler.AlqLibro.getText();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fechaL = formatoFecha.format(alquiler.AlqFecha.getDate()); 
            
            String alquilar = model.AlquilarLibro(dni, libroN, fechaL);
            
            if (alquilar != null){
                JOptionPane.showMessageDialog(null, alquilar);
            }else {
                JOptionPane.showMessageDialog(null, "No es posible de alquilar.");
            }
        }
    }
    
    public void LlenarTabla (JTable tablaD) {
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        
        modeloT.addColumn("N° de inventario");
        modeloT.addColumn("Autor");
        modeloT.addColumn("Título");
        modeloT.addColumn("Lugar de edición");
        modeloT.addColumn("Editorial");
        modeloT.addColumn("Serie");
        modeloT.addColumn("Tomo o volumen");
        modeloT.addColumn("Descripción(tamaño)");
        modeloT.addColumn("Alquilado");
        
        Object[] columna = new Object[9];
        
        int numRegistros = model.listLibros().size();
        for(int i = 0; i < numRegistros ; i++){
            columna[0] = model.listLibros().get(i).getNumero();
            columna[1] = model.listLibros().get(i).getAutor();
            columna[2] = model.listLibros().get(i).getTitulo();
            columna[3] = model.listLibros().get(i).getLugardEdicion();
            columna[4] = model.listLibros().get(i).getEditorial();
            columna[5] = model.listLibros().get(i).getSerie();
            columna[6] = model.listLibros().get(i).getTomo();
            columna[7] = model.listLibros().get(i).getDescripcion();
            columna[8] = model.listLibros().get(i).getAlquilado();
            modeloT.addRow(columna);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.jTextField1){
            String libros = vista.jTextField1.getText();
            DefaultTableModel modeloT = new DefaultTableModel();
            vista.jTable1.setModel(modeloT);

            modeloT.addColumn("N° de inventario");
            modeloT.addColumn("Autor");
            modeloT.addColumn("Título");
            modeloT.addColumn("Lugar de edición");
            modeloT.addColumn("Editorial");
            modeloT.addColumn("Serie");
            modeloT.addColumn("Tomo o volumen");
            modeloT.addColumn("Descripción(tamaño)");
            modeloT.addColumn("Alquilado");
        
            Object[] columna = new Object[9];
        
            int numRegistros = model.buscarLibros(libros).size();
            for(int i = 0; i < numRegistros ; i++){
                columna[0] = model.buscarLibros(libros).get(i).getNumero();
                columna[1] = model.buscarLibros(libros).get(i).getAutor();
                columna[2] = model.buscarLibros(libros).get(i).getTitulo();
                columna[3] = model.buscarLibros(libros).get(i).getLugardEdicion();
                columna[4] = model.buscarLibros(libros).get(i).getEditorial();
                columna[5] = model.buscarLibros(libros).get(i).getSerie();
                columna[6] = model.buscarLibros(libros).get(i).getTomo();
                columna[7] = model.buscarLibros(libros).get(i).getDescripcion();
                columna[8] = model.listLibros().get(i).getAlquilado();
                modeloT.addRow(columna);
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}