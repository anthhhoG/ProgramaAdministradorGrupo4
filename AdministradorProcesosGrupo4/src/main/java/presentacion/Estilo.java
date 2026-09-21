package presentacion;

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

/**
 * Define la identidad visual compartida por la aplicación. Reúne la paleta de
 * colores usada para estados y procesos, asigna colores al diagrama de Gantt y
 * configura la fuente general de los componentes Swing.
 *
 * @author Anthony Hetzael Suc Gomez, carné: 9959 24 389
 */
public final class Estilo {
    public static final Color GRIS = new Color(103, 118, 139);
    public static final Color TINTA = new Color(34, 48, 68);
    public static final Color AZUL = new Color(45, 99, 226);
    public static final Color VERDE = new Color(0, 135, 120);
    public static final Color ROJO = new Color(191, 52, 68);
    private static final Color[] COLORES = {AZUL, VERDE, new Color(133, 83, 202),
        new Color(211, 130, 33), new Color(200, 75, 110), new Color(47, 138, 183)};

    private Estilo() { }

    public static void aplicar() {
        Font fuente = new Font("Segoe UI", Font.PLAIN, 13);
        for (Object clave : UIManager.getDefaults().keySet().toArray()) {
            if (UIManager.get(clave) instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(clave, new javax.swing.plaf.FontUIResource(fuente));
        }
    }

    public static Color color(int id) {
        return id == 0 ? new Color(174, 185, 199) : COLORES[Math.floorMod(id - 1, COLORES.length)];
    }
}
