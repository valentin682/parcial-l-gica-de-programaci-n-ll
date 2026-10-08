package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.TipoEnvio;

public class EnvioVista extends JFrame {

    private JTextField txtCodigo, txtCliente, txtPeso, txtDistancia;
    private JComboBox<TipoEnvio> cbxTipo;
    private JButton btnAgregar, btnEliminar, btnGuardar, btnCancelar;
    private JTable tablaEnvios;
    private DefaultTableModel modeloTabla;

    public EnvioVista() {
        super("Operador Logístico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL SUPERIOR: BARRA DE BOTONES PRINCIPALES (ACCIONES) ---
        JPanel pnlBarraHerramientas = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnAgregar = new JButton("Agregar Envío (+)");
        btnEliminar = new JButton("Eliminar Envío (-)");

        pnlBarraHerramientas.add(btnAgregar);
        pnlBarraHerramientas.add(btnEliminar);

        // --- PANEL CONTENEDOR SUPERIOR ---
        JPanel pnlNorte = new JPanel(new BorderLayout(5, 5));
        pnlNorte.add(pnlBarraHerramientas, BorderLayout.NORTH);

        // --- FORMULARIO ---
        JPanel pnlFormulario = new JPanel(new GridLayout(3, 4, 10, 10));

        pnlFormulario.add(new JLabel("Número:"));
        txtCodigo = new JTextField();
        pnlFormulario.add(txtCodigo);

        pnlFormulario.add(new JLabel("Tipo:"));
        cbxTipo = new JComboBox<>(TipoEnvio.values());
        pnlFormulario.add(cbxTipo);

        pnlFormulario.add(new JLabel("Cliente:"));
        txtCliente = new JTextField();
        pnlFormulario.add(txtCliente);

        pnlFormulario.add(new JLabel("Distancia en Km:"));
        txtDistancia = new JTextField();
        pnlFormulario.add(txtDistancia);

        pnlFormulario.add(new JLabel("Peso:"));
        txtPeso = new JTextField();
        pnlFormulario.add(txtPeso);

        // Botones de acción del formulario
        JPanel pnlBotonesForm = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        pnlBotonesForm.add(btnGuardar);
        pnlBotonesForm.add(btnCancelar);

        pnlFormulario.add(pnlBotonesForm);

        pnlNorte.add(pnlFormulario, BorderLayout.CENTER);
        add(pnlNorte, BorderLayout.NORTH);

        // --- TABLA ---
        String[] columnas = {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEnvios = new JTable(modeloTabla);
        add(new JScrollPane(tablaEnvios), BorderLayout.CENTER);
    }

    // Getters para el controlador
    public JTextField getTxtCodigo() { return txtCodigo; }
    public JTextField getTxtCliente() { return txtCliente; }
    public JTextField getTxtPeso() { return txtPeso; }
    public JTextField getTxtDistancia() { return txtDistancia; }
    public JComboBox<TipoEnvio> getCbxTipo() { return cbxTipo; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JTable getTablaEnvios() { return tablaEnvios; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cbxTipo.setSelectedIndex(0);
        txtCodigo.requestFocus();
    }
}