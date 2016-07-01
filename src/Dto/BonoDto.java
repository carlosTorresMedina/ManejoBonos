/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.text.DecimalFormat;

/**
 *
 * @author carlos
 */
public class BonoDto {

    private String serial;
    private double valor;
    private String estado;
    private String fechaPago;
    private String codigo_entidad;
    private String usuario_creacion;
    private String usuario_pago;
    private String fecha_creacion;
    private String nuip_creador;
    private String nombre_creador;
    private String apellido_creador;
    private String nuip_paga;
    private String nombre_paga;
    private String apellido_paga;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public double getValor() {
        return valor;
    }

    /**
     * retorna el valor en formato
     * @return 
     */
    public String getValorFormatedo() {
        DecimalFormat formateador = new DecimalFormat("###,###.##");

        String valor = formateador.format(this.valor);
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getCodigo_entidad() {
        return codigo_entidad;
    }

    public void setCodigo_entidad(String codigo_entidad) {
        this.codigo_entidad = codigo_entidad;
    }

    public String getUsuario_creacion() {
        return usuario_creacion;
    }

    public void setUsuario_creacion(String usuario_creacion) {
        this.usuario_creacion = usuario_creacion;
    }

    public String getUsuario_pago() {
        return usuario_pago;
    }

    public void setUsuario_pago(String usuario_pago) {
        this.usuario_pago = usuario_pago;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getNuip_creador() {
        return nuip_creador;
    }

    public void setNuip_creador(String nuip_creador) {
        this.nuip_creador = nuip_creador;
    }

    public String getNombre_creador() {
        return nombre_creador;
    }

    public void setNombre_creador(String nombre_creador) {
        this.nombre_creador = nombre_creador;
    }

    public String getApellido_creador() {
        return apellido_creador;
    }

    public void setApellido_creador(String apellido_creador) {
        this.apellido_creador = apellido_creador;
    }

    public String getNuip_paga() {
        return nuip_paga;
    }

    public void setNuip_paga(String nuip_paga) {
        this.nuip_paga = nuip_paga;
    }

    public String getNombre_paga() {
        return nombre_paga;
    }

    public void setNombre_paga(String nombre_paga) {
        this.nombre_paga = nombre_paga;
    }

    public String getApellido_paga() {
        return apellido_paga;
    }

    public void setApellido_paga(String apellido_paga) {
        this.apellido_paga = apellido_paga;
    }

}
