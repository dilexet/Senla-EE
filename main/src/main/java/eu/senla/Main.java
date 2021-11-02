package eu.senla;

import eu.senla.abstraction.controller.ControllerInterface;
import eu.senla.di.ApplicationContext;
import eu.senla.di.DependencyInjector;
import eu.senla.di.ObjectFactory;

public class Main {
    public static void main(String[] args) {
        ObjectFactory objectFactory = new ObjectFactory();
        ApplicationContext context = new ApplicationContext();
        context.setFactory(objectFactory);
        DependencyInjector.run(Main.class, context);
        ControllerInterface controller = context.getBean(ControllerInterface.class);
        System.out.println(controller.doSome());
    }
}
