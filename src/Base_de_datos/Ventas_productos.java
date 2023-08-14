/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base_de_datos;
//Biblioteca org.json es para manipular objetos JSON. 
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.*;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.IOException;
import static java.nio.file.Files.lines;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dayan
 */
public class Ventas_productos extends javax.swing.JFrame {
DefaultTableModel tabla2;
   String id_venta;
   String fecha;
   ArrayList<String> productos =new ArrayList<>();
   ArrayList<String> precios =new ArrayList<>();
   ArrayList<String> cantidad =new ArrayList<>();
   
    public Ventas_productos() {
        initComponents();
        tabla2 = new DefaultTableModel();//para establecer un modelo de tabla
        tabla2.addColumn("id_venta");//para definir las columnas de tabla
        tabla2.addColumn("id_producto");
        tabla2.addColumn("fecha_venta");
        tabla2.addColumn("cantidad_producto");
        tabla2.addColumn("precio");
        tabla2.addColumn("nombre_producto");
        this.jTable1.setModel(tabla2);
        buscar_tabla("http://localhost/Appi/btn/venta_buscar.php");
    }
    
    public void buscar_tabla(String x){
        try {
            // URL del API
            URL url = new URL(x);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            int responseCode = conn.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader leer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder respuesta = new StringBuilder();
                
                while ((inputLine = leer.readLine()) != null) {
                    respuesta.append(inputLine);
                }
                leer.close();
                tabla2.setRowCount(0);
                JSONArray jsonArray = new JSONArray(respuesta.toString());
                
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id_venta = jsonObject.getString("id_venta");
                    String id_producto = jsonObject.getString("id_producto");
                    String fecha_venta = jsonObject.getString("fecha_venta");
                    int cantidad_producto = jsonObject.getInt("cantidad_producto");
                    int precio = jsonObject.getInt("precio");
                    String nombre_producto = jsonObject.getString("nombre_producto");
                   
                    
                    tabla2.addRow(new Object[]{id_venta, id_producto, fecha_venta, cantidad_producto, precio, nombre_producto});
                }
            } else {
                System.out.println("Error en la solicitud HTTP: " + responseCode);
            }
            
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
        
    }
    //La función entrar toma una cadena acceso como parámetro y ajusta el estado de los botones en función de la operación de acceso especificada. 
    //ejemplo: Si acceso es igual a "insertar", deshabilita los botones de actualizar, borrar y buscar. y asi con los siguentes
    public void entrar (String acceso){
        if (acceso.equals("insertar")){
            btnActualizar.setEnabled(false);
             btnBorrar.setEnabled(false);
              btnBuscar.setEnabled(false);
            
        }else if (acceso.equals("borrar")){
            btnActualizar.setEnabled(false);
             btnInsertar.setEnabled(false);
              btnBuscar.setEnabled(false);
            
        }else if (acceso.equals("actualizar")){
            btnInsertar.setEnabled(false);
             btnBorrar.setEnabled(false);
              btnBuscar.setEnabled(false);
            
        }else if (acceso.equals("buscar")){
            btnActualizar.setEnabled(false);
             btnBorrar.setEnabled(false);
              btnInsertar.setEnabled(false);
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfidprod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfidventa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tffecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfcanpro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfnombrepro = new javax.swing.JTextField();
        tfprecio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        btnImprimir_Ticket = new javax.swing.JButton();
        btnBuscarpro = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        regresar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida.png"))); // NOI18N
        regresar.setText("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });
        getContentPane().add(regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 130, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("id_producto");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 100, 20));

        tfidprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfidprodActionPerformed(evt);
            }
        });
        getContentPane().add(tfidprod, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 200, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("id_venta");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 100, 20));

        tfidventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfidventaActionPerformed(evt);
            }
        });
        getContentPane().add(tfidventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 200, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("fecha_venta");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 100, 20));

        tffecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tffechaActionPerformed(evt);
            }
        });
        getContentPane().add(tffecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 200, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("cantidad_productos");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 170, 20));

        tfcanpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfcanproActionPerformed(evt);
            }
        });
        getContentPane().add(tfcanpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 200, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("nombre_producto");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 160, 20));

        tfnombrepro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfnombreproActionPerformed(evt);
            }
        });
        getContentPane().add(tfnombrepro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 200, 30));

        tfprecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfprecioActionPerformed(evt);
            }
        });
        getContentPane().add(tfprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 200, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("precio");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 100, 20));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, -1, -1));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, -1, -1));

        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });
        getContentPane().add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, -1, -1));

        btnImprimir_Ticket.setText("Imprimir Ticket");
        btnImprimir_Ticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir_TicketActionPerformed(evt);
            }
        });
        getContentPane().add(btnImprimir_Ticket, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, -1, -1));

        btnBuscarpro.setText("Buscar Producto");
        btnBuscarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarproActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/windows22 (1).jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 350));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 620, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        //para tener el control de ventanas para mostrar y ocultar conforme se le balla pidiendo
        menu entrar = new menu();
        entrar.setVisible(true);//crea una instancia de la clase menu y la hace visible utilizando el método 
        this.setVisible(false);//donde se cierra la ventana actual y se muestra la nueva instancia del menu
    }//GEN-LAST:event_regresarActionPerformed

    private void tfidprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfidprodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfidprodActionPerformed

    private void tfidventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfidventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfidventaActionPerformed

    private void tffechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tffechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tffechaActionPerformed

    private void tfcanproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfcanproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfcanproActionPerformed

    private void tfnombreproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfnombreproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfnombreproActionPerformed

    private void tfprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfprecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfprecioActionPerformed

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
    Metodos medo=new Metodos();//existe una clase llamada metodos que contiene métodos y lógica relacionada con la aplicación.
        String idv=tfidventa.getText();
        String idp=tfidprod.getText();
        String fe=tffecha.getText();
        String cap=tfcanpro.getText();
        String pre=tfprecio.getText();
        String nopro=tfnombrepro.getText();
        id_venta=idv;
        fecha=fe;
        productos.add(nopro);
        precios.add(pre);
        cantidad.add(cap);
        try {
            String leer=medo.gett("http://localhost/Appi/btn/venta_buscar.php?id_venta=" +idv+"");
            if (leer != null) {
                JSONArray J= new JSONArray(leer);

                if (J.length() > 0) {
                    System.out.println("La venta_productos ya existe");
                } else {
                    medo.insertar("http://localhost/Appi/btn/venta_insertar.php", "id_venta="+idv+"&id_producto="+idp+"&fecha_venta="+fe+"&cantidad_producto="+cap+"&precio="+pre+"&nombre_producto="+nopro);
                    System.out.println("La venta_productos fue registrada correctamente");
                }
            }
        buscar_tabla("http://localhost/Appi/btn/venta_buscar.php?");
        } catch (IOException ex) {
            System.out.println("error");
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void btnBuscarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarproActionPerformed
       Metodos medo=new Metodos(); 
        String id=tfidprod.getText();
        String leer;
        try {
            leer = medo.gett("http://localhost/Appi/btn/registro_buscar.php?id="+id);
        
        JSONArray j= new JSONArray(leer); if (j.length() > 0) {
                                   for (int i = 0; i < j.length(); i++) {
                    JSONObject jsonObject = j.getJSONObject(i);
                    String nombre2 = jsonObject.getString("nombre"
                            + "");
                    int prec = jsonObject.getInt("precio");
                    String pres = jsonObject.getString("presentacion");
                                    tfprecio.setText(""+prec);
                                    tfnombrepro.setText(nombre2);
                                    JOptionPane.showMessageDialog(this, "El producto es:\n"
                                            + "Nombre: "+nombre2+"\n"
                                            +"Precio: "+prec+"\n"
                                            +"Precentacion: "+pres+"\n");
                                    tfcanpro.setEnabled(true);
                                    tffecha.setEnabled(true);
                                    
                                   
                                    }
                                 }
        } catch (IOException ex) {
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarproActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
     Metodos medo=new Metodos();
        String idv=tfidventa.getText();
        String idp=tfidprod.getText();
        String fe=tffecha.getText();
        String cap=tfcanpro.getText();
        String pre=tfprecio.getText();
        String nopro=tfnombrepro.getText();
        
        try {
            String leer=medo.gett("http://localhost/Appi/btn/venta_buscar.php?id_venta=" +idv+"");
            if (leer != null) {
                JSONArray J= new JSONArray(leer);

                if (J.length() > 0) {
                    buscar_tabla("http://localhost/Appi/btn/venta_buscar.php?id_venta=" +idv+"");
                } else {
                    System.out.println("La venta_productos no existente");

                }
            }
        } catch (IOException ex) {
            System.out.println("error");
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
    Metodos medo=new Metodos();
        String idv=tfidventa.getText();
        String idp=tfidprod.getText();
        String fe=tffecha.getText();
        String cap=tfcanpro.getText();
        String pre=tfprecio.getText();
        String nopro=tfnombrepro.getText();
       
        try {
            String leer=medo.gett("http://localhost/Appi/btn/venta_buscar.php?id_venta="+idv+"");
            if (leer != null) {
                JSONArray J= new JSONArray(leer);

                if (J.length() > 0) {
                    System.out.println("La venta_productos se borro");
                    medo.borrar("http://localhost/Appi/btn/venta_borrar.php?id_venta="+idv+"");
                } else {

                   System.out.println("La venta_productos no existe");

                }
            }
         buscar_tabla("http://localhost/Appi/btn/venta_buscar.php?");
        } catch (IOException ex) {
            System.out.println("error");
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    Metodos medo=new Metodos();
        String idv=tfidventa.getText();
        String idp=tfidprod.getText();
        String fe=tffecha.getText();
        String cap=tfcanpro.getText();
        String pre=tfprecio.getText();
        String nopro=tfnombrepro.getText();
        
        try {
            String leer=medo.gett("http://localhost/Appi/btn/venta_buscar.php?id_venta="+idv+"");
            if (leer != null) {
                JSONArray J= new JSONArray(leer);

                if (J.length() > 0) {
                    System.out.println("La venta_productos se actualizo correctamente");
                    medo.actualizar("http://localhost/Appi/btn/venta_actualizar.php?id_venta="+idv+"&id_producto="+idp+"&fecha_venta="+fe+"&cantidad_producto="+cap+"&precio="+pre+"&nombre_producto="+nopro);
                    
                }else
                {
                    System.out.println("La venta_productos no existe");
                }
            }

        } catch (IOException ex) {
            System.out.println("error");
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnImprimir_TicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir_TicketActionPerformed
    try {
       File file = new File("C:/Users/PILAR/OneDrive/Documentos/NetBeansProjects/Examen2/Venta "+id_venta+".pdf");
        file.getParentFile().mkdirs();
        
        PdfWriter writer;
      
            writer = new PdfWriter(file);
        
        PdfDocument pdf = new PdfDocument(writer);
        Document document =new Document(pdf,PageSize.A7);
        document.setMargins(10, 10, 10, 10);
        Paragraph header = new Paragraph("VENTA").setTextAlignment(TextAlignment.CENTER);
        
        document.add(header);
        Paragraph info = new Paragraph("Número de venta: " + id_venta + "\nFecha de hoy: " + fecha);
        document.add(info);
        Table table = new Table(3);
        table.addCell("Producto");
        table.addCell("Cantidad");
        table.addCell("Precio");
        for (int i = 0; i < productos.size(); i++) {
    table.addCell(productos.get(i));
    table.addCell(cantidad.get(i).toString());
    table.addCell(precios.get(i).toString());
}

double total = 0.0;
for (int i = 0; i < productos.size(); i++) {
           double num1 =Integer.parseInt(precios.get(i));
            double num2 =Integer.parseInt(cantidad.get(i));
    double subtotal =num1*num2;
    total += subtotal;
}
    document.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));
        
        Paragraph totalInfo = new Paragraph("Total: $" + total).setTextAlignment(TextAlignment.RIGHT);
            document.add(totalInfo);
         PrinterJob job = PrinterJob.getPrinterJob();
            if (job.printDialog()) {
                    job.print();
            }
        document.close();
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrinterException ex) {
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimir_TicketActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventas_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventas_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventas_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventas_productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarpro;
    private javax.swing.JButton btnImprimir_Ticket;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton regresar;
    private javax.swing.JTextField tfcanpro;
    private javax.swing.JTextField tffecha;
    private javax.swing.JTextField tfidprod;
    private javax.swing.JTextField tfidventa;
    private javax.swing.JTextField tfnombrepro;
    private javax.swing.JTextField tfprecio;
    // End of variables declaration//GEN-END:variables
}
