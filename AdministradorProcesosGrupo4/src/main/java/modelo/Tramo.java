package modelo;

/**
 * Describe un intervalo continuo del diagrama de Gantt mediante el proceso que
 * ocupó la CPU y los límites {@code [inicio, fin)}. El identificador cero se
 * reserva para los periodos en los que la CPU permaneció ociosa.
 *
 * @author Anthony Hetzael Suc Gomez, carné: 9959 24 389
 */
public record Tramo(int id, String nombre, int inicio, int fin) {
    public Tramo {
        if (inicio < 0 || fin <= inicio) throw new IllegalArgumentException("Intervalo inválido.");
    }
}
