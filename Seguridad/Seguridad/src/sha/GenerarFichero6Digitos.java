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
public class GenerarFichero6Digitos {

    private static BufferedWriter bw;
    private static FileWriter fw;

    public static void main(String[] args) {
        try {
            bw = new BufferedWriter(
                    new FileWriter("digitos.txt"));
            String numero = "";
            System.out.println("Creando Fichero");
            for (int digitos = 1; digitos < 7; digitos++) {
                for (int i = 0; i < 10; i++) {
                    numero = "" + i;
                    if (digitos == 1) {
                        bw.write(numero + "," + util.SHA.getSHA(numero) + "\n");
                    }
                    for (int j = 0; j < 10 && digitos > 1; j++) {
                        numero = "" + i + j;
                        if (digitos == 2) {
                            bw.write(numero + "," + util.SHA.getSHA(numero) + "\n");
                        }
                        for (int k = 0; k < 10 && digitos > 2; k++) {
                            numero = "" + i + j + k;
                            if (digitos == 3) {
                                bw.write(numero + "," + util.SHA.getSHA(numero) + "\n");
                            }
                            for (int l = 0; l < 10 && digitos > 3; l++) {
                                numero = "" + i + j + k + l;
                                if (digitos == 4) {
                                    bw.write(numero + "," + util.SHA.getSHA(numero) + "\n");
                                }
                                for (int m = 0; m < 10 && digitos > 4; m++) {
                                    numero = "" + i + j + k + l + m;
                                    if (digitos == 5) {
                                        bw.write(numero + "," + util.SHA.getSHA(numero) + "\n");
                                    }
                                    for (int n = 0; n < 10 && digitos > 5; n++) {
                                        numero = "" + i + j + k + l + m + n;
                                        bw.write(numero + "," + util.SHA.getSHA(numero) + "\n");
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println(((digitos + 1)  * 10 / 6) + "%");
            }
            bw.flush();
            bw.close();
            System.out.println("Fichero Generado!");
        } catch (IOException ex) {
            Logger.getLogger(GenerarFichero6Digitos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
