import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;

public class GeneradorDeInfo {
    // Método para obtener el valor de una moneda específica
    public Double tomaValores(Monedas moneda, String aliasMoneda) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Acceder al valor de la moneda que se pasa como parámetro
        Double valMoneda = moneda.getConversionRates().get(aliasMoneda);

        // Retornar el valor de la moneda si existe, de lo contrario null
        return valMoneda;
    }
}
