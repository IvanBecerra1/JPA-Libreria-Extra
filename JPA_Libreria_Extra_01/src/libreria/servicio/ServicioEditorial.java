/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicio;

import libreria.persistencia.EditorialDAO;
import libreria.entidad.Editorial;

/**
 * @author ivan
 */
public class ServicioEditorial {
    private EditorialDAO dao;
    
    public ServicioEditorial() {
        dao = new EditorialDAO();
    }
    
    public Editorial crearEditorial(String nombre, Boolean alta) throws Exception {
        try {
            if (nombre.isEmpty()) {
                throw new Exception("Error, el nombre del editorial no puede ser vacio");
            }
            
            Editorial classEditorial = new Editorial();
            
            classEditorial.setNombre(nombre);
            classEditorial.setAlta(alta);
            
            return classEditorial;
        } catch (Exception ex){
            throw ex;
        }
    }
    
}
