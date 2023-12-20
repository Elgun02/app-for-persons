package de.telran.appforpersons.service;

import de.telran.appforpersons.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    Person getById(Integer id);

    void save(Person person);

    void deleteById(Integer id);

    void update(Integer id, Person person);

}
