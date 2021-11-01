package eu.senla.implementation.service;

import eu.senla.abstraction.dao.DatabaseInterface;
import eu.senla.abstraction.service.ServiceInterface;
import eu.senla.di.annotation.Autowire;
import eu.senla.di.annotation.Component;

@Component
public class Service implements ServiceInterface {
    @Autowire
    private DatabaseInterface database;

    public String doSome() {
        return database.doSome();
    }
}
