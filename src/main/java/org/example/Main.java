package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    private static final String urlJson = "https://viacep.com.br/ws/01001000/json/";
    public static void main(String[] args) {
        try {
            String res = getResponse(urlJson);
            System.out.println(res);
        } catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }
    }
        private static String getResponse (String urlExample) throws IOException {
            URL url = new URL(urlExample);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            int responseCodeHttp = conexao.getResponseCode();
            if (responseCodeHttp == HttpURLConnection.HTTP_OK) {
               try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conexao.getInputStream()))) {
                   StringBuilder builder = new StringBuilder();
                   String linha;

                   while ((linha = bufferedReader.readLine()) != null) {
                       builder.append(linha).append("\n");
                   }
                   return builder.toString();
               }
            } else {
                throw new IOException("Code HTTP: " + responseCodeHttp);
            }
        }
    }


