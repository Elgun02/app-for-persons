package de.telran.appforpersons.service;

import de.telran.appforpersons.entity.Person;
import de.telran.appforpersons.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(Integer id) {
        Optional<Person> optional = personRepository.findById(id);

        if (optional.isPresent()) {
            Person person = optional.get();
            String encrypted_password = person.getPassword();
            byte[] decoded_bytes = Base64.getDecoder().decode(encrypted_password);
            String decoded_password = new String(decoded_bytes);
            person.setPassword(decoded_password);

            return person;

        } else {
            return null;
        }
    }

    @Override
    public void save(Person person) {
        if (person == null) {
            return;
        }

        if (!(person.getPassword() == null)) {
            String password = person.getPassword();
            String encrypted_password = Base64.getEncoder().encodeToString(password.getBytes());

            person.setPassword(encrypted_password);
        }

        personRepository.save(person);
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            return;
        }

        personRepository.deleteById(id);
    }

    @Override
    public void update(Integer id, Person person) {
        if (person == null) {
            return;
        }

        Optional<Person> persistCarOptional = personRepository.findById(id);

        if (persistCarOptional.isPresent()) {
            Person persistPerson = persistCarOptional.get();
            persistPerson.setName(person.getName());
            personRepository.save(persistPerson);
        }
    }
}
