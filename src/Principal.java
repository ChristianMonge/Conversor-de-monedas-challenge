import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        int op = 0;
        Scanner teclado = new Scanner(System.in);
        CalculosDeDivisa calculo = new CalculosDeDivisa();
        DarFormato darFormato = new DarFormato();
        double resultado, montoCambio;

        try {
            while (op != 7){

                String moneda = "USD";
                ConsultaCambioDeDivisas divisa = new ConsultaCambioDeDivisas();
                TipoCambio tipoCambio = divisa.tipoCambio();
                List<CambioActualizado> listaCambio = new ArrayList<>();

                for (Map.Entry<String, Double> entry : tipoCambio.conversion_rates().entrySet()){

                    String divisaJson = entry.getKey();
                    Double cambioJson = entry.getValue();

                    CambioActualizado cambioActualizado = new CambioActualizado(divisaJson,cambioJson);
                    listaCambio.add(cambioActualizado);

                }

                System.out.println("****************************************************************");
                System.out.println("Bienvenido/a al sistema de conversor de divisa");
                System.out.println("Elija el tipo de cambio que quiere realizar");
                System.out.println("1) dólar a peso colombiano");
                System.out.println("2) peso colombiano a dolar");
                System.out.println("3) colones a dólar");
                System.out.println("4) Dolar a colones");
                System.out.println("5) peso mexicano a dolar");
                System.out.println("6) dólar a peso mexicano");
                System.out.println("7) Salir");
                op = teclado.nextInt();
                double monto = 0;

                switch (op){

                    case 1:
                        //dolares a pesos colombianos
                        System.out.println("Ingrese la cantidad de dolares a cambiar ");
                        monto = teclado.nextDouble();
                        montoCambio = calculo.obtenerTasa("COP",listaCambio);
                        resultado = monto * montoCambio;
                        System.out.println("Cantidad en pesos colombianos es de: " + darFormato.darFormato(resultado));

                        break;
                    case 2:
                        // pesos colombianos a dolares
                        System.out.println("Ingrese la cantidad de pesos colombianos a cambiar ");
                        monto = teclado.nextDouble();
                        montoCambio = calculo.obtenerTasa("COP",listaCambio);
                         resultado = monto / montoCambio;
                        System.out.println("Cantidad de dolares es de: " + darFormato.darFormato(resultado));
                        break;
                    case 3:
                        //colones a dolar
                        System.out.println("Ingrese la cantidad de colones a cambiar ");
                        monto = teclado.nextDouble();
                        montoCambio = calculo.obtenerTasa("CRC",listaCambio);
                        resultado = monto / montoCambio;
                        System.out.println("Cantidad en pesos dolares es de: " + darFormato.darFormato(resultado));

                        break;
                    case 4:
                        //dolares a colones
                        System.out.println("Ingrese la cantidad de dolares a cambiar ");
                        monto = teclado.nextDouble();
                        montoCambio = calculo.obtenerTasa("CRC",listaCambio);
                        resultado = monto * montoCambio;
                        System.out.println("Cantidad de colones es de: " + darFormato.darFormato(resultado));
                        break;
                    case 5:
                        //peso mexicano a dolares
                        System.out.println("Ingrese la cantidad de pesos mexicanos a cambiar ");
                        monto = teclado.nextDouble();
                        montoCambio = calculo.obtenerTasa("MXN",listaCambio);
                        resultado = monto / montoCambio;
                        System.out.println("Cantidad en dolares es de: " + darFormato.darFormato(resultado));
                        break;
                    case 6:
                        //dolares a peso mexicano
                        System.out.println("Ingrese la cantidad de dolares a cambiar ");
                        monto = teclado.nextDouble();
                        montoCambio = calculo.obtenerTasa("MXN",listaCambio);
                        resultado = monto * montoCambio;
                        System.out.println("Cantidad en pesos colombianos es de: " + darFormato.darFormato(resultado));
                        break;
                    case 7:
                        System.out.println("Saliendo del programa");
                        break;
                    default:
                        System.out.println("Opción inválida, por favor seleccione una opcion correcta");
                        break;
                }

            }


        }catch (InputMismatchException e){
            System.out.println("Error: se ingresó un valor no numerico.... saliendo del sistema.");

        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
