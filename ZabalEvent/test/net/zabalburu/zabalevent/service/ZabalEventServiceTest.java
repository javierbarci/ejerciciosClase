/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.service;

import java.util.List;
import net.zabalburu.zabalevent.modelo.Categoria;
import net.zabalburu.zabalevent.modelo.Evento;
import net.zabalburu.zabalevent.modelo.Lugar;
import net.zabalburu.zabalevent.modelo.Usuario;
import net.zabalburu.zabalevent.modelo.UsuarioEvento;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iñigo
 */
public class ZabalEventServiceTest {
    private static ZabalEventService service = new ZabalEventService();
    public ZabalEventServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        generarDatos(service);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    private static void generarDatos(ZabalEventService service){
        for(Evento e : service.getEventos()){
            service.eliminarEvento(e);
        }
        for(Usuario u : service.getUsuarios()){
            service.eliminarUsuario(u);
        }
        for(Lugar l : service.getLugares()){
            service.eliminarLugar(l);
        }
        for(Categoria c : service.getCategorias()){
            service.eliminarCategoria(c);
        }
        Usuario u = new Usuario();
        u.setNombre("Juan");
        service.nuevoUsuario(u);
        u = new Usuario();
        u.setNombre("Pedro");
        service.nuevoUsuario(u);
        Categoria c = new Categoria();
        c.setNombre("Cat1");
        service.nuevaCategoria(c);
        Lugar l = new Lugar();
        l.setNombre("Lug1");
        service.nuevoLugar(l);
        Evento e = new Evento();
        e.setTitulo("Evento1");
        e.setIdCategoria(1);
        e.setIdLugar(1);
        e.setIdUsuario(1);
        service.nuevoEvento(e);
        e = new Evento();
        e.setTitulo("Evento2");
        e.setIdCategoria(1);
        e.setIdLugar(1);
        e.setIdUsuario(1);
        service.nuevoEvento(e);
        service.apuntarAEvento(1, 2);
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCategorias method, of class ZabalEventService.
     */
    @Test
    public void testGetCategorias() {
        System.out.print("getCategorias");
        assertEquals(service.getCategorias().size(), 1);
        Categoria c = new Categoria();
        c.setNombre("Cat2");
        service.nuevaCategoria(c);
        assertEquals(service.getCategorias().size(), 2);
        List<Categoria> categorias = service.getCategorias();
        assertEquals(categorias.get(categorias.size()-1), c);
        System.out.println(" -- PASADO");
    }

    /**
     * Test of getCategoria method, of class ZabalEventService.
     */
    @Test
    public void testGetCategoria() {
        System.out.print("getCategoria");
        int id = 1;
        assertNotNull(service.getCategoria(id));
        id = 5;
        assertNull(service.getCategoria(id));
        System.out.println(" -- PASADO");
    }

    /**
     * Test of nuevaCategoria method, of class ZabalEventService.
     */
    @Test
    public void testNuevaCategoria() {
        Categoria c = new Categoria();
        c.setNombre("Cat2");
        service.nuevaCategoria(c);
        assertEquals(service.getCategorias().size(), 2);
        System.out.println(" -- PASADO");
    }

    /**
     * Test of eliminarCategoria method, of class ZabalEventService.
     */
    @Test
    public void testEliminarCategoria() {
        System.out.print("eliminarCategoria");
        Categoria c = new Categoria();
        c.setIdCategoria(1);
        service.eliminarCategoria(c);
        assertNull(service.getCategoria(1));
        System.out.println(" -- PASADO");
    }

    /**
     * Test of modificarCategoria method, of class ZabalEventService.
     */
    @Test
    public void testModificarCategoria() {
        System.out.println("modificarCategoria");
        Categoria c = null;
        ZabalEventService instance = new ZabalEventService();
        instance.modificarCategoria(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLugares method, of class ZabalEventService.
     */
    @Test
    public void testGetLugares() {
        System.out.println("getLugares");
        ZabalEventService instance = new ZabalEventService();
        List<Lugar> expResult = null;
        List<Lugar> result = instance.getLugares();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLugar method, of class ZabalEventService.
     */
    @Test
    public void testGetLugar() {
        System.out.println("getLugar");
        int idLugar = 0;
        ZabalEventService instance = new ZabalEventService();
        Lugar expResult = null;
        Lugar result = instance.getLugar(idLugar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nuevoLugar method, of class ZabalEventService.
     */
    @Test
    public void testNuevoLugar() {
        System.out.println("nuevoLugar");
        Lugar c = null;
        ZabalEventService instance = new ZabalEventService();
        boolean expResult = false;
        boolean result = instance.nuevoLugar(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarLugar method, of class ZabalEventService.
     */
    @Test
    public void testEliminarLugar() {
        System.out.println("eliminarLugar");
        Lugar c = null;
        ZabalEventService instance = new ZabalEventService();
        instance.eliminarLugar(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarLugar method, of class ZabalEventService.
     */
    @Test
    public void testModificarLugar() {
        System.out.println("modificarLugar");
        Lugar c = null;
        ZabalEventService instance = new ZabalEventService();
        instance.modificarLugar(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventos method, of class ZabalEventService.
     */
    @Test
    public void testGetEventos() {
        System.out.println("getEventos");
        ZabalEventService instance = new ZabalEventService();
        List<Evento> expResult = null;
        List<Evento> result = instance.getEventos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEvento method, of class ZabalEventService.
     */
    @Test
    public void testGetEvento() {
        System.out.println("getEvento");
        int idEvento = 0;
        ZabalEventService instance = new ZabalEventService();
        Evento expResult = null;
        Evento result = instance.getEvento(idEvento);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nuevoEvento method, of class ZabalEventService.
     */
    @Test
    public void testNuevoEvento() {
        System.out.println("nuevoEvento");
        Evento e = null;
        ZabalEventService instance = new ZabalEventService();
        instance.nuevoEvento(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarEvento method, of class ZabalEventService.
     */
    @Test
    public void testEliminarEvento() {
        System.out.println("eliminarEvento");
        Evento e = null;
        ZabalEventService instance = new ZabalEventService();
        instance.eliminarEvento(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarEvento method, of class ZabalEventService.
     */
    @Test
    public void testModificarEvento() {
        System.out.println("modificarEvento");
        Evento e = null;
        ZabalEventService instance = new ZabalEventService();
        instance.modificarEvento(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventosLugar method, of class ZabalEventService.
     */
    @Test
    public void testGetEventosLugar() {
        System.out.println("getEventosLugar");
        int idLugar = 0;
        ZabalEventService instance = new ZabalEventService();
        List<Evento> expResult = null;
        List<Evento> result = instance.getEventosLugar(idLugar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventosCategoria method, of class ZabalEventService.
     */
    @Test
    public void testGetEventosCategoria() {
        System.out.println("getEventosCategoria");
        int idCategoria = 0;
        ZabalEventService instance = new ZabalEventService();
        List<Evento> expResult = null;
        List<Evento> result = instance.getEventosCategoria(idCategoria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventosUsuario method, of class ZabalEventService.
     */
    @Test
    public void testGetEventosUsuario() {
        System.out.println("getEventosUsuario");
        int idUsuario = 0;
        ZabalEventService instance = new ZabalEventService();
        List<Evento> expResult = null;
        List<Evento> result = instance.getEventosUsuario(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarios method, of class ZabalEventService.
     */
    @Test
    public void testGetUsuarios() {
        System.out.println("getUsuarios");
        ZabalEventService instance = new ZabalEventService();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.getUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuario method, of class ZabalEventService.
     */
    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        int idUsuario = 0;
        ZabalEventService instance = new ZabalEventService();
        Usuario expResult = null;
        Usuario result = instance.getUsuario(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nuevoUsuario method, of class ZabalEventService.
     */
    @Test
    public void testNuevoUsuario() {
        System.out.println("nuevoUsuario");
        Usuario u = null;
        ZabalEventService instance = new ZabalEventService();
        boolean expResult = false;
        boolean result = instance.nuevoUsuario(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarUsuario method, of class ZabalEventService.
     */
    @Test
    public void testEliminarUsuario() {
        System.out.println("eliminarUsuario");
        Usuario u = null;
        ZabalEventService instance = new ZabalEventService();
        instance.eliminarUsuario(u);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarUsuario method, of class ZabalEventService.
     */
    @Test
    public void testModificarUsuario() {
        System.out.println("modificarUsuario");
        Usuario c = null;
        ZabalEventService instance = new ZabalEventService();
        instance.modificarUsuario(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuariosEvento method, of class ZabalEventService.
     */
    @Test
    public void testGetUsuariosEvento() {
        System.out.println("getUsuariosEvento");
        int idEvento = 0;
        ZabalEventService instance = new ZabalEventService();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.getUsuariosEvento(idEvento);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class ZabalEventService.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String email = "";
        String password = "";
        ZabalEventService instance = new ZabalEventService();
        Usuario expResult = null;
        Usuario result = instance.login(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
