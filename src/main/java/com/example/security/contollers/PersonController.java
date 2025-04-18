package com.example.security.contollers;

import com.example.security.dto.PersonDTO;
import com.example.security.errors.PersonNotCreatedException;
import com.example.security.mapper.PersonMapper;
import com.example.security.models.Person;
import com.example.security.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @Autowired
    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @GetMapping
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping
    public Person save(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errMsg = new StringBuilder();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
            }

            throw new PersonNotCreatedException(errMsg.toString());
        }

        Person person = personMapper.toPerson(personDTO);
        personService.save(person);

        return person;
    }
}
