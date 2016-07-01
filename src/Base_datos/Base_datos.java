/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base_datos;

import com.mysql.jdbc.Connection;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author carlos torres Esta clase utiliza el patron singleton
 */
public class Base_datos {

    public DataSource dataSource;
    private String driver = "org.gjt.mm.mysql.Driver";//es el driver varia segun la base de datos que usemos
    private String nombreBD;
    private String host;
    private String user;
    private String contra;

    private static Base_datos instance;

    private Base_datos() {
        this.lecturaArchivoServidor();
        this.inicializarDataSource();
    }

    public static Base_datos getInstance() {
        if (instance == null) {
            instance = new Base_datos();
        }
        return instance;
    }

    /**
     * genera la url de la base de datos la cual esta conformada por el host y
     * el nombre de la base de datos
     *
     * @return
     */
    private String getUrl() {
        return "jdbc:mysql://" + host + "/" + this.nombreBD;
    }

    /**
     * inicializa el dataSource
     */
    private void inicializarDataSource() {
//        String url = "jdbc:mysql://sandbox2.ufps.edu.co/ufps_76";
//        String contra = "ufps_29";
//        String user = "ufps_76";

        BasicDataSource basic = new BasicDataSource();
        basic.setDriverClassName(driver);
        basic.setUsername(user);
        basic.setPassword(contra);
        basic.setUrl(this.getUrl());
        basic.setMaxActive(5);
        this.dataSource = basic;
    }

    /**
     * lee el archivo de config.txt
     *
     * @return
     */
    private void lecturaArchivoServidor() {
        String mensaje = "";
        try {
            FileInputStream fstream = new FileInputStream("CONFIGURACION/config.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                mensaje += strLine;
            }
            in.close();
            fstream.close();
            br.close();
        } catch (Exception e) {

            System.err.println("Error: " + e.getMessage());
        }
        String v[] = mensaje.split(";");
        this.nombreBD = v[0];
        this.host = v[1];
        this.user = v[2];
        this.contra = v[3];

    }

}
