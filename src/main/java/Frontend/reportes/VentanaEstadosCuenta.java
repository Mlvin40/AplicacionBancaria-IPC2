/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend.reportes;

import Backend.reportes.EstadoCuenta;
import Backend.reportes.ListadoEstadosCuenta;

import java.util.List;

/**
 *
 * @author melvin
 */
public class VentanaEstadosCuenta extends javax.swing.JFrame {

    /**
     * Creates new form VentanaEstadosCuenta
     */
    public VentanaEstadosCuenta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedorEstadosCT = new javax.swing.JScrollPane();
        btnGenerarEstadosCuenta = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGenerarEstadosCuenta.setText("Generar");
        btnGenerarEstadosCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarEstadosCuentaActionPerformed(evt);
            }
        });

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(btnGenerarEstadosCuenta))
                .addGap(49, 49, 49)
                .addComponent(contenedorEstadosCT, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(btnGenerarEstadosCuenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(contenedorEstadosCT, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarEstadosCuentaActionPerformed(java.awt.event.ActionEvent evt) {
        ListadoEstadosCuenta listadoEstadosCuenta = new ListadoEstadosCuenta();
        List<EstadoCuenta> estadosCuenta = listadoEstadosCuenta.obtenerEstadosCuenta();

        
        for (EstadoCuenta estadoCuenta : estadosCuenta) {
            PanelEstadoCuenta panelEstadoCuenta = new PanelEstadoCuenta();
            panelEstadoCuenta.llenarCampos(estadoCuenta);
            panelEstadoCuenta.setVisible(true);
            contenedorEstadosCT.add(panelEstadoCuenta);    
        }
        
        // Actualiza la vista
        contenedorEstadosCT.revalidate();
        contenedorEstadosCT.repaint();

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaEstadosCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaEstadosCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaEstadosCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaEstadosCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaEstadosCuenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarEstadosCuenta;
    private javax.swing.JScrollPane contenedorEstadosCT;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
