package eu.senla.di;

import eu.senla.di.annotation.Autowire;
import eu.senla.di.annotation.Component;
import eu.senla.di.annotation.Value;
import eu.senla.di.exceptions.InjectionException;
import eu.senla.di.utils.Properties;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationContext {

    private final Map<Class<?>, Object> context;
    private final Map<Class<?>, Class<?>> classInterfaceMap;
    private ObjectFactory objectFactory;

    public ApplicationContext() {
        this.context = new HashMap<>();
        this.classInterfaceMap = new HashMap<>();
    }

    public void setFactory(ObjectFactory factory) {
        this.objectFactory = factory;
    }

    public void createContext(Set<Class<?>> classes) throws IllegalAccessException {
        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(Component.class)) {
                continue;
            }

            Class<?>[] interfaces = clazz.getInterfaces();

            if (interfaces.length == 0) {
                classInterfaceMap.put(clazz, clazz);
                continue;
            }

            for (Class<?> interfaceKey : interfaces) {
                if (!classInterfaceMap.containsValue(interfaceKey)) {
                    classInterfaceMap.put(clazz, interfaceKey);
                }
            }
        }
        fillContext();
    }

    private void fillContext() throws IllegalAccessException {
        for (Class<?> clazz : classInterfaceMap.keySet()) {
            Object bean = objectFactory.createBean(clazz);
            context.put(clazz, bean);
            injectDependencies(clazz, bean);
        }
    }

    private void injectDependencies(Class<?> clazz, Object bean) throws IllegalAccessException {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Value.class)) {
                Value res = field.getAnnotation(Value.class);
                String value = res.value();
                Properties properties = Properties.getInstance();
                try {
                    String someValue = properties.getProperty(value);
                    field.setAccessible(true);
                    field.set(bean, someValue);
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                continue;
            }
            if (!field.isAnnotationPresent(Autowire.class)) {
                continue;
            }
            Object instance = this.getBean(field.getType());
            field.setAccessible(true);
            field.set(bean, instance);
            injectDependencies(instance.getClass(), instance);
        }
    }

    public <T> T getBean(Class<?> type) {
        Set<Map.Entry<Class<?>, Class<?>>> classSet = classInterfaceMap.entrySet().stream()
                .filter(entry -> type.equals(entry.getValue()))
                .collect(Collectors.toSet());

        if (classSet.size() != 1) {
            throw new InjectionException("Class not found");
        }

        Class<?> clazz = classSet.stream()
                .findFirst()
                .get()
                .getKey();

        if (context.containsKey(clazz)) {
            return (T) context.get(clazz);
        }

        Object bean = objectFactory.createBean(clazz);
        context.put(clazz, bean);
        return (T) bean;
    }
}
