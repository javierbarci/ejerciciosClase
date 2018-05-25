/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Iñigo
 */
public class Ventana extends JFrame {

    private JLabel lblLogo = new JLabel();
    private JLabel lblCabecera = new JLabel("Introduzca sus datos");
    private JLabel lblNombre = new JLabel("Nombre");
    private JTextField txtNombre = new JTextField();
    private JLabel lblEdad = new JLabel("Edad");
    private JTextField txtEdad = new JTextField();
    private JLabel lblDireccion = new JLabel("Dirección");
    private JTextField txtDireccion = new JTextField();
    private JButton btnAceptar = new JButton("Aceptar");
    private JButton btnLimpiar = new JButton("Limpiar");
    private JButton btnSalir = new JButton("Salir");

    private JPanel pnlSur = new JPanel();
    private JPanel pnlCentro = new JPanel(new GridLayout(3, 2, 0, 10));
    private JPanel pnlNorte = new JPanel();
    private JPanel pnlOeste = new JPanel();

    private Dimension dmVentana = new Dimension(500, 250);

    public Ventana() {
        this.setTitle("Ventana de Prueba");
        Dimension dmPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dmPantalla.width - dmVentana.width) / 2;
        int y = (dmPantalla.height - dmVentana.height) / 2;
        this.setLocation(x, y);
        this.setSize(dmVentana);
        //this.setLayout(new BorderLayout(10, 10));
        //lblNombre.setOpaque(true);
        //lblNombre.setBackground(Color.red);
        //this.add(lblNombre,BorderLayout.NORTH);
        //txtNombre.setPreferredSize(new Dimension(0, 50));
        EnfoqueListener ef = new EnfoqueListener();
        pnlCentro.add(lblNombre);
        txtNombre.addFocusListener(ef);
        pnlCentro.add(txtNombre);
        pnlCentro.add(lblEdad);
        txtEdad.addFocusListener(ef);
        pnlCentro.add(txtEdad);
        pnlCentro.add(lblDireccion);
        txtDireccion.addFocusListener(ef);
        pnlCentro.add(txtDireccion);
        pnlCentro.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEtchedBorder(),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)));

        //btnAceptar.setPreferredSize(new Dimension(100,100));
        btnAceptar.addMouseListener(new AceptarListener());
        
        btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(Ventana.this, "Pressed!!");
            }
            
        });
        
        BoxLayout bl = new BoxLayout(pnlSur, BoxLayout.X_AXIS);
        pnlSur.setLayout(bl);
        //pnlSur.add(Box.createRigidArea(new Dimension(30,0)));
        pnlSur.add(btnAceptar);
        pnlSur.add(Box.createHorizontalGlue());
        btnLimpiar.addMouseListener(new LimpiarAdapter());
        btnLimpiar.setMnemonic('l');
        pnlSur.add(btnLimpiar);
        pnlSur.add(Box.createHorizontalGlue());
        btnSalir.addActionListener(new salirAction());
        btnSalir.setMnemonic('S');
        pnlSur.add(btnSalir);
        pnlSur.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        /*lblCabecera.setOpaque(true);
        lblCabecera.setBackground(Color.red);
        lblCabecera.setHorizontalAlignment(JLabel.CENTER);*/
        lblCabecera.setFont(new Font("Arial", Font.PLAIN, 22));
        pnlNorte.add(lblCabecera);
        pnlNorte.setBorder(BorderFactory.createEtchedBorder());

        ImageIcon imIcon = new ImageIcon("logo.png");
        Image imag = imIcon.getImage();
        imag = imag.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        pnlOeste.setBackground(Color.WHITE);
        pnlOeste.setBorder(BorderFactory.createEtchedBorder());
        imIcon.setImage(imag);
        lblLogo.setIcon(imIcon);
        pnlOeste.add(lblLogo);

        this.add(pnlSur, BorderLayout.SOUTH);
        this.add(pnlCentro, BorderLayout.CENTER);
        this.add(pnlNorte, BorderLayout.NORTH);
        this.add(pnlOeste, BorderLayout.WEST);
        
        this.addWindowListener(new VentanaAdapter());

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public static void main(String[] args) {
        Ventana v = new Ventana();
        v.setVisible(true);
    }

    class AceptarListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            String listado = "<html>";
            listado += "<table border=1>";
            listado += "<tr><td>Nombre</td>";
            listado += "<td>" + txtNombre.getText() + "</td></tr>";
            listado += "<tr><td>Dirección</td>";
            listado += "<td>" + txtDireccion.getText() + "</td></tr>";
            listado += "<tr><td>Edad</td>";
            listado += "<td>" + txtEdad.getText() + "</td></tr>";
            listado += "</table></html>";
            JOptionPane.showMessageDialog(
                    Ventana.this,
                    listado,
                    "Datos",
                    JOptionPane.PLAIN_MESSAGE);
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    class LimpiarAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            txtNombre.setText("");
            txtDireccion.setText("");
            txtEdad.setText("");
            txtNombre.requestFocus();
        }

    }

    class salirAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            salir();
        }

    }

    private void salir() {
        if (JOptionPane.showConfirmDialog(Ventana.this,
                "Salir de la Aplicación",
                "Salir",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    class VentanaAdapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            salir();
        }

    }

    class EnfoqueListener implements FocusListener{

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource() == txtNombre){
                txtNombre.setBackground(Color.YELLOW);
            } else if (e.getSource() == txtDireccion){
                txtDireccion.setBackground(Color.YELLOW);
            } else if (e.getSource() == txtEdad){
                txtEdad.setBackground(Color.YELLOW);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextField txt = (JTextField) e.getSource();
            txt.setBackground(Color.WHITE);
        }
        
    }
    
    
}
