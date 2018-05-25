/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.views;

import com.alee.laf.WebLookAndFeel;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import net.zabalburu.zabalevent.modelo.Usuario;
import net.zabalburu.zabalevent.service.ZabalEventService;
import net.zabalburu.zabalevent.util.Colores;

/**
 *
 * @author Iñigo
 */
public class LoginForm extends JFrame{
    private JLabel lblTitulo = new JLabel("ZabalEvent");
    private JLabel lblImagen = new JLabel();
    private JLabel lblUsuario = new JLabel("Usuario");
    private JTextField txtUsuario = new JTextField();
    private JLabel lblPassword = new JLabel("Contraseña");
    private JPasswordField pwdPassword = new JPasswordField();
    private JLabel lblErrores = new JLabel();
    private JButton btnEntrar = new JButton("Entrar");
    private JButton btnSalir = new JButton("Salir");
    
    private JPanel pnlTitulo = new JPanel();
    private JPanel pnlImagen = new JPanel();
    private JPanel pnlBotones = new JPanel();
    private JPanel pnlLogin = new JPanel(new BorderLayout());
    private JPanel pnlDatos = new JPanel(new GridLayout(2,2,0,10));
    private JPanel pnlErrores = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    private Dimension dmVentana = new Dimension(400,220);
    
    private ZabalEventService service = new ZabalEventService();
    
    public LoginForm(){
        Dimension dmPantalla = Toolkit.getDefaultToolkit()
                .getScreenSize();
        int x = (dmPantalla.width - dmVentana.width) / 2;
        int y = (dmPantalla.height - dmVentana.height) / 2;
        this.setLocation(x, y);
        this.setSize(dmVentana);
        this.setTitle("ZabalEvent");
        
        lblTitulo.setFont(new Font("Arial",Font.PLAIN,20));
        pnlTitulo.setBorder(BorderFactory.createEtchedBorder());
        pnlTitulo.setBackground(Colores.FONDO_TITULO);
        pnlTitulo.add(lblTitulo);
        
        pnlImagen.setBorder(BorderFactory.createEtchedBorder());
        ImageIcon imIc = new ImageIcon("imagenes/logo.png");
        Image image = imIc.getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imIc.setImage(image);
        lblImagen.setIcon(imIc);
        pnlImagen.setBackground(Colores.FONDO_DATOS);
        pnlImagen.add(lblImagen);
        
        pnlDatos.add(lblUsuario);
        pnlDatos.add(txtUsuario);
        pnlDatos.add(lblPassword);
        pnlDatos.add(pwdPassword);
        pnlDatos.setBackground(Colores.FONDO_DATOS);
        pnlLogin.add(pnlDatos, BorderLayout.CENTER);
        
        lblErrores.setForeground(Color.red);
        pnlErrores.add(lblErrores);
        pnlErrores.setBackground(Colores.FONDO_DATOS);
        pnlLogin.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 0, 10)));
        pnlLogin.add(pnlErrores, BorderLayout.SOUTH);
        pnlLogin.setBackground(Colores.FONDO_DATOS);
        
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        pnlBotones.add(btnEntrar);
        
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pnlBotones.add(btnSalir);
        pnlBotones.setBorder(BorderFactory.createEtchedBorder());
        pnlBotones.setBackground(Colores.FONDO_TITULO);
        
        this.add(pnlTitulo, BorderLayout.NORTH);
        this.add(pnlImagen, BorderLayout.WEST);
        this.add(pnlLogin, BorderLayout.CENTER);
        this.add(pnlBotones, BorderLayout.SOUTH);
        
        this.setResizable(false);
        
        this.getRootPane().setDefaultButton(btnEntrar);
        //pwdPassword.setEchoChar('\u03C0');
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        IconFontSwing.register(FontAwesome.getIconFont());
        /*try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            new LoginForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        for(LookAndFeelInfo lf : UIManager.getInstalledLookAndFeels()){
            System.out.println(lf.getName() + " : " + 
                    lf.getClassName());
        }*/
        WebLookAndFeel.install ();
        new LoginForm();
    }
    
    private void login(){
        if (txtUsuario.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, 
                "Debe especificarse el usuario",
                "Faltan datos",
                JOptionPane.ERROR_MESSAGE);
            txtUsuario.requestFocus();
        } else if (pwdPassword.getPassword().length == 0){
            JOptionPane.showMessageDialog(this, 
                "Debe especificarse la contraseña",
                "Faltan datos",
                JOptionPane.ERROR_MESSAGE);
            pwdPassword.requestFocus();
        } else {
            String pwd = new String(pwdPassword.getPassword());
            Usuario u = service.login(txtUsuario.getText(), pwd);
            if (u == null){
                lblErrores.setText("Usuario o contraseña erróneos");
                pwdPassword.setText("");
                pwdPassword.requestFocus();
            } else {
                new MainForm(u);
                this.dispose();
            }
        }
    }
    
}
