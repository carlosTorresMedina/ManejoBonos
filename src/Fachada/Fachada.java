/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fachada;

import Dto.EntidadDto;
import Negocio.Bono;
import Negocio.Entidad;
import Negocio.Usuario;
import java.util.ArrayList;
import java.util.Date;
import Pdf.Pdf;

/**
 *
 * @author carlos torres
 */
public class Fachada {

    private Bono bono;
    private Entidad entidad;
    private Usuario usuario;

    public Fachada() {
        this.bono = new Bono();
        this.entidad = new Entidad();
        this.usuario = new Usuario();
    }

   

    /**
     * genera documento pdf
     *
     * @param datos
     * @param ruta
     */
    public boolean generarDocumentoPdf(ArrayList<Object> datos, String ruta) {
        Pdf pdf = new Pdf();
        return pdf.generarPDFFactura(datos, ruta);
    }

    /**
     * genera un reporte a partir de una fecha inicial y una fecha final
     *
     * @param fechaI
     * @param fechaF
     * @return
     */
    public ArrayList<Object> generarReporte(Date fechaI, Date fechaF) {
        ArrayList<EntidadDto> lista = this.entidad.consultarEntidad();
        return this.bono.generarReporte(fechaI, fechaF, lista);

    }

    /**
     * inicia sesion en el sistema
     *
     * @return
     */
    public ArrayList<String> iniciarSesion(String Nuip, String password) {

        return this.usuario.iniciarSesion(Nuip, password);
    }

    /**
     * registra entidades en el sistema
     *
     * @param codigo
     * @param nombre
     * @param sufijo
     * @param direccion
     * @param telefono
     * @return
     */
    public boolean registrarEntidad(int codigo, String nombre, String sufijo, String direccion, String telefono) {

        return this.entidad.registrarEntidad(codigo, nombre, sufijo, direccion, telefono);
    }

    /**
     * consutar todas las entidades
     *
     * @return
     */
    public ArrayList<EntidadDto> consultarEntidades() {

        return this.entidad.consultarEntidad();
    }

    /**
     * consulta todos los bonos enrolados en el sistema
     *
     * @return
     */
    public ArrayList<String> consultarBonosEnrolados() {

        return bono.consultarBonosEnrolados();

    }

    /**
     * modifica entidades en el sistema
     *
     * @param codigo
     * @return
     */
    public boolean modificarEntidad(int codigop, int codigo, String nombre, String sufijo, String direccion, String telefono) {

        return this.entidad.modifircarEntidad(codigop, codigo, nombre, sufijo, direccion, telefono);

    }

    /**
     * consulta una entidad especifica en el sistema a partir del codigo
     *
     * @param codigo
     * @return
     */
    public ArrayList<String> consultarEntidadPersonalizado(int codigo) {

        return this.entidad.consultarEntidadPersonalido(codigo);
    }

    /**
     * consulta un bono especifico en la base de datos
     *
     * @param serial
     * @return
     */
    public ArrayList<String> consultarBonoDisponible(String serial) {

        return bono.consultarBonoDisponible(serial);
    }

    /**
     * actualiza en la base de datos un bono como pagado
     *
     * @param fechaPago
     * @param usuario
     * @param serial
     * @return
     */
    public boolean pagarBono(String usuario, String serial) {

        return bono.pagarBono(usuario, serial);
    }

    /**
     * borra entidades del sistema
     *
     * @param codigo
     * @return
     */
    public boolean borrarEntidad(int codigo) {

        boolean a = entidad.borrarEntidad(codigo);
        return a;
    }

    /**
     * enrola bonos en el sistema
     *
     * @param fecha
     * @param codigo_entidad
     * @param serial
     * @param valor
     * @return
     */
    public boolean crearEnrolarBono(int codigo_entidad, String serial, int valor, String usuario) {
        return bono.crearEnrolarBono(codigo_entidad, serial, valor, usuario);
    }

    /**
     * borra bonos enrolados en el sistema
     *
     * @param codigo
     * @return
     */
    public boolean BorrarBonoEnrolado(String serial) {
        return bono.borrarBonoEnrolado(serial);
    }

}
