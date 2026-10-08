package servicios;

import java.util.ArrayList;
import java.util.List;
import modelos.Aereo;
import modelos.Envio;
import modelos.Maritimo;
import modelos.Terrestre;
import modelos.TipoEnvio;

public class EnvioServicio {

    private final List<Envio> listaEnvios;

    public EnvioServicio() {
        this.listaEnvios = new ArrayList<>();
    }

    public void agregarEnvio(TipoEnvio tipo, String codigo, String cliente, double peso, double distancia) {
        Envio nuevo = null;
        switch (tipo) {
            case TERRESTRE:
                nuevo = new Terrestre(codigo, cliente, peso, distancia);
                break;
            case AEREO:
                nuevo = new Aereo(codigo, cliente, peso, distancia);
                break;
            case MARITIMO:
                nuevo = new Maritimo(codigo, cliente, peso, distancia);
                break;
        }
        if (nuevo != null) {
            listaEnvios.add(nuevo);
        }
    }

    public boolean retirarEnvio(int indice) {
        if (indice >= 0 && indice < listaEnvios.size()) {
            listaEnvios.remove(indice);
            return true;
        }
        return false;
    }

    public List<Envio> obtenerTodos() {
        return listaEnvios;
    }
}