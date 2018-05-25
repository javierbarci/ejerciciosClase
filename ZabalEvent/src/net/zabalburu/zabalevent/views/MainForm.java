/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import net.zabalburu.zabalevent.modelo.Usuario;
import net.zabalburu.zabalevent.util.Colores;

/**
 *
 * @author Iñigo
 */
public class MainForm extends JFrame{
    private Dimension dmVentana = new Dimension(500,400);
    private JPanel pnlCabecera = new JPanel();
    private JPanel pnlUsuario = new JPanel(new BorderLayout(0,5));
    private JPanel pnlOpciones = new JPanel(new GridLayout(2,2,10,10));
    private JPanel pnlEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JLabel lblTitulo = new JLabel("ZabalEvent");
    private JLabel lblFoto = new JLabel();
    private JButton btnSalir = new JButton("Salir");
    private JButton btnBuscar = new JButton("Buscar Evento");
    private JButton btnNuevoEventos = new JButton("Nuevo Evento");
    private JButton btnMisEventos = new JButton("Mis Eventos");
    private JButton btnAdmin = new JButton("Admin");
    private JLabel lblEstado = new JLabel();
    private Usuario usuario;
    
    public MainForm(Usuario u){
        this.usuario = u;
        Dimension dmPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dmPantalla.width - dmVentana.width) / 2;
        int y = (dmPantalla.height - dmVentana.height) / 2;
        this.setLocation(x, y);
        this.setSize(dmVentana);
        this.setTitle("Formulario Principal");
        
        
        BoxLayout bx = new BoxLayout(pnlCabecera, BoxLayout.X_AXIS);
        pnlCabecera.setLayout(bx);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 32));
        pnlCabecera.add(lblTitulo);
        pnlCabecera.setBackground(Colores.FONDO_TITULO);
        pnlCabecera.add(Box.createHorizontalGlue());
        
        ImageIcon imIcon = new ImageIcon("imagenes/" 
                + u.getFoto());
        Image imag = imIcon.getImage();
        imag = imag.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imIcon.setImage(imag);
        lblFoto.setIcon(imIcon);
        lblFoto.setHorizontalAlignment(JLabel.CENTER);
        pnlUsuario.add(lblFoto, BorderLayout.CENTER);
        
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarEventoForm(u).setVisible(true);
                MainForm.this.dispose();
            }
        });
        
        btnNuevoEventos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new NuevoEventoForm(u).setVisible(true);
                MainForm.this.dispose();
            }
            
        });
        
        pnlUsuario.add(btnSalir, BorderLayout.SOUTH);
        pnlUsuario.setBackground(Colores.FONDO_TITULO);
        pnlUsuario.setMaximumSize(new Dimension(50,100));
        pnlCabecera.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        pnlCabecera.add(pnlUsuario);
        
        pnlOpciones.add(btnNuevoEventos);
        pnlOpciones.add(btnMisEventos);
        pnlOpciones.add(btnBuscar);
        pnlOpciones.add(btnAdmin);
        pnlOpciones.setBackground(Colores.FONDO_DATOS);
        pnlOpciones.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        
        Date hoy = new Date();
        DateFormat df = DateFormat.getDateTimeInstance();
        lblEstado.setText(df.format(hoy));
        lblEstado.setFont(new Font("Arial",Font.ITALIC,10));
        pnlEstado.add(lblEstado);
        pnlEstado.setBackground(Colores.FONDO_TITULO);
        pnlEstado.setBorder(BorderFactory.createLoweredBevelBorder());
        
        this.add(pnlCabecera, BorderLayout.NORTH);
        this.add(pnlOpciones,BorderLayout.CENTER);
        this.add(pnlEstado, BorderLayout.SOUTH);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    
}
