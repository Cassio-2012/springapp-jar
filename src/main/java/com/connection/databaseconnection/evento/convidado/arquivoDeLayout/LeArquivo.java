package com.connection.databaseconnection.evento.convidado.arquivoDeLayout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeArquivo {

    public static void leArquivo(String nomeArq) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        String nome,email;

        int qtdFalta, contRegistro=0;

        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        // Lê os registros do arquivo
        try {
            // Lê um registro
            registro = entrada.readLine();

            while (registro != null) {
                // Obtém o tipo do registro
                tipoRegistro = registro.substring(0, 2); // obtém os 2 primeiros caracteres do registro

                if (tipoRegistro.equals("00")) {
                    System.out.println("Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2,12));
                    System.out.println("Data/hora de geração do arquivo: " + registro.substring(12,21));
                    System.out.println("Versão do layout: " + registro.substring(21,23));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("\nTrailer");
                    int qtdRegistro = Integer.parseInt(registro.substring(2,12));
                    if (qtdRegistro == contRegistro) {
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                    }
                    else {
                        System.out.println("Quantidade de registros gravados não confere com quantidade lida");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%-45s %-100s\n", "NOME","EMAIL");

                    }

                    nome = registro.substring(2,47);
                    email = registro.substring(47,146);


                    System.out.printf("%-45s %-100s \n", nome,email);
                    contRegistro++;
                }
                else {
                    System.out.println("Tipo de registro inválido");
                }

                // lê o próximo registro
                registro = entrada.readLine();
            }

            // Fecha o arquivo
            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }

    }

    public static void main(String[] args) {
        leArquivo("Lista_De_Convidados.txt");
    }


}
