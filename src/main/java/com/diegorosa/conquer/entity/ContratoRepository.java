package com.diegorosa.conquer.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends CrudRepository<Contrato, Integer> {

}
