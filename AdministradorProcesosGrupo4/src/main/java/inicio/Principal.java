package inicio;

import javax.swing.SwingUtilities;
import presentacion.Estilo;
import vista.Ventana;

/**
 * Punto de entrada de la aplicación. Programa la creación de la interfaz en el
 * hilo de eventos de Swing, aplica el estilo global y muestra la ventana
 * principal del simulador.
 *
 * @author Ferdynand Sebastian Monroy Salazar, carné: 9959 24 14049
 */
public final class Principal {
    private Principal() { }

    public static void main(String[] argumentos) {
        SwingUtilities.invokeLater(() -> {
            Estilo.aplicar();
            new Ventana().setVisible(true);
        });
    }
}
