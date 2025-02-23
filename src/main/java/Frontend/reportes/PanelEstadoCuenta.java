/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Frontend.reportes;

import Backend.movimientos.MovimientoTarjeta;
import Backend.reportes.EstadoCuenta;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author melvin
 */
public class PanelEstadoCuenta extends javax.swing.JPanel {

    /**
     * Creates new form PanelEstadoCuenta
     */
    public PanelEstadoCuenta() {
        initComponents();
        setVisible(true);
    }

    //Con esto se recibe un objeto de tipo EstadoCuenta y se llenan los campos
    public void llenarCampos(EstadoCuenta estadoCuenta) {
        txtNumeroTarjeta.setText(estadoCuenta.getNumeroTarjeta());
        txtTipoTarjeta.setText(estadoCuenta.getTipoTarjeta());
        txtNombreCliente.setText(estadoCuenta.getNombreTitular());
        txtInteres.setText(String.valueOf(estadoCuenta.getIntereses()));
        txtSaldoTotal.setText(String.valueOf(estadoCuenta.getSaldoTotal()));
        txtMontoTotal.setText(String.valueOf(estadoCuenta.getMontoTotal()));
        txtDireccionCliente.setText(estadoCuenta.getDireccion());
        //Llenar la tabla de movimientos
        llenarTablaMovimientos(estadoCuenta.getMovimientosRealizados());
        
        //Revalidaciones del panel
        revalidate();
        repaint();

    }

    private void llenarTablaMovimientos(List<MovimientoTarjeta> movimientos){
        // Asegúrate de que tablaMovimientos esté inicializada
        if (tablaMovimientos == null) {
            System.err.println("Error: tablaMovimientos no está inicializada.");
            return;
        }

        // Crear el modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FECHA");
        modelo.addColumn("TIPO DE MOVIMIENTO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("ESTABLECIMIENTO");
        modelo.addColumn("MONTO");

        // Agregar las filas al modelo
        for (MovimientoTarjeta movimiento : movimientos) {
            Object[] fila = new Object[5];
            fila[0] = movimiento.getFecha(); // Asegúrate de que getFecha() retorne un valor adecuado
            fila[1] = movimiento.getTipoMovimiento(); // Asegúrate de que getTipoMovimiento() retorne un valor adecuado
            fila[2] = movimiento.getDescripcion(); // Asegúrate de que getDescripcion() retorne un valor adecuado
            fila[3] = movimiento.getEstablecimiento(); // Asegúrate de que getEstablecimiento() retorne un valor adecuado
            fila[4] = movimiento.getMonto(); // Asegúrate de que getMonto() retorne un valor adecuado
            modelo.addRow(fila);
        }

        // Establecer el modelo en la tabla
        tablaMovimientos.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMovimientos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTipoTarjeta = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JLabel();
        txtDireccionCliente = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JLabel();
        txtNumeroTarjeta = new javax.swing.JLabel();
        txtInteres = new javax.swing.JLabel();
        txtSaldoTotal = new javax.swing.JLabel();

        jLabel3.setText("TIPO TARJETA");

        jLabel1.setText("NUMERO TARJETA");

        jLabel2.setText("TIPO TARJETA");

        jLabel4.setText("NOMBRE CLIENTE");

        jLabel5.setText("DIRECCION CLIENTE");

        tablaMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "FECHA", "TIPO DE MOVIMIENTO", "DESCRIPCION", "ESTABLECIMIENTO", "MONTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaMovimientos);

        jLabel6.setText("MONTO TOTAL");

        jLabel7.setText("SALDO TOTAL");

        jLabel8.setText("INTERESE");

        txtTipoTarjeta.setText(".");

        txtMontoTotal.setText(".");

        txtDireccionCliente.setText(".");

        txtNombreCliente.setText(".");

        txtNumeroTarjeta.setText(".");

        txtInteres.setText(".");

        txtSaldoTotal.setText(".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(59, 59, 59))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(89, 89, 89)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(44, 44, 44)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(txtDireccionCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTipoTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNumeroTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtInteres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(txtSaldoTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMontoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNumeroTarjeta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtTipoTarjeta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtDireccionCliente))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMontoTotal))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtInteres))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSaldoTotal))
                .addContainerGap(55, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMovimientos;
    private javax.swing.JLabel txtDireccionCliente;
    private javax.swing.JLabel txtInteres;
    private javax.swing.JLabel txtMontoTotal;
    private javax.swing.JLabel txtNombreCliente;
    private javax.swing.JLabel txtNumeroTarjeta;
    private javax.swing.JLabel txtSaldoTotal;
    private javax.swing.JLabel txtTipoTarjeta;
    // End of variables declaration//GEN-END:variables
}
