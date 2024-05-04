public class CambioActualizado {
    private String divisa;
    private double cambio;

    public CambioActualizado(String divisa, double cambio) {
        this.divisa = divisa;
        this.cambio = cambio;
    }

    public String getDivisa() {
        return divisa;
    }

    public double getCambio() {
        return cambio;
    }

}
