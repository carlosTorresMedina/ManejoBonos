/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Base_datos.Base_datos;
import Dto.BonoDto;
import Dto.EntidadDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class Bono {

    /**
     * genera un reporte de bonos pagados atravez de una fecha incial y una
     * fecha final
     *
     * @param Inicial
     * @param Final
     * @return retorna una lista de listas con un tamaño de dos listas en la
     * lista en la pocision 0 tiene los datos en la lista en la pocision 1 tiene
     * una lista con las fechas iniciales y finales junto con la sumatoria de
     * los bonos
     */
    public ArrayList<Object> generarReporte(Date Inicial, Date Final, ArrayList<EntidadDto> entidades) {
        Connection link = null;
        ArrayList<Object> listaTotal = new ArrayList();
        ArrayList<String> lista1 = new ArrayList();
        ArrayList<String> lista2 = new ArrayList();

        int suma = 0;
        try {
            link = Base_datos.getInstance().dataSource.getConnection();
            java.sql.Date fechaI = new java.sql.Date(Inicial.getTime());
            java.sql.Date fechaF = new java.sql.Date(Final.getTime());

            for (EntidadDto x : entidades) {
                String sql = "SELECT "
                        + "bono.Fecha_creacion,"
                        + "User1.Nuip,"
                        + "user1.Nombres,"
                        + "user1.Apellidos,"
                        + "bono.Serial, "
                        + "bono.Fecha_pago,"
                        + "bono.Valor "
                        + "FROM"
                        + " bono join usuario as user1 on bono.usuario_pago = user1.nuip"
                        + " where bono.Fecha_pago>='" + fechaI + "' and bono.Fecha_pago<='" + fechaF + "' and " + "bono.Estado='P' and bono.Codigo_entidad=" + x.getCodigo_entidad();

                PreparedStatement str;
                str = link.prepareStatement(sql);
                ResultSet rs = str.executeQuery();

                while (rs.next()) {
                    BonoDto dto = new BonoDto();
                    dto.setCodigo_entidad(x.getCodigo_entidad() + "-" + x.getNombre_entidad());
                    dto.setFecha_creacion(rs.getDate(1) + "");
                    dto.setNuip_paga(rs.getString(2));
                    dto.setNombre_paga(rs.getString(3));
                    dto.setApellido_paga(rs.getString(4));
                    dto.setSerial(rs.getString(5) + "");
                    dto.setFechaPago(rs.getString(6));
                    dto.setValor(rs.getDouble(7));

                    suma += rs.getDouble(7);
                    x.getBonos().add(dto);

                }

            }
        } catch (SQLException ex) {
            System.err.println("Errr" + ex.getMessage());

        } finally {
            if (link != null) {
                try {
                    link.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Bono.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        DateFormat formato = DateFormat.getDateInstance();
        DateFormat formato1 = DateFormat.getDateInstance();
        String fecha1 = formato1.format(Inicial);
        lista2.add(fecha1);
        DateFormat formato2 = DateFormat.getDateInstance();
        String fecha2 = formato2.format(Final);
        lista2.add(fecha2);
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        String sumaFinal = formateador.format(suma);
        lista2.add(sumaFinal);

        listaTotal.add(entidades);
        listaTotal.add(lista2);

        return listaTotal;
    }

    /**
     * actualiza un bono como pagado en el sistema
     *
     * @param fecha
     * @param usuario
     * @param serial
     * @return
     */
    public boolean pagarBono(String usuario, String serial) {
        PreparedStatement stm = null;
        boolean exito = false;
        Connection con = null;
        try {
            con = Base_datos.getInstance().dataSource.getConnection();
            Date fecha = new Date();
            java.sql.Date fechaI = new java.sql.Date(fecha.getTime());

            stm = con.prepareStatement("UPDATE bonos.bono SET Usuario_pago='" + usuario + "',Fecha_pago='" + fechaI + "',Estado='P' " + " WHERE Serial = '" + serial + "'");
            int total = stm.executeUpdate();
            if (total > 0) {
                exito = true;

            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Bono.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return exito;
    }

    /**
     * consulta un determinado bono en la base de datos
     *
     * @param Serial
     * @return
     */
    public ArrayList<String> consultarBonoDisponible(String Serial) {
        Connection con = null;
        ArrayList<String> lista = new ArrayList();
        try {
            con = Base_datos.getInstance().dataSource.getConnection();
            String sql = "SELECT bono.Fecha_creacion,bono.Usuario_creacion,bono.Codigo_entidad,bono.Serial,bono.Valor,bono.Usuario_pago,bono.Fecha_pago,bono.Estado,entidad.Nombre_entidad FROM bonos.bono join bonos.entidad on bono.Codigo_entidad=entidad.Codigo_entidad where Serial='" + Serial + "' and Estado='D'";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {

                lista.add(rs.getDate(1) + "@" + rs.getString(2) + "@" + rs.getInt(3) + "@" + rs.getString(4) + "@" + rs.getInt(5) + "@" + rs.getString(6) + "@" + rs.getDate(7) + "@" + rs.getString(8) + "@" + rs.getString(9));

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
                    Logger.getLogger(Bono.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return lista;
    }

    /**
     * borra los bonos enrolados a una determinada entidad
     *
     * @param entidad
     * @return
     */
    public boolean borrarBonosEnroladoEntidad(int entidad) {
        System.out.print(entidad);
        Connection con = null;
        String instruccion = "delete from bonos.bono where Codigo_entidad =" + entidad;
        boolean val = false;
        PreparedStatement pre;
        try {
            con = Base_datos.getInstance().dataSource.getConnection();
            pre = con.prepareStatement(instruccion);
            pre.execute();
            val = true;
            pre.close();
        } catch (SQLException ex) {
            Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Bono.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return val;
    }

    /**
     * borra el bono enrolado con el determinado serial
     *
     * @param serial
     * @return
     */
    public boolean borrarBonoEnrolado(String serial) {
        System.out.print(serial);
        Connection con = null;
        String instruccion = "delete from bonos.bono where Serial ='" + serial + "'";
        boolean val = false;
        PreparedStatement pre;
        try {
            con = Base_datos.getInstance().dataSource.getConnection();
            pre = con.prepareStatement(instruccion);
            pre.execute();
            val = true;
            pre.close();
        } catch (SQLException ex) {
            Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Bono.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return val;
    }

    /**
     * consultar bonos enrolados
     *
     * @return
     */
    public ArrayList<String> consultarBonosEnrolados() {
        Connection con = null;
        ArrayList<String> lista = new ArrayList();
        try {
            con = Base_datos.getInstance().dataSource.getConnection();
            String sql = "SELECT "
                    + "bono.Fecha_creacion,"
                    + "User1.Nuip,"
                    + "user1.Nombres,"
                    + "user1.Apellidos,"
                    + "entidad.Codigo_entidad,"
                    + "entidad.Nombre_entidad,"
                    + "bono.Serial,"
                    + "user2.Nuip,"
                    + "user2.Nombres,"
                    + "user2.Apellidos,"
                    + "bono.Fecha_pago,"
                    + "bono.Estado,"
                    + "bono.Valor "
                    + "FROM"
                    + " bono join usuario as user1 on bono.usuario_creacion = user1.nuip "
                    + "join entidad on bono.codigo_entidad=entidad.codigo_entidad "
                    + "left join usuario as user2 on bono.usuario_pago=user2.nuip";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                DecimalFormat formateador = new DecimalFormat("###,###.##");

                String valor = formateador.format(rs.getInt(13));
                lista.add(rs.getDate(1) + "@"
                        + rs.getString(2) + "@"
                        + rs.getString(3) + " " + rs.getString(4) + "@"
                        + rs.getInt(5) + "-" + rs.getString(6) + "@"
                        + rs.getString(7) + "@"
                        + rs.getString(8) + "@"
                        + rs.getString(9) + " " + rs.getString(10) + "@"
                        + rs.getDate(11) + "@"
                        + rs.getString(12) + "@"
                        + valor
                );
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
                    Logger.getLogger(Bono.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }

    /**
     * metodo que enrola un bono en la base de datos
     *
     * @return
     */
    public boolean crearEnrolarBono(int codigo_entidad, String serial, int valor, String usuario) {
        boolean x = false;
        Connection con = null;
        Date fecha = new Date();
        java.sql.Date fechaI = new java.sql.Date(fecha.getTime());
        try {
            con = Base_datos.getInstance().dataSource.getConnection();
            PreparedStatement stm = con.prepareStatement("insert into bonos.Bono (Serial,Codigo_entidad,Valor,Fecha_creacion,Usuario_creacion,Estado) VALUES (?,?,?,?,?,?)");
            stm.setString(1, serial);
            stm.setInt(2, codigo_entidad);
            stm.setInt(3, valor);
            stm.setDate(4, fechaI);
            stm.setString(5, usuario);
            stm.setString(6, "D");

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
                    Logger.getLogger(Bono.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return x;

    }

}
