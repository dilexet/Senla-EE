package eu.senla.implementation.dao;

import eu.senla.abstraction.dao.DatabaseInterface;
import eu.senla.di.annotation.Value;

public class Database implements DatabaseInterface {
    @Value("")
    private String someValue;

    public String doSome() {
        return someValue;
    }
}
