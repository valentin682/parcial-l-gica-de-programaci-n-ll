import controladores.EnvioControlador;
import servicios.EnvioServicio;
import javax.swing.SwingUtilities;
import vistas.EnvioVista;

public class Logistica {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EnvioVista vista = new EnvioVista();
            EnvioServicio servicio = new EnvioServicio();
            new EnvioControlador(vista, servicio);
            
            vista.setVisible(true);
        });
    }
}