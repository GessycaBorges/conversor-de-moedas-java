import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorDeMoedas {

    private static final String URL = "https://v6.exchangerate-api.com/v6/0addea1a5cfaf8bf517452e0/latest/";

    public double converter (Conversao conversao, double valor) throws IOException, InterruptedException {
        String moedaInicial = conversao.getMoedaInicial();
        String moedaFinal = conversao.getMoedaConvertida();

        URI endereco = URI.create(URL + moedaInicial);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        HttpResponse<String>response = HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        MoedaConvertida moedaConvertida = gson.fromJson(response.body(), MoedaConvertida.class);

        Double taxaDeCambio = moedaConvertida.getConversion_rates().get(moedaFinal);
        if (taxaDeCambio == null) {
            throw new IllegalArgumentException("Moeda de destino inválida: " + moedaFinal);
        }

        return valor * taxaDeCambio;
    }
}
