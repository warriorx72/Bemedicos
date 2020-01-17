package com.bemedicos.springboot.app.models.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.MedicosApp;

@Repository
public class MedicosAppDaoImpl implements IMedicosAppDao {

	@PersistenceContext
	private EntityManager em;
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	
	@Override
	public List<MedicosApp> findAll() {
		return em.createQuery("From MedicosApp").getResultList();
	}//end_list

	
	@Override
	@Transactional(readOnly =true)
	public MedicosApp findOne(Long MedicosId) {
		return em.find(MedicosApp.class, MedicosId);
	}//end_findOne
	

	@Override
	@Transactional
	public void save(MedicosApp medicosApp) {
		if(medicosApp.getMedicos_id() != null && medicosApp.getMedicos_id() >0) {
			em.merge(medicosApp);
		}else{
			em.persist(medicosApp);	
		}//end_else
		
	}//end_save

}//END_Class