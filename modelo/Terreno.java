package modelo;

public class Terreno extends Financiamento {
    private String tipoZona;

    public Terreno(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, double desconto, String tipoZona) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual, desconto);
        this.tipoZona = tipoZona;
    }

    public double calcularPagamentoMensal() {
                double pagamentoMensalTerreno = (this.valorImovel / (this.prazoFinanciamento * 12)) * (1 + (this.taxaJurosAnual / 12) / 100);
        return pagamentoMensalTerreno * 1.02;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * 12 * getPrazoFinanciamento();
    }

    public String toString(){
        return String.format("Terreno:\nValor: R$%.2f; Financiamento: R$%.2f; Taxa: %.2f%%; Prazo: %d anos; Tipo Zona: %s",
                valorImovel, calcularTotalPagamento(), taxaJurosAnual, prazoFinanciamento, tipoZona);
    }
}
