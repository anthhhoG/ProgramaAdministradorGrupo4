package presentacion;

import java.awt.Component;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Ficha;
import vista.Entrada;

/**
 * Centraliza los cuadros de diálogo compartidos por los formularios. Permite
 * capturar y validar los datos de un proceso, seleccionar archivos CSV para
 * lectura o escritura, confirmar reemplazos y presentar errores al usuario.
 *
 * @author Ferdynand Sebastian Monroy Salazar, carné: 9959 24 14049
 */
public final class Dialogos {
    private Dialogos() { }

    public static Path elegir(Component padre, boolean guardar, String nombre) {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv"));
        if (guardar) selector.setSelectedFile(new java.io.File(nombre));
        int opcion = guardar ? selector.showSaveDialog(padre) : selector.showOpenDialog(padre);
        if (opcion != JFileChooser.APPROVE_OPTION) return null;
        Path ruta = selector.getSelectedFile().toPath();
        if (guardar && !ruta.toString().toLowerCase(Locale.ROOT).endsWith(".csv")) ruta = Path.of(ruta + ".csv");
        if (guardar && Files.exists(ruta) && JOptionPane.showConfirmDialog(padre, "¿Reemplazar el archivo " + ruta.getFileName() + "?",
                "Archivo existente", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) return null;
        return ruta;
    }

    public static void error(Component padre, Exception ex) {
        JOptionPane.showMessageDialog(padre, ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage(),
                "No se pudo completar la operación", JOptionPane.ERROR_MESSAGE);
    }

    public static Optional<Ficha> proceso(Component padre, Ficha ficha, int minimo) {
        Entrada entrada = new Entrada();
        entrada.cargar(ficha, minimo);
        while (JOptionPane.showConfirmDialog(padre, entrada, ficha == null ? "Agregar proceso" : "Editar proceso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
            try {
                entrada.confirmarEdicion();
                Ficha resultado = new Ficha(entrada.nombre(), entrada.llegada(), entrada.rafaga(), entrada.prioridad());
                if (resultado.llegada() < minimo)
                    throw new IllegalArgumentException("La llegada mínima es " + minimo + ".");
                return Optional.of(resultado);
            }
            catch (IllegalArgumentException | ParseException excepcion) {
                JOptionPane.showMessageDialog(padre, excepcion.getMessage(), "Revisa los datos", JOptionPane.WARNING_MESSAGE);
            }
        }
        return Optional.empty();
    }
}
