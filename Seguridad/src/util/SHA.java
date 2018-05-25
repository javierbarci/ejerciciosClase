/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iñigo
 */
public class SHA {
    public static String getSHA(String password, String salto){
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String clave = password + salto;
            md.update(clave.getBytes("UTF-8")); 
            byte[] digest = md.digest();
            hash = String.format("%064x", new java.math.BigInteger(1, digest));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SHA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SHA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    
    public static String getSHA(String password){
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8")); 
            byte[] digest = md.digest();
            hash = String.format("%064x", new java.math.BigInteger(1, digest));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SHA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SHA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    
    public static String getSalt(){
        SecureRandom sr = new SecureRandom();
        byte[] bytes = new byte[20];
        sr.nextBytes(bytes);
        return String.format("%064x", new java.math.BigInteger(1, bytes));
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(SHA.getSHA("1",SHA.getSalt()).length());
        System.out.println(SHA.getSalt().length());
        
    }
    
}
