package vista;

import java.text.ParseException;
import modelo.Ficha;

/**
 * Formulario reutilizable para capturar los datos de un proceso. Permite cargar
 * una ficha existente, confirmar la edición de sus campos y exponer el nombre,
 * la llegada, la ráfaga y la prioridad escritos por el usuario.
 *
 * @author Marco Antonio Hernandez Tevelan, carné: 9959-24-6201
 */
public class Entrada extends javax.swing.JPanel {
    public Entrada() { initComponents(); }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiqueta = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        arribo = new javax.swing.JLabel();
        llegada = new javax.swing.JSpinner();
        duracion = new javax.swing.JLabel();
        rafaga = new javax.swing.JSpinner();
        importancia = new javax.swing.JLabel();
        prioridad = new javax.swing.JSpinner();

        setBackground(new java.awt.Color(242, 245, 250));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setLayout(new java.awt.GridLayout(4, 2, 12, 12));

        etiqueta.setText("Nombre");
        etiqueta.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        etiqueta.setForeground(new java.awt.Color(34, 48, 68));
        add(etiqueta);

        nombre.setColumns(24);
        add(nombre);

        arribo.setText("Llegada (unidades)");
        arribo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        arribo.setForeground(new java.awt.Color(34, 48, 68));
        add(arribo);

        llegada.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10000, 1));
        llegada.setPreferredSize(new java.awt.Dimension(70, 30));
        add(llegada);

        duracion.setText("Ráfaga de CPU");
        duracion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        duracion.setForeground(new java.awt.Color(34, 48, 68));
        add(duracion);

        rafaga.setModel(new javax.swing.SpinnerNumberModel(5, 1, 1000, 1));
        rafaga.setPreferredSize(new java.awt.Dimension(70, 30));
        add(rafaga);

        importancia.setText("Prioridad (1 = máxima)");
        importancia.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        importancia.setForeground(new java.awt.Color(34, 48, 68));
        add(importancia);

        prioridad.setModel(new javax.swing.SpinnerNumberModel(5, 1, 10, 1));
        prioridad.setPreferredSize(new java.awt.Dimension(70, 30));
        add(prioridad);
    }// </editor-fold>//GEN-END:initComponents
    // </editor-fold>


    public void cargar(Ficha ficha, int minimo) {
        nombre.setText(ficha == null ? "Nuevo proceso" : ficha.nombre());
        llegada.setValue(ficha == null ? minimo : ficha.llegada());
        rafaga.setValue(ficha == null ? 5 : ficha.rafaga());
        prioridad.setValue(ficha == null ? 5 : ficha.prioridad());
    }

    public void confirmarEdicion() throws ParseException {
        llegada.commitEdit(); rafaga.commitEdit(); prioridad.commitEdit();
    }

    public String nombre() { return nombre.getText(); }
    public int llegada() { return (int) llegada.getValue(); }
    public int rafaga() { return (int) rafaga.getValue(); }
    public int prioridad() { return (int) prioridad.getValue(); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arribo;
    private javax.swing.JLabel duracion;
    private javax.swing.JLabel etiqueta;
    private javax.swing.JLabel importancia;
    private javax.swing.JSpinner llegada;
    private javax.swing.JTextField nombre;
    private javax.swing.JSpinner prioridad;
    private javax.swing.JSpinner rafaga;
    // End of variables declaration//GEN-END:variables
}
