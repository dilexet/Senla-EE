package eu.senla.implementation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.abstraction.service.RoleServiceInterface;
import eu.senla.dto.RoleDTO;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController {
    private final RoleServiceInterface roleService;
    private final ObjectMapper mapper = new ObjectMapper();

    public RoleController(RoleServiceInterface roleService) {
        this.roleService = roleService;
    }

    public String create(String json) {
        RoleDTO role = mapping(json);
        if (role == null) {
            return "Error!";
        }
        return roleService.create(role);
    }

    public String update(String json) {
        RoleDTO role = mapping(json);
        if (role == null) {
            return "Error!";
        }
        return roleService.update(role);
    }

    public String remove(Long id) {
        return roleService.remove(id);
    }

    public String find_by_id(Long id) {
        RoleDTO roleDTO = roleService.find_by_id(id);
        String roleJson;
        try {
            roleJson = mapper.writeValueAsString(roleDTO);
        } catch (JsonProcessingException e) {
            return "Error";
        }
        if (roleJson == null) {
            return "Error!";
        }
        return roleJson;
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
}
