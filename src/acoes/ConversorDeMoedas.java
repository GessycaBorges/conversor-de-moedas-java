package acoes;

import com.google.gson.Gson;
import modelos.Conversao;
import modelos.HistoricoDeConversao;
import modelos.MoedaConvertida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ConversorDeMoedas {

    private static final String API_KEY = "0addea1a5cfaf8bf517452e0";
    private static final String URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    private final List<HistoricoDeConversao> historico = new ArrayList<>();

    public void salvarHistorico (){
        try (FileWriter writer = new FileWriter("historico.txt", true)){
            for (HistoricoDeConversao conversao : historico){
                writer.write(conversao.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar historico");
        }
    }

    public void carregarHistorico(){
        try (BufferedReader br = new BufferedReader(new FileReader("historico.txt"))) {
            String linha;
            System.out.println("----------------------------------------------------------------");
            System.out.println("Histórico de consultas realizadas: ");
            while ((linha = br.readLine()) != null){
                System.out.println(linha);
            }
            System.out.println("\n----------------------------------------------------------------");
        }catch (IOException e){
            System.out.println("Erro ao carregar historico");
        }
    }

    public double converter (Conversao conversao, double valor) throws IOException, InterruptedException {
        String moedaInicial = conversao.getMoedaInicial();
        String moedaFinal = conversao.getMoedaFinal();

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
        double resultado = valor * taxaDeCambio;

        historico.add(new HistoricoDeConversao(moedaInicial, moedaFinal, valor, resultado));

        return resultado;
    }
}
