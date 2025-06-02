package modelo;

import util.InterfaceUsuario;

class DescontoMaiorDoQueJurosException extends Exception{
    public DescontoMaiorDoQueJurosException(String errorMessage){
        super(errorMessage);
    }
}

public class Casa extends Financiamento {
    private double areaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, double desconto, double areaConstruida, double tamanhoTerreno) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual, desconto);
        this.areaConstruida = areaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    public double calcularPagamentoMensal() {
        double valorJurosMensal = 1 + this.taxaJurosAnual / 12;
        double pagMensalInicial = (this.valorImovel / (this.prazoFinanciamento * 12)) * valorJurosMensal;
        double valorDescontoMensal = pagMensalInicial * this.desconto;
        double pagMensalComDesconto = pagMensalInicial - valorDescontoMensal;
        return pagMensalComDesconto + 80;
    }


    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * this.getPrazoFinanciamento() * 12;
    }

    public String toString(){
        return String.format("Casa:\nValor: R$%.2f; Financiamento: R$%.2f; Taxa: %.2f%%; Prazo: %d anos; Área Construiída %.2f; Tamanho terreno: %.2f", valorImovel, calcularTotalPagamento(), taxaJurosAnual, prazoFinanciamento, areaConstruida, tamanhoTerreno);
    }
}


