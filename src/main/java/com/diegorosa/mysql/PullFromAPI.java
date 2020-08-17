package com.diegorosa.mysql;

import com.diegorosa.common.Http;

public class PullFromAPI {
    private static final String URL_GOV = "http://compras.dados.gov.br/contratos/v1/contratos.json?uasg=20001&order_by=data_assinatura&order=desc";

    public static void main(String... args) {
        seed();
    }

    private static void seed() {
        System.out.println("inicio do seed...");

        String response = new Http().request(URL_GOV);


    }

}
