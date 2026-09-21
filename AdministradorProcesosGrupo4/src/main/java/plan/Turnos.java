package plan;

import modelo.Proceso;
import java.util.List;

/**
 * Implementa Round Robin sobre el orden de llegada a listos. Valida el quantum
 * configurado y ordena la interrupción del proceso actual cuando se consume el
 * turno y existe otro proceso esperando la CPU.
 *
 * @author Anthony Hetzael Suc Gomez, carné: 9959 24 389
 */
public final class Turnos extends Llegada {
    private final int quantum;
    public Turnos(int quantum) {
        if (quantum < 1 || quantum > 100) throw new IllegalArgumentException("El quantum debe estar entre 1 y 100.");
        this.quantum = quantum;
    }
    @Override public boolean interrumpir(Proceso actual, List<Proceso> preparar, int consumo) {
        return !preparar.isEmpty() && consumo >= quantum;
    }
    @Override public boolean agotado(int consumo) { return consumo >= quantum; }
}
