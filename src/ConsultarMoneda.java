import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMoneda {

    public Monedas consultarMoneda(String moneda){
//        URI direcccion = URI.create("https://v6.exchangerate-api.com/v6/2a7e7e668ff591837370ec04/latest/"+moneda);
        URI direcccion = URI.create("https://v6.exchangerate-api.com/v6/2a7e7e668ff591837370ec04/latest/USD");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direcccion)
                .build();
        try {
            HttpResponse<String> response =  client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Monedas.class);
        } catch (Exception e) {
            throw new RuntimeException("No encontre el valor de esa moneda ");
        }
    }


}
