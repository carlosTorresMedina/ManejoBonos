/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFormulario;

import Dto.BonoDto;
import Dto.EntidadDto;
import javax.swing.table.DefaultTableModel;
import Pdf.*;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos
 */
public class GuiReporte extends javax.swing.JFrame {

    private GuiMenu menu;
    public ArrayList<Object> datos;
    private boolean estado;

    /**
     * Creates new form GuiReporte
     */
    public GuiReporte(GuiMenu menu) {
        initComponents();
        this.btnGenerarReporte.setBackground(Color.white);
        this.btnConsultar.setBackground(Color.white);
        this.btnCancelar.setBackground(Color.white);
        this.btnGenerarReporte.setEnabled(false);
        this.menu = menu;
        this.estado = false;

        this.generarTablaEstandar();

    }

    /**
     * carga los comopnentes iniciales
     */
    public void cargarComponentes() {

        this.cmdEntidad.removeAllItems();
        ArrayList<EntidadDto> lista = new ArrayList();
        lista = this.menu.getPrincipal().getFachada().consultarEntidades();
        this.cmdEntidad.addItem("NO SELECCION");
        this.cmdEntidad.addItem("TODAS");
        ArrayList<EntidadDto> entidad = (ArrayList<EntidadDto>) this.datos.get(0);
        for (EntidadDto x : entidad) {
            if (!x.getBonos().isEmpty()) {
                this.cmdEntidad.addItem(x.getCodigo_entidad() + "-" + x.getNombre_entidad());
            }
        }
    }

    /**
     * carga la tabla de manera estandar
     */
    private void generarTablaEstandar() {

        String[] nomC = new String[7];
        nomC[0] = "Fecha";
        nomC[1] = "D.I usuario";
        nomC[2] = "Usuario";
        nomC[3] = "Entidad";
        nomC[4] = "Serial";
        nomC[5] = "Fecha pago";
        nomC[6] = "Valor";
        this.jTableReporte.setModel(new DefaultTableModel(null, nomC));
    }

    /**
     * carga la tabla de manera general
     */
    private void cargarTabla() {

        this.btnGenerarReporte.setEnabled(true);
        String[] nomC = new String[7];
        nomC[0] = "Fecha bono";
        nomC[1] = "D.I usuario";
        nomC[2] = "Usuario";
        nomC[3] = "Entidad";
        nomC[4] = "Serial";
        nomC[5] = "Fecha pago";
        nomC[6] = "Valor";
        ArrayList<EntidadDto> entidades = (ArrayList<EntidadDto>) this.datos.get(0);
        int valor = this.calcularTotalRegistro(entidades);
        String datos[][] = new String[valor][7];
        int i = 0;
        while (i < valor) {
            for (EntidadDto x : entidades) {

                for (BonoDto b : x.getBonos()) {
                    datos[i][0] = b.getFecha_creacion();
                    datos[i][1] = b.getNuip_paga();
                    datos[i][2] = b.getNombre_paga() + " " + b.getApellido_paga();
                    datos[i][3] = b.getCodigo_entidad();
                    datos[i][4] = b.getSerial();
                    datos[i][5] = b.getFechaPago();
                    datos[i][6] = b.getValorFormatedo();
                    i++;
                }
            }
        }
        ArrayList<String> extras = (ArrayList<String>) this.datos.get(1);
        this.jTableReporte.setModel(new DefaultTableModel(datos, nomC));
        this.LabelTotal.setText("TOTAL PAGO: " + extras.get(2));
    }

    /**
     * carga la tabla en base a una entidad
     *
     * @param entidad
     */
    private void cargarTablaPersonalizada(String entidad) {
        this.btnGenerarReporte.setEnabled(true);
        String[] nomC = new String[7];
        nomC[0] = "Fecha bono";
        nomC[1] = "D.I usuario";
        nomC[2] = "Usuario";
        nomC[3] = "Entidad";
        nomC[4] = "Serial";
        nomC[5] = "Fecha pago";
        nomC[6] = "Valor";
        ArrayList<EntidadDto> entidades = (ArrayList<EntidadDto>) this.datos.get(0);
        EntidadDto dato = this.entidadEspecifica(entidades, entidad);
        int valor = dato.getBonos().size();
        String datos[][] = new String[valor][7];
        int i = 0;
        while (i < valor) {

            for (BonoDto b : dato.getBonos()) {

                datos[i][0] = b.getFecha_creacion();
                datos[i][1] = b.getNuip_paga();
                datos[i][2] = b.getNombre_paga() + " " + b.getApellido_paga();
                datos[i][3] = b.getCodigo_entidad();
                datos[i][4] = b.getSerial();
                datos[i][5] = b.getFechaPago();
                datos[i][6] = b.getValorFormatedo();
                i++;
            }
        }
        ArrayList<String> extras = (ArrayList<String>) this.datos.get(1);
        this.jTableReporte.setModel(new DefaultTableModel(datos, nomC));
        this.LabelTotal.setText("TOTAL PAGO: " + dato.totalPago());
    }

    /**
     * retorna una entidad especifica
     *
     * @param lista
     * @param entidad
     * @return
     */
    private EntidadDto entidadEspecifica(ArrayList<EntidadDto> lista, String entidad) {

        for (EntidadDto x : lista) {

            if ((x.getCodigo_entidad() + "-" + x.getNombre_entidad()).equals(entidad)) {
                return x;
            }
        }
        return null;
    }

    private int calcularTotalRegistro(ArrayList<EntidadDto> entidades) {
        int valor = 0;
        for (EntidadDto x : entidades) {
            valor += x.getBonos().size();
        }
        return valor;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooserInicial = new com.toedter.calendar.JDateChooser();
        jDateChooserFinal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        btnGenerarReporte = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        labelRegresar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReporte = new javax.swing.JTable();
        btnConsultar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        LabelTotal = new javax.swing.JLabel();
        cmdEntidad = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("FECHA INICIAL:");

        btnGenerarReporte.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGenerarReporte.setText("Generar Reporte Pdf");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("FECHA FINAL:");

        labelRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/regresar.jpg"))); // NOI18N
        labelRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelRegresarMouseClicked(evt);
            }
        });

        jTableReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableReporte.setEnabled(false);
        jScrollPane1.setViewportView(jTableReporte);

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Reporte de bonos pagados");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        cmdEntidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEntidadActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Empresas con bonos pagados en las fechas establecidas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(labelRegresar)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jDateChooserFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addGap(77, 77, 77))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(LabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(365, 365, 365))))
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel4)
                .addGap(33, 33, 33)
                .addComponent(cmdEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRegresar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnConsultar)
                            .addComponent(jDateChooserFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        // TODO add your handling code here
        this.guardarArchivo();
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void guardarArchivo() {

        JFileChooser file = new JFileChooser();
        file.showSaveDialog(this);
        File guarda = file.getSelectedFile();
        boolean resultado = false;
        if (guarda != null) {

            if (guarda.exists()) {

                if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this, "El archivo existe,deseas reemplazarlo?", "Titulo", JOptionPane.YES_NO_OPTION)) {
                    resultado = this.menu.getPrincipal().getFachada().generarDocumentoPdf(this.menu.getReporte().datos, guarda.getAbsolutePath());
                } else {

                    this.reiniciarPantalla();
                    return;
                }

            } else {
                resultado = this.menu.getPrincipal().getFachada().generarDocumentoPdf(this.menu.getReporte().datos, guarda.getAbsolutePath());
            }
            if (resultado) {
                JOptionPane.showMessageDialog(null,
                        "El archivo se a guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "ya existe un documento con ese nombre \n el documento esta en uso");
            }
        }

        this.reiniciarPantalla();
    }

    /**
     * reinicia la pantalla
     */
    private void reiniciarPantalla() {

        this.generarTablaEstandar();
        this.LabelTotal.setText("");

        this.cmdEntidad.removeAllItems();
        this.btnGenerarReporte.setEnabled(false);
        this.btnConsultar.setEnabled(true);
        this.jDateChooserFinal.setEnabled(true);
        this.jDateChooserInicial.setEnabled(true);
    }

    private void labelRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelRegresarMouseClicked
        // TODO add your handling code here:

        this.cmdEntidad.removeAllItems();
        this.generarTablaEstandar();
        this.LabelTotal.setText("");
        this.jDateChooserInicial.setEnabled(true);
        this.jDateChooserFinal.setEnabled(true);
        this.btnGenerarReporte.setEnabled(true);
        this.btnConsultar.setEnabled(true);
        this.setVisible(false);
        if (menu.tipo.equals("0")) {
            this.menu.getPagarBono().setVisible(true);
            return;
        }
        this.menu.setVisible(true);

    }//GEN-LAST:event_labelRegresarMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "debe cerrar sesion");
    }//GEN-LAST:event_formWindowClosing

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.generarTablaEstandar();
        this.jDateChooserFinal.setEnabled(true);
        this.jDateChooserInicial.setEnabled(true);
        this.btnConsultar.setEnabled(true);
        this.btnGenerarReporte.setEnabled(false);
        this.LabelTotal.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
        Date fechaI = this.jDateChooserInicial.getDate();
        Date fechaF = this.jDateChooserFinal.getDate();

        if (fechaI == null || fechaF == null) {
            JOptionPane.showMessageDialog(null, "debe llenar los campos");
            return;
        }
        if (fechaI.compareTo(fechaF) > 0) {
            JOptionPane.showMessageDialog(null, "la fecha inicial no puede ser mayor a la final");
            return;
        }
        this.datos = this.menu.getPrincipal().getFachada().generarReporte(fechaI, fechaF);
        ArrayList<EntidadDto> entidad = (ArrayList<EntidadDto>) this.datos.get(0);
        if (entidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No existen bonos pagados en el rango de fecha especificado");
            return;
        }
        this.cargarComponentes();
        this.cargarTabla();
        this.jDateChooserFinal.setEnabled(false);
        this.jDateChooserInicial.setEnabled(false);
        this.btnConsultar.setEnabled(false);
        this.btnGenerarReporte.setEnabled(true);
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void cmdEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEntidadActionPerformed
        // TODO add your handling code here:
        if (this.cmdEntidad.getSelectedItem() != null) {
            String codigo = this.cmdEntidad.getSelectedItem().toString();
            if (this.cmdEntidad.getItemCount() >= 1) {
                if (codigo.equals("TODAS")) {
                    this.cargarTabla();
                    return;
                }
                if (!codigo.equals("NO SELECCION")) {
                    this.cargarTablaPersonalizada(codigo);
                }
            }
        }
    }//GEN-LAST:event_cmdEntidadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JComboBox cmdEntidad;
    private com.toedter.calendar.JDateChooser jDateChooserFinal;
    private com.toedter.calendar.JDateChooser jDateChooserInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReporte;
    private javax.swing.JLabel labelRegresar;
    // End of variables declaration//GEN-END:variables
}
