package principal;

import acoes.ConversorDeMoedas;
import modelos.Conversao;

import java.io.IOException;
import java.util.Scanner;

public class PrincipalConsulta {
    public static void main(String[] args) {

        int execucao = -1;

        Scanner scanner = new Scanner(System.in);
        ConversorDeMoedas conversorDeMoedas = new ConversorDeMoedas();

        System.out.println("------------------------------------------------------");
        System.out.println("Bem vindo(a) ao conversor de moedas\n");

        System.out.println("Converter:");
        System.out.println("1 - Dólar -> Peso Argentino");
        System.out.println("2 - Dólar -> Real Brasileiro");
        System.out.println("3 - Dólar -> Euro");
        System.out.println("4 - Peso Argentino -> Dólar");
        System.out.println("5 - Peso Argentino -> Real Brasileiro");
        System.out.println("6 - Peso Argentino -> Euro");
        System.out.println("7 - Real Brasileiro -> Peso Argentino");
        System.out.println("8 - Real Brasileiro -> Dólar");
        System.out.println("9 - Real Brasileiro -> Euro");
        System.out.println("10 - Euro -> Peso Argentino");
        System.out.println("11 - Euro -> Real Brasileiro");
        System.out.println("12 - Euro -> Dólar");
        System.out.println("0 - Finalizar programa");

        while (execucao != 0) {

            System.out.println("\n------------------------------------------------------");
            System.out.println("Digite uma opção de conversão que deseja realizar: ");
            int id = scanner.nextInt();

            if (id != 0){
                Conversao conversao = Conversao.buscarPorId(id);
                if (conversao == null){
                    System.out.println("Opção inválida");
                    continue;
                }

                System.out.println("Digite o valor que deseja converter: ");
                double valor = scanner.nextDouble();

                try {
                    double resultado = conversorDeMoedas.converter(conversao, valor);
                    System.out.println("Resultado: " +
                            String.format("%.2f", valor) + " (" + conversao.getMoedaInicial() + ") = " +
                            String.format("%.2f", resultado) + " (" + conversao.getMoedaFinal() + ")");
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }

            execucao = id;
        }

        conversorDeMoedas.salvarHistorico();
        System.out.println("Histórico de conversões salvo com sucesso");

    }
}