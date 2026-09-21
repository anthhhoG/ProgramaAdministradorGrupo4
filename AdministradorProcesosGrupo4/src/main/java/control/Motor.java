package control;

import modelo.*;
import plan.*;
import java.util.*;

/**
 * Coordina la simulación de una CPU sin depender de Swing. Administra el reloj,
 * la cola de procesos, las transiciones de estado, la política de planificación,
 * los tramos del Gantt, el registro de eventos y las métricas resultantes. Sus
 * operaciones están diseñadas para ejecutarse desde un único hilo.
 *
 * @author Ferdynand Sebastian Monroy Salazar, carné: 9959 24 14049
 */
public final class Motor {
    public static final int LIMITE = 100;
    private final List<Proceso> procesos = new ArrayList<>();
    private final List<Tramo> tramos = new ArrayList<>();
    private final Deque<String> eventos = new ArrayDeque<>();
    private Metodo algoritmo = Metodo.FCFS;
    private Politica politica = algoritmo.crear(2);
    private int quantum = 2;
    private int tiempo;
    private int ocupado;
    private int siguiente = 1;
    private int consumo;
    private long turno;
    private Proceso actual;

    public static List<Ficha> ejemplo() {
        return List.of(new Ficha("Editor", 0, 7, 3), new Ficha("Compilador", 2, 4, 1),
                new Ficha("Navegador", 3, 6, 4), new Ficha("Audio", 5, 3, 2),
                new Ficha("Copia de archivos", 6, 5, 5));
    }

    public void configurar(Metodo valor, int nuevo) {
        validar();
        Objects.requireNonNull(valor);
        if (nuevo < 1 || nuevo > 100) throw new IllegalArgumentException("Quantum inválido.");
        algoritmo = valor;
        quantum = nuevo;
        politica = algoritmo.crear(quantum);
    }

    public Proceso agregar(Ficha ficha) {
        Objects.requireNonNull(ficha);
        if (procesos.size() >= LIMITE) throw new IllegalArgumentException("Se permiten hasta 100 procesos.");
        if (ficha.llegada() < tiempo) throw new IllegalArgumentException("La llegada no puede ser anterior al reloj actual (" + tiempo + ").");
        Proceso p = new Proceso(siguiente++, ficha);
        procesos.add(p);
        admitir();
        return p;
    }

    public void editar(int id, Ficha ficha) {
        validar();
        int indice = procesos.indexOf(buscar(id));
        procesos.set(indice, new Proceso(id, ficha));
        reiniciar();
    }

    public void eliminar(int id) {
        validar();
        procesos.remove(buscar(id));
        reiniciar();
    }

    /** Valida por completo antes de sustituir el escenario actual. */
    public void cargar(List<Ficha> fichas) {
        List<Ficha> validadas = List.copyOf(fichas);
        if (validadas.size() > LIMITE) throw new IllegalArgumentException("Se permiten hasta 100 procesos.");
        procesos.clear();
        siguiente = 1;
        for (Ficha ficha : validadas) procesos.add(new Proceso(siguiente++, ficha));
        reiniciar();
    }

    public void reiniciar() {
        for (int i = 0; i < procesos.size(); i++) {
            Proceso anterior = procesos.get(i);
            procesos.set(i, new Proceso(anterior.id(), anterior.ficha()));
        }
        tiempo = 0;
        ocupado = 0;
        consumo = 0;
        turno = 0;
        actual = null;
        tramos.clear();
        eventos.clear();
        politica = algoritmo.crear(quantum);
        admitir();
    }

    /** Ejecuta exactamente el intervalo [tiempo, tiempo+1). */
    public boolean avanzar() {
        if (completo()) return false;
        admitir();
        List<Proceso> preparar = listos();
        if (actual != null && politica.interrumpir(actual, preparar, consumo)) {
            anotar("P" + actual.id() + " vuelve a la cola de listos.");
            actual.preparar(turno++);
            actual = null;
            consumo = 0;
        } else if (politica.agotado(consumo)) {
            consumo = 0;
        }
        if (actual == null) {
            actual = politica.elegir(listos());
            consumo = 0;
            if (actual != null) {
                actual.iniciar(tiempo);
                anotar("P" + actual.id() + " entra a la CPU.");
            }
        }
        for (Proceso p : procesos) p.contar();
        registrar(actual == null ? 0 : actual.id(), actual == null ? "Ociosa" : actual.ficha().nombre());
        if (actual != null) {
            ocupado++;
            consumo++;
            actual.ejecutar(tiempo + 1);
        }
        tiempo++;
        if (actual != null && actual.estado() == Estado.TERMINADO) {
            anotar("P" + actual.id() + " terminó.");
            actual = null;
            consumo = 0;
        }
        admitir();
        return true;
    }

    public void bloquear(int id) {
        Proceso p = buscar(id);
        p.bloquear();
        if (actual == p) { actual = null; consumo = 0; }
        anotar("P" + id + " bloqueado manualmente.");
    }

    public void desbloquear(int id) {
        Proceso p = buscar(id);
        if (p.estado() != Estado.BLOQUEADO) throw new IllegalStateException("El proceso no está bloqueado.");
        p.preparar(turno++);
        anotar("P" + id + " desbloqueado.");
    }

    public void cancelar(int id) {
        Proceso p = buscar(id);
        p.cancelar();
        if (actual == p) { actual = null; consumo = 0; }
        anotar("P" + id + " cancelado.");
    }

    private void admitir() {
        procesos.stream().filter(p -> p.estado() == Estado.NUEVO && p.ficha().llegada() <= tiempo)
                .sorted(Comparator.comparingInt((Proceso p) -> p.ficha().llegada()).thenComparingInt(Proceso::id))
                .forEach(p -> { p.preparar(turno++); anotar("P" + p.id() + " llegó a listos."); });
    }

    private void registrar(int id, String nombre) {
        if (!tramos.isEmpty()) {
            Tramo ultimo = tramos.getLast();
            if (ultimo.id() == id && ultimo.fin() == tiempo) {
                tramos.set(tramos.size() - 1, new Tramo(id, nombre, ultimo.inicio(), tiempo + 1));
                return;
            }
        }
        tramos.add(new Tramo(id, nombre, tiempo, tiempo + 1));
    }

    private void anotar(String evento) {
        if (eventos.size() == 300) eventos.removeFirst();
        eventos.addLast("t=" + tiempo + "   " + evento);
    }

    private void validar() {
        if (tiempo != 0) throw new IllegalStateException("Reinicia la simulación antes de editar el escenario o el algoritmo.");
    }

    public Proceso buscar(int id) {
        return procesos.stream().filter(p -> p.id() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Proceso inexistente: " + id));
    }

    public List<Proceso> procesos() { return List.copyOf(procesos); }
    public List<Ficha> escenario() { return procesos.stream().map(Proceso::ficha).toList(); }
    public List<Tramo> tramos() { return List.copyOf(tramos); }
    public List<String> eventos() { return List.copyOf(eventos); }
    public List<Proceso> listos() {
        return procesos.stream().filter(p -> p.estado() == Estado.LISTO)
                .sorted(Comparator.comparingLong(Proceso::orden)).toList();
    }
    public boolean completo() { return procesos.stream().allMatch(Proceso::finalizado); }
    public boolean detenido() {
        return !completo() && actual == null && procesos.stream()
                .noneMatch(p -> p.estado() == Estado.NUEVO || p.estado() == Estado.LISTO);
    }
    public int tiempo() { return tiempo; }
    public int ocupado() { return ocupado; }
    public Metodo algoritmo() { return algoritmo; }
    public int quantum() { return quantum; }
    public Proceso actual() { return actual; }
    public long terminados() { return procesos.stream().filter(p -> p.estado() == Estado.TERMINADO).count(); }
    public double uso() { return tiempo == 0 ? 0 : 100.0 * ocupado / tiempo; }
    public double rendimiento() { return tiempo == 0 ? 0 : (double) terminados() / tiempo; }
    public double demora() {
        return procesos.stream().filter(p -> p.estado() == Estado.TERMINADO).mapToInt(Proceso::espera).average().orElse(0);
    }
    public double retorno() {
        return procesos.stream().filter(p -> p.estado() == Estado.TERMINADO).mapToInt(Proceso::retorno).average().orElse(0);
    }
    public double respuesta() {
        return procesos.stream().filter(p -> p.estado() == Estado.TERMINADO).mapToInt(Proceso::respuesta).average().orElse(0);
    }
}
