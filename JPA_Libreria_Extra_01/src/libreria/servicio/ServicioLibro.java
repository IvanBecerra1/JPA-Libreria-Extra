/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicio;

// entidades
import java.util.Collection;
import libreria.entidad.Libro;
import libreria.entidad.Autor;
import libreria.entidad.Editorial;

// dao
import libreria.persistencia.LibroDAO;

/**
 *
 * @author ivan
 */
public class ServicioLibro {
    private LibroDAO libroDAO;
    
    public ServicioLibro() {
        libroDAO = new LibroDAO();
    }
    
    /**
     *  private Long isbn;
     */
    public void insertarLibro(Long ISB, String titulo, Integer ejemplares, Boolean alta, Autor classAutor, Editorial classEditorial ) throws Exception {
        try {
            Libro classLibro = new Libro();
            
            /**
            Chequeos de comprobacion
            * ejemplo: Que el ISBN sea unico
            * Que el titulo no se encuentre en uso
            * etc...
             */
            
            if (titulo.isEmpty()) {
                 throw new Exception("El titulo esta vacio");
            }
            
            if (ejemplares  <= 0) {
                throw new Exception("La cantidad de ejemplares no puede estar vacio");
            }
            /*Chequeo de ISBN*/
            if (libroDAO.comprobarISBN(ISB) == false) {
                throw new Exception("El codigo ISBN ya existe, ingrese uno distinto.");
            }
            
            if (ISB <= 0) {
                throw new Exception("El codigo ISBN no puede estar vacio");
            }
            
            /*Chequeo de titulo*/
            if (libroDAO.comprobarTitulo(titulo) == false) {
                throw new Exception("El titulo ya existe, ingrese uno distinto. ");
            }
            
            classLibro.setIsbn(ISB);
            classLibro.setTitulo(titulo);
            classLibro.setEjemplares(ejemplares);
            classLibro.setEjemplaresRestantes(ejemplares);
            classLibro.setEjemplaresPrestados(0);
            classLibro.setAlta(alta);
            classLibro.setAutor(classAutor);
            classLibro.setEditorial(classEditorial);
            
            libroDAO.guardarLibro(classLibro);
            System.out.println("[JPA] Datos registrados.");
        }
        catch (Exception ex) {
            System.out.println("[SERVICIO-EXCEPTION] " + ex.fillInStackTrace());
        }
    }
    
    /***
     * Eliminacion definitivo desde la base de datos
     * @throws Exception 
     */
    public void eliminarLibro(Long ISBN) throws Exception {
        try {
            /*
            Comprobar que el ISBN sea el correcto
            */
            
            if (libroDAO.comprobarISBN(ISBN) == true) {
                throw new Exception("El codigo ISBN no se registra en la base de datos");
            }
            
            libroDAO.eliminarLibro(ISBN);
        } catch (Exception ex){
            System.out.println("[EXCEPTION] " + ex.fillInStackTrace());
        }
    }
    
    public void darBajaLibro(Long ISBN, boolean alta) throws Exception {
        try {
            /* comprobar que el ISBN sea correcto */
            if (libroDAO.comprobarISBN(ISBN) == true) {
                throw new Exception("El codigo ISBN no se registra en la base de datos");
            }
            
            libroDAO.darBajaLibro(ISBN, alta);
        } catch (Exception ex) {
            System.out.println("[SERVICIO-EXCEPTION] "+ ex.fillInStackTrace());
        }
    }
    
    public Libro obtenerLibro(Long ISBN) throws Exception {
        try {
            Libro classLibro =  libroDAO.obtenerClaseISBN(ISBN);
            
            return classLibro;
        }
        catch (Exception ex){
            return null;
        }
    }
    public Libro obtenerLibroPorTitulo(String titulo){
        Libro classLibro = new Libro();
        Collection <Libro> listLibro = libroDAO.obtenerListaLibros();

        for (Libro aux : listLibro) {
            if (aux.getTitulo().equals(titulo)) {
                classLibro = aux;
                break;
            }
        }
        return classLibro;
    }
    
    public Collection <Libro> obtenerListaLibro(){
        try {
            return libroDAO.obtenerListaLibros();
        } catch (Exception ex){
            return null;
        }
    }
    
    public Collection <Libro> buscarLibroPorNombre(String nombre){
        try{
             return libroDAO.obtenerLibroPorNombre(nombre);
        } catch (Exception ex){
            return null;
        }
    }

    /*
    public Libro buscarLibroPorTitulo(String titulo) {
        try {
            Libro classLibro = new Libro();
            classLibro = libroDAO.obtenerLibroPorTitulo(titulo);
            
            return classLibro;
            
        } catch (Exception ex) {
            return null;
        }
    }
    */
    public Libro buscarLibroPorISBN(Long ISBN) {
        try {
            Libro classLibro = new Libro();
            classLibro = libroDAO.obtenerClaseISBN(ISBN);
            
            return classLibro;
            
        } catch (Exception ex){
            return null;
        }
    }

    public Collection <Libro> buscarLibroPorEditorial(String editorial) {
        try {
            Collection <Libro> listLibro = libroDAO.obtenerLibroPorEditorial(editorial);
            return listLibro;
        } catch (Exception ex){
            return null;
        }
    }
}
