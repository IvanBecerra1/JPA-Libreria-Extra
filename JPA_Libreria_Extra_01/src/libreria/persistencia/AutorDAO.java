package libreria.persistencia;

import libreria.entidad.Autor;
/**
 *
 * @author ivan
 */
public class AutorDAO extends DAO <Autor> {
    public void removerAutor(Autor classAutor){
        try{
            super.remover(classAutor);
        } catch (Exception ex){
            System.out.println("[EXCEPTION] " + ex.fillInStackTrace());
        }
    }
}
