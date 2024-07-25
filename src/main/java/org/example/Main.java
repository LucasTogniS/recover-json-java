package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
        public static void main(String[] args) {
            String urlJson = "https://viacep.com.br/ws/01001000/json/";

            try {
                URL url = new URL(urlJson);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                conexao.setRequestMethod("GET");
                int responseCodeHttp = conexao.getResponseCode();

                if (responseCodeHttp == HttpURLConnection.HTTP_OK) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                    StringBuilder builder = new StringBuilder();

                    String linha;

                    while ((linha = bufferedReader.readLine()) != null) {
                        builder.append(linha).append("\n");
                    }

                    bufferedReader.close();

                    System.out.println(builder.toString());
                } else {
                    System.out.println("Erro: " + responseCodeHttp);
                }
            } catch (Exception e) {
                System.err.println("Error " + e.getMessage());
        }
    }
}