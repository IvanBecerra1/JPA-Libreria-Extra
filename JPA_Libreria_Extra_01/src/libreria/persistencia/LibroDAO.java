/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.Collection;
import libreria.entidad.Libro;
/**
 *
 * @author ivan
 */
public final class LibroDAO extends DAO <Libro> {

    public void guardarLibro(Libro classLibro) throws Exception {
        try {
            if (classLibro == null) {
                throw new Exception("La clase se encuentra vacia.");
            }    
            super.guardar(classLibro);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public void eliminarLibro(Long ISBN)  throws Exception {
        try {
            Libro classLibro = obtenerClaseISBN(ISBN);
            
            if (classLibro == null){
                throw new Exception("La clase se encuentra vacia");
            }
            
            AutorDAO daoAutor = new AutorDAO();
            EditorialDAO daoEditorial = new EditorialDAO();
            
            super.remover(classLibro);
            daoAutor.removerAutor(classLibro.getAutor());
            daoEditorial.removerEditorial(classLibro.getEditorial());
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public void darBajaLibro(Long ISBN, Boolean alta)  throws Exception {
        try {
            conectarBase();
            Libro classLibro = obtenerClaseISBN(ISBN); 
            desconectarBase();
            
            classLibro.setAlta(alta);
            classLibro.getEditorial().setAlta(alta);
            classLibro.getAutor().setAlta(alta);
            
            super.modificar(classLibro);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public Boolean comprobarISBN(Long ISBN){
         Libro classLibro = obtenerClaseISBN(ISBN);
        return classLibro == null; // Verdadero §
    }

    
    public boolean comprobarTitulo(String titulo) throws Exception  {
        try {
            conectarBase();
            Libro classLibro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo").
                        setParameter("titulo", titulo).
                        getSingleResult();
                
            desconectarBase();
            return false;
            
        } catch (Exception ex) {
            desconectarBase();
            return true;
        }
    }
    
    public Libro obtenerClaseISBN(Long ISBN) {
        try{
            conectarBase();
            Libro classLibro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :ISBN").
                    setParameter("ISBN", ISBN).
                    getSingleResult();
                    
            desconectarBase();
            return classLibro;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Puede devolver tanto una clase como varias.
     * @param nombre
     * @return 
     */
    
    public Collection <Libro> obtenerLibroPorNombre(String nombre) {
        try {
            conectarBase();
            Collection <Libro> listLibro = em.createQuery("SELECT a FROM Libro a WHERE a.autor.nombre LIKE :nombre ").setParameter("nombre", nombre).getResultList();
            
            desconectarBase();
            return listLibro;
        } catch (Exception ex){
            return null;
        }
    }
    
    public Libro obtenerLibroPorTitulo(String titulo) throws Exception{
        try{
            conectarBase();
            Libro classLibro = (Libro) em.createQuery("SELECT A FROM Libro A WHERE A.titulo LIKE :titulo").setParameter("titulo", titulo).getSingleResult();
            
            desconectarBase();
            return classLibro;
        } catch (Exception ex){
           desconectarBase();
           return null; 
        }
    }

    public Collection<Libro> obtenerLibroPorEditorial(String editorial) {
        try {
            conectarBase();
            Collection <Libro> listLibro = em.createQuery("SELECT a FROM Libro a WHERE a.editorial.nombre LIKE :editorial ").setParameter("editorial", editorial).getResultList();
            desconectarBase();
            return listLibro;
        } catch (Exception ex){
            return null;
        }
    }

    public Collection <Libro> obtenerListaLibros() {
        try {
            conectarBase();
            Collection <Libro> listLibro = em.createQuery("SELECT a FROM Libro a").getResultList();
            desconectarBase();
            return listLibro;
        } catch (Exception ex){
            return null;
        }
    }
}
