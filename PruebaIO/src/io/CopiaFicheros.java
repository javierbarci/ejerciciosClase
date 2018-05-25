/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Iñigo
 */
public class CopiaFicheros extends JFrame {

    private JTextArea txaDatos = new JTextArea();
    private JButton btnCopiar = new JButton("Copiar");
    private JButton btnSalir = new JButton("Salir");
    private JScrollPane jspDatos = new JScrollPane(txaDatos);
    private JPanel pnlBotones = new JPanel();

    private Dimension dmVentana = new Dimension(400, 300);

    public CopiaFicheros() {
        Dimension dmPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dmPantalla.width - dmVentana.width) / 2;
        int y = (dmPantalla.height - dmVentana.height) / 2;
        this.setSize(dmVentana);
        this.setLocation(x, y);
        this.setTitle("Copia Ficheros");

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiar();
            }
        });

        pnlBotones.add(btnCopiar);
        pnlBotones.add(btnSalir);

        this.add(jspDatos);
        this.add(pnlBotones, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class Copia extends Thread {

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            File origen;
            File destino;
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.setMultiSelectionEnabled(false);
            jfc.setDialogTitle("Fichero a copiar...");
            jfc.setApproveButtonText("Seleccionar");
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                origen = jfc.getSelectedFile();
                System.out.println("Copiando : " + origen);
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.setDialogTitle("Destino");
                jfc.setApproveButtonText("Copiar");
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    destino = jfc.getSelectedFile();
                    System.out.println("A : " + destino);
                    try {
                        FileInputStream fis = new FileInputStream(origen);
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        File fDestino = new File(destino, origen.getName());
                        FileOutputStream fos = new FileOutputStream(fDestino);
                        BufferedOutputStream bos = new BufferedOutputStream(fos);
                        int b = bis.read();
                        while (b != -1) {
                            bos.write(b);
                            b = bis.read();
                        }
                        /*int b;
                        while ((b = fis.read()) != -1) {
                            fos.write(b);
                        }*/
                        bos.flush();
                        bos.close();
                        fos.close();
                        bis.close();
                        fis.close();
                        System.out.println("Fichero Copiado!");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Se ha producido un error");
                    }
                }
            }
        }

    }

    private void copiar() {
        new Copia().start();
        /*new Thread(){
            @Override
            public void run() {
                super.run(); //To change body of generated methods, choose Tools | Templates.
                // Código
            }
        }.start();*/
    }

    public static void main(String[] args) {
        new CopiaFicheros();
        /*JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.setMultiSelectionEnabled(true);
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File[] seleccionados = jfc.getSelectedFiles();
            String s = "";
            for (File f : seleccionados){
                s += f.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, 
                "Seleccionado : \n" + s );
        }*/
 /*File origen;
        File destino;
        JFileChooser jfc = new JFileChooser(); 
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setMultiSelectionEnabled(false);
        jfc.setDialogTitle("Fichero a copiar...");
        jfc.setApproveButtonText("Seleccionar");
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            origen = jfc.getSelectedFile();
            System.out.println("Copiando : " + origen);
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.setDialogTitle("Destino");
            jfc.setApproveButtonText("Copiar");
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                destino = jfc.getSelectedFile();
                System.out.println("A : " + destino);
                try {
                    FileInputStream fis = new FileInputStream(origen);
                    File fDestino = new File(destino,origen.getName());
                    FileOutputStream fos = new FileOutputStream(fDestino);
                    int b = fis.read();
                    while (b != -1){
                        fos.write(b);
                        b = fis.read();
                    }
                    //int b;
                    while ((b = fis.read()) != -1){
                       fos.write(b); 
                    }//
                    fos.flush();
                    fos.close();
                    fis.close();
                    System.out.println("Fichero Copiado!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un error");
                }
            }
        }*/
    }
}
