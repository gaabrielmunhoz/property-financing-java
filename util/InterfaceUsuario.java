package util;
import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;

import java.util.Scanner;
import java.io.*;
import java.util.List;


public class InterfaceUsuario {
    private static final Scanner scanner = new Scanner(System.in);

    public static double obterAreaConstruida(){
        while (true) {
            try {
                System.out.print("Digite a área construída (em metros): ");
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Formato inválido, digite apenas números!");
            }
        }
    }

    public static double obterTamanhoTerreno(){
        while (true) {
            try {
                System.out.print("Digite o tamanho do terreno (em metros): ");
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido, digite apenas números!");
            }
        }
    }

    public static int obterNumeroVagas(){
        while (true) {
            try {
                System.out.print("Digite o número de vagas de garagem: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido, digite apenas números!");
            }
        }
    }

    public static int obterNumeroAndar(){
        while(true) {
            try {
                System.out.print("Digite o número do andar do apartamento: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido, digite apenas números!");
            }
        }
    }

    public static String obterTipoZona(){
        while (true) {
            try {
                System.out.print("Digite o tipo de zona (urbana/comercial/rural/industrial): ");
                String tipoDeZona = scanner.nextLine().trim().toLowerCase();

                switch (tipoDeZona) {
                    case "urbana":
                    case "comercial":
                    case "rural":
                    case "industrial":
                        return tipoDeZona;
                    default:
                        throw new IllegalArgumentException("Opção inválida! Digite apenas os termos citados.");
                }

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

// O usuário vai inserir o valor do imóvel, aceitando apenas números positivos.

    public static double obterValorImovel() {
        while (true) {
            try {
                System.out.print("Digite o valor do imóvel: ");
                double valorImovel = Double.parseDouble(scanner.nextLine());
                // verificando se o valor está positivo
                if (valorImovel < 50000) {
                    System.out.println("Valor inválido, tem que ser positivo e maior que 50.000");
                } else {
                    return valorImovel;
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido, digite apenas números.");
            }
        }
    }

// Aqui o usuário irá inserir o prazo do financiamento em anos, entre 1 ano e 35 anos
    public static int obterPrazoFinanciamento() {
        while (true) {
            try {
                System.out.print("Digite o prazo do financiamento (em anos): ");
                int prazoFinanciamento = Integer.parseInt(scanner.nextLine());
// etapa em que verifica dentro do loop se está entre 1 e 35 anos
                if (prazoFinanciamento < 1) {
                    System.out.println("O prazo tem que ser maior ou igual a 1 ano.");
                } else if (prazoFinanciamento > 35) {
                    System.out.println("Apenas aceitamos o prazo de até 35 anos.");
                } else {
                    return prazoFinanciamento;
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido, digite apenas números.");
            }
        }
    }
// aqui o usuário irá inserir a taxa de juros, que apenas aceitará valores positivos de até 20%
    public static double obterTaxaJuros() {
        while (true) {
            try {
                System.out.print("Digite a taxa de juros anual (Apenas números): ");
                double taxaJurosAnual = Double.parseDouble(scanner.nextLine());
                if (taxaJurosAnual < 1) {
                    System.out.println("A taxa tem que ser um valor positivo maior ou igual a 1.");
                } else if (taxaJurosAnual > 20) {
                    System.out.println("Taxa abusiva demais, tente um valor menor ou igual a 20");
                } else {
                    return taxaJurosAnual;
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido, digite apenas números.");
            }
        }
    }

    public static double obterDesconto() {
        while (true) {
            try {
                System.out.print("Digite o desconto mensal (Apenas números): ");
                double desconto = Double.parseDouble(scanner.nextLine());
                return desconto;
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido, digite apenas números.");
            }
        }
    }

    public static void fecharScanner() {
        scanner.close();
    }

    public static void salvarFinanciamentosEmTexto(List<Financiamento> financiamentos, String caminhoArq){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArq))) {
            for (Financiamento financiamento : financiamentos) {
                String linha;
                if (financiamento instanceof Casa) {
                    Casa casa = (Casa) financiamento;
                    linha = String.format("Casa:\nValor: R$%.2f; Financiamento: R$%.2f; Taxa: %.2f%%; Prazo: %d anos; Área Construiída %.2f; Tamanho terreno: %.2f",
                            casa.getValorImovel(), casa.calcularTotalPagamento(), casa.getTaxaJurosAnual(), casa.getPrazoFinanciamento(),
                            casa.getAreaConstruida(), casa.getTamanhoTerreno());
                } else if (financiamento instanceof Apartamento) {
                    Apartamento apartamento = (Apartamento) financiamento;
                    linha = String.format("Apartamento:\nValor: R$%.2f; Financiamento: R$%.2f; Taxa: %.2f%%; Prazo: %d anos; Vagas: %d: Andar: %d",
                            apartamento.getValorImovel(), apartamento.calcularTotalPagamento(), apartamento.getTaxaJurosAnual(),
                            apartamento.getPrazoFinanciamento(), apartamento.getNumeroVagas(), apartamento.getNumeroAndar());
                } else if (financiamento instanceof Terreno) {
                    Terreno terreno = (Terreno) financiamento;
                    linha = String.format("Terreno:\nValor: R$%.2f; Financiamento: R$%.2f; Taxa: %.2f%%; Prazo: %d anos; Tipo Zona: %s",
                            terreno.getValorImovel(), terreno.calcularTotalPagamento(), terreno.getTaxaJurosAnual(),
                            terreno.getPrazoFinanciamento(), terreno.getTipoZona());
                } else {
                    linha = "Tipo de financiamento desconhecido...";
                }
                writer.write(linha);
                writer.newLine();
            }
            System.out.println("\nDados salvos no arquivo financiamentos.txt com sucesso!");
        } catch (IOException e){
            System.out.println("\nErro ao salvar dados no arquivo financiamentos.txt." +e.getMessage());
        }
    }

    public static void lerArqTexto(String caminhoArquivo){
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))){
            String linha;
            System.out.println("\nDados do arquivo financiamentos.txt:");
            while((linha = reader.readLine()) != null){
                System.out.println(linha);
            }
        } catch (IOException e){
            System.out.println("Erro ao ler dados do arquivo financiamentos.txt: "+e.getMessage());
        }
    }

    public static void salvarSerializados(List<Financiamento> financiamentos, String caminhoArquivo){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))){
            oos.writeObject(financiamentos);
            System.out.println("\nDados serializados salvos com sucesso!");
        } catch (IOException e){
            System.out.println("\nErro ao salvar dados serializados: " + e.getMessage());
        }
    }

    public static List<Financiamento> lerSerializados(String caminhoArquivo){
        try (ObjectInputStream objeto = new ObjectInputStream(new FileInputStream(caminhoArquivo))){
            return (List<Financiamento>) objeto.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Erro ao ler dados serialziados: " + e.getMessage());
            return null;
        }
    }

}
