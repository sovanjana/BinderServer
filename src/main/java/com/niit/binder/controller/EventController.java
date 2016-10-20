/*package com.niit.binder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.binder.dao.EventDAO;
import com.niit.binder.model.Event;

@RestController
public class EventController {
	
	@Autowired
	EventDAO eventDAO;
	
	//	http://localhost:8081/BinderServer/events
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> listEvents() {
		List<Event> event = eventDAO.list();
		if(event.isEmpty()) {
			return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Event>>(HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/event/
	@RequestMapping(value = "/event/", method = RequestMethod.POST)
	public ResponseEntity<Event> createEvent(@RequestBody Event event) {
		if(eventDAO.get(event.getId()) == null) {
			eventDAO.save(event);
			return new ResponseEntity<Event>(HttpStatus.OK);
		}
		event.setErrorMessage("Event already exist with id : " +event.getId());
		return new ResponseEntity<Event>(HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/event/{id}
	@RequestMapping(value = "/event/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Event> updateEvent(@PathVariable("id") String id, @RequestBody Event event) {
		if(eventDAO.get(id) == null) {
			event = new Event();
			event.setErrorMessage("No event exist with id : " +event.getId());
			return new ResponseEntity<Event>(event, HttpStatus.NOT_FOUND);
		}
		eventDAO.update(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	//	http://localhost:8081/BinderServer/event/{id}
	@RequestMapping(value = "/event/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Event> deleteEvent(@PathVariable("id") String id) {
		Event event = eventDAO.get(id);
		if(event == null) {
			event = new Event();
			event.setErrorMessage("No event exist with id : " + id);
			return new ResponseEntity<Event>(event, HttpStatus.NOT_FOUND);
		}
		eventDAO.delete(event);
		return new ResponseEntity<Event>(HttpStatus.OK);		
	}
	
	//	http://localhost:8081/BinderServer/event/{id}
	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable("id") String id) {
		Event event = eventDAO.get(id);
		if(event == null) {
			event = new Event();
			event.setErrorMessage("No event exist with id : " + id);
			return new ResponseEntity<Event>(event, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
}
*/