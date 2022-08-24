package br.com.erudio.services;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {


    Logger logger = Logger.getLogger(PersonServices.class.getName());
    @Autowired
    private PersonRepository repository;


    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for  this ID!"));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all persons!");
        var list = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
        for (PersonVO l : list) {
            l.add(linkTo(methodOn(PersonController.class).findById(l.getKey())).withSelfRel());
        }
        return list;
    }


    public PersonVO create(PersonVO person) {
        logger.info("Creating one person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        repository.save(entity);
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating one person!");
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        repository.delete(entity);

    }

}