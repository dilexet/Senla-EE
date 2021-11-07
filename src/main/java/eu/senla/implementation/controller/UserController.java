package eu.senla.implementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.abstraction.service.UserServiceInterface;
import eu.senla.dto.UserDTO;
import eu.senla.tools.Response;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final String MAPPING_TO_DTO_ERROR_MSG = "Error converting json to dto";
    private final String MAPPING_TO_JSON_ERROR_MSG = "Error converting dto to json";

    private final UserServiceInterface userService;
    private final ObjectMapper mapper = new ObjectMapper();

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    public String create(String json) {
        UserDTO user = mapping(json);
        if (user == null) {
            Response response = new Response(400, MAPPING_TO_DTO_ERROR_MSG);
            return mapToJson(response);
        }
        var result = userService.create(user);
        return checkResult(result);
    }

    public String update(String json) {
        UserDTO user = mapping(json);
        if (user == null) {
            Response response = new Response(400, MAPPING_TO_DTO_ERROR_MSG);
            return mapToJson(response);
        }
        var result = userService.update(user);
        return checkResult(result);
    }

    public String remove(Long id) {
        var result = userService.remove(id);
        return checkResult(result);
    }

    public String find_by_id(Long id) {
        UserDTO userDTO = userService.find_by_id(id);
        Response response = new Response(201, userDTO);
        return mapToJson(response);
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
