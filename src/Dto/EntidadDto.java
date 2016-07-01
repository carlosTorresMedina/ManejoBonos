/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author carlos
 */
public class EntidadDto {

    private String codigo_entidad;
    private String nombre_entidad;
    private String sufijo;
    private String direccion;
    private String telefono;
    ArrayList<BonoDto> bonos;

    public EntidadDto() {
        this.bonos = new ArrayList();
    }

    public ArrayList<BonoDto> getBonos() {
        return bonos;
    }

    public void setBonos(ArrayList<BonoDto> bonos) {
        this.bonos = bonos;
    }

    public String totalPago() {
        double valor0 = 0;
        for (BonoDto x : bonos) {
            valor0 += x.getValor();
        }
        DecimalFormat formateador = new DecimalFormat("###,###.##");

        String valor = formateador.format(valor0);
        return valor;
    }

    public String getCodigo_entidad() {
        return codigo_entidad;
    }

    public void setCodigo_entidad(String codigo_entidad) {
        this.codigo_entidad = codigo_entidad;
    }

    public String getNombre_entidad() {
        return nombre_entidad;
    }

    public void setNombre_entidad(String nombre_entidad) {
        this.nombre_entidad = nombre_entidad;
    }

    public String getSufijo() {
        return sufijo;
    }

    public void setSufijo(String sufijo) {
        this.sufijo = sufijo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
