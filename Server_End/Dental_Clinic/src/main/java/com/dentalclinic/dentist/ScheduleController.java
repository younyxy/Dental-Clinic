package com.dentalclinic.dentist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/dentist/{dentistId}/schedule")
public class ScheduleController {
	
	private static final String VIEWS_SCHEDULE_CREATE_OR_UPDATE_FORM = "dentist/createOrUpdateScheduleForm";
	private final ScheduleRepository scheduleRP;
	private final DentistRepository dentistRP;
	
	@Autowired
	public ScheduleController(ScheduleRepository scheduleService, DentistRepository dentistService){
		this.scheduleRP = scheduleService;
		this.dentistRP = dentistService;
	}
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
	
	@ModelAttribute("dentist")
	public Dentist findDentist(@PathVariable("dentistId") int dentistId){
		return this.dentistRP.findById(dentistId);
	}
	
	@GetMapping(path="/schedules")
    public String ScheduleList(Map<String, Object> model) {
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.addAll(scheduleRP.findAll());
		model.put("schedules", schedules);
        return "dentist/scheduleList";
    }
	
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Schedule schedule = new Schedule();
        model.put("schedule", schedule);
        return VIEWS_SCHEDULE_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Schedule schedule, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_SCHEDULE_CREATE_OR_UPDATE_FORM;
        } else {
            this.scheduleRP.save(schedule);
            return "redirect:/patient/" + schedule.getId();
        }
    }
    
    @RequestMapping(value="/update", method = RequestMethod.GET)
    public String initUpdateForm(Dentist dentist, ModelMap model){
    	Schedule schedule = new Schedule();
    	dentist.addSchedule(schedule);
    	model.put("schedule", schedule);
    	return VIEWS_SCHEDULE_CREATE_OR_UPDATE_FORM;
    }
    
    @RequestMapping(value="/update", method = RequestMethod.POST) 
    public String processUpdateForm(Dentist dentist, @Valid Schedule schedule, BindingResult result, ModelMap model){
    	if(result.hasErrors()){
    		model.put("schedule", schedule);
    		return VIEWS_SCHEDULE_CREATE_OR_UPDATE_FORM;
    	}else{
    		dentist.addSchedule(schedule);
    		this.scheduleRP.save(schedule);
    		return "redirect:/dentist/{dentistId}";
    	}
    }

}
