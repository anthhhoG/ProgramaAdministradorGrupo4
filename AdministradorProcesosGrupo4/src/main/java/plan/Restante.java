package plan;

import modelo.Proceso;
import java.util.List;

/**
 * Implementa SRTF como extensión de la selección del trabajo más corto. Permite
 * interrumpir el proceso en ejecución cuando aparece otro proceso listo cuyo
 * tiempo restante es estrictamente menor.
 *
 * @author Ferdynand Sebastian Monroy Salazar, carné: 9959 24 14049
 */
public final class Restante extends Corto {
    @Override public boolean interrumpir(Proceso actual, List<Proceso> preparar, int consumo) {
        Proceso candidato = elegir(preparar);
        return candidato != null && candidato.restante() < actual.restante();
    }
}
