package com.example.security.validators;

import com.example.security.models.Person;
import com.example.security.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        Optional<Person> optionalPerson = personService.findByUsername(person.getUsername());

        if (optionalPerson.isPresent()) {
            errors.rejectValue("username", "", "Человек с таким именем уже существует!");
        }
    }
}
