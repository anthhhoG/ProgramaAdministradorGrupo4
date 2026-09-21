package plan;

import modelo.Proceso;
import java.util.List;

/**
 * Define el contrato de una estrategia de planificación. El motor administra
 * el tiempo y los estados, mientras cada política decide qué proceso listo usa
 * la CPU y si el proceso actual debe cederla o ha agotado su turno.
 *
 * @author Marco Antonio Hernandez Tevelan, carné: 9959-24-6201
 */
public interface Politica {
    Proceso elegir(List<Proceso> preparar);
    default boolean agotado(int consumo) { return false; }
    default boolean interrumpir(Proceso actual, List<Proceso> preparar, int consumo) {
        return false;
    }
}
