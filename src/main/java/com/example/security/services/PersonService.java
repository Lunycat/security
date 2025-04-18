package com.example.security.services;

import com.example.security.errors.PersonNotFoundException;
import com.example.security.models.Person;
import com.example.security.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }

    public Person findById(Long id) {
        return peopleRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Такой человек не найден"));
    }

    public void save(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdateAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN");

        peopleRepository.save(person);
    }

}
