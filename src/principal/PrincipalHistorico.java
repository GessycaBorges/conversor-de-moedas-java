package principal;

import acoes.ConversorDeMoedas;

public class PrincipalHistorico {
    public static void main(String[] args) {
        ConversorDeMoedas conversor = new ConversorDeMoedas();
        conversor.carregarHistorico();
    }
}
