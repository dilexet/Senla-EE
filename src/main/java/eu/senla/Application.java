package eu.senla;

import eu.senla.implementation.controller.ChatController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        ChatController controller = context.getBean(ChatController.class);

        String chatJson = """
                {
                  "name": "suck dick"
                }
                """;
        System.out.println(controller.create(chatJson));
        Long id = 1L;
        System.out.println(controller.find_by_id(id));

    }
}
