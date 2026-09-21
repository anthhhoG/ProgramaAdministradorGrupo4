package plan;

import modelo.Proceso;
import java.util.Comparator;
import java.util.List;

/**
 * Implementa la selección del trabajo más corto. Escoge el proceso listo con
 * menor tiempo pendiente y utiliza el orden de ingreso a la cola para resolver
 * empates; sirve como base para SJF y para la variante expropiativa SRTF.
 *
 * @author Marco Antonio Hernandez Tevelan, carné: 9959-24-6201
 */
public class Corto implements Politica {
    @Override public Proceso elegir(List<Proceso> preparar) {
        return preparar.stream().min(Comparator.comparingInt(Proceso::restante)
                .thenComparingLong(Proceso::orden)).orElse(null);
    }
}
