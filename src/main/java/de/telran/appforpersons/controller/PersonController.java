package de.telran.appforpersons.controller;

import de.telran.appforpersons.service.PersonService;
import de.telran.appforpersons.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/person/get-all")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping(value = "/person/{id}")
    public Person getCarById(@PathVariable(value = "id") Integer id) {
        Person person = personService.getById(id);
        return person;
    }

    @PostMapping(value = "/person")
    public void saveCar(@RequestBody Person person) {
        personService.save(person);
    }

    @DeleteMapping(value = "/person/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id) {
        personService.deleteById(id);
    }

    @PutMapping(value = "/person/{id}")
    public void updateCarById(@PathVariable(value = "id") Integer id, @RequestBody Person person) {
        personService.update(id, person);
    }
}
