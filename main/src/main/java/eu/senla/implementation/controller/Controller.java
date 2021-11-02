package eu.senla.implementation.controller;

import eu.senla.abstraction.controller.ControllerInterface;
import eu.senla.abstraction.service.ServiceInterface;
import eu.senla.di.annotation.Autowire;
import eu.senla.di.annotation.Component;

@Component
public class Controller implements ControllerInterface {
    @Autowire
    private ServiceInterface service;

    public String doSome() {
        return service.doSome();
    }
}
