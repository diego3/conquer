package com.diegorosa.conquer.model;

import com.diegorosa.common.MaskUtil;
import com.diegorosa.conquer.entity.Contrato;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReport implements CsvReport {
    private static final Logger logger = LoggerFactory.getLogger(AbstractReport.class);
    private static final char CSV_CHARACTER_SEPARATOR = ';';

    protected List<Contrato> contratos;
    protected Writer writer;
    protected MaskUtil formatter;

    public AbstractReport() {
        this.contratos = new ArrayList<>();
        this.formatter = new MaskUtil();
    }

    public AbstractReport(List<Contrato> contratos, Writer writer) {
        this.contratos = contratos;
        this.writer = writer;
        this.formatter = new MaskUtil();
    }

    public abstract void generate();

    protected <T> void generateCsv(ArrayList<T> result) {
        try {
            StatefulBeanToCsv<T> builder = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(CSV_CHARACTER_SEPARATOR)
                    .build();
            builder.write(result);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            logger.error(e.getMessage());
        }
    }
}
