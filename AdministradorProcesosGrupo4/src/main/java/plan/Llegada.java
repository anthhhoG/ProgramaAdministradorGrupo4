package plan;

import modelo.Proceso;
import java.util.Comparator;
import java.util.List;

/**
 * Implementa la selección por orden de llegada a la cola de listos. Elige al
 * proceso con el turno de ingreso más antiguo y sirve como política de FCFS y
 * como base para la rotación utilizada por Round Robin.
 *
 * @author Ferdynand Sebastian Monroy Salazar, carné: 9959 24 14049
 */
public class Llegada implements Politica {
    @Override public Proceso elegir(List<Proceso> preparar) {
        return preparar.stream().min(Comparator.comparingLong(Proceso::orden)).orElse(null);
    }
}
