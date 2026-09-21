package modelo;

/**
 * Enumera las etapas posibles del ciclo de vida de un proceso y proporciona la
 * descripción en español que se muestra en la interfaz y en los reportes.
 *
 * @author Ferdynand Sebastian Monroy Salazar, carné: 9959 24 14049
 */
public enum Estado {
    NUEVO("Nuevo"), LISTO("Listo"), EJECUTANDO("En ejecución"), BLOQUEADO("Bloqueado"),
    TERMINADO("Terminado"), CANCELADO("Cancelado");

    private final String texto;
    Estado(String texto) { this.texto = texto; }
    @Override public String toString() { return texto; }
}
