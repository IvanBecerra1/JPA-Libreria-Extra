/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import libreria.entidad.Cliente;
import java.util.List;
/**
 *
 * @author ivan
 */
public final class ClienteDAO extends DAO<Cliente> {
    
    public void registrarUsuario(Cliente classCliente) throws Exception {
        try {
            if (classCliente == null){
                throw new Exception("La clase se encuentra vacio");
            }
            super.guardar(classCliente);
            
        } catch (Exception ex){
            throw ex;
        }
    }

    public Boolean verificarTelefono(String telefono) {
        List <Cliente> listCliente = consultarClientes();
        
        if (listCliente.isEmpty())
            return false;
        
        return listCliente.stream().anyMatch((cliente) -> (cliente.getTelefono().equals(telefono)));
    }

    public Boolean verificarDocumento(Long documento) {
        List <Cliente> listCliente = consultarClientes();
        
        if (listCliente.isEmpty()){
            return false;
        }
        
        return listCliente.stream().anyMatch((cliente) -> (cliente.getDocumento().equals(documento)));
    }
    
    public List <Cliente> consultarClientes(){
        try {
            conectarBase();
            List <Cliente> listCliente = (List <Cliente>) em.createQuery("SELECT C FROM Cliente c").getResultList();
            desconectarBase();
            
            return listCliente;
        } catch (Exception ex){
            return null;
        }
    }

    public Boolean verificarDNI(Long DNI) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
