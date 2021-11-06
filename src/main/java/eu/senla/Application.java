package eu.senla;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import eu.senla.dto.ChatDTO;
import eu.senla.dto.EventDTO;
import eu.senla.dto.RoleDTO;
import eu.senla.dto.UserDTO;
import eu.senla.implementation.controller.ChatController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

@ComponentScan



public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        ChatController controller = context.getBean(ChatController.class);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        ChatDTO chat = new ChatDTO();
        chat.setName("Some chat");

        UserDTO user1 = new UserDTO();
        user1.setName("Maksim");

        UserDTO user2 = new UserDTO();
        user2.setName("Andrey");

        RoleDTO role1 = new RoleDTO();
        role1.setName("Admin");

        RoleDTO role2 = new RoleDTO();
        role2.setName("Member");

        var roles1 = new HashSet<RoleDTO>();
        roles1.add(role1);

        var roles2 = new HashSet<RoleDTO>();
        roles2.add(role2);

        var roles = new HashSet<RoleDTO>();
        roles.add(role1);
        roles.add(role2);


        user1.setRoles(roles1);
        user2.setRoles(roles2);

        var users = new HashSet<UserDTO>();
        users.add(user1);
        users.add(user2);

        EventDTO event = new EventDTO();
        event.setName("Some event");
        event.setDescription("Some description");

        try {
            event.setStart_Date(dateFormat.parse("06.11.2021 19:50"));
        } catch (ParseException e) {
            System.out.println("Error converting date");
        }

        event.setRoles(roles);
        event.setUsers(users);
        event.setChat(chat);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String json = mapper.writeValueAsString(chat);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

//        String chatJson = """
//                {
//                  "name": "suck dick"
//                }
//                """;
//        System.out.println(controller.create(chatJson));
//        Long id = 1L;
//        System.out.println(controller.find_by_id(id));

    }
}
