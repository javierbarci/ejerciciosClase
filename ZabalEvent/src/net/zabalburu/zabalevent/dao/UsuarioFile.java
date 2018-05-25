/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.zabalburu.zabalevent.modelo.Usuario;
import net.zabalburu.zabalevent.modelo.UsuarioEvento;

/**
 *
 * @author Iñigo
 */
public class UsuarioFile implements UsuarioDAO {

    private DataInputStream dis;
    private DataOutputStream dos;
    private File fichero = new File("datos/usuarios.data");

    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            dis = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(fichero)));
            try {
                while (true) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(dis.readInt());
                    u.setNombre(dis.readUTF());
                    u.setEmail(dis.readUTF());
                    u.setPassword(dis.readUTF());
                    u.setFoto(dis.readUTF());
                    usuarios.add(u);
                }
            } catch (EOFException ex) {
                try {
                    dis.close();
                } catch (IOException ex1) {
                    Logger.getLogger(UsuarioFile.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
                Logger.getLogger(UsuarioFile.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(UsuarioFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    @Override
    public Usuario getUsuario(int idUsuario) {
        Usuario u = null;
        try {
            dis = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(fichero)));
            do {
                u = new Usuario();
                u.setIdUsuario(dis.readInt());
                u.setNombre(dis.readUTF());
                u.setEmail(dis.readUTF());
                u.setPassword(dis.readUTF());
                u.setFoto(dis.readUTF());
            } while (idUsuario > u.getIdUsuario());
            if (idUsuario != u.getIdUsuario()) {
                u = null;
            }
        } catch (EOFException ex) {
            u = null;
        } catch (IOException ex) {
            Logger.getLogger(UsuarioFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    public void nuevoUsuario(Usuario u) {
        try {
            dos = new DataOutputStream(
                    new BufferedOutputStream(
                    new FileOutputStream(fichero,true)));
            dos.writeInt(u.getIdUsuario());
            dos.writeUTF(u.getNombre());
            dos.writeUTF(u.getEmail());
            dos.writeUTF(u.getPassword());
            dos.writeUTF(u.getFoto());
            dos.flush();
            dos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UsuarioFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarUsuario(Usuario u) {
        try {
            File temp = File.createTempFile("usuarios", "tmp");
            dis = new DataInputStream(
                new BufferedInputStream(
                new FileInputStream(fichero)));
            dos = new DataOutputStream(
                new BufferedOutputStream(
                new FileOutputStream(temp)));
            try {
                while (true){
                    int idUsuario = dis.readInt();
                    String nombre = dis.readUTF();
                    String email = dis.readUTF();
                    String password = dis.readUTF();
                    String foto = dis.readUTF();
                    if (idUsuario != u.getIdUsuario()){
                        dos.writeInt(idUsuario);
                        dos.writeUTF(nombre);
                        dos.writeUTF(email);
                        dos.writeUTF(password);
                        dos.writeUTF(foto);
                    }
                }
            } catch (EOFException ex){
                dis.close();
                dos.flush();
                dos.close();
                fichero.delete();
                temp.renameTo(fichero);
            }
        } catch (IOException ex) {
            Logger.getLogger(UsuarioFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modificarUsuario(Usuario c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> getUsuariosEvento(int idEvento) {
        List<Usuario> usuarios = new ArrayList<>();
        UsuarioEventoDAO usuarioEventoDAO = new UsuarioEventoList();
        for (UsuarioEvento ue : usuarioEventoDAO.getUsuarioEventosUsuario(idEvento)) {
            Usuario u = this.getUsuario(ue.getIdUsuario());
            usuarios.add(u);
        }
        return usuarios;
    }

    public static void main(String[] args) {
        try {
            DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("datos/usuarios.data")));
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(new Usuario(1, "Juan López", "juanLopez@zabalevent.com", "12345", "1.jpg"));
            usuarios.add(new Usuario(2, "Ana Marín", "anaMarin@zabalevent.com", "12345", "2.jpg"));
            usuarios.add(new Usuario(4, "Sara Sanz", "saraSanz@zabalevent.com", "12345", "3.jpg"));
            usuarios.add(new Usuario(5, "Carlos Ginés", "carlosGines@zabalevent.com", "12345", "4.jpg"));
            for (Usuario u : usuarios) {
                dos.writeInt(u.getIdUsuario());
                dos.writeUTF(u.getNombre());
                dos.writeUTF(u.getEmail());
                dos.writeUTF(u.getPassword());
                dos.writeUTF(u.getFoto());
            }
            dos.flush();
            dos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UsuarioFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
