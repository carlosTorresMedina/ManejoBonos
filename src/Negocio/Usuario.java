/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Base_datos.Base_datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos torres
 */
public class Usuario {

    /**
     * inicia sesion en el sistema
     *
     * @param Nuip
     * @param password
     * @return
     */
    public ArrayList<String> iniciarSesion(String Nuip, String password) {
        Connection con = null;
        ArrayList<String> lista = new ArrayList();
        try {
            con=Base_datos.getInstance().dataSource.getConnection();
            String sql = "SELECT Nuip,Nombres,Apellidos,Password,Tipo  FROM Bonos.Usuario WHERE Nuip=? AND Password=?";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            str.setString(1, Nuip);
            str.setString(2, password);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {

                lista.add(rs.getInt(1) + "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getString(4) + "-" + rs.getInt(5));

            }
           str.close();
           rs.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
        }
        return lista;
    }

}
