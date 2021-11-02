package eu.senla.di;

import eu.senla.di.annotation.Component;
import org.reflections.Reflections;

import java.util.Set;

public class ClassScanner {

    public Set<Class<?>> reflectionsScan(Class<?> mainClass) {
        Reflections reflections = new Reflections(mainClass);
        return reflections.getTypesAnnotatedWith(Component.class);
    }
}
