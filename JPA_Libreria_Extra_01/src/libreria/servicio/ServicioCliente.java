/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicio;

import libreria.entidad.Cliente;

import libreria.persistencia.ClienteDAO;
/**
 *
 * @author ivan
 */
public class ServicioCliente {

    private final ClienteDAO dao;
    public ServicioCliente(){
        dao = new ClienteDAO();
    }
    /**
     * *
     * private Long documento; private String nombre; private String apellido;
     * private String telefono;
     * @param documento
     * @param nombre
     * @param apellido
     * @param telefono
     * @return 
     * @throws java.lang.Exception
     */
    public Boolean registrarCliente(Long documento, String nombre, String apellido, String telefono) throws Exception{
        try {
            if (documento <= 0){
                throw new Exception("Error: Ingrese un document valido.");
            }
            
            if (nombre.isEmpty()){
                throw new Exception("Error: el nombre esta vacio");
            }
            if (apellido.isEmpty()){
                throw new Exception("Error: el apellido esta vacio");
            }
            
            if (telefono.isEmpty()){
                throw new Exception("Error: el numero de telefono esta vacio");
            }
            
            if (verificarDocumento(documento) == true){
                throw new Exception("Error: el documento ya existe");
            }
            
            if (verificarTelefono(telefono) == true){
                throw new Exception("Error: el numero de telefono ya esta registrado");
            }
            
            Cliente classCliente = new Cliente(); 
            
            classCliente.setNombre(nombre);
            classCliente.setApellido(apellido);
            classCliente.setDocumento(documento);
            classCliente.setTelefono(telefono);
            classCliente.setAlta(Boolean.TRUE);
            
            dao.registrarUsuario(classCliente);
            return true;
        } catch (Exception ex){
            throw ex;
        }
    }

    private boolean verificarTelefono(String telefono) {
        return dao.verificarTelefono(telefono);
    }

    private boolean verificarDocumento(Long documento) {
        return dao.verificarDocumento(documento);
    }

    private Boolean verificarClienteDNI(Long DNI){
        return dao.verificarDNI(DNI);
    }
    public Cliente obtenerClienteDni(Long documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modificarAltaCliente(Object tipo) {
        try {
            
            
        }
        catch (Exception ex){
            throw ex;
        }    
    }
}
