package eu.senla.implementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.abstraction.service.RoleServiceInterface;
import eu.senla.dto.RoleDTO;
import eu.senla.tools.Response;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController {
    private final String MAPPING_TO_DTO_ERROR_MSG = "Error converting json to dto";
    private final String MAPPING_TO_JSON_ERROR_MSG = "Error converting dto to json";

    private final RoleServiceInterface roleService;
    private final ObjectMapper mapper = new ObjectMapper();

    public RoleController(RoleServiceInterface roleService) {
        this.roleService = roleService;
    }

    public String create(String json) {
        RoleDTO role = mapping(json);
        if (role == null) {
            Response response = new Response(400, MAPPING_TO_DTO_ERROR_MSG);
            return mapToJson(response);
        }
        var result = roleService.create(role);
        return checkResult(result);
    }

    public String update(String json) {
        RoleDTO role = mapping(json);
        if (role == null) {
            Response response = new Response(400, MAPPING_TO_DTO_ERROR_MSG);
            return mapToJson(response);
        }
        var result = roleService.update(role);
        return checkResult(result);
    }

    public String remove(Long id) {
        var result = roleService.remove(id);
        return checkResult(result);
    }

    public String find_by_id(Long id) {
        RoleDTO roleDTO = roleService.find_by_id(id);
        Response response = new Response(201, roleDTO);
        return mapToJson(response);
    }

    private RoleDTO mapping(String json) {
        RoleDTO role;
        try {
            role = mapper.readValue(json, RoleDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return role;
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
