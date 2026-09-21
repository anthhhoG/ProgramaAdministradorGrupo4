package plan;

import modelo.Proceso;
import java.util.Comparator;
import java.util.List;

/**
 * Implementa la planificación no expropiativa por prioridad. Selecciona primero
 * el valor numérico menor —que representa mayor prioridad— y conserva el orden
 * de ingreso a listos como criterio de desempate.
 *
 * @author Marco Antonio Hernandez Tevelan, carné: 9959-24-6201
 */
public final class Prioridad implements Politica {
    @Override public Proceso elegir(List<Proceso> preparar) {
        return preparar.stream().min(Comparator.comparingInt((Proceso p) -> p.ficha().prioridad())
                .thenComparingLong(Proceso::orden)).orElse(null);
    }
}
