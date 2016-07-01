/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pdf;

import Dto.BonoDto;
import Dto.EntidadDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos torres
 */
public class Pdf {

    private Document documento;
    private FileOutputStream ficheroPdf;

    /**
     * genera un archivo pdf a partir de una ruta
     *
     * @param ruta
     */
    private boolean generarArchvo(String ruta) {
        boolean dato = false;
        documento = new Document();
        try {
            if (ruta.contains(".pdf")) {

                ficheroPdf = new FileOutputStream(ruta);
            } else {
                ficheroPdf = new FileOutputStream(ruta + ".pdf");
            }

            PdfWriter.getInstance(
                    documento,
                    ficheroPdf
            ).setInitialLeading(20);
            dato = true;
        } catch (Exception ex) {

            System.err.println("Error :" + ex.getMessage());

        }
        return dato;
    }

    /**
     * genera el documento pdf de reportes
     *
     * @param datos
     * @param ruta
     */
    public boolean generarPDFFactura(ArrayList<Object> datos, String ruta) {
        boolean dato = this.generarArchvo(ruta);
        ArrayList<EntidadDto> lista1 = (ArrayList<EntidadDto>) datos.get(0);
        ArrayList<EntidadDto> lista2 = (ArrayList<EntidadDto>) datos.get(1);
        try {
            documento.open();
            //aqui agregamos todo el contenido del PDF...

            documento.add(new Paragraph("REPORTE DE BONOS PAGADOS"));
            String fecha = formatoFecha();
            documento.add(new Paragraph("Fecha de generacion de reporte: " + fecha));
            documento.add(new Paragraph("Bonos pagados en el rango de fecha :" + lista2.get(0) + "  -  " + lista2.get(1)));
            for (EntidadDto entidad : lista1) {
                if (!entidad.getBonos().isEmpty()) {

                    documento.add(new Paragraph("\n"));
                    documento.add(new Paragraph("Empresa :" + entidad.getCodigo_entidad() + "-" + entidad.getNombre_entidad()));
                    documento.add(new Paragraph("\n"));
                    PdfPTable tabla = new PdfPTable(7);
                    tabla.setWidthPercentage(100f);
                    //el numero indica la cantidad de Columnas
                    tabla = formatoColumna(tabla);
                    for (BonoDto x : entidad.getBonos()) {

                        tabla.addCell(x.getFecha_creacion());
                        tabla.addCell(x.getNuip_paga());
                        tabla.addCell(x.getNombre_paga());
                        tabla.addCell(x.getCodigo_entidad());
                        tabla.addCell(x.getSerial());
                        tabla.addCell(x.getFechaPago());
                        tabla.addCell(x.getValorFormatedo());

                    }
                    documento.add(tabla);
                    documento.add(new Paragraph("\n"));
                    documento.add(new Paragraph("                                                                                                                  TOTAL PAGO :" + entidad.totalPago()));
                }
            }  
                   documento.add(new Paragraph("\n"));
                   documento.add(new Paragraph("                                                             TOTAL A PAGAR EN LA FECHA ESTIPULADA :" + lista2.get(2)));
                   documento.close();

        } catch (Exception ex) {
            dato = false;
            System.out.println("Error :" + ex.toString());
        }
        return dato;
    }

    /**
     * da forrmato a las columnas
     *
     * @param tabla
     * @return
     */
    private PdfPTable formatoColumna(PdfPTable tabla) {
        PdfPCell columna[] = new PdfPCell[7];

        columna[0] = new PdfPCell(new Paragraph("Fecha bono"));
        columna[0].setBackgroundColor(BaseColor.GRAY);

        columna[1] = new PdfPCell(new Paragraph("D.I usuario"));
        columna[1].setBackgroundColor(BaseColor.GRAY);

        columna[2] = new PdfPCell(new Paragraph("Usuario"));
        columna[2].setBackgroundColor(BaseColor.GRAY);

        columna[3] = new PdfPCell(new Paragraph("Entidad"));
        columna[3].setBackgroundColor(BaseColor.GRAY);

        columna[4] = new PdfPCell(new Paragraph("Serial"));
        columna[4].setBackgroundColor(BaseColor.GRAY);

        columna[5] = new PdfPCell(new Paragraph("Fecha  pago"));
        columna[5].setBackgroundColor(BaseColor.GRAY);

        columna[6] = new PdfPCell(new Paragraph("valor"));
        columna[6].setBackgroundColor(BaseColor.GRAY);

        tabla.addCell(columna[0]);
        tabla.addCell(columna[1]);
        tabla.addCell(columna[2]);
        tabla.addCell(columna[3]);
        tabla.addCell(columna[4]);
        tabla.addCell(columna[5]);
        tabla.addCell(columna[6]);

        return tabla;

    }

    /**
     * establece un formtao para la fecha
     *
     * @return
     */
    private String formatoFecha() {

        DateFormat formato = DateFormat.getDateInstance();
        Date fecha = new Date();
        DateFormat formato2 = DateFormat.getDateInstance(DateFormat.FULL);
        return formato2.format(fecha);
    }

}
