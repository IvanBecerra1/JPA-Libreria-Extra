/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicio;

import libreria.entidad.Autor;
import libreria.persistencia.AutorDAO;

/**
 *
 * @author ivan
 */
public class ServicioAutor {
    private AutorDAO dao;
    
    public ServicioAutor() {
        dao = new AutorDAO();
    }
    
    public Autor crearAutor(String nombre, Boolean alta) throws Exception{
        try {
            if (nombre.isEmpty()) {
                throw new Exception("El nombre de autor se encuentra vacio.");
            }
            
            Autor classAutor = new Autor();
            
            classAutor.setNombre(nombre);
            classAutor.setAlta(alta);
            
            return classAutor;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
