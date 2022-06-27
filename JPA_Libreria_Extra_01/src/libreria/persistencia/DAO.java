/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author ivan
 * @param <T>: Recibira el tipo de Objecto que lo modele
 */
public class DAO <T> {
    EntityManagerFactory MF = Persistence.createEntityManagerFactory("JPA_LibreriaPU");
    EntityManager em = MF.createEntityManager();
    
    public void conectarBase() throws Exception {
        try {
            if (!em.isOpen()) {
                em = MF.createEntityManager();
            }  
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public void desconectarBase() throws Exception{
        try {
            if (em.isOpen()) {
                em.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public void guardar(T Object) throws Exception {
        try {
            conectarBase();
            em.getTransaction().begin();
            em.persist(Object);
            em.getTransaction().commit();
            desconectarBase();
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public void remover(T Object) throws Exception {
        try {
            Object classOBJ = Object;
            
            conectarBase();
            em.getTransaction().begin();
            if (!em.contains(Object)) {
                 classOBJ = em.merge(Object);
            }
            
            em.remove(classOBJ);
            em.getTransaction().commit();
            desconectarBase();
            
        } catch (Exception ex){
            throw ex;
        }
    }
    
    public void modificar(T Object) throws Exception {
        try {
            conectarBase();
            em.getTransaction().begin();
            em.merge(Object);
            em.getTransaction().commit();
            desconectarBase();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
