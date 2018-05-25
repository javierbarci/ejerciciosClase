/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sha;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iñigo
 */
public class GenerarLetras {

    private static BufferedWriter bw;
    private static FileWriter fw;

    public static void main(String[] args) {
        try {
            bw = new BufferedWriter(
                    new FileWriter("letras.txt"));
            String caracteres = "ABCDEFGHIJKLMÑOPQRSTUVWXYZ" +
                    "abcdefghijklmnñopqrstuvwxyz" +
                    "!@#$%^&*0123456789áéíóúÁÉÍÓÚ";
            String texto = "";
            System.out.println("Creando Fichero");
            for (int letras = 1; letras < 4; letras++) {
                for (int i = 0; i < caracteres.length(); i++) {
                    texto = "" + caracteres.charAt(i);
                    if (letras == 1) {
                        bw.write(texto + "," + util.SHA.getSHA(texto) + "\n");
                    }
                    for (int j = 0; j < caracteres.length() 
                            && letras > 1; j++) {
                        texto = "" + caracteres.charAt(i) + 
                                caracteres.charAt(j);
                        if (letras == 2) {
                            bw.write(texto + "," + util.SHA.getSHA(texto) + "\n");
                        }
                        for (int k = 0; k < caracteres.length() 
                                && letras > 2; k++) {
                            texto = "" + caracteres.charAt(i) + 
                                caracteres.charAt(j) +
                                caracteres.charAt(k);
                            if (letras == 3) {
                                bw.write(texto + "," + util.SHA.getSHA(texto) + "\n");
                            }
                            for (int l = 0; l < caracteres.length() && letras > 3; l++) {
                                texto = "" + caracteres.charAt(i) + 
                                caracteres.charAt(j) +
                                caracteres.charAt(k) + 
                                caracteres.charAt(l);
                                if (letras == 4) {
                                    bw.write(texto + "," + util.SHA.getSHA(texto) + "\n");
                                }
                                for (int m = 0; m < caracteres.length() && letras > 4; m++) {
                                    texto = "" + caracteres.charAt(i) + 
                                        caracteres.charAt(j) +
                                        caracteres.charAt(k) + 
                                        caracteres.charAt(l) +
                                        caracteres.charAt(m);
                                    if (letras == 5) {
                                        bw.write(texto + "," + util.SHA.getSHA(texto) + "\n");
                                    }
                                    /*for (int n = 0; n < 10 && letras > 5; n++) {
                                        texto = "" + i + j + k + l + m + n;
                                        bw.write(texto + "," + util.SHA.getSHA(texto) + "\n");
                                    }*/
                                }
                            }
                        }
                    }
                }
                System.out.println(((letras + 1)  * 10 / 6) + "%");
            }
            bw.flush();
            bw.close();
            System.out.println("Fichero Generado!");
        } catch (IOException ex) {
            Logger.getLogger(GenerarLetras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
