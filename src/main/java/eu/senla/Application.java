package eu.senla;

import eu.senla.implementation.controller.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        ChatController chatController = context.getBean(ChatController.class);
        EventController eventController = context.getBean(EventController.class);
        MessageController messageController = context.getBean(MessageController.class);
        RoleController roleController = context.getBean(RoleController.class);
        UserController userController = context.getBean(UserController.class);

        String chat1 = """
                {
                       "name" : "Some chat name 1",
                         "id" : null,
                         "messages" : null,
                         "event" : null,
                         "users" : null
                     }
                """;

        String chat2 = """
                {
                      "name" : "Some chat name 2",
                         "id" : null,
                         "messages" : null,
                         "event" : null,
                         "users" : null
                 }
                 """;

        String chat3 = """
                {
                       "name" : "Some chat name 3",
                         "id" : 2,
                         "messages" : null,
                         "event" : null,
                         "users" : null
                      }
                """;

        String event1 = """
                {
                    "name" : "Some event name 1",
                        "id" : null,
                        "description" : "Some event description 1",
                        "users" : null,
                        "roles" : null,
                        "chat" : null,
                        "start_date" : "08.11.2021 12:11"
                }
                """;

        String event2 = """
                {
                    "name" : "Some event name 2",
                        "id" : null,
                        "description" : "Some event description 2",
                        "users" : null,
                        "roles" : null,
                        "chat" : null,
                        "start_date" : "08.11.2021 12:12"
                }
                """;

        String event3 = """
                {
                    "name" : "Some event name 3",
                        "id" : 2,
                        "description" : "Some event description 3",
                        "users" : null,
                        "roles" : null,
                        "chat" : null,
                        "start_date" : "08.11.2021 12:13"
                }
                """;

        String message1 = """
                {
                   "text" : "Some message text 1",
                       "id" : null,
                       "user" : null,
                       "chat" : null,
                       "send_date" : "08.13.2021 12:13:54"
                }
                """;

        String message2 = """
                {
                   "text" : "Some message text 2",
                       "id" : null,
                       "user" : null,
                       "chat" : null,
                       "send_date" : "08.13.2021 12:14:54"
                }
                """;

        String message3 = """
                {
                   "text" : "Some message text 3",
                       "id" : 2,
                       "user" : null,
                       "chat" : null,
                       "send_date" : "08.13.2021 12:15:54"
                }
                """;

        String role1 = """
                {
                   "name" : "Some role name 1",
                       "id" : null,
                       "event" : null,
                       "user" : null
                }
                """;

        String role2 = """
                {
                   "name" : "Some role name 2",
                       "id" : null,
                       "event" : null,
                       "user" : null
                }
                """;

        String role3 = """
                {
                   "name" : "Some role name 3",
                       "id" : 2,
                       "event" : null,
                       "user" : null
                }
                """;

        String user1 = """
                {
                   "name" : "Some user name 1",
                       "id" : null,
                       "roles" : null,
                       "chats" : null,
                       "events" : null
                }
                """;

        String user2 = """
                {
                   "name" : "Some user name 2",
                       "id" : null,
                       "roles" : null,
                       "chats" : null,
                       "events" : null
                }
                """;

        String user3 = """
                {
                   "name" : "Some user name 3",
                       "id" : 2,
                       "roles" : null,
                       "chats" : null,
                       "events" : null
                }
                """;


        System.out.println(chatController.create(chat1));
        System.out.println(chatController.create(chat2));
        System.out.println(chatController.remove(1L));
        System.out.println(chatController.find_by_id(2L));
        System.out.println(chatController.update(chat3));
        System.out.println(chatController.find_by_id(2L));


        System.out.println(eventController.create(event1));
        System.out.println(eventController.create(event2));
        System.out.println(eventController.remove(1L));
        System.out.println(eventController.find_by_id(2L));
        System.out.println(eventController.update(event3));
        System.out.println(eventController.find_by_id(2L));

        System.out.println(messageController.create(message1));
        System.out.println(messageController.create(message2));
        System.out.println(messageController.remove(1L));
        System.out.println(messageController.find_by_id(2L));
        System.out.println(messageController.update(message3));
        System.out.println(messageController.find_by_id(2L));

        System.out.println(roleController.create(role1));
        System.out.println(roleController.create(role2));
        System.out.println(roleController.remove(1L));
        System.out.println(roleController.find_by_id(2L));
        System.out.println(roleController.update(role3));
        System.out.println(roleController.find_by_id(2L));

        System.out.println(userController.create(user1));
        System.out.println(userController.create(user2));
        System.out.println(userController.remove(1L));
        System.out.println(userController.find_by_id(2L));
        System.out.println(userController.update(user3));
        System.out.println(userController.find_by_id(2L));

    }
}
