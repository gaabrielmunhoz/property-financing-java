package main;

import modelo.Financiamento;
import modelo.Apartamento;
import modelo.Casa;
import modelo.Terreno;
import util.InterfaceUsuario;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        System.out.println("Insira dados para o financiamento:");
        double taxaJuros = InterfaceUsuario.obterTaxaJuros();
        int prazoFinanciamentoEmAnos = InterfaceUsuario.obterPrazoFinanciamento();
        double valorImovel = InterfaceUsuario.obterValorImovel();
        double desconto;
        while (true){
            desconto = InterfaceUsuario.obterDesconto();
            if (desconto > taxaJuros){
                System.out.println("\nDesconto inválido! O desconto não pode ser maior que a taxa de juros.\n");
                System.out.printf("Taxa de juros: %.2f%n", taxaJuros);
                System.out.printf("Desconto informado: %.2f%n\n", desconto);
                System.out.println("Por favor, insira um novo desconto válido.\n");
            } else {
                break;
            }
        }

        System.out.println("\nDados para casa:");
        double areaConstruida = InterfaceUsuario.obterAreaConstruida();
        double tamanhoTerreno = InterfaceUsuario.obterTamanhoTerreno();
        financiamentos.add(new Casa(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, desconto, areaConstruida, tamanhoTerreno));

        System.out.println("\nDados para apartamento:");
        int numeroVagas = InterfaceUsuario.obterNumeroVagas();
        int numeroAndar = InterfaceUsuario.obterNumeroAndar();
        financiamentos.add(new Apartamento(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, desconto, numeroVagas, numeroAndar));

        System.out.println("\nDados para terreno:");
        String tipoZona = InterfaceUsuario.obterTipoZona();
        financiamentos.add(new Terreno(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, desconto, tipoZona));

        double totalImoveis = 0;
        double totalFinanciamentos = 0;

        for (Financiamento financiamento : financiamentos) {
            totalImoveis += financiamento.getValorImovel();
            totalFinanciamentos += financiamento.calcularTotalPagamento();

            String tipoImovel = financiamento instanceof Casa ? "Casa" : financiamento instanceof Terreno ? "Terreno": "Apartamento";

            System.out.printf("\nFinanciamento de %s no valor de: R$%.2f\nValor do financiamento com desconto: R$%.2f%n\n", tipoImovel, financiamento.getValorImovel(), financiamento.calcularTotalPagamento());
        }

        System.out.printf("Total de todos os imóveis: R$%.2f%n", totalImoveis);
        System.out.printf("Total de todos os financiamentos: R$%.2f%n", totalFinanciamentos);

        String arquivoTexto = "financiamentos.txt";
        String arquivoSerializado = "financiamentos.ser";
        InterfaceUsuario.salvarFinanciamentosEmTexto(financiamentos, arquivoTexto);
        InterfaceUsuario.lerArqTexto(arquivoTexto);
        InterfaceUsuario.salvarSerializados(financiamentos,arquivoSerializado);
        List<Financiamento> financiamentosLidos = InterfaceUsuario.lerSerializados(arquivoSerializado);
        if (financiamentosLidos != null){
            System.out.println("\nDados do arquivo serializado (financimamentos.ser):");
            for (Financiamento financiamento : financiamentosLidos){
                System.out.println(financiamento);
            }
        }
        InterfaceUsuario.fecharScanner();
    }
}
