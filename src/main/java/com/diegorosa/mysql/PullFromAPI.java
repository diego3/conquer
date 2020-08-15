package com.diegorosa.mysql;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Scanner;

public class PullFromAPI {
    private static final String URL_GOV = "http://compras.dados.gov.br/contratos/v1/contratos.json?uasg=20001&order_by=data_assinatura&order=desc";

    public static void main(String... args) {
        seed();
    }

    private static void seed() {
        System.out.println("inicio do seed...");
        SimpleClientHttpRequestFactory factory =  new SimpleClientHttpRequestFactory();

        try {
            URI address = URI.create(URL_GOV);

            ClientHttpRequest request = factory.createRequest(address, HttpMethod.GET);
            System.out.println("iniciando requisição...");
            ClientHttpResponse response = request.execute();
            System.out.println("retornou");
            InputStream is = response.getBody();
            Scanner s = new Scanner(is);
            StringBuffer buffer = new StringBuffer();
            while(s.hasNext()) {
                buffer.append(s.next());
            }
            System.out.println(buffer);
            //FileOutputStream fout = new FileOutputStream("/resources/resultado.json");
            //fout.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
