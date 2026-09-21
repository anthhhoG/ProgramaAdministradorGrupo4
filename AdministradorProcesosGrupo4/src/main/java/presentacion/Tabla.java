package presentacion;

import modelo.Proceso;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Adapta la colección de procesos al contrato de datos de un {@code JTable}.
 * Define las columnas visibles, sus tipos y la correspondencia entre cada
 * propiedad de {@link Proceso} y la celda que la representa.
 *
 * @author Marco Antonio Hernandez Tevelan, carné: 9959-24-6201
 */
public final class Tabla extends AbstractTableModel {
    private static final String[] COLUMNAS = {"ID", "Proceso", "Estado", "Llegada", "Ráfaga", "Prioridad",
        "Restante", "Espera", "Bloqueo", "Inicio", "Fin", "Retorno", "Respuesta"};
    private List<Proceso> filas = List.of();

    public void actualizar(List<Proceso> procesos) { filas = List.copyOf(procesos); fireTableDataChanged(); }
    public Proceso proceso(int fila) { return filas.get(fila); }
    @Override public int getRowCount() { return filas.size(); }
    @Override public int getColumnCount() { return COLUMNAS.length; }
    @Override public String getColumnName(int columna) { return COLUMNAS[columna]; }
    @Override public Class<?> getColumnClass(int columna) { return columna == 1 || columna == 2 ? String.class : Integer.class; }

    @Override public Object getValueAt(int fila, int columna) {
        Proceso p = filas.get(fila);
        return switch (columna) {
            case 0 -> p.id(); case 1 -> p.ficha().nombre(); case 2 -> p.estado().toString();
            case 3 -> p.ficha().llegada(); case 4 -> p.ficha().rafaga(); case 5 -> p.ficha().prioridad();
            case 6 -> p.restante(); case 7 -> p.espera(); case 8 -> p.bloqueo();
            case 9 -> opcional(p.inicio()); case 10 -> opcional(p.fin());
            case 11 -> opcional(p.retorno()); case 12 -> opcional(p.respuesta());
            default -> throw new IndexOutOfBoundsException();
        };
    }

    private Integer opcional(int valor) { return valor < 0 ? null : valor; }
}
