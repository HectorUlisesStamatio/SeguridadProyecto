package mx.edu.utez.controller.admin;


import mx.edu.utez.model.Personas;
import mx.edu.utez.repo.PersonasRepository;
import mx.edu.utez.web.dto.PersonasDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/admin/personas")
public class AdminPersonasController {
    private final PersonasRepository personasRepository;

    @Autowired
    public AdminPersonasController(PersonasRepository personasRepository) {
        this.personasRepository = personasRepository;
    }

    @GetMapping("/listar")
    List<Personas> listar(){
        List<Personas> personas = personasRepository.findAll();
        System.out.println(personas.size());
        return personas;
    }

    @PostMapping
     ResponseEntity<Personas> crear(@RequestBody @Validated PersonasDTO personasDTO){
            try{
                Personas personas = new ModelMapper().map(personasDTO, Personas.class);
                Personas per = personasRepository.save(personas);
                return new ResponseEntity<Personas>(per, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

    }

    @PutMapping("{id}")
    Personas actualizar(@PathVariable Integer id,@RequestBody @Validated PersonasDTO personasDTO){
        Personas personasFromDb = personasRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ModelMapper mapper = new ModelMapper();
        mapper.map(personasDTO,personasFromDb);
        return personasRepository.save(personasFromDb);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void eliminar(@PathVariable Integer id){
        Personas personas = personasRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        personasRepository.delete(personas);
    }
}
