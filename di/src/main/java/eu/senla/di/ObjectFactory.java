package eu.senla.di;

import eu.senla.di.exceptions.InjectionException;

public class ObjectFactory {
    public <T> T createBean(Class<T> clazz){
        try{
            return clazz.getConstructor().newInstance();
        }catch (ReflectiveOperationException e ){
            throw new InjectionException("Can not create bean",e);
        }
    }
}