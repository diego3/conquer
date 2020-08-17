package com.diegorosa.common;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Scanner;

public class HttpRequest {

    public String get(String uri) {
        SimpleClientHttpRequestFactory factory =  new SimpleClientHttpRequestFactory();

        try {
            URI address = URI.create(uri);

            ClientHttpRequest request = factory.createRequest(address, HttpMethod.GET);
            ClientHttpResponse response = request.execute();
            InputStream is = response.getBody();
            Scanner scan = new Scanner(is);
            StringBuffer buffer = new StringBuffer();
            while (scan.hasNext()) {
                buffer.append(scan.next());
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
