    /*
 */
package jpa_libreria;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;


import libreria.entidad.*;
import libreria.servicio.*;

/**
 *
 * @author ivan
 */
public final class Menu {
    private final ServicioLibro servicioLibro;
    private final ServicioAutor servicioAutor;
    private final ServicioEditorial servicioEditorial;
    private final ServicioCliente servicioCliente;
    private final Scanner read;
    
    
    private final Integer MENU_SIMPLE = 0;
    private final Integer MENU_ALTA = 1;
    private final Integer MENU_LISTA = 2;
    private final Integer MENU_ELIMINAR = 3;
    
    public Menu() {
        servicioLibro = new ServicioLibro();
        servicioAutor = new ServicioAutor(); 
        servicioEditorial = new ServicioEditorial();
        servicioCliente = new ServicioCliente();
        read = new Scanner(System.in).useDelimiter("\n");
    }
    
    /*************************************************/
    //   [MENUS]
    /************************************************/   
    
    public void Principal() {
        Integer opcion;
        
        System.out.println("--------------------------------------");
        System.out.println("Menu principal\n");
        
        System.out.println("1. Modificaciones de libros");
        System.out.println("2. Administracion de informacion de libros");
        System.out.println("3. Administracion de prestamos de libros");
        
        System.out.println("\n\n0. Terminar programa");
        
        try {
            System.out.print("\n* Opcion: #");
            opcion = read.nextInt();
            funcionMenuPrincipal(opcion);
            
        } catch (Exception ex) {
            System.out.println("[EXCEPTION] " + ex.fillInStackTrace());
        }
    }
    
    public void funcionMenuPrincipal(Integer opcion) throws Exception {
        switch (opcion) {
            case 0: {
                System.out.println("-----FIN----");
                break;
            }
            case 1: {
                menuModificaciones();
                break;
            }
            case 2: {
                menuFuncionesLibros();
                break;
            }
            default: Principal();break;
        }
    }
    
    public void menuModificaciones() throws Exception{
        System.out.println("--------------Modificaciones de libros------------");
        
        System.out.println("1. Registrar un nuevo libro");
        System.out.println("2. Dar alta/baja de un libro");
        System.out.println("3. Eliminar libro desde la base de datos");
        System.out.println("\n\n0. Volver atras");
        try {
            System.out.print("* Opcion: #");
            Integer opcion = read.nextInt();
            
            funcionModificaciones(opcion);
        } catch (Exception ex){
            throw ex;
        }
    }
    
    public void funcionModificaciones(Integer opcion) throws Exception{
        switch (opcion) {
            case 0: Principal(); break;
            case 1: {
                crearNuevoLibro();
                break;
            }
            case 2: {
                modificarAlta();
                break;
            }
            case 3: {
                eliminarLibro();
                break;
            }
            default: menuModificaciones();
        }
    }
    
    public void menuFuncionesLibros(){
        System.out.println("------------Funciones de libros--------------");
        
        System.out.println("1. Buscar un libro por titulo");
        System.out.println("2. Buscar un libro por codigo ISBN");
        System.out.println("3. Buscar una lista de libros por autor");
        System.out.println("4. Buscar una lista de libros por editorial");
        System.out.println("5. Listar todos los libros");
        
        try {
            System.out.print("* Opcion: #");
            Integer opcion = read.nextInt();
            funcionAdministrarLibros(opcion);
        } catch (Exception ex){
            System.out.println("[Exception-menu] " + ex.fillInStackTrace());
        }
    }
    
    public void funcionAdministrarLibros(Integer opcion) throws Exception {
        switch (opcion){
            case 0: Principal(); break;
            case 1: {
                buscarLibroPorTitulo();
                break;
            }
            case 2: {
                buscarLibroPorISBN();
                break;
            }
            case 3: {
                buscarLibrosPorAutor();
                break;
            }
            case 4: {
                buscarLibrosPorEditorial();
                break;
            }
            case 5: {
                datosListLibros();
                break;
            }
            default: menuFuncionesLibros(); break;
        }
        
    }
    
    public Boolean datosLibroList(List <Libro> listLibro, Libro entidadLibro, Integer i, Integer tipo) {
        System.out.println("--------Datos del libro en lista-----------");
        
        Libro classLibro = entidadLibro;
        Boolean isBool = null;
        
        try {
            if (Objects.equals(tipo, MENU_LISTA))   {
                classLibro = listLibro.get(i);
            }

            System.out.println("Libro: " + classLibro.getTitulo());

            if (listLibro != null && listLibro.size()> 1) {
                System.out.println("Pagina: " + (i+1) + "/" + (listLibro.size()));
            }

            System.out.println("\nAtributos: ");
            System.out.println("- Autor: " + classLibro.getAutor().getNombre());
            System.out.println("- Editorial: " + classLibro.getEditorial().getNombre());
            System.out.println("\n- Ejemplares inicial: " + classLibro.getEjemplares());
            System.out.println("- Ejemplares prestados: " + classLibro.getEjemplaresPrestados());
            System.out.println("- Ejemplares restantes: " + classLibro.getEjemplaresRestantes());

            System.out.println("\n- Codigo IBMS: " + classLibro.getIsbn());
            System.out.println("- Estado: " + (classLibro.getAlta() == true ? "Libro en alta" : "Libro en baja"));

            if (Objects.equals(tipo, MENU_ALTA)){
                System.out.print("\n1. Dar " + (classLibro.getAlta() == true ? "Baja" : "Alta") + " a este libro"  );
            }
            else if (Objects.equals(tipo, MENU_ELIMINAR))
                System.out.println("\n1. Eliminar libro");
            else if (Objects.equals(tipo, MENU_LISTA)){
                System.out.println("\n9. Ver libro siguiente");
            }

            System.out.println("\n0. volver atras");

            System.out.print("Opcion: #");
            Integer opcion = read.nextInt();

            if (opcion == 0) {
                Principal();
                return isBool;
            }

            if (Objects.equals(tipo, MENU_LISTA)) {
                switch (opcion){
                    case 9:{
                        if ((++i) >= listLibro.size()){
                            i = 0;
                        }
                        datosLibroList(listLibro, null, i, MENU_LISTA);
                    }
                    default: {
                        datosLibroList(listLibro, null, i, MENU_LISTA);
                        break;
                    }
                }
            }
            else if (Objects.equals(tipo, MENU_ALTA)) {
                if (opcion == 1) {
                    isBool = (classLibro.getAlta() != true);
                }
            }
            else if (Objects.equals(tipo, MENU_ELIMINAR)){
                isBool = true;
            }

            return isBool;
        } catch (Exception ex) {
            System.out.println("[Exception] " + ex.fillInStackTrace());
            return null;
        }
    }
    
     /*************************************************/
    //   [FUNCIONES]
    /*************************************************/
    /**
     * @throws java.lang.Exception***********************************************/ 
    public void crearNuevoLibro() throws Exception {
        try {
            System.out.println("------------Registrar Libro------------");

            Long ISBN = null;
            Integer ejemplares = null;
            String titulo = null, autor = null, editorial = null;
            Boolean alta = true;

            read.skip("\n");
            System.out.print("* Digite un titulo: ");
            titulo = read.nextLine();
            
            System.out.print("* Digite autor: ");
            autor = read.nextLine();

            System.out.print("* Digite editorial: ");
            editorial = read.nextLine();
            
            System.out.print("* Digite ejemplares: ");
            ejemplares = read.nextInt();

            read.skip("\n");
            System.out.print("* Digite codigo ISBN: ");
            ISBN = read.nextLong();
          
            Autor classAutor = new Autor(); 
                    classAutor = servicioAutor.crearAutor(autor, alta);
                    
            Editorial classEditorial = new Editorial(); 
                    classEditorial = servicioEditorial.crearEditorial(editorial, alta);
            
            servicioLibro.insertarLibro(ISBN, titulo, ejemplares, alta, classAutor, classEditorial);
            
        } catch (Exception ex) {
            throw ex;
        }
        finally {
            Principal();
        }
    }
    
    public void crearNuevoCliente() throws Exception {
        System.out.println("------------Registrar Cliente-------------");
        try {
            
            String nombre, apellido, telefono;
            Long documento;
            
            System.out.print("Digite nombre: ");
            nombre = read.nextLine();
            
            System.out.print("Digite apellido: ");
            apellido = read.nextLine();
            
            System.out.print("Digite DNI (sin puntos): ");
            documento = read.nextLong();
            
            read.skip("\n");
            
            System.out.print("Digite telefono: ");
            telefono = read.nextLine();
            
            servicioCliente.registrarCliente(documento, nombre, apellido, telefono);
        } catch (Exception ex){
            throw ex;
        }
    }
    
    public void modificarAlta() throws Exception {
        try {
            System.out.println("------------Modificar Alta----------------");
            
            Long ISBN = entradaCodigoISBN();
            Libro classLibro = servicioLibro.obtenerLibro(ISBN);
            
            Boolean isAlta = datosLibroList(null, classLibro, 0, MENU_ALTA );
            
            servicioLibro.darBajaLibro(ISBN, isAlta);
        } catch (Exception ex){
            throw ex;
        }
        finally {
            Principal();
        }
    }
    
    public void eliminarLibro() throws Exception{
        System.out.println("--------------Eliminar Libro forma permanente-------------");
        
        try {
            Long ISBN = entradaCodigoISBN();
            Libro classLibro = servicioLibro.obtenerLibro(ISBN);
            
            if (classLibro == null){
                throw new Exception("No se encontro la clase con el codigo ISBN");
            }
            Boolean isEliminar = datosLibroList(null, classLibro, 0, MENU_SIMPLE );
            
            if (isEliminar == true)
                servicioLibro.eliminarLibro(ISBN);
            
        } catch (Exception ex){
             throw ex;
        }
        finally {
            Principal();
        }
    }
    
    public void modificarAltaCliente() throws Exception {
        try {
            Long documento = entradaDocumento();
            servicioCliente.modificarAltaCliente(documento);
            
        } catch (Exception ex){
            throw ex;
        }
    }
    
    public void eliminarCliente(){
          
    }

     /*************************************************/
    //   [BUSQUEDAS]
    /***********************************************/   
    
    public void datosListLibros() throws Exception {
        System.out.println("-------------------Lista de todos los libros--------------");
        try {
            
            List <Libro> listLibro = (List <Libro>) servicioLibro.obtenerListaLibro();
            
            if (listLibro.isEmpty() == true){
                throw new Exception("No hay registros de los libros");
            }
            datosLibroList(listLibro, null, 0, MENU_LISTA);
            
        } catch (Exception ex) {
            throw ex;
        }
        finally {
            Principal();
        }
    }
      
    private void buscarLibroPorTitulo() throws Exception {
        System.out.println("-------------Buscar libro por Titulo------------");
        try {
            read.skip("\n");
            System.out.print("* Digite el titulo de algun libro: ");
            String titulo = read.nextLine();
            
            Libro classLibro = new Libro();
            classLibro = servicioLibro.obtenerLibroPorTitulo(titulo);
            
            if (classLibro == null) {
                throw new Exception("No se encontro el libro");
            }
            
            datosLibroList(null, classLibro, 0, MENU_SIMPLE);
        } catch (Exception ex){
            throw ex;
        }
        finally {
            Principal();
        }
    }

    private void buscarLibroPorISBN() throws Exception{
        System.out.println("-----------------Buscar Libro por ISBN----------------");
        try {
            Long ISBN = entradaCodigoISBN();
            Libro classLibro = servicioLibro.buscarLibroPorISBN(ISBN);
            
            if (classLibro == null){
                throw new Exception("No se encontro la clase con el codigo ISBN");
            }
            
            datosLibroList(null, classLibro, 0, MENU_SIMPLE);
        } catch (Exception ex){
            throw ex;
        }
        finally {
            Principal();
        }
    }

    private void buscarLibrosPorAutor() throws Exception {
        
        System.out.println("------------Buscar libro/s por autor--------------");
        try{
            read.skip("\n");
            System.out.println("Digite el nombre de autor: ");
            String autor = read.nextLine();
            List <Libro>  listLibro =  (List <Libro>) servicioLibro.buscarLibroPorNombre(autor);
            
            if (listLibro.isEmpty() == true){
                throw new Exception("No se encontro la clase");
            }
            datosLibroList(listLibro, null, 0, MENU_LISTA);
        } catch(Exception ex){
            throw ex;
        }
        finally {
            Principal();
        }
    }

    private void buscarLibrosPorEditorial() throws Exception {
        System.out.println("-------------Buscar libro/s por editorial----------------");
        try {
            read.skip("\n");
            System.out.println("Digite editorial: ");
            String editorial = read.nextLine();
            
            List <Libro> listLibro = (List <Libro>) servicioLibro.buscarLibroPorEditorial(editorial);
            
            if (listLibro.isEmpty() == true) {
                throw new Exception("No se encontro la clase");
            }
            datosLibroList(listLibro, null, 0, MENU_LISTA);
        } catch (Exception ex){
            throw ex;
        }
        finally {
            Principal();
        }
    }
    
    public void buscarClientePorDNI() {
        
    }
    
     /*************************************************/
    //   [Entrada]
    /************************************************/   
    public Long entradaCodigoISBN() {
        System.out.print("* Digite codigo ISBN\nCodigo: #");
        Long ISBN = read.nextLong();
        return ISBN;
    }
    public Long entradaDocumento(){
        System.out.println("* Digite el numero de documento\nDNI: ");
        return read.nextLong();
    }
}