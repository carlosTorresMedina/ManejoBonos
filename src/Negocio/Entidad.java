/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Base_datos.Base_datos;
import Dto.EntidadDto;
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
public class Entidad {

    /**
     * modifica los datos de la entidad especificada
     *
     * @param codigop
     * @param codigo
     * @param nombre
     * @param sufijo
     * @param direccion
     * @param telefono
     * @return
     */
    public boolean modifircarEntidad(int codigop, int codigo, String nombre, String sufijo, String direccion, String telefono) {
        PreparedStatement stm = null;
        boolean exito = false;
        Connection con = null;
        try {
           con=Base_datos.getInstance().dataSource.getConnection();
            stm = con.prepareStatement("UPDATE bonos.Entidad "
                    + "SET Codigo_entidad=" + codigo
                    + ",Nombre_entidad ='" + nombre
                    + "',Sufijo=' "
                    + sufijo
                    + "',Direccion='"
                    + direccion
                    + "',Telefono= '"
                    + telefono
                    + "' WHERE Codigo_entidad = " + codigop);
            int total = stm.executeUpdate();
            if (total > 0) {
                exito = true;

            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
        }
        return exito;
    }

    /**
     * borra una entidad de la base de datos
     *
     * @param codigo
     * @return
     */
    public boolean borrarEntidad(int codigo) {
        Connection con = null;
        String instruccion = "delete from bonos.Entidad where Codigo_entidad =" + codigo;
        boolean val = false;
        PreparedStatement pre;
        try {
            con=Base_datos.getInstance().dataSource.getConnection();
            pre = con.prepareStatement(instruccion);
            pre.execute();
            val = true;
            pre.close();
        } catch (SQLException ex) {
            System.err.println("Error 1 :" + ex.toString());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
        }

        return val;
    }

    /**
     * consulta todas las entidades en la base de datos
     *
     * @return
     */
    public ArrayList<EntidadDto> consultarEntidad() {
        Connection con = null;
        ArrayList<EntidadDto> lista = new ArrayList();
        try {
            con=Base_datos.getInstance().dataSource.getConnection();
            String sql = "SELECT Codigo_entidad,Nombre_entidad,Sufijo,Direccion,Telefono FROM Bonos.Entidad";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                EntidadDto dto = new EntidadDto();
                dto.setCodigo_entidad(rs.getInt(1) + "");
                dto.setNombre_entidad(rs.getString(2));
                dto.setSufijo(rs.getString(3));
                dto.setDireccion(rs.getString(4));
                dto.setTelefono(rs.getString(5));
                lista.add(dto);

            }
           str.close();
           rs.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
         finally {
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

    /**
     * consulta una entidad
     *
     * @param codigo
     * @return
     */
    public ArrayList<String> consultarEntidadPersonalido(int codigo) {
        Connection con =null;
        ArrayList<String> lista = new ArrayList();
        try {
            con=Base_datos.getInstance().dataSource.getConnection();
            String sql = "SELECT Codigo_entidad,"
                    + "Nombre_entidad,"
                    + "Sufijo,"
                    + "Direccion,"
                    + "Telefono"
                    + " FROM Bonos.Entidad WHERE Codigo_entidad=" + codigo;
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {

                lista.add(rs.getInt(1) + "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getString(4) + "-" + rs.getString(5));

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

    /**
     * registra entidades en la base de datos
     *
     * @param codigo
     * @param nombre_entidad
     * @param sufijo
     * @param direccion
     * @param telefono
     * @return
     */
    public boolean registrarEntidad(int codigo, String nombre_entidad, String sufijo, String direccion, String telefono) {

        boolean x = false;
        Connection con = null;

        try {
            con=Base_datos.getInstance().dataSource.getConnection();
            PreparedStatement stm = con.prepareStatement("insert into bonos.Entidad (Codigo_entidad,Nombre_entidad,Sufijo,Direccion,Telefono) VALUES (?,?,?,?,?)");
            stm.setInt(1, codigo);
            stm.setString(2, nombre_entidad);
            stm.setString(3, sufijo);
            stm.setString(4, direccion);
            stm.setString(5, telefono);

            int total = stm.executeUpdate();
            if (total > 0) {
                x = true;
            }
            stm.close();
        } catch (SQLException ex) {
            System.err.println("error1 " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
        }
        return x;

    }

}
