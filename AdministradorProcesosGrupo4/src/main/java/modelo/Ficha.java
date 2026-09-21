package modelo;

/**
 * Agrupa los datos inmutables con los que se define un proceso en un escenario:
 * nombre, instante de llegada, ráfaga de CPU y prioridad. Su constructor valida
 * todos los límites aceptados antes de permitir la creación de la ficha.
 *
 * @author Anthony Hetzael Suc Gomez, carné: 9959 24 389
 */
public record Ficha(String nombre, int llegada, int rafaga, int prioridad) {
    public Ficha {
        if (nombre == null || nombre.isBlank() || nombre.strip().length() > 40
                || nombre.codePoints().anyMatch(Character::isISOControl)) {
            throw new IllegalArgumentException("El nombre debe tener entre 1 y 40 caracteres, sin saltos de línea.");
        }
        nombre = nombre.strip();
        if (llegada < 0 || llegada > 10_000)
            throw new IllegalArgumentException("La llegada debe estar entre 0 y 10000.");
        if (rafaga < 1 || rafaga > 1_000)
            throw new IllegalArgumentException("La ráfaga debe estar entre 1 y 1000.");
        if (prioridad < 1 || prioridad > 10)
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 10 (1 es la más alta).");
    }
}
