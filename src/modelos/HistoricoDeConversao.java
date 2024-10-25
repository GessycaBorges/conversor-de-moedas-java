package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HistoricoDeConversao {
    private String moedaInicial;
    private String moedaFinal;
    private double valor;
    private double resultado;
    private final LocalDate data = LocalDate.now();

    public HistoricoDeConversao(String moedaInicial, String moedaFinal, double valor, double resultado) {
        this.moedaInicial = moedaInicial;
        this.moedaFinal = moedaFinal;
        this.valor = valor;
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%.2f", valor) + "(" + moedaInicial + ")" + " -> " + String.format("%.2f", resultado) + "(" + moedaFinal + ")" +
                 " (Consulta realizada em: " + data.format(formatar) + ")";
    }
}
