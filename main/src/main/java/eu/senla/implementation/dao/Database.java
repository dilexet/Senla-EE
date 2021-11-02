package eu.senla.implementation.dao;

import eu.senla.abstraction.dao.DatabaseInterface;
import eu.senla.di.annotation.Component;
import eu.senla.di.annotation.Value;

@Component
public class Database implements DatabaseInterface {
    @Value("my.param.db")
    private String someValue;

    public String doSome() {
        return someValue;
    }
}
