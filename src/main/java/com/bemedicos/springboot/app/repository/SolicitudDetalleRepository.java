package com.bemedicos.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bemedicos.springboot.app.models.entity.Solicitud_Detalle;

@Repository
public interface SolicitudDetalleRepository extends CrudRepository<Solicitud_Detalle,Long> {

}
