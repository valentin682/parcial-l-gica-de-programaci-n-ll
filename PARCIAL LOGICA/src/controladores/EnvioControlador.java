package controladores;

import javax.swing.JOptionPane;
import modelos.Envio;
import modelos.TipoEnvio;
import servicios.EnvioServicio;
import vistas.EnvioVista;

public class EnvioControlador {

    private final EnvioVista vista;
    private final EnvioServicio servicio;

    public EnvioControlador(EnvioVista vista, EnvioServicio servicio) {
        this.vista = vista;
        this.servicio = servicio;

        // Registrar eventos de los botones
        this.vista.getBtnAgregar().addActionListener(e -> prepararNuevoEnvio());
        this.vista.getBtnEliminar().addActionListener(e -> eliminarEnvio());
        this.vista.getBtnGuardar().addActionListener(e -> guardarEnvio());
        this.vista.getBtnCancelar().addActionListener(e -> this.vista.limpiarCampos());
    }

    private void prepararNuevoEnvio() {
        vista.limpiarCampos();
    }

    private void guardarEnvio() {
        try {
            String codigo = vista.getTxtCodigo().getText().trim();
            String cliente = vista.getTxtCliente().getText().trim();
            double peso = Double.parseDouble(vista.getTxtPeso().getText().trim());
            double distancia = Double.parseDouble(vista.getTxtDistancia().getText().trim());
            TipoEnvio tipo = (TipoEnvio) vista.getCbxTipo().getSelectedItem();

            if (codigo.isEmpty() || cliente.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos del formulario.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            servicio.agregarEnvio(tipo, codigo, cliente, peso, distancia);
            actualizarTabla();
            vista.limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Peso y Distancia deben ser valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEnvio() {
        int filaSeleccionada = vista.getTablaEnvios().getSelectedRow();
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(
                vista, 
                "¿Está seguro de retirar/eliminar este envío?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                servicio.retirarEnvio(filaSeleccionada);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una fila de la tabla para eliminar el envío.", "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarTabla() {
        vista.getModeloTabla().setRowCount(0);
        for (Envio e : servicio.obtenerTodos()) {
            Object[] fila = {
                e.getClass().getSimpleName(),
                e.getCodigo(),
                e.getCliente(),
                e.getPeso(),
                e.getDistancia(),
                e.calcularTarifa() // Polimorfismo
            };
            vista.getModeloTabla().addRow(fila);
        }
    }
}