package com.dentalclinic.dentist;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dentalclinic.queryModel.DaysAvailableWithDentist;

public interface ScheduleRepository extends Repository<Schedule, Integer> {
	
	void save(Schedule schedule);
	
	@Transactional(readOnly = true)
    @Cacheable("scheduleList")
    Collection<Schedule> findAll() throws DataAccessException;
	
	/**
     * Retrieve a {@link Schedule} from the data store by id.
     * @param id the id to search for
     * @return the {@link Schedule} if found
     */
    @Transactional(readOnly = true)
    Schedule findById(Integer id);
    
    public final static String Query_time_available_by_dentistId = 
    		"SELECT s.Day, s.Time_begin, s.Time_end FROM Schedule s join s.dentists dt "
    		+ "where dt.id = :id";
    
    public final static String query2 = "Select new "
    		+ "com.dentalclinic.queryModel.DaysAvailableWithDentist(s.id, s.Day, s.Time_begin, s.Time_end) "
    		+ "FROM Dentist d join d.schedules s where d.id = :id";    
   // @Query("SELECT distinct s.id, s.Day, s.Time_begin, s.Time_end , sd.id FROM Schedule s join s.dentists sd where sd.id = :id")
    @Query(query2)
    @Transactional(readOnly = true)
    Collection<DaysAvailableWithDentist> findTimeAvailableByDentistId(@Param("id") int dentist_Id) throws DataAccessException;
}
