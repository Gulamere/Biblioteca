/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import Controlador.*;
import Modelo.*;
import Vista.*;

/**
 *
 * @author Lidia
 */
public class Biblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vista VistaC = new Vista();
        ModeloBiblio modeloC = new ModeloBiblio();
        Agregar AddC = new Agregar();
        AddSoc Socio = new AddSoc();
        AddAlq Alq = new AddAlq();
        Devolver dev = new Devolver();
    
        Controlador controlaC = new Controlador(VistaC, modeloC, AddC, Alq, Socio, dev);
        
        VistaC.setVisible(true);
        VistaC.setLocationRelativeTo(null);
        AddC.setVisible(false);
        }
    
}
