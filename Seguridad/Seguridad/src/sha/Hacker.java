/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sha;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iñigo
 */
public class Hacker {
    private static BufferedReader br;
    private static FileReader fr;
    private static BufferedReader brPasswd;
    private static FileReader frPasswd;
    public static void main(String[] args) {
        try {
            String linea;
            /*br = new BufferedReader(
                    new FileReader("palabras.txt"));*/
            /*br = new BufferedReader(
                    new FileReader("digitos.txt"));*/
            br = new BufferedReader(
                    new FileReader("letras.txt"));
            HashMap<String,String> hm = new HashMap<>();
            System.out.println("Generando mapa de claves...");
            while ((linea = br.readLine()) != null){
                int pos = linea.indexOf(",");
                String clave = linea.substring(0,pos);
                String valor = linea.substring(pos+1);
                hm.put(clave, valor);
            }
            
            brPasswd = new BufferedReader(
                    new FileReader("passwd"));
            while ((linea = brPasswd.readLine()) != null){
                int pos = linea.indexOf(",");
                String usuario = linea.substring(0,pos);
                String hash = linea.substring(pos+1);
                if (hm.containsValue(hash)){
                    for (Map.Entry<String, String> entry : hm.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (value.equals(hash)){
                            System.out.println(usuario + ":" + key);
                            break;
                        }
                    }   
                    /*Set<Map.Entry<String,String>> set = hm.entrySet();
                    Iterator<Map.Entry<String,String>> it = set.iterator();
                    boolean encontrado = false;
                    while (it.hasNext() && !encontrado) {
                        Map.Entry<String,String> entrada = it.next();
                        String key = entrada.getKey();
                        String value = entrada.getValue();
                        if (value.equals(hash)){
                            System.out.println(usuario + ":" + key);
                            encontrado = true;
                        }
                    }*/
                } else {
                    System.out.println(usuario + ": NO ENCONTRADO");
                }
            }
            brPasswd.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hacker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Hacker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
