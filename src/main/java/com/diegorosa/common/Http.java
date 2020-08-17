package com.diegorosa.common;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Scanner;

public class Http {

    public String request(String uri) {
        SimpleClientHttpRequestFactory factory =  new SimpleClientHttpRequestFactory();

        try {
            URI address = URI.create(uri);

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
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
