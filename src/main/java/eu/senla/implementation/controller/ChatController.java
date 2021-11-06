package eu.senla.implementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.abstraction.service.ChatServiceInterface;
import eu.senla.dto.ChatDTO;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatServiceInterface chatService;

    private final ObjectMapper mapper = new ObjectMapper();

    public ChatController(ChatServiceInterface chatService) {
        this.chatService = chatService;
    }

    public String create(String json) {
        ChatDTO chat = mapping(json);
        if (chat == null) {
            return "Error!";
        }
        return chatService.create(chat);
    }

    public String update(String json) {
        ChatDTO chat = mapping(json);
        if (chat == null) {
            return "Error!";
        }
        return chatService.update(chat);
    }

    public String remove(Long id) {
        return chatService.remove(id);
    }

    public String find_by_id(Long id) {
        ChatDTO chatDTO = chatService.find_by_id(id);
        String chatJson;
        try {
            chatJson = mapper.writeValueAsString(chatDTO);
        } catch (JsonProcessingException e) {
            return "Error";
        }
        if (chatJson == null) {
            return "Error!";
        }
        return chatJson;
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
}
