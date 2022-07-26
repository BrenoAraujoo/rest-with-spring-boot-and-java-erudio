package br.com.erudio.controllers;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices service;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    public List<PersonVO> findAll(){
        return DozerMapper.parseListObject(service.findAll(), PersonVO.class);
    }


    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable (value = "id")Long id) throws Exception{
        return  DozerMapper.parseObject(service.findById(id), PersonVO.class);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO person){
        var  p = DozerMapper.parseObject(person, PersonVO.class);
        return service.create(p);
    }

//    @PutMapping(
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public PersonVO update(@RequestBody PersonVO person){
//        return service.update(person);
//    }


    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable (value = "id")Long id) throws Exception{
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
