package com.diegorosa.conquer.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Repository
public interface ContratoRepository extends CrudRepository<Contrato, Integer> {

}
