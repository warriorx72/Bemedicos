package com.bemedicos.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bemedicos.springboot.app.models.entity.Evolucion;

@Repository
public interface EvolucionRepository extends CrudRepository<Evolucion,Long>{

}
