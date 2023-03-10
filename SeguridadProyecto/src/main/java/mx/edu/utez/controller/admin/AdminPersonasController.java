package mx.edu.utez.controller.admin;


import lombok.extern.slf4j.Slf4j;
import mx.edu.utez.model.Personas;
import mx.edu.utez.repo.PersonasRepository;
import mx.edu.utez.util.Message;
import mx.edu.utez.web.dto.PersonasDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/admin/personas")
public class AdminPersonasController {
    private final PersonasRepository personasRepository;

    private final static Logger logger = LoggerFactory.getLogger(AdminPersonasController.class);

    @Autowired
    public AdminPersonasController(PersonasRepository personasRepository) {
        this.personasRepository = personasRepository;
    }

    @GetMapping("/listar")
    ResponseEntity<Object> listar() {
        try {
            logger.warn("Algo ocurr");
            return new ResponseEntity(new Message("Personas registradas", "success", personasRepository.findAll()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Ocurrió un error al listar", "failed"), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    ResponseEntity<Object> get(@PathVariable Integer id) {
        try {
            //optional
            Personas persona = personasRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            return new ResponseEntity(new Message("Persona consultada", "success", persona), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Ocurrió un error al consultar", "failed"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/")
    ResponseEntity<Object> crear(@RequestBody @Validated PersonasDTO personasDTO) {
        try {
            Personas personas = new ModelMapper().map(personasDTO, Personas.class);
            Personas per = personasRepository.save(personas);
            return new ResponseEntity(new Message("Persona Insertada", "success", per), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Ocurrió un error al insertar", "failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> actualizar(@PathVariable Integer id, @RequestBody @Validated PersonasDTO personasDTO) {
        try {
            Personas personasFromDb = personasRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            ModelMapper mapper = new ModelMapper();
            mapper.map(personasDTO, personasFromDb);
            personasRepository.save(personasFromDb);
            return new ResponseEntity(new Message("Persona Actualizada Correctamente", "success", personasFromDb), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Ocurrió un error al Actualizar", "failed"), HttpStatus.BAD_REQUEST);
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    ResponseEntity<Object> eliminar(@PathVariable Integer id) {
        try {
            Personas personas = personasRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            personasRepository.delete(personas);
            return new ResponseEntity(new Message("Persona Eliminada Correctamente", "success", personas), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Ocurrió un error al Eliminar", "failed"), HttpStatus.BAD_REQUEST);
        }
    }
}
