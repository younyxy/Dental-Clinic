package com.dentalclinic.dentist;

//import java.sql.Time;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.dentalclinic.model.BaseEntity;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{
	
	@Column(name="Day")
	@NotNull
	private String Day;
	
	@NotNull
	@Column(name = "Time_begin")
 //   @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm")
    private Date Time_begin;
	
	@NotNull
	@Column(name = "Time_end")
	@DateTimeFormat(pattern = "HH:mm")
	private Date Time_end;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Dentist_Schedule", joinColumns = @JoinColumn(name = "dentist_id"), inverseJoinColumns = @JoinColumn(name = "schedule_id"))
	private Set<Dentist> dentists;
	
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	
	public Date getTime_begin(){
		return this.Time_begin;
	}
	public void setTime_begin(Date Time_begin){
		this.Time_begin = Time_begin;
	}
	public Date getTime_end(){
		return this.Time_end;
	}
	public void setTime_end(Date Time_end){
		this.Time_end=Time_end;
	}

}