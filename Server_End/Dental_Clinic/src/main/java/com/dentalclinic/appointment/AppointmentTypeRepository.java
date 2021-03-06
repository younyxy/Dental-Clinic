package com.dentalclinic.appointment;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dentalclinic.queryModel.AppointmentTypeName;

public interface AppointmentTypeRepository extends Repository<AppointmentType, Integer>{
	
	@Transactional(readOnly = true)
    @Cacheable("appointmentTypes")
    Collection<AppointmentType> findAll() throws DataAccessException;

	void save(AppointmentType appointmentType);

	Object findById(int appointmentTypeId);
	
//	@Query("SELECT id, name FROM AppointmentType at")
	@Query("SELECT new com.dentalclinic.queryModel.AppointmentTypeName(at.id, at.name) FROM AppointmentType at")
    @Transactional(readOnly = true)
	@Cacheable("appointmentTypes")
    Collection<AppointmentTypeName> findAppointmentTypeName() throws DataAccessException;	
}
