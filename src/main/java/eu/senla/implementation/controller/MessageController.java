package eu.senla.implementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.abstraction.service.MessageServiceInterface;
import eu.senla.dto.MessageDTO;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    private final MessageServiceInterface messageService;
    private final ObjectMapper mapper = new ObjectMapper();

    public MessageController(MessageServiceInterface messageService) {
        this.messageService = messageService;
    }

    public String create(String json) {
        MessageDTO message = mapping(json);
        if (message == null) {
            return "Error!";
        }
        return messageService.create(message);
    }

    public String update(String json) {
        MessageDTO message = mapping(json);
        if (message == null) {
            return "Error!";
        }
        return messageService.update(message);
    }

    public String remove(Long id) {
        return messageService.remove(id);
    }

    public String find_by_id(Long id) {
        MessageDTO messageDTO = messageService.find_by_id(id);
        String messageJson;
        try {
            messageJson = mapper.writeValueAsString(messageDTO);
        } catch (JsonProcessingException e) {
            return "Error";
        }
        if (messageJson == null) {
            return "Error!";
        }
        return messageJson;
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
}
