package modelos;

public enum Conversao {

    USD_ARS(1, "USD", "ARS"),
    USD_BRL(2, "USD", "BRL"),
    USD_EUR(3, "USD", "EUR"),
    ARS_USD(4, "ARS", "USD"),
    ARS_BRL(5, "ARS", "BRL"),
    ARS_EUR(6, "ARS", "EUR"),
    BRL_ARS(7, "BRL", "ARS"),
    BRL_USD(8, "BRL", "USD"),
    BRL_EUR(9, "BRL", "EUR"),
    EUR_ARS(10, "EUR", "ARS"),
    EUR_BRL(11, "EUR", "BRL"),
    EUR_USD(12, "EUR", "USD");

    private int id;
    private String moedaInicial;
    private String moedaFinal;

    Conversao(int id, String moedaInicial, String moedaConvertida) {
        this.id = id;
        this.moedaInicial = moedaInicial;
        this.moedaFinal = moedaConvertida;
    }

    public int getId() {
        return id;
    }

    public String getMoedaFinal() {
        return moedaFinal;
    }

    public String getMoedaInicial() {
        return moedaInicial;
    }

    public static Conversao buscarPorId(int id) {
        for (Conversao c : Conversao.values()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
