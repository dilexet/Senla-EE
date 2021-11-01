package eu.senla;

import eu.senla.abstraction.controller.ControllerInterface;
import eu.senla.abstraction.dao.DatabaseInterface;
import eu.senla.abstraction.service.ServiceInterface;
import eu.senla.di.ApplicationContext;
import eu.senla.di.DependencyInjector;
import eu.senla.di.ObjectFactory;
import eu.senla.di.annotation.Component;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        Context context = ApplicationInit.init(Main.class);
//        Controller controller = context.getObject(ControllerInterface.class);
//        System.out.println(controller.doSome());

        ObjectFactory objectFactory = new ObjectFactory();
        ApplicationContext context = new ApplicationContext();
        context.setFactory(objectFactory);
        try {
            DependencyInjector.run(Main.class, context);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        try {
            DatabaseInterface database = context.getBean(DatabaseInterface.class);
            ServiceInterface service = context.getBean(ServiceInterface.class);
            ControllerInterface controller = context.getBean(ControllerInterface.class);
            System.out.println("Database: " + database.doSome());
            System.out.println("Service: " + service.doSome());
            System.out.println("Controller: " + controller.doSome());
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void reflectionScan() {
        Reflections reflection = new Reflections(Main.class);
        Set<Class<?>> classes = reflection.getTypesAnnotatedWith(Component.class);


        for (Class<?> findClass : classes) {
            System.out.println(Arrays.toString(findClass.getInterfaces()));
            System.out.println(findClass.getName());
        }
    }
}
