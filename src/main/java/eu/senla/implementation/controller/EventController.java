package eu.senla.implementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.abstraction.service.EventServiceInterface;
import eu.senla.dto.EventDTO;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {
    private final EventServiceInterface eventService;
    private final ObjectMapper mapper = new ObjectMapper();

    public EventController(EventServiceInterface eventService) {
        this.eventService = eventService;
    }

    public String create(String json) {
        EventDTO event = mapping(json);
        if (event == null) {
            return "Error!";
        }
        return eventService.create(event);
    }

    public String update(String json) {
        EventDTO event = mapping(json);
        if (event == null) {
            return "Error!";
        }
        return eventService.update(event);
    }

    public String remove(Long id) {
        return eventService.remove(id);
    }

    public String find_by_id(Long id) {
        EventDTO eventDTO = eventService.find_by_id(id);
        String eventJson;
        try {
            eventJson = mapper.writeValueAsString(eventDTO);
        } catch (JsonProcessingException e) {
            return "Error";
        }
        if (eventJson == null) {
            return "Error!";
        }
        return eventJson;
    }

    private EventDTO mapping(String json) {
        EventDTO event;
        try {
            event = mapper.readValue(json, EventDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return event;
    }
}
