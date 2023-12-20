package com.example.phonebook;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }

    @Transactional
    public Person updatePerson(Long id, Person updatedPerson) {
        try {
            Person existingPerson = personRepository.findById(id)
                    .orElseThrow(() -> new javax.persistence.EntityNotFoundException("Person not found with id: " + id));

            existingPerson.setName(updatedPerson.getName());
            existingPerson.setPhone(updatedPerson.getPhone());

            return personRepository.save(existingPerson);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public void deletePerson(Long id) {
        try {
            Person existingPerson = personRepository.findById(id)
                    .orElseThrow(() -> new javax.persistence.EntityNotFoundException("Person not found with id: " + id));

            personRepository.delete(existingPerson);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}


