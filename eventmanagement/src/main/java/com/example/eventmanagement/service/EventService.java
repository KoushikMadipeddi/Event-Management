package com.example.eventmanagement.service;

import com.example.eventmanagement.exception.EventNotFoundException;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event createEvent(Event event) {
        return repository.save(event);
    }

    public Event getEventById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = getEventById(id);
        event.setName(updatedEvent.getName());
        event.setLocation(updatedEvent.getLocation());
        event.setDate(updatedEvent.getDate());
        return repository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        repository.delete(event);
    }
}
