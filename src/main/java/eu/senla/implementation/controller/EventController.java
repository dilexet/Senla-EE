package eu.senla.implementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.abstraction.service.EventServiceInterface;
import eu.senla.constants.MappingError;
import eu.senla.dto.EventDTO;
import eu.senla.tools.Response;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {
    private final EventServiceInterface eventService;
    private final ObjectMapper mapper;

    public EventController(EventServiceInterface eventService, ObjectMapper mapper) {
        this.eventService = eventService;
        this.mapper = mapper;
    }

    public String create(String json) {
        EventDTO event = mapping(json);
        if (event == null) {
            Response response = new Response(400, MappingError.MAPPING_TO_DTO_ERROR_MSG);
            return mapToJson(response);
        }
        var result = eventService.create(event);
        return checkResult(result);
    }

    public String update(String json) {
        EventDTO event = mapping(json);
        if (event == null) {
            Response response = new Response(400, MappingError.MAPPING_TO_DTO_ERROR_MSG);
            return mapToJson(response);
        }
        var result = eventService.update(event);
        return checkResult(result);
    }

    public String remove(Long id) {
        var result = eventService.remove(id);
        return checkResult(result);
    }

    public String findById(Long id) {
        EventDTO eventDTO = eventService.findById(id);
        if (eventDTO == null) {
            Response response = new Response(400, MappingError.OBJECT_NOT_FOUND_ERROR_MSG);
            return mapToJson(response);
        }
        Response response = new Response(200, eventDTO);
        return mapToJson(response);
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

    private String mapToJson(Response response) {
        String json;
        try {
            json = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return MappingError.MAPPING_TO_JSON_ERROR_MSG;
        }
        return json;
    }

    private String checkResult(Result result) {
        if (result.getStatus() == StatusType.Error) {
            Response response = new Response(400, result.getMessage());
            return mapToJson(response);
        }
        Response response = new Response(201, result.getMessage());
        return mapToJson(response);
    }
}
