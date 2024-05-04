import java.util.List;

public class CalculosDeDivisa {

    public double obtenerTasa(String divisa, List<CambioActualizado> listaCambio){
        for (CambioActualizado cambio : listaCambio){
            if (cambio.getDivisa().equalsIgnoreCase(divisa)){
                return cambio.getCambio();
            }
        }
        return 0.0;
    }

}
