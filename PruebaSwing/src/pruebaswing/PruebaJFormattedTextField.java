/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaswing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Iñigo
 */
public class PruebaJFormattedTextField extends JFrame{
    private Dimension dmVentana = new Dimension(300,200);
    private JLabel lblFecha = new JLabel("Fecha");
    private DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
    private NumberFormat nfDecimal = NumberFormat.getInstance();
    private NumberFormat nfPorcentaje = NumberFormat.getPercentInstance();
    private JFormattedTextField ftxFecha = new JFormattedTextField(df);
    private JLabel lblNumero = new JLabel("Valor con decimales");
    private JFormattedTextField ftxNumero = new JFormattedTextField(nfDecimal);
    private JLabel lblPorcentaje = new JLabel("Porcentaje");
    private JFormattedTextField ftxPorcentaje = new JFormattedTextField(nfPorcentaje);
    private JPanel pnlDatos = new JPanel(new GridLayout(3,2,0,5));
    private JPanel pnlBotones = new JPanel();
    private JButton btnVer = new JButton("Ver");
    private JButton btnSalir = new JButton("Salir");
    
    public PruebaJFormattedTextField(){
        nfPorcentaje.setMinimumFractionDigits(2);
        Dimension dmPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dmPantalla.width - dmVentana.width) / 2;
        int y = (dmPantalla.height - dmVentana.height) / 2;
        this.setLocation(x, y);
        this.setSize(dmVentana);
        this.setTitle("Prueba JFormattedtextField");
        
        pnlDatos.add(lblFecha);
        pnlDatos.add(ftxFecha);
        pnlDatos.add(lblNumero);
        pnlDatos.add(ftxNumero);
        pnlDatos.add(lblPorcentaje);
        pnlDatos.add(ftxPorcentaje);
        
        pnlDatos.setBorder(
                BorderFactory.createEmptyBorder(10, 10,10,10));
        
        pnlBotones.add(btnVer);
        btnVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date f = (Date) ftxFecha.getValue();
                /*if (f == null){
                    
                }*/
                Number n = (Number) ftxNumero.getValue();
                double d = n.doubleValue();
                n = (Number) ftxPorcentaje.getValue();
                double dp = n.doubleValue();
                JOptionPane.showMessageDialog(
                    PruebaJFormattedTextField.this,
                    "Fecha : " + f +
                    "\nNúmero : " + d +
                    "\nPorcentaje : " + dp);
            }
        });
        pnlBotones.add(btnSalir);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        this.add(pnlDatos);
        this.add(pnlBotones, BorderLayout.SOUTH);
        
        ftxFecha.setValue(new Date());
        ftxNumero.setValue(0);
        ftxPorcentaje.setValue(0.0);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new PruebaJFormattedTextField();
    }
    
}
