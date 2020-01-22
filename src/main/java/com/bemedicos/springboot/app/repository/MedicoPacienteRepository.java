package com.bemedicos.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bemedicos.springboot.app.models.entity.MedicoPaciente;

@Repository
public interface MedicoPacienteRepository extends CrudRepository<MedicoPaciente,Long> {

}
