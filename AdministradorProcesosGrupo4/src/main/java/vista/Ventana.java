package vista;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Ventana anfitriona de la aplicación. Ajusta su tamaño al área disponible de
 * la pantalla, contiene el formulario {@link Simulador} dentro de un panel con
 * desplazamiento y detiene la animación cuando la ventana se cierra.
 *
 * @author Ferdynand Sebastian Monroy Salazar, carné: 9959 24 14049
 */
public class Ventana extends javax.swing.JFrame {
    public Ventana() {
        initComponents();
        Rectangle pantalla = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(Math.min(1360, pantalla.width), Math.min(940, pantalla.height));
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosed(WindowEvent evento) { simulador.pausar(); }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desplazamiento = new javax.swing.JScrollPane();
        simulador = new vista.Simulador();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrador de procesos · Simulador");
        setMinimumSize(new java.awt.Dimension(1120, 740));

        desplazamiento.setViewportView(simulador);

        getContentPane().add(desplazamiento, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // </editor-fold>



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane desplazamiento;
    private vista.Simulador simulador;
    // End of variables declaration//GEN-END:variables
}
