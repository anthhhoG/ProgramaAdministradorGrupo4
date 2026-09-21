package presentacion;

import modelo.Tramo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;

/**
 * Componente Swing especializado que representa gráficamente la línea de
 * tiempo de la CPU. Dibuja cada tramo de ejecución u ocio con una escala
 * adaptable y ofrece el intervalo y la duración mediante textos emergentes.
 *
 * @author Marco Antonio Hernandez Tevelan, carné: 9959-24-6201
 */
public final class Gantt extends JPanel {
    private List<Tramo> tramos = List.of();
    private int tiempo;

    public Gantt() { setBackground(Color.WHITE); setToolTipText(""); }

    public void actualizar(List<Tramo> valores, int reloj) {
        tramos = valores;
        tiempo = reloj;
        setPreferredSize(new Dimension(Math.max(850, Math.min(12_000, reloj * 30 + 60)), 150));
        revalidate(); repaint();
    }

    private double escala() { return (getWidth() - 60.0) / Math.max(25, tiempo); }

    @Override protected void paintComponent(Graphics grafico) {
        super.paintComponent(grafico);
        Graphics2D g = (Graphics2D) grafico.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        if (tramos.isEmpty()) {
            g.setColor(Estilo.GRIS);
            g.drawString("Inicia la simulación o avanza un paso para construir el diagrama de Gantt.", 24, 67);
        } else {
            double escala = escala();
            Rectangle recorte = g.getClipBounds();
            for (Tramo tramo : tramos) {
                int x = 30 + (int) Math.round(tramo.inicio() * escala);
                int ancho = Math.max(1, (int) Math.round(tramo.fin() * escala) - (x - 30));
                if (recorte != null && !recorte.intersects(x, 32, ancho, 66)) continue;
                g.setColor(Estilo.color(tramo.id()));
                g.fillRoundRect(x, 32, Math.max(1, ancho - 2), 48, 8, 8);
                String texto = tramo.id() == 0 ? "Ociosa" : "P" + tramo.id();
                g.setColor(Color.WHITE);
                if (g.getFontMetrics().stringWidth(texto) + 10 < ancho) g.drawString(texto, x + 8, 61);
                if (ancho > 32) { g.setColor(Estilo.GRIS); g.drawString(String.valueOf(tramo.inicio()), x, 99); }
            }
            g.setColor(Estilo.TINTA);
            g.drawString(String.valueOf(tiempo), 30 + (int) (tiempo * escala), 99);
        }
        g.setColor(Estilo.GRIS);
        g.drawString("Tiempo simulado (u)   ·   Pasa el cursor sobre un bloque para ver su intervalo", 24, 129);
        g.dispose();
    }

    @Override public String getToolTipText(MouseEvent evento) {
        if (evento.getY() < 32 || evento.getY() > 80) return null;
        double instante = (evento.getX() - 30) / escala();
        for (Tramo tramo : tramos) {
            if (instante >= tramo.inicio() && instante < tramo.fin())
                return (tramo.id() == 0 ? "CPU ociosa" : "P" + tramo.id() + " · " + tramo.nombre())
                        + " | [" + tramo.inicio() + ", " + tramo.fin() + ") | " + (tramo.fin() - tramo.inicio()) + " u";
        }
        return null;
    }
}
