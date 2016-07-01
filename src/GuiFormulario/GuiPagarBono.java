/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFormulario;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos
 */
public class GuiPagarBono extends javax.swing.JFrame {
    
    private GuiMenu menu;
    private double pagar;
    private double auxiliar;

    /**
     * Creates new form GuiCrearEnrolarBono
     */
    public GuiPagarBono(GuiMenu menu) {
        this.pagar = 0;
        
        initComponents();
        this.menu = menu;
        this.btnPagarBono.setBackground(Color.white);
        this.btnVerificar.setBackground(Color.white);
        this.btnLimpiar.setBackground(Color.white);
        this.txtValor.setEnabled(false);
        this.txtEntidad.setEnabled(false);
        this.labelTotal.setText("Total a pagar: " + pagar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        txtSerial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        labelRegresar = new javax.swing.JLabel();
        btnPagarBono = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtEntidad = new javax.swing.JTextField();
        btnVerificar = new javax.swing.JButton();
        labelTotal = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuGenerarReporte = new javax.swing.JMenu();
        MenuReporte = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Serial:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel5.setText("Valor:");

        txtValor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });

        txtSerial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSerialKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSerialKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel6.setText("Pago de bonos");

        labelRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/regresar.jpg"))); // NOI18N
        labelRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelRegresarMouseClicked(evt);
            }
        });

        btnPagarBono.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnPagarBono.setText("Pagar bono");
        btnPagarBono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarBonoActionPerformed(evt);
            }
        });
        btnPagarBono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPagarBonoKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Entidad:");

        txtEntidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        labelTotal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        labelTotal.setText("Total a pagar: ");

        btnLimpiar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimpiar.setText("Limpiar total pago");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenuGenerarReporte.setBackground(new java.awt.Color(255, 255, 255));
        jMenuGenerarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/Reporte.png"))); // NOI18N
        jMenuGenerarReporte.setText("Reporte");
        jMenuGenerarReporte.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuGenerarReporteActionPerformed(evt);
            }
        });

        MenuReporte.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MenuReporte.setText("Generar reporte ");
        MenuReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuReporteActionPerformed(evt);
            }
        });
        jMenuGenerarReporte.add(MenuReporte);

        jMenuBar1.add(jMenuGenerarReporte);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelRegresar)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEntidad, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(txtValor)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnPagarBono, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVerificar))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEntidad)
                            .addComponent(jLabel7))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotal)
                    .addComponent(btnLimpiar))
                .addGap(45, 45, 45)
                .addComponent(btnPagarBono)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void verificar() {
        String serial = this.txtSerial.getText();
        if (serial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "debe digitar el serial");
            return;
        }
        ArrayList<String> lista = this.menu.getPrincipal().getFachada().consultarBonoDisponible(serial);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "no existe un bono enrolado con el serial \n o el estado del bono ya esta pago");
            this.txtSerial.setText("");
            return;
        }
        this.txtSerial.setEnabled(false);
        String v[] = lista.get(0).split("@");
        this.auxiliar = Double.parseDouble(v[4]);
        this.txtValor.setText(this.formatoDecimal(auxiliar));
        this.txtEntidad.setText(v[2] + "-" + v[8]);
        this.btnPagarBono.requestFocus();
    }

    /**
     * metodo que da formato a un valor decimal ejemplo 40000 lo convierte:
     * 40,000
     *
     * @param valorR
     * @return
     */
    private String formatoDecimal(double valorR) {
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        
        String valor = formateador.format(valorR);
        return valor;
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "debe cerrar sesion");
    }//GEN-LAST:event_formWindowClosing

    private void labelRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelRegresarMouseClicked
        // TODO add your handling code here:

        this.setVisible(false);
        this.pagar = 0;
        this.labelTotal.setText("Total a pagar: " + this.pagar);
        if (menu.tipo.equals("0")) {
            this.menu.getPrincipal().setVisible(true);
            return;
        }
        this.menu.setVisible(true);
    }//GEN-LAST:event_labelRegresarMouseClicked

    private void btnPagarBonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarBonoActionPerformed
        // TODO add your handling code here:
        this.pagarBono();

    }//GEN-LAST:event_btnPagarBonoActionPerformed

    /**
     * limpia los campos de la base de datos y se enccarga de dejar la pantalla
     * de manera limpia
     */
    public void limpiarCamposBono() {
        
        this.txtSerial.setEnabled(true);
        this.txtEntidad.setText("");
        this.txtSerial.setText("");
        this.txtValor.setText("");
        this.txtSerial.requestFocus();
    }

    /**
     * se encarga de ejecutar la accion pagar bono
     */
    private void pagarBono() {
        String serial = this.txtSerial.getText();
        String usuario = this.menu.Nuip;
        if (serial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "debe digitar un serial");
            return;
        }
        
        boolean val = this.menu.getPrincipal().getFachada().pagarBono(usuario, serial);
        
        if (val) {
            this.pagar += auxiliar;
            this.labelTotal.setText("Total a pagar: " + this.formatoDecimal(pagar));
            JOptionPane.showMessageDialog(null, "el bono se pago  con exito");
            
        } else {
            JOptionPane.showMessageDialog(null, "existe un error contactar a un programador");
            
        }
        this.txtSerial.setText("");
        this.txtValor.setText("");
        this.txtEntidad.setText("");
        
        this.txtSerial.setEnabled(true);
        this.txtSerial.requestFocus();
    }

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();
        String cadena = this.txtValor.getText();
        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtValorKeyTyped

    private void txtSerialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerialKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.verificar();
        }
    }//GEN-LAST:event_txtSerialKeyPressed

    private void btnPagarBonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPagarBonoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.pagarBono();
        }
    }//GEN-LAST:event_btnPagarBonoKeyPressed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        // TODO add your handling code here:
        this.verificar();
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void txtSerialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerialKeyReleased
        // TODO add your handling code here:
        String cadena = (this.txtSerial.getText()).toUpperCase();
        this.txtSerial.setText(cadena);
    }//GEN-LAST:event_txtSerialKeyReleased

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        this.pagar = 0;
        this.labelTotal.setText("Total a pagar: " + pagar);
       
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void MenuReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuReporteActionPerformed
  System.out.print("hla");
         if ( this.menu.getReporte() == null) {
           this.menu.setReporte(new GuiReporte(this.menu));;
          this.menu.getReporte().setTitle(this.getTitle());
            this.menu.getPrincipal().personalizar(this.menu.getReporte());
        }

        this.setVisible(false);
        this.menu.getReporte().setVisible(true);
    }//GEN-LAST:event_MenuReporteActionPerformed

    private void jMenuGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuGenerarReporteActionPerformed
        // TODO add your handling code here:
      
      
    }//GEN-LAST:event_jMenuGenerarReporteActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuReporte;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnPagarBono;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuGenerarReporte;
    private javax.swing.JLabel labelRegresar;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JTextField txtEntidad;
    private javax.swing.JTextField txtSerial;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
