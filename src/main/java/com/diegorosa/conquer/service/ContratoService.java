package com.diegorosa.conquer.service;

import com.diegorosa.common.FileUtil;
import org.springframework.core.io.FileSystemResource;
import com.diegorosa.common.HttpRequest;
import com.diegorosa.conquer.entity.Contrato;
import com.diegorosa.conquer.entity.ContratoRepository;
import com.diegorosa.conquer.model.dto.DataAssinaturaResultDTO;
import com.diegorosa.mysql.ContratoJsonObject;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContratoService {
    private static final String URL_GOV = "http://compras.dados.gov.br/contratos/v1/contratos.json?uasg=20001&order_by=data_assinatura&order=desc";

    @Autowired
    private ContratoRepository contratoRepository;

    @PersistenceContext
    private EntityManager em;

    public List<Contrato> findAll() {
        return (List<Contrato>)contratoRepository.findAll();
    }

    public List<Contrato> findAllOrderedByDataAssinaturaDesc(Integer limit) {
        String sql = "SELECT c from Contrato c ORDER BY c.dataAssinatura DESC";
        TypedQuery<Contrato> query = em.createQuery(sql, Contrato.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<DataAssinaturaResultDTO> findGroupedByDataAssinatura(Integer limit) {
        String sql = "SELECT " +
                "NEW com.diegorosa.conquer.model.dto.DataAssinaturaResultDTO(c.dataAssinatura, SUM(c.valorInicial)) " +
                "FROM Contrato c GROUP BY c.dataAssinatura " +
                "ORDER BY c.dataAssinatura DESC";
        TypedQuery<DataAssinaturaResultDTO> query = em.createQuery(sql, DataAssinaturaResultDTO.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public void importaContratos() {
        System.out.println("inicio");
        String response = new HttpRequest().get(URL_GOV);
        //String response = FileUtil.convert(new FileSystemResource("src/main/resources/static/contratos-example.json"));

        ArrayList<ContratoJsonObject> contratos = parseContratos(response);
        ArrayList<Contrato> entities = new ArrayList<>();
        for (ContratoJsonObject json : contratos) {
            Contrato entity = new Contrato();
            entity.setIdentificador(Long.valueOf(json.getIdentificador()));
            entity.setCnpjContratada(Long.valueOf(json.getCnpjContratada()));
            entity.setCodigoContrato(json.getCodigoContrato());
            entity.setCpfContratada(json.getCpfContratada());
            entity.setFundamentoLegal(json.getFundamentoLegal());
            entity.setUasg(json.getUasg());
            entity.setOrigemLicitacao(json.getOrigemLicitacao());
            entity.setNumero(json.getNumero());
            entity.setNumeroAditivo(json.getNumeroAditivo());
            entity.setNumeroProcesso(json.getNumeroProcesso());
            entity.setLicitacaoAssociada(json.getLicitacaoAssociada());
            entity.setValorInicial(json.getValorInicial());
            entity.setNumeroAvisoLicitacao(json.getNumeroAvisoLicitacao());
            entity.setModalidadeLicitacao(json.getModalidadeLicitacao());

            entity.setDataAssinatura(parse(json.getDataAssinatura()));
            entity.setDataInicioVigencia(parse(json.getDataInicioVigencia()));
            entity.setDataTerminoVigencia(parse(json.getDataTerminoVigencia()));

            entities.add(entity);
        }

        contratoRepository.saveAll(entities);
    }

    private LocalDate parse(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.err.println("date parse error: "+e.getMessage());
        }
        return null;
    }

    private ArrayList<ContratoJsonObject> parseContratos(String json) {
        Gson gson = new Gson();
        ArrayList<ContratoJsonObject> contratos = new ArrayList<>();

        JsonElement rootElement = null;
        try {
            rootElement = JsonParser.parseString(json);
        } catch (JsonSyntaxException e) {
            System.out.println(e.getMessage());
            System.out.println(json);
            return contratos;
        }

        if (rootElement == null || !rootElement.isJsonObject()) {
            System.err.println("Root Element não é um json válido");
            return contratos;
        }

        JsonObject ob = rootElement.getAsJsonObject();
        JsonElement embeddedEl = ob.get("_embedded");
        if (!embeddedEl.isJsonObject()) {
            System.err.println("Json inválido: embedded não é um objeto");
            return contratos;
        }

        JsonObject embeddedObject = embeddedEl.getAsJsonObject();
        JsonElement contratosEl = embeddedObject.get("contratos");
        if (!contratosEl.isJsonArray()) {
            System.err.println("Json inválido: Contratos não existe ou não é array");
            return contratos;
        }

        JsonArray contratosArray = contratosEl.getAsJsonArray();
        if (contratosArray.size() == 0){
            System.err.println("Json inválido: O array de contratos está vazio");
            return contratos;
        }

        for (JsonElement element: contratosArray) {
            if (!element.isJsonObject()) {
                continue;
            }
            try {
                contratos.add(gson.fromJson(element, ContratoJsonObject.class));
            } catch (JsonSyntaxException e) {
                System.out.println(String.format("json syntax error: %s", e.getMessage()));
            }
        }

        return contratos;
    }


}
