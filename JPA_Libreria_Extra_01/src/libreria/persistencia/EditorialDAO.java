/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import libreria.entidad.Editorial;
/**
 *
 * @author ivan
 */
public class EditorialDAO  extends DAO <Editorial> {
    
    public void removerEditorial(Editorial classEditorial) throws Exception{
        try {
            super.remover(classEditorial);
        } catch (Exception ex){
            System.out.println("[EXCEPTION] " + ex.fillInStackTrace());
        }
    }
}
