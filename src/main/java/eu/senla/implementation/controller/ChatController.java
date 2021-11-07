package eu.senla.implementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.abstraction.service.ChatServiceInterface;
import eu.senla.dto.ChatDTO;
import eu.senla.tools.Response;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final String MAPPING_TO_DTO_ERROR_MSG = "Error converting json to dto";
    private final String MAPPING_TO_JSON_ERROR_MSG = "Error converting dto to json";

    private final ChatServiceInterface chatService;
    private final ObjectMapper mapper = new ObjectMapper();

    public ChatController(ChatServiceInterface chatService) {
        this.chatService = chatService;
    }

    public String create(String json) {
        ChatDTO chat = mapping(json);
        if (chat == null) {
            Response response = new Response(400, MAPPING_TO_DTO_ERROR_MSG);
            return mapToJson(response);
        }
        var result = chatService.create(chat);

        return checkResult(result);
    }

    public String update(String json) {
        ChatDTO chat = mapping(json);
        if (chat == null) {
            Response response = new Response(400, MAPPING_TO_DTO_ERROR_MSG);
            return mapToJson(response);
        }
        var result = chatService.update(chat);

        return checkResult(result);
    }

    public String remove(Long id) {
        var result = chatService.remove(id);

        return checkResult(result);
    }

    public String find_by_id(Long id) {
        ChatDTO chatDTO = chatService.find_by_id(id);
        Response response = new Response(201, chatDTO);
        return mapToJson(response);
    }

    private ChatDTO mapping(String json) {
        ChatDTO chat;
        try {
            chat = mapper.readValue(json, ChatDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return chat;
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
