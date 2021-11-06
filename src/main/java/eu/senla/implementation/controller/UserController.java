package eu.senla.implementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.abstraction.service.UserServiceInterface;
import eu.senla.dto.UserDTO;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserServiceInterface userService;
    private final ObjectMapper mapper = new ObjectMapper();

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    public String create(String json) {
        UserDTO user = mapping(json);
        if (user == null) {
            return "Error!";
        }
        return userService.create(user);
    }

    public String update(String json) {
        UserDTO user = mapping(json);
        if (user == null) {
            return "Error!";
        }
        return userService.update(user);
    }

    public String remove(Long id) {
        return userService.remove(id);
    }

    public String find_by_id(Long id) {
        UserDTO userDTO = userService.find_by_id(id);
        String userJson;
        try {
            userJson = mapper.writeValueAsString(userDTO);
        } catch (JsonProcessingException e) {
            return "Error";
        }
        if (userJson == null) {
            return "Error!";
        }
        return userJson;
    }

    private UserDTO mapping(String json) {
        UserDTO user;
        try {
            user = mapper.readValue(json, UserDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return user;
    }
}
