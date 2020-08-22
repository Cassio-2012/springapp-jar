package com.connection.databaseconnection.evento.convidado.arquivoDeLayout;

import com.connection.databaseconnection.evento.convidado.Convidado;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GravaArquivo {
    private String nomeArq = "Lista_De_Convidados.txt";
    private String header = "";
    private String corpo = "";
    private String trailer = "";
    private int contRegDados = 0;

    public static void gravaRegistro(String nomeArq, String registro) {
        Writer arquivo = null;
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
          arquivo =   saida.append(registro + "\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }

    public void montarRegistroHeader(String versao) {

        // Monta o registro header
        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00CONVIDADOS";
        header += formatter.format(dataDeHoje);
        header += versao;

        // Grava o registro header
        gravaRegistro(nomeArq, header);
    }

    public void montarCorpo(Convidado convidado) {

        if (contRegDados == 0) {
            corpo += "02";
        } else {
            corpo = "02";
        }
        corpo += String.format("%-45s", convidado.getNomeConvidado());
        corpo += String.format("%-100s", convidado.getEmail());

        contRegDados++;
        gravaRegistro(nomeArq, corpo);
    }

    public void montarTrailer(String info) {
        // monta o trailer
        trailer += info;
        trailer += String.format("%010d", contRegDados);

        gravaRegistro(nomeArq,trailer);
    }
}
