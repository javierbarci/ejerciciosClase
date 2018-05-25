/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Iñigo
 */
public class PruebaResultSet {

    public static void main(String[] args) {
        Connection cnn = null;
        Statement stmt = null;
        ResultSet rst = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            cnn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL",
                    "scott", "tiger");
            stmt = cnn.createStatement();

            /*rst = stmt.executeQuery(
                    "Select * From Usuarios Order by nombre");
            String listado = "<html><h1>Listado de Usuarios</h1>"
                    + "<table border=1><tr><th>Id.</th><th>Nombre</th><th>Email</th>"
                    + "<th></th></tr>";
            while (rst.next()) {
                listado += "<tr><td>" + rst.getInt(1) + "</td>"
                        + "<td>" + rst.getString("nombre") + "</td>"
                        + "<td>" + rst.getString("email") + "</td>"
                        + "<td><img src='file:imagenes/" + rst.getString("foto")
                        + "' width=100 height=100></td></tr>";
            }
            listado += "</table></html>";
            JOptionPane.showMessageDialog(null, listado,
                    "Listado de Usuarios",
                    JOptionPane.PLAIN_MESSAGE);

            rst = stmt.executeQuery("Select * From Lugares Order By nombre");
            listado = "<html><h1>Listado de Lugares</h1>"
                    + "<table border=1><tr><th>Id.</th><th>Nombre</th><th>Dirección</th>"
                    + "<th>Localidad</th><th></th></tr>";
            while (rst.next()) {
                listado += "<tr><td>" + rst.getInt("idLugar") + "</td>"
                        + "<td>" + rst.getString("nombre") + "</td>"
                        + "<td>" + rst.getString("direccion") + "</td>"
                        + "<td>" + rst.getString("localidad") + "</td>"
                        + "<td><img src='file:imagenes/lugares/" + rst.getString("foto")
                        + "' width=100 height=100></td></tr>";
            }
            listado += "</table></html>";
            JOptionPane.showMessageDialog(null, listado,
                    "Listado de Lugares",
                    JOptionPane.PLAIN_MESSAGE);

            String localidad = JOptionPane.showInputDialog("Localidad");
            rst = stmt.executeQuery("Select * From Lugares "
                    + "where lower(localidad)='" + localidad.toLowerCase() + "'");
            if (rst.next()) {
                listado = "<html><h1>Listado de Lugares</h1>"
                        + "<table border=1><tr><th>Id.</th><th>Nombre</th><th>Dirección</th>"
                        + "<th>Localidad</th><th></th></tr>";
                do {
                    listado += "<tr><td>" + rst.getInt("idLugar") + "</td>"
                            + "<td>" + rst.getString("nombre") + "</td>"
                            + "<td>" + rst.getString("direccion") + "</td>"
                            + "<td>" + rst.getString("localidad") + "</td>"
                            + "<td><img src='file:imagenes/lugares/" + rst.getString("foto")
                            + "' width=100 height=100></td></tr>";
                } while (rst.next());
                listado += "</table></html>";
                JOptionPane.showMessageDialog(null, listado,
                        "Listado de Lugares",
                        JOptionPane.PLAIN_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null,
                        "No hay ningún lugar en " + localidad);
            }
            */
            String usuario = JOptionPane.showInputDialog("Usuario");
            String password = JOptionPane.showInputDialog("Contraseña");
            /*rst = stmt.executeQuery("Select * From Usuarios "
                    + "where email='" + usuario + "' and "
                    + "password='" + password + "'");*/
            // Con PreparedStatement
            PreparedStatement pst = cnn.prepareStatement(
                "Select * From Usuarios " +
                "where email=? and password=?");
            pst.setString(1, usuario);
            pst.setString(2, password);
            rst = pst.executeQuery();
            if (rst.next()) {
                JOptionPane.showMessageDialog(null,
                        "Bienvenido!!!");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Usuario / Contraseña erróneos ");
            }
            pst.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PruebaResultSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PruebaResultSet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rst.close();
            } catch (Exception ex) {
            }
            try {
                stmt.close();
            } catch (Exception ex) {
            }
            try {
                cnn.close();
            } catch (Exception ex) {
            }
        }
    }
}
