package com.bemedicos.springboot.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bemedicos.springboot.app.models.entity.CodigoConfirmacion;
import com.bemedicos.springboot.app.models.entity.User;

@Repository
public interface CodigoConfirmacionRepository extends CrudRepository<CodigoConfirmacion, Long>
{
	public CodigoConfirmacion findByCodigo(String token);
	
	public CodigoConfirmacion findByusermed(int id);
}
