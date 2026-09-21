package modelo;

/**
 * Representa un proceso durante la simulación. Conserva su identidad y ficha,
 * valida cada transición de estado y contabiliza ejecución, espera, bloqueo,
 * inicio, finalización, retorno y tiempo de respuesta.
 *
 * @author Marco Antonio Hernandez Tevelan, carné: 9959-24-6201
 */
public final class Proceso {
    private final int id;
    private final Ficha ficha;
    private Estado estado = Estado.NUEVO;
    private int restante;
    private int espera;
    private int bloqueo;
    private int inicio = -1;
    private int fin = -1;
    private long orden;

    public Proceso(int id, Ficha ficha) {
        this.id = id;
        this.ficha = java.util.Objects.requireNonNull(ficha);
        restante = ficha.rafaga();
    }

    public void preparar(long orden) {
        if (finalizado()) throw new IllegalStateException("El proceso ya finalizó.");
        estado = Estado.LISTO;
        this.orden = orden;
    }

    public void iniciar(int tiempo) {
        if (estado != Estado.LISTO && estado != Estado.EJECUTANDO)
            throw new IllegalStateException("El proceso no está listo.");
        if (inicio < 0) inicio = tiempo;
        estado = Estado.EJECUTANDO;
    }

    public void ejecutar(int fin) {
        if (estado != Estado.EJECUTANDO || restante <= 0)
            throw new IllegalStateException("El proceso no puede ejecutar.");
        if (--restante == 0) {
            estado = Estado.TERMINADO;
            this.fin = fin;
        }
    }

    public void contar() {
        if (estado == Estado.LISTO) espera++;
        if (estado == Estado.BLOQUEADO) bloqueo++;
    }

    public void bloquear() {
        if (estado != Estado.LISTO && estado != Estado.EJECUTANDO)
            throw new IllegalStateException("Solo se puede bloquear un proceso listo o en ejecución.");
        estado = Estado.BLOQUEADO;
    }

    public void cancelar() {
        if (finalizado()) throw new IllegalStateException("El proceso ya finalizó.");
        estado = Estado.CANCELADO;
    }

    public boolean finalizado() { return estado == Estado.TERMINADO || estado == Estado.CANCELADO; }
    public int id() { return id; }
    public Ficha ficha() { return ficha; }
    public Estado estado() { return estado; }
    public int restante() { return restante; }
    public int ejecutado() { return ficha.rafaga() - restante; }
    public int espera() { return espera; }
    public int bloqueo() { return bloqueo; }
    public int inicio() { return inicio; }
    public int fin() { return fin; }
    public int retorno() { return fin < 0 ? -1 : fin - ficha.llegada(); }
    public int respuesta() { return inicio < 0 ? -1 : inicio - ficha.llegada(); }
    public long orden() { return orden; }
}
