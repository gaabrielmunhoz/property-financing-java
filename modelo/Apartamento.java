package modelo;

public class Apartamento extends Financiamento{
    private int numeroVagas;
    private int numeroAndar;

    public Apartamento(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, double desconto, int numeroVagas, int numeroAndar){
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual, desconto);
        this.numeroVagas = numeroVagas;
        this.numeroAndar = numeroAndar;
    }

    public int getNumeroVagas() {
        return numeroVagas;
    }

    public int getNumeroAndar() {
        return numeroAndar;
    }

    public double calcularPagamentoMensal(){
        double taxaMensal = getTaxaJurosAnual()/12/100;
        int totalMeses = getPrazoFinanciamento()* 12;
        return (getValorImovel()* taxaMensal)/(1- Math.pow(1+ taxaMensal, -totalMeses));
    }

    public double calcularTotalPagamento(){
        return calcularPagamentoMensal() * 12 * getPrazoFinanciamento();
    }
    public String toString(){
        return String.format("Apartamento:\nValor: R$%.2f; Financiamento: R$%.2f; Taxa: %.2f%%; Prazo: %d anos; Vagas: %d: Andar: %d",
                valorImovel,calcularTotalPagamento(),taxaJurosAnual,prazoFinanciamento,numeroVagas,numeroAndar);
    }

}
