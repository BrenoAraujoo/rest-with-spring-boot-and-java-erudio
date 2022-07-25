package br.com.erudio.services;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    Logger logger = Logger.getLogger(PersonServices.class.getName());
    @Autowired
    private PersonRepository repository;


    public Person findById(Long id) {
        logger.info("Finding one person!");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for  this ID!"));
    }

    public List<Person> findAll() {
        logger.info("Finding all persons!");
        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating one person!");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person!");
        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        repository.delete(entity);

    }

}