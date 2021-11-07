package eu.senla.implementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.abstraction.service.MessageServiceInterface;
import eu.senla.dto.MessageDTO;
import eu.senla.tools.Response;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    private final String MAPPING_TO_DTO_ERROR_MSG = "Error converting json to dto";
    private final String MAPPING_TO_JSON_ERROR_MSG = "Error converting dto to json";

    private final MessageServiceInterface messageService;
    private final ObjectMapper mapper = new ObjectMapper();

    public MessageController(MessageServiceInterface messageService) {
        this.messageService = messageService;
    }

    public String create(String json) {
        MessageDTO message = mapping(json);
        if (message == null) {
            Response response = new Response(400, MAPPING_TO_DTO_ERROR_MSG);
            return mapToJson(response);
        }
        var result = messageService.create(message);
        return checkResult(result);
    }

    public String update(String json) {
        MessageDTO message = mapping(json);
        if (message == null) {
            Response response = new Response(400, MAPPING_TO_DTO_ERROR_MSG);
            return mapToJson(response);
        }
        var result = messageService.update(message);
        return checkResult(result);
    }

    public String remove(Long id) {
        var result = messageService.remove(id);
        return checkResult(result);
    }

    public String find_by_id(Long id) {
        MessageDTO messageDTO = messageService.find_by_id(id);
        Response response = new Response(201, messageDTO);
        return mapToJson(response);
    }

    private MessageDTO mapping(String json) {
        MessageDTO message;
        try {
            message = mapper.readValue(json, MessageDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return message;
    }

    private String mapToJson(Response response) {
        String json;
        try {
            json = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return MAPPING_TO_JSON_ERROR_MSG;
        }
        return json;
    }

    private String checkResult(Result result) {
        if (result.status() == StatusType.Error) {
            Response response = new Response(400, result.message());
            return mapToJson(response);
        }
        Response response = new Response(201, result.message());
        return mapToJson(response);
    }
}
