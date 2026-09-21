package datos;

import modelo.*;
import control.Motor;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Gestiona la persistencia CSV del simulador. Lee y valida escenarios, exporta
 * procesos y tramos de Gantt, protege valores interpretables como fórmulas y
 * escribe en UTF-8 mediante reemplazo atómico cuando el sistema lo permite.
 *
 * @author Anthony Hetzael Suc Gomez, carné: 9959 24 389
 */
public final class Archivo {
    public List<Ficha> leer(Path ruta) throws IOException {
        if (Files.size(ruta) > 1_000_000) throw new IOException("El escenario no puede superar 1 MB.");
        List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
        if (lineas.isEmpty() || !lineas.getFirst().replace("\uFEFF", "").equals("nombre,llegada,rafaga,prioridad"))
            throw new IOException("Encabezado esperado: nombre,llegada,rafaga,prioridad");
        List<Ficha> resultado = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            if (lineas.get(i).isBlank()) continue;
            try {
                List<String> campos = separar(lineas.get(i));
                if (campos.size() != 4) throw new IllegalArgumentException("Se esperan 4 columnas.");
                resultado.add(new Ficha(campos.get(0), Integer.parseInt(campos.get(1).strip()),
                        Integer.parseInt(campos.get(2).strip()), Integer.parseInt(campos.get(3).strip())));
                if (resultado.size() > Motor.LIMITE) throw new IllegalArgumentException("Máximo: 100 procesos.");
            } catch (IllegalArgumentException ex) {
                throw new IOException("Línea " + (i + 1) + ": " + ex.getMessage(), ex);
            }
        }
        return List.copyOf(resultado);
    }

    public void guardar(Path ruta, List<Ficha> procesos) throws IOException {
        StringBuilder datos = new StringBuilder("nombre,llegada,rafaga,prioridad\n");
        for (Ficha p : procesos) datos.append(fila(p.nombre(), p.llegada(), p.rafaga(), p.prioridad()));
        escribir(ruta, datos.toString());
    }

    public void informar(Path ruta, Motor motor) throws IOException {
        StringBuilder datos = new StringBuilder("id,nombre,estado,llegada,rafaga,prioridad,ejecutado,restante,espera,bloqueado,inicio,fin,retorno,respuesta,algoritmo,quantum,reloj\n");
        for (Proceso p : motor.procesos()) {
            datos.append(fila(p.id(), proteger(p.ficha().nombre()), p.estado(), p.ficha().llegada(), p.ficha().rafaga(),
                    p.ficha().prioridad(), p.ejecutado(), p.restante(), p.espera(), p.bloqueo(),
                    opcional(p.inicio()), opcional(p.fin()), opcional(p.retorno()), opcional(p.respuesta()),
                    motor.algoritmo().name(), motor.quantum(), motor.tiempo()));
        }
        escribir(ruta, datos.toString());
    }

    public void graficar(Path ruta, Motor motor) throws IOException {
        StringBuilder datos = new StringBuilder("id,nombre,inicio,fin,duracion\n");
        for (Tramo tramo : motor.tramos()) datos.append(fila(tramo.id(), proteger(tramo.nombre()),
                tramo.inicio(), tramo.fin(), tramo.fin() - tramo.inicio()));
        escribir(ruta, datos.toString());
    }

    private static Object opcional(int valor) { return valor < 0 ? "" : valor; }
    public static String proteger(String texto) {
        return !texto.isEmpty() && "=+-@\t\r".indexOf(texto.charAt(0)) >= 0 ? "'" + texto : texto;
    }
    public static String fila(Object... campos) {
        StringJoiner fila = new StringJoiner(",", "", "\n");
        for (Object valor : campos) fila.add("\"" + String.valueOf(valor).replace("\"", "\"\"") + "\"");
        return fila.toString();
    }

    private List<String> separar(String linea) {
        List<String> campos = new ArrayList<>();
        StringBuilder campo = new StringBuilder();
        boolean citado = false;
        boolean cerrado = false;
        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);
            if (citado) {
                if (c == '"') {
                    if (i + 1 < linea.length() && linea.charAt(i + 1) == '"') { campo.append('"'); i++; }
                    else { citado = false; cerrado = true; }
                } else campo.append(c);
            } else if (c == ',') {
                campos.add(campo.toString()); campo.setLength(0); cerrado = false;
            } else if (c == '"' && campo.isEmpty() && !cerrado) citado = true;
            else if (cerrado || c == '"') throw new IllegalArgumentException("Comillas CSV inválidas.");
            else campo.append(c);
        }
        if (citado) throw new IllegalArgumentException("Falta cerrar una comilla.");
        campos.add(campo.toString());
        return campos;
    }

    public static void escribir(Path ruta, String datos) throws IOException {
        Path absoluta = ruta.toAbsolutePath();
        Path temporal = Files.createTempFile(absoluta.getParent(), ".cpu-", ".tmp");
        try {
            Files.writeString(temporal, datos, StandardCharsets.UTF_8);
            try { Files.move(temporal, absoluta, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING); }
            catch (AtomicMoveNotSupportedException ex) { Files.move(temporal, absoluta, StandardCopyOption.REPLACE_EXISTING); }
        } finally { Files.deleteIfExists(temporal); }
    }
}
