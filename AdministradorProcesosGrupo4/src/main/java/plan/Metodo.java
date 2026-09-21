package plan;

/**
 * Cataloga los algoritmos de planificación disponibles y sus descripciones para
 * la interfaz. También actúa como fábrica de la implementación de
 * {@link Politica} correspondiente al método seleccionado.
 *
 * @author Anthony Hetzael Suc Gomez, carné: 9959 24 389
 */
public enum Metodo {
    FCFS("FCFS · orden de llegada", "Ejecuta en orden de llegada, sin expropiación."),
    SJF("SJF · trabajo más corto", "Selecciona la menor ráfaga pendiente, sin interrumpir el proceso actual."),
    SRTF("SRTF · menor tiempo restante", "Interrumpe el proceso actual si otro tiene menos tiempo restante."),
    TURNOS("Round Robin · turnos", "Reparte la CPU en turnos de duración quantum."),
    PRIORIDAD("Prioridad · 1 es la más alta", "Selecciona la prioridad más alta, sin expropiación.");

    private final String texto;
    private final String descripcion;
    Metodo(String texto, String descripcion) { this.texto = texto; this.descripcion = descripcion; }
    public String descripcion() { return descripcion; }
    public Politica crear(int quantum) {
        return switch (this) {
            case FCFS -> new Llegada();
            case SJF -> new Corto();
            case SRTF -> new Restante();
            case TURNOS -> new Turnos(quantum);
            case PRIORIDAD -> new Prioridad();
        };
    }
    @Override public String toString() { return texto; }
}
