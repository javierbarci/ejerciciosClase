/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajdbc;

import java.sql.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Iñigo
 */
public class ListadoEventosFecha {

    public static void main(String[] args) {
        Connection cnn = null;
        PreparedStatement pst = null;
        PreparedStatement pstAsistentes = null;
        ResultSet rst = null;
        ResultSet rstAsistentes = null;
        java.util.Date fechaInicial = null, fechaFinal = null;
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            cnn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:ORCL",
                    "scott",
                    "tiger");
            pst = cnn.prepareStatement(
                    "Select e.idEvento,u.nombre usuario,e.titulo,e.descripcion, "
                    + "c.nombre categoria, l.nombre lugar, e.fecha, e.precio, e.foto "
                    + "From Eventos e, Categorias c, Lugares l, Usuarios u "
                    + "Where e.idCategoria = c.idCategoria and e.idLugar = l.idLugar "
                    + "and e.idUsuario = u.idUsuario and e.fecha between ? and ? "
                    + "Order by e.fecha desc");
            pstAsistentes = cnn.prepareStatement(
                    "Select nombre " +
                    "From usuarioEventos ue, Usuarios u " +
                    "Where ue.idUsuario = u.idUsuario " +
                    "and ue.idEvento = ? "+
                    "order by nombre");
            boolean ok = false;
            do {
                String resp = JOptionPane.showInputDialog("Fecha Inicial (dd/mm/aaaa)");
                try {
                    fechaInicial = df.parse(resp);
                    ok = true;
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,
                        "El formato de fecha debe ser dd/mm/aaaa",
                        "Error en fecha",
                        JOptionPane.ERROR_MESSAGE);
                }
            } while (!ok);
            do {
                String resp = JOptionPane.showInputDialog("Fecha Final (dd/mm/aaaa)");
                try {
                    fechaFinal = df.parse(resp);
                    ok = true;
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,
                        "El formato de fecha debe ser dd/mm/aaaa",
                        "Error en fecha",
                        JOptionPane.ERROR_MESSAGE);
                }
            } while (!ok);
            if (fechaInicial.after(fechaFinal)){
                JOptionPane.showMessageDialog(null,
                    "La fecha inicial no puede ser posterior a la final",
                    "Error en fecha",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                pst.setDate(1, new java.sql.Date(fechaInicial.getTime()));
                pst.setDate(2, new java.sql.Date(fechaFinal.getTime()));
                rst = pst.executeQuery();
                if (!rst.next()){
                    JOptionPane.showMessageDialog(null,
                        "No hay eventos entre el " + df.format(fechaInicial) +
                        " y el " + fechaFinal,
                        "No hay datos",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String listado = "<html><h2>Eventos entre el " + df.format(fechaInicial) +
                        " y el " + df.format(fechaFinal) + "</h2>";
                    listado += "<table border=1><tr><th>Id.Evento</th><th>Usuario</th>" +
                        "<th>Titulo</th><th>Descripción</th><th>Categoría</th><th>Lugar</th>" +
                        "<th>Fecha</th><th>Precio</th><th>Foto</th><th>Asistentes</th></tr>";
                    do {
                        pstAsistentes.setInt(1, rst.getInt("idEvento"));
                        rstAsistentes = pstAsistentes.executeQuery();
                        listado += "<tr><td>" + rst.getInt("idEvento") + "</td>" +
                            "<td>" + rst.getString("usuario") + "</td>" +
                            "<td>" + rst.getString("titulo") + "</td>" +    
                            "<td>" + rst.getString("descripcion").substring(0,20) + "</td>" +
                            "<td>" + rst.getString("categoria") + "</td>" +
                            "<td>" + rst.getString("lugar") + "</td>" +
                            "<td>" + df.format(rst.getDate("fecha")) + "</td>" +
                            "<td>" + nf.format(rst.getDouble("precio")) + "</td>" +
                            "<td><img src='file:imagenes/eventos/" + rst.getString("foto") + "' width=50 height=50></td>";
                        String asistentes = "";
                        while (rstAsistentes.next()){
                            asistentes += rstAsistentes.getString("nombre") +
                               "<br>";
                        }
                        listado += "<td>" + asistentes + "</td></tr>";
                    } while (rst.next());
                    listado += "</table></html>";
                    JOptionPane.showMessageDialog(null, 
                        listado,
                        "Listado de Eventos",
                        JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListadoEventosFecha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListadoEventosFecha.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
