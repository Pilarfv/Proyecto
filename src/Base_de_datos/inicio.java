/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base_de_datos;
import java.net.HttpURLConnection;
import javax.swing.*;
import java.io.*;
import java.net.*;
import org.json.JSONArray;
/**
 *
 * @author BD_ALUMNOS
 */
public class inicio extends javax.swing.JFrame {

    String red="";
    
    public inicio() {
        initComponents();
        this.setLocationRelativeTo(this);
    }

    public void imag(JLabel labelname, String root){
        ImageIcon image= new ImageIcon(root);
        Icon icon =new ImageIcon(
                image.getImage().getScaledInstance(labelname.getWidth(), labelname.getHeight(),image.getImageLoadStatus())
        );
        labelname.setIcon(icon);
        this.repaint();
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        tfcontrasena = new javax.swing.JTextField();
        tfnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida.png"))); // NOI18N
        jButton1.setText("salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 100, 30));
        getContentPane().add(tfcontrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 240, 30));

        tfnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfnombreActionPerformed(evt);
            }
        });
        getContentPane().add(tfnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 240, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("contraseña");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 120, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 100, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/puerta.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 110, 100));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/login_pilarf.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 230, 150));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/windows22 (1).jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, 640, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        menu entrar = new menu();
        String usuario = tfnombre.getText().toString();
        String clave = tfcontrasena.getText().toString();
        try {
            String appi = "http://localhost/Appi/usuarios.php?usuario="+usuario+"&clave="+clave+"";
            URL url = new URL(appi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        
        reader.close();
        red=response.toString();
    }
            if (red != null) {
            // El API respondió correctamente, ahora se puede realizar la comparación de datos
            // Aquí se puede procesar la respuesta JSON y comparar los datos con los campos de texto
            // Supongamos que la respuesta JSON contiene un array de objetos JSON
                JSONArray J= new JSONArray(red);
            
            if (J.length() > 0) {
                // Si el array contiene al menos un objeto, se considera que existe el usuario y clave
  
                entrar.setVisible(true);
               this.setVisible(false);
            } else {
                // Si el array está vacío, no existe el usuario y clave
                System.out.println("nop");
                System.out.println(red);
            }
        } else {
            // Error al realizar la solicitud al API
            System.out.println("nopi");
        }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void tfnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfnombreActionPerformed
        
    
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
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField tfcontrasena;
    private javax.swing.JTextField tfnombre;
    // End of variables declaration//GEN-END:variables
}
