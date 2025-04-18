package com.example.security.mapper;

import com.example.security.dto.PersonDTO;
import com.example.security.models.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toPerson(PersonDTO personDTO) {
        Person person = new Person();

        person.setUsername(personDTO.getUsername());
        person.setYearOfBirth(personDTO.getYearOfBirth());
        person.setPassword(personDTO.getPassword());
        person.setRole(personDTO.getRole());

        return person;
    }
}
