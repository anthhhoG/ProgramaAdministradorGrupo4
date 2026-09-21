package vista;

import control.Motor;
import datos.Archivo;
import modelo.Estado;
import modelo.Ficha;
import modelo.Proceso;
import plan.Metodo;
import presentacion.Dialogos;
import presentacion.Estilo;
import presentacion.Tabla;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Formulario principal del simulador. Presenta controles, métricas, procesos y
 * resultados; traduce las acciones del usuario en solicitudes al motor y
 * actualiza los componentes visuales con el estado recibido. El diseño de sus
 * componentes se conserva en {@code Simulador.form}.
 *
 * @author Anthony Hetzael Suc Gomez, carné: 9959 24 389
 */
public class Simulador extends JPanel implements Scrollable {
    private final Motor motor = new Motor();
    private final Archivo archivo = new Archivo();
    private final Tabla modelo = new Tabla();
    private final Timer pulso = new Timer(650, evento -> avanzar());
    private boolean listo;

    public Simulador() {
        initComponents();
        tabla.setModel(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (int i = 0; i < modelo.getColumnCount(); i++)
            tabla.getColumnModel().getColumn(i).setPreferredWidth(i == 1 ? 140 : i == 2 ? 120 : 72);
        DefaultTableCellRenderer numeros = new DefaultTableCellRenderer();
        numeros.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.setDefaultRenderer(Integer.class, numeros);
        tabla.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccionado,
                    boolean enfocado, int fila, int columna) {
                super.getTableCellRendererComponent(tabla, valor, seleccionado, enfocado, fila, columna);
                Estado estado = modelo.proceso(tabla.convertRowIndexToModel(fila)).estado();
                setForeground(switch (estado) {
                    case EJECUTANDO, TERMINADO -> Estilo.VERDE;
                    case BLOQUEADO, CANCELADO -> Estilo.ROJO;
                    default -> Estilo.AZUL;
                });
                setFont(getFont().deriveFont(Font.BOLD));
                return this;
            }
        });
        tabla.getSelectionModel().addListSelectionListener(evento -> habilitar());
        motor.cargar(Motor.ejemplo());
        ayuda.setCaretPosition(0);
        listo = true;
        actualizar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cabecera = new javax.swing.JPanel();
        marca = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        subtitulo = new javax.swing.JLabel();
        archivos = new javax.swing.JPanel();
        ejemplo = new javax.swing.JButton();
        vaciar = new javax.swing.JButton();
        abrir = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        informe = new javax.swing.JButton();
        exportar = new javax.swing.JButton();
        contenido = new javax.swing.JPanel();
        ajustes = new javax.swing.JPanel();
        controles = new javax.swing.JPanel();
        etiqueta = new javax.swing.JLabel();
        algoritmos = new javax.swing.JComboBox<>();
        cuanto = new javax.swing.JLabel();
        quantum = new javax.swing.JSpinner();
        inicio = new javax.swing.JButton();
        paso = new javax.swing.JButton();
        reinicio = new javax.swing.JButton();
        ritmo = new javax.swing.JLabel();
        velocidad = new javax.swing.JComboBox<>();
        detalle = new javax.swing.JLabel();
        metricas = new javax.swing.JPanel();
        tiempo = new javax.swing.JPanel();
        instante = new javax.swing.JLabel();
        reloj = new javax.swing.JLabel();
        unidad = new javax.swing.JLabel();
        carga = new javax.swing.JPanel();
        porcentaje = new javax.swing.JLabel();
        uso = new javax.swing.JLabel();
        periodo = new javax.swing.JLabel();
        finales = new javax.swing.JPanel();
        total = new javax.swing.JLabel();
        terminados = new javax.swing.JLabel();
        cantidad = new javax.swing.JLabel();
        demoras = new javax.swing.JPanel();
        demora = new javax.swing.JLabel();
        espera = new javax.swing.JLabel();
        promedio = new javax.swing.JLabel();
        retornos = new javax.swing.JPanel();
        duracion = new javax.swing.JLabel();
        retorno = new javax.swing.JLabel();
        media = new javax.swing.JLabel();
        respuestas = new javax.swing.JPanel();
        reaccion = new javax.swing.JLabel();
        respuesta = new javax.swing.JLabel();
        latencia = new javax.swing.JLabel();
        trabajo = new javax.swing.JPanel();
        procesos = new javax.swing.JPanel();
        lista = new javax.swing.JLabel();
        desplazamiento = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        acciones = new javax.swing.JPanel();
        agregar = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        bloquear = new javax.swing.JButton();
        desbloquear = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        historial = new javax.swing.JPanel();
        cola = new javax.swing.JLabel();
        pestanas = new javax.swing.JTabbedPane();
        cronograma = new javax.swing.JScrollPane();
        gantt = new presentacion.Gantt();
        sucesos = new javax.swing.JScrollPane();
        registro = new javax.swing.JTextArea();
        manual = new javax.swing.JScrollPane();
        ayuda = new javax.swing.JEditorPane();
        estado = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 245, 250));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 14, 14, 14));
        setPreferredSize(new java.awt.Dimension(1220, 840));
        setLayout(new java.awt.BorderLayout(0, 12));

        cabecera.setBackground(new java.awt.Color(20, 32, 52));
        cabecera.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        cabecera.setLayout(new java.awt.BorderLayout(12, 0));

        marca.setBackground(new java.awt.Color(20, 32, 52));
        marca.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        marca.setLayout(new java.awt.BorderLayout(0, 4));

        titulo.setText("Simulador de CPU");
        titulo.setFont(new java.awt.Font("Segoe UI", 1, 23)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        marca.add(titulo, java.awt.BorderLayout.CENTER);

        subtitulo.setForeground(new java.awt.Color(176, 193, 219));
        subtitulo.setText("Grupo4");
        marca.add(subtitulo, java.awt.BorderLayout.SOUTH);

        cabecera.add(marca, java.awt.BorderLayout.WEST);

        archivos.setBackground(new java.awt.Color(20, 32, 52));
        archivos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        archivos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 6));

        ejemplo.setText("Ejemplo");
        ejemplo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        ejemplo.setBackground(new java.awt.Color(232, 238, 248));
        ejemplo.setForeground(new java.awt.Color(34, 48, 68));
        ejemplo.setFocusPainted(false);
        ejemplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejemplificar(evt);
            }
        });
        archivos.add(ejemplo);

        vaciar.setText("Vaciar");
        vaciar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        vaciar.setBackground(new java.awt.Color(232, 238, 248));
        vaciar.setForeground(new java.awt.Color(34, 48, 68));
        vaciar.setFocusPainted(false);
        vaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaciar(evt);
            }
        });
        archivos.add(vaciar);

        abrir.setText("Abrir CSV");
        abrir.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        abrir.setBackground(new java.awt.Color(232, 238, 248));
        abrir.setForeground(new java.awt.Color(34, 48, 68));
        abrir.setFocusPainted(false);
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrir(evt);
            }
        });
        archivos.add(abrir);

        guardar.setText("Guardar CSV");
        guardar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        guardar.setBackground(new java.awt.Color(232, 238, 248));
        guardar.setForeground(new java.awt.Color(34, 48, 68));
        guardar.setFocusPainted(false);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar(evt);
            }
        });
        archivos.add(guardar);

        informe.setText("Resultados CSV");
        informe.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        informe.setBackground(new java.awt.Color(232, 238, 248));
        informe.setForeground(new java.awt.Color(34, 48, 68));
        informe.setFocusPainted(false);
        informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informar(evt);
            }
        });
        archivos.add(informe);

        exportar.setBackground(new java.awt.Color(232, 238, 248));
        exportar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        exportar.setForeground(new java.awt.Color(34, 48, 68));
        exportar.setText("Gantt CSV");
        exportar.setFocusPainted(false);
        exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportar(evt);
            }
        });
        archivos.add(exportar);

        cabecera.add(archivos, java.awt.BorderLayout.EAST);

        add(cabecera, java.awt.BorderLayout.NORTH);

        contenido.setBackground(new java.awt.Color(242, 245, 250));
        contenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        contenido.setLayout(new java.awt.BorderLayout(0, 12));

        ajustes.setBackground(new java.awt.Color(242, 245, 250));
        ajustes.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ajustes.setLayout(new java.awt.BorderLayout(0, 8));

        controles.setBackground(new java.awt.Color(255, 255, 255));
        controles.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        controles.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 3));

        etiqueta.setText("Algoritmo");
        etiqueta.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        etiqueta.setForeground(new java.awt.Color(34, 48, 68));
        controles.add(etiqueta);

        algoritmos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FCFS", "SJF", "SRTF", "Round Robin", "Prioridad" }));
        algoritmos.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        algoritmos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionar(evt);
            }
        });
        controles.add(algoritmos);

        cuanto.setText("Quantum");
        cuanto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cuanto.setForeground(new java.awt.Color(34, 48, 68));
        controles.add(cuanto);

        quantum.setModel(new javax.swing.SpinnerNumberModel(2, 1, 100, 1));
        quantum.setPreferredSize(new java.awt.Dimension(70, 30));
        quantum.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ajustar(evt);
            }
        });
        controles.add(quantum);

        inicio.setText("Iniciar");
        inicio.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        inicio.setBackground(new java.awt.Color(45, 99, 226));
        inicio.setForeground(new java.awt.Color(255, 255, 255));
        inicio.setFocusPainted(false);
        inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciar(evt);
            }
        });
        controles.add(inicio);

        paso.setText("Paso +1");
        paso.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        paso.setBackground(new java.awt.Color(232, 238, 248));
        paso.setForeground(new java.awt.Color(34, 48, 68));
        paso.setFocusPainted(false);
        paso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasar(evt);
            }
        });
        controles.add(paso);

        reinicio.setText("Reiniciar");
        reinicio.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        reinicio.setBackground(new java.awt.Color(232, 238, 248));
        reinicio.setForeground(new java.awt.Color(34, 48, 68));
        reinicio.setFocusPainted(false);
        reinicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciar(evt);
            }
        });
        controles.add(reinicio);

        ritmo.setText("Velocidad");
        ritmo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        ritmo.setForeground(new java.awt.Color(34, 48, 68));
        controles.add(ritmo);

        velocidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lenta", "Normal", "Rápida", "Muy rápida" }));
        velocidad.setSelectedIndex(1);
        velocidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        velocidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acelerar(evt);
            }
        });
        controles.add(velocidad);

        ajustes.add(controles, java.awt.BorderLayout.NORTH);

        detalle.setText("Configura el algoritmo para comenzar.");
        detalle.setForeground(new java.awt.Color(103, 118, 139));
        ajustes.add(detalle, java.awt.BorderLayout.CENTER);

        metricas.setBackground(new java.awt.Color(242, 245, 250));
        metricas.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        metricas.setLayout(new java.awt.GridLayout(1, 6, 8, 0));

        tiempo.setBackground(new java.awt.Color(255, 255, 255));
        tiempo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tiempo.setLayout(new java.awt.BorderLayout(0, 2));

        instante.setText("RELOJ");
        instante.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        instante.setForeground(new java.awt.Color(103, 118, 139));
        tiempo.add(instante, java.awt.BorderLayout.NORTH);

        reloj.setText("—");
        reloj.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        reloj.setForeground(new java.awt.Color(34, 48, 68));
        tiempo.add(reloj, java.awt.BorderLayout.CENTER);

        unidad.setText("unidades simuladas");
        unidad.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        unidad.setForeground(new java.awt.Color(103, 118, 139));
        tiempo.add(unidad, java.awt.BorderLayout.SOUTH);

        metricas.add(tiempo);

        carga.setBackground(new java.awt.Color(255, 255, 255));
        carga.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        carga.setLayout(new java.awt.BorderLayout(0, 2));

        porcentaje.setText("CPU OCUPADA");
        porcentaje.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        porcentaje.setForeground(new java.awt.Color(103, 118, 139));
        carga.add(porcentaje, java.awt.BorderLayout.NORTH);

        uso.setText("—");
        uso.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        uso.setForeground(new java.awt.Color(34, 48, 68));
        carga.add(uso, java.awt.BorderLayout.CENTER);

        periodo.setText("desde el inicio");
        periodo.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        periodo.setForeground(new java.awt.Color(103, 118, 139));
        carga.add(periodo, java.awt.BorderLayout.SOUTH);

        metricas.add(carga);

        finales.setBackground(new java.awt.Color(255, 255, 255));
        finales.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        finales.setLayout(new java.awt.BorderLayout(0, 2));

        total.setText("TERMINADOS");
        total.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        total.setForeground(new java.awt.Color(103, 118, 139));
        finales.add(total, java.awt.BorderLayout.NORTH);

        terminados.setText("—");
        terminados.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        terminados.setForeground(new java.awt.Color(34, 48, 68));
        finales.add(terminados, java.awt.BorderLayout.CENTER);

        cantidad.setText("procesos del escenario");
        cantidad.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cantidad.setForeground(new java.awt.Color(103, 118, 139));
        finales.add(cantidad, java.awt.BorderLayout.SOUTH);

        metricas.add(finales);

        demoras.setBackground(new java.awt.Color(255, 255, 255));
        demoras.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        demoras.setLayout(new java.awt.BorderLayout(0, 2));

        demora.setText("ESPERA MEDIA");
        demora.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        demora.setForeground(new java.awt.Color(103, 118, 139));
        demoras.add(demora, java.awt.BorderLayout.NORTH);

        espera.setText("—");
        espera.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        espera.setForeground(new java.awt.Color(34, 48, 68));
        demoras.add(espera, java.awt.BorderLayout.CENTER);

        promedio.setText("terminados · unidades");
        promedio.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        promedio.setForeground(new java.awt.Color(103, 118, 139));
        demoras.add(promedio, java.awt.BorderLayout.SOUTH);

        metricas.add(demoras);

        retornos.setBackground(new java.awt.Color(255, 255, 255));
        retornos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        retornos.setLayout(new java.awt.BorderLayout(0, 2));

        duracion.setText("RETORNO MEDIO");
        duracion.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        duracion.setForeground(new java.awt.Color(103, 118, 139));
        retornos.add(duracion, java.awt.BorderLayout.NORTH);

        retorno.setText("—");
        retorno.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        retorno.setForeground(new java.awt.Color(34, 48, 68));
        retornos.add(retorno, java.awt.BorderLayout.CENTER);

        media.setText("terminados · unidades");
        media.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        media.setForeground(new java.awt.Color(103, 118, 139));
        retornos.add(media, java.awt.BorderLayout.SOUTH);

        metricas.add(retornos);

        respuestas.setBackground(new java.awt.Color(255, 255, 255));
        respuestas.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        respuestas.setLayout(new java.awt.BorderLayout(0, 2));

        reaccion.setText("RESPUESTA MEDIA");
        reaccion.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        reaccion.setForeground(new java.awt.Color(103, 118, 139));
        respuestas.add(reaccion, java.awt.BorderLayout.NORTH);

        respuesta.setText("—");
        respuesta.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        respuesta.setForeground(new java.awt.Color(34, 48, 68));
        respuestas.add(respuesta, java.awt.BorderLayout.CENTER);

        latencia.setText("terminados · unidades");
        latencia.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        latencia.setForeground(new java.awt.Color(103, 118, 139));
        respuestas.add(latencia, java.awt.BorderLayout.SOUTH);

        metricas.add(respuestas);

        ajustes.add(metricas, java.awt.BorderLayout.SOUTH);

        contenido.add(ajustes, java.awt.BorderLayout.NORTH);

        trabajo.setBackground(new java.awt.Color(242, 245, 250));
        trabajo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        trabajo.setLayout(new java.awt.BorderLayout(0, 10));

        procesos.setBackground(new java.awt.Color(255, 255, 255));
        procesos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        procesos.setLayout(new java.awt.BorderLayout(0, 8));

        lista.setText("Procesos / Selecciona una fila para administrarla");
        lista.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lista.setForeground(new java.awt.Color(34, 48, 68));
        procesos.add(lista, java.awt.BorderLayout.NORTH);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Proceso", "Estado", "Llegada", "Ráfaga", "Prioridad", "Restante", "Espera", "Bloqueo", "Inicio", "Fin", "Retorno", "Respuesta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setRowHeight(30);
        tabla.setAutoCreateRowSorter(true);
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setFillsViewportHeight(true);
        tabla.setBackground(new java.awt.Color(255, 255, 255));
        tabla.setSelectionBackground(new java.awt.Color(226, 235, 255));
        tabla.setSelectionForeground(new java.awt.Color(34, 48, 68));
        tabla.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        desplazamiento.setViewportView(tabla);

        procesos.add(desplazamiento, java.awt.BorderLayout.CENTER);

        acciones.setBackground(new java.awt.Color(255, 255, 255));
        acciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        acciones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));

        agregar.setText("+ Agregar proceso");
        agregar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        agregar.setBackground(new java.awt.Color(45, 99, 226));
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setFocusPainted(false);
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar(evt);
            }
        });
        acciones.add(agregar);

        editar.setText("Editar");
        editar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        editar.setBackground(new java.awt.Color(232, 238, 248));
        editar.setForeground(new java.awt.Color(34, 48, 68));
        editar.setFocusPainted(false);
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar(evt);
            }
        });
        acciones.add(editar);

        eliminar.setText("Eliminar");
        eliminar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        eliminar.setBackground(new java.awt.Color(232, 238, 248));
        eliminar.setForeground(new java.awt.Color(34, 48, 68));
        eliminar.setFocusPainted(false);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar(evt);
            }
        });
        acciones.add(eliminar);

        bloquear.setText("Bloquear");
        bloquear.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        bloquear.setBackground(new java.awt.Color(232, 238, 248));
        bloquear.setForeground(new java.awt.Color(34, 48, 68));
        bloquear.setFocusPainted(false);
        bloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloquear(evt);
            }
        });
        acciones.add(bloquear);

        desbloquear.setText("Desbloquear");
        desbloquear.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        desbloquear.setBackground(new java.awt.Color(232, 238, 248));
        desbloquear.setForeground(new java.awt.Color(34, 48, 68));
        desbloquear.setFocusPainted(false);
        desbloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desbloquear(evt);
            }
        });
        acciones.add(desbloquear);

        cancelar.setText("Cancelar proceso");
        cancelar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cancelar.setBackground(new java.awt.Color(232, 238, 248));
        cancelar.setForeground(new java.awt.Color(34, 48, 68));
        cancelar.setFocusPainted(false);
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar(evt);
            }
        });
        acciones.add(cancelar);

        procesos.add(acciones, java.awt.BorderLayout.SOUTH);

        trabajo.add(procesos, java.awt.BorderLayout.CENTER);

        historial.setBackground(new java.awt.Color(255, 255, 255));
        historial.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        historial.setPreferredSize(new java.awt.Dimension(900, 210));
        historial.setLayout(new java.awt.BorderLayout(0, 6));

        cola.setText("CPU: libre | Listos: ninguno");
        cola.setForeground(new java.awt.Color(34, 48, 68));
        historial.add(cola, java.awt.BorderLayout.NORTH);

        gantt.setPreferredSize(new java.awt.Dimension(900, 150));
        cronograma.setViewportView(gantt);

        pestanas.addTab("Diagrama de Gantt", cronograma);

        registro.setEditable(false);
        registro.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        registro.setRows(6);
        sucesos.setViewportView(registro);

        pestanas.addTab("Registro de eventos", sucesos);

        ayuda.setEditable(false);
        ayuda.setContentType("text/html"); // NOI18N
        ayuda.setText("<html><body style=\"font-family:Segoe UI;color:#223044;margin:18px\">\n<h1>Guía del simulador de CPU</h1>\n<p>Agrega procesos o utiliza el ejemplo. Configura el algoritmo y pulsa <b>Iniciar</b> o <b>Paso +1</b>.\nCada paso representa una unidad simulada; la velocidad solo cambia la animación.</p>\n<h2>Algoritmos</h2><ul>\n<li><b>FCFS:</b> orden de entrada a listos, sin expropiación.</li>\n<li><b>SJF:</b> menor ráfaga restante entre los listos, sin expropiación.</li>\n<li><b>SRTF:</b> expropia cuando aparece un proceso con tiempo restante estrictamente menor.</li>\n<li><b>Round Robin:</b> turnos de duración quantum; una llegada en el límite entra antes que el proceso desalojado.</li>\n<li><b>Prioridad:</b> 1 es la más alta y 10 la más baja; sin expropiación.</li></ul>\n<p>Los empates se resuelven por ingreso a listos. Llegadas simultáneas: menor ID primero.\nLa cola muestra el orden de ingreso; cada algoritmo aplica después su regla de selección.</p>\n<h2>Administración</h2><p>Selecciona una fila para bloquear, desbloquear o cancelar. Los bloqueos son manuales.\nSi todos están bloqueados, la animación se pausa. Puedes desbloquear alguno o avanzar con Paso +1.\nReiniciar conserva el escenario y borra los resultados; permite editar, eliminar o cambiar el algoritmo.\nLos nuevos procesos deben llegar en el instante actual o después.</p>\n<h2>Métricas</h2><ul><li><b>Espera:</b> tiempo en Listo, sin ejecución ni bloqueo.</li>\n<li><b>Retorno:</b> fin menos llegada.</li><li><b>Respuesta:</b> primera ejecución menos llegada.</li>\n<li><b>CPU:</b> tiempo ocupado / tiempo transcurrido × 100.</li>\n<li><b>Rendimiento:</b> terminados / tiempo transcurrido.</li></ul>\n<p>Los promedios incluyen solo terminados; los cancelados no cuentan. — o una casilla vacía indica que aún no hay dato.\nEl Gantt usa intervalos [inicio, fin); gris representa CPU ociosa. Pasa el cursor sobre un bloque para ver su duración.</p>\n<h2>Archivos</h2><p>Abrir CSV y Guardar CSV trabajan con escenarios. Encabezado:\n<code>nombre,llegada,rafaga,prioridad</code>. Hasta 100 procesos, nombres de 1–40 caracteres, llegada 0–10000,\nráfaga 1–1000, prioridad 1–10 y quantum 1–100. Se usa UTF-8. Guardar no conserva una sesión en ejecución.\nResultados y Gantt exportan reportes separados; no son escenarios importables.</p>\n<p>Este modelo tiene una CPU, sin coste de cambio de contexto, memoria simulada ni E/S automática.</p>\n</body></html>");
        manual.setViewportView(ayuda);

        pestanas.addTab("Guía de uso", manual);

        historial.add(pestanas, java.awt.BorderLayout.CENTER);

        trabajo.add(historial, java.awt.BorderLayout.SOUTH);

        contenido.add(trabajo, java.awt.BorderLayout.CENTER);

        add(contenido, java.awt.BorderLayout.CENTER);

        estado.setText("Listo para comenzar.");
        estado.setForeground(new java.awt.Color(103, 118, 139));
        add(estado, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents
    // </editor-fold>


    private void configurar() {
        if (!listo || motor.tiempo() != 0) return;
        actuar(() -> motor.configurar(Metodo.values()[algoritmos.getSelectedIndex()], (int) quantum.getValue()));
    }

    public void pausar() { pulso.stop(); if (listo) actualizar(); }

    private void avanzar() {
        motor.avanzar();
        if (motor.completo() || motor.detenido()) pulso.stop();
        actualizar();
    }

    private void actuar(Runnable operacion) {
        try { operacion.run(); }
        catch (IllegalArgumentException | IllegalStateException excepcion) { Dialogos.error(this, excepcion); }
        if (motor.completo() || motor.detenido()) pulso.stop();
        actualizar();
    }

    private void aplicar(java.util.function.Consumer<Proceso> operacion) {
        Proceso proceso = seleccionado();
        if (proceso != null) actuar(() -> operacion.accept(proceso));
    }

    private Proceso seleccionado() {
        int fila = tabla.getSelectedRow();
        return fila < 0 || fila >= tabla.getRowCount() ? null : modelo.proceso(tabla.convertRowIndexToModel(fila));
    }

    private void habilitar() {
        Proceso proceso = seleccionado();
        editar.setEnabled(proceso != null && motor.tiempo() == 0 && !pulso.isRunning());
        eliminar.setEnabled(editar.isEnabled());
        bloquear.setEnabled(proceso != null && (proceso.estado() == Estado.LISTO || proceso.estado() == Estado.EJECUTANDO));
        desbloquear.setEnabled(proceso != null && proceso.estado() == Estado.BLOQUEADO);
        cancelar.setEnabled(proceso != null && !proceso.finalizado());
        agregar.setEnabled(motor.procesos().size() < Motor.LIMITE && motor.tiempo() <= 10_000);
    }

    private void actualizar() {
        Proceso seleccion = seleccionado();
        modelo.actualizar(motor.procesos());
        if (seleccion != null) {
            for (int i = 0; i < modelo.getRowCount(); i++) if (modelo.proceso(i).id() == seleccion.id()) {
                int fila = tabla.convertRowIndexToView(i);
                if (fila >= 0) tabla.setRowSelectionInterval(fila, fila);
                break;
            }
        }
        reloj.setText(motor.tiempo() + " u");
        uso.setText(formato(motor.uso()) + " %");
        terminados.setText(motor.terminados() + " / " + motor.procesos().size());
        boolean completo = motor.terminados() > 0;
        espera.setText(completo ? formato(motor.demora()) : "—");
        retorno.setText(completo ? formato(motor.retorno()) : "—");
        respuesta.setText(completo ? formato(motor.respuesta()) : "—");
        inicio.setText(pulso.isRunning() ? "Pausar" : motor.tiempo() > 0 ? "Continuar" : "Iniciar");
        inicio.setEnabled(!motor.completo() && !motor.detenido());
        paso.setEnabled(!pulso.isRunning() && !motor.completo());
        algoritmos.setEnabled(motor.tiempo() == 0 && !pulso.isRunning());
        quantum.setEnabled(algoritmos.isEnabled() && motor.algoritmo() == Metodo.TURNOS);
        detalle.setText(motor.algoritmo().descripcion() + "  ·  Una CPU, tiempos en unidades simuladas.");
        String listos = motor.listos().stream().map(proceso -> "P" + proceso.id()).collect(java.util.stream.Collectors.joining(" → "));
        String texto = "CPU: " + (motor.actual() == null ? "libre" : "P" + motor.actual().id())
                + "    |    Listos (orden de entrada): " + (listos.isEmpty() ? "ninguno" : listos);
        cola.setText(texto); cola.setToolTipText(texto);
        gantt.actualizar(motor.tramos(), motor.tiempo());
        registro.setText(String.join("\n", motor.eventos())); registro.setCaretPosition(registro.getDocument().getLength());
        String mensaje = motor.procesos().isEmpty() ? "Agrega procesos para comenzar."
                : motor.completo() ? "Simulación finalizada. Reinicia para comparar otro algoritmo."
                : motor.detenido() ? "Desbloquea un proceso para continuar; Paso +1 permite avanzar el reloj."
                : pulso.isRunning() ? "Simulación en ejecución."
                : motor.tiempo() == 0 ? "Listo. Configura el algoritmo y pulsa Iniciar o Paso +1." : "Simulación en pausa.";
        estado.setText(mensaje + "    |    Rendimiento: " + String.format(Locale.ROOT, "%.3f", motor.rendimiento()) + " procesos/u");
        habilitar();
    }

    private static String formato(double valor) { return String.format(Locale.ROOT, "%.1f", valor); }

    private boolean confirmar() {
        return motor.procesos().isEmpty() || JOptionPane.showConfirmDialog(this,
                "Se reemplazará el escenario y se borrarán los resultados actuales. ¿Continuar?", "Reemplazar escenario",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void importar() {
        pausar();
        Path ruta = Dialogos.elegir(this, false, "");
        if (ruta == null) return;
        try { List<Ficha> fichas = archivo.leer(ruta); if (confirmar()) motor.cargar(fichas); }
        catch (Exception excepcion) { Dialogos.error(this, excepcion); }
        actualizar();
    }

    private void exportar(String tipo) {
        pausar();
        Path ruta = Dialogos.elegir(this, true, tipo + ".csv");
        if (ruta == null) return;
        try {
            switch (tipo) {
                case "escenario" -> archivo.guardar(ruta, motor.escenario());
                case "resultados" -> archivo.informar(ruta, motor);
                case "gantt" -> archivo.graficar(ruta, motor);
                default -> throw new IllegalArgumentException("Formato no soportado.");
            }
            estado.setText("Archivo guardado: " + ruta.toAbsolutePath());
        } catch (Exception excepcion) { Dialogos.error(this, excepcion); }
    }

    @Override public Dimension getPreferredScrollableViewportSize() { return getPreferredSize(); }
    @Override public int getScrollableUnitIncrement(Rectangle visible, int orientacion, int direccion) { return 24; }
    @Override public int getScrollableBlockIncrement(Rectangle visible, int orientacion, int direccion) { return Math.max(24, visible.height - 40); }
    @Override public boolean getScrollableTracksViewportWidth() { return true; }
    @Override public boolean getScrollableTracksViewportHeight() {
        return getParent() instanceof JViewport visor && visor.getHeight() >= getPreferredSize().height;
    }


    private void seleccionar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_seleccionar
        configurar();
    }//GEN-LAST:event_seleccionar

    private void ajustar(javax.swing.event.ChangeEvent evento) {//GEN-FIRST:event_ajustar
        configurar();
    }//GEN-LAST:event_ajustar

    private void acelerar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_acelerar
        pulso.setDelay(new int[] {1200, 650, 250, 60}[velocidad.getSelectedIndex()]);
    }//GEN-LAST:event_acelerar

    private void iniciar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_iniciar
        if (pulso.isRunning()) pausar();
        else if (!motor.completo() && !motor.detenido()) pulso.start();
        actualizar();
    }//GEN-LAST:event_iniciar

    private void pasar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_pasar
        avanzar();
    }//GEN-LAST:event_pasar

    private void reiniciar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_reiniciar
        pausar(); motor.reiniciar(); actualizar();
    }//GEN-LAST:event_reiniciar

    private void ejemplificar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_ejemplificar
        pausar(); if (confirmar()) { motor.cargar(Motor.ejemplo()); actualizar(); }
    }//GEN-LAST:event_ejemplificar

    private void vaciar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_vaciar
        pausar(); if (confirmar()) { motor.cargar(java.util.List.of()); actualizar(); }
    }//GEN-LAST:event_vaciar

    private void abrir(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_abrir
        importar();
    }//GEN-LAST:event_abrir

    private void guardar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_guardar
        exportar("escenario");
    }//GEN-LAST:event_guardar

    private void informar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_informar
        exportar("resultados");
    }//GEN-LAST:event_informar

    private void exportar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_exportar
        exportar("gantt");
    }//GEN-LAST:event_exportar

    private void agregar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_agregar
        pausar();
        Dialogos.proceso(this, null, motor.tiempo()).ifPresent(ficha -> actuar(() -> motor.agregar(ficha)));
    }//GEN-LAST:event_agregar

    private void editar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_editar
        pausar();
        Proceso proceso = seleccionado();
        if (proceso != null) Dialogos.proceso(this, proceso.ficha(), 0).ifPresent(ficha -> actuar(() -> motor.editar(proceso.id(), ficha)));
    }//GEN-LAST:event_editar

    private void eliminar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_eliminar
        aplicar(proceso -> motor.eliminar(proceso.id()));
    }//GEN-LAST:event_eliminar

    private void bloquear(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_bloquear
        aplicar(proceso -> motor.bloquear(proceso.id()));
    }//GEN-LAST:event_bloquear

    private void desbloquear(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_desbloquear
        aplicar(proceso -> motor.desbloquear(proceso.id()));
    }//GEN-LAST:event_desbloquear

    private void cancelar(java.awt.event.ActionEvent evento) {//GEN-FIRST:event_cancelar
        aplicar(proceso -> motor.cancelar(proceso.id()));
    }//GEN-LAST:event_cancelar

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrir;
    private javax.swing.JPanel acciones;
    private javax.swing.JButton agregar;
    private javax.swing.JPanel ajustes;
    private javax.swing.JComboBox<String> algoritmos;
    private javax.swing.JPanel archivos;
    private javax.swing.JEditorPane ayuda;
    private javax.swing.JButton bloquear;
    private javax.swing.JPanel cabecera;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel cantidad;
    private javax.swing.JPanel carga;
    private javax.swing.JLabel cola;
    private javax.swing.JPanel contenido;
    private javax.swing.JPanel controles;
    private javax.swing.JScrollPane cronograma;
    private javax.swing.JLabel cuanto;
    private javax.swing.JLabel demora;
    private javax.swing.JPanel demoras;
    private javax.swing.JButton desbloquear;
    private javax.swing.JScrollPane desplazamiento;
    private javax.swing.JLabel detalle;
    private javax.swing.JLabel duracion;
    private javax.swing.JButton editar;
    private javax.swing.JButton ejemplo;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel espera;
    private javax.swing.JLabel estado;
    private javax.swing.JLabel etiqueta;
    private javax.swing.JButton exportar;
    private javax.swing.JPanel finales;
    private presentacion.Gantt gantt;
    private javax.swing.JButton guardar;
    private javax.swing.JPanel historial;
    private javax.swing.JButton informe;
    private javax.swing.JButton inicio;
    private javax.swing.JLabel instante;
    private javax.swing.JLabel latencia;
    private javax.swing.JLabel lista;
    private javax.swing.JScrollPane manual;
    private javax.swing.JPanel marca;
    private javax.swing.JLabel media;
    private javax.swing.JPanel metricas;
    private javax.swing.JButton paso;
    private javax.swing.JLabel periodo;
    private javax.swing.JTabbedPane pestanas;
    private javax.swing.JLabel porcentaje;
    private javax.swing.JPanel procesos;
    private javax.swing.JLabel promedio;
    private javax.swing.JSpinner quantum;
    private javax.swing.JLabel reaccion;
    private javax.swing.JTextArea registro;
    private javax.swing.JButton reinicio;
    private javax.swing.JLabel reloj;
    private javax.swing.JLabel respuesta;
    private javax.swing.JPanel respuestas;
    private javax.swing.JLabel retorno;
    private javax.swing.JPanel retornos;
    private javax.swing.JLabel ritmo;
    private javax.swing.JLabel subtitulo;
    private javax.swing.JScrollPane sucesos;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel terminados;
    private javax.swing.JPanel tiempo;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel total;
    private javax.swing.JPanel trabajo;
    private javax.swing.JLabel unidad;
    private javax.swing.JLabel uso;
    private javax.swing.JButton vaciar;
    private javax.swing.JComboBox<String> velocidad;
    // End of variables declaration//GEN-END:variables
}
