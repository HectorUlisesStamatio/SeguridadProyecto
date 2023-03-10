package mx.edu.utez.controller.admin;

import javax.persistence.EntityNotFoundException;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/admin/personas"})
public class AdminPersonasController {
    private static final Logger log = LoggerFactory.getLogger(AdminPersonasController.class);
    private final PersonasRepository personasRepository;
    private static final Logger logger = LoggerFactory.getLogger(AdminPersonasController.class);

    @Autowired
    public AdminPersonasController(PersonasRepository personasRepository) {
        this.personasRepository = personasRepository;
    }

    @GetMapping({"/listar"})
    ResponseEntity<Object> listar() {
        try {
            logger.info("Se mostró exitosamente a las personas registradas");
            return new ResponseEntity(new Message("Personas Mostradas Correctamente", "Se mostró exitosamente a las personas mostradas", "success", this.personasRepository.findAll(), 200), HttpStatus.OK);
        } catch (Exception var2) {
            logger.warn("No se pudo mostrar el listado de las personas");
            logger.warn(var2.toString());
            return new ResponseEntity(new Message("Ocurrió un error al listar", "No se pudieron listar a las personas", "failed", (Object) null, 400), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping({"/{id}"})
    ResponseEntity<Object> get(@PathVariable Integer id) {
        try {
            Personas persona = (Personas) this.personasRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            log.info("Se consultó correctamente a la persona");
            return new ResponseEntity(new Message("Persona consultada", "La persona se consultó exitosamente", "success", persona, 200), HttpStatus.OK);
        } catch (Exception var3) {
            logger.warn("No se pudo consultar a la persona que se buscaba");
            logger.warn(var3.toString());
            return new ResponseEntity(new Message("Ocurrió un error al consultar", "La persona no se pudo consultar debido a un error inesperado.", "failed", (Object) null, 400), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/"})
    ResponseEntity<Object> crear(@RequestBody @Validated PersonasDTO personasDTO) {
        try {
            Personas personas = (Personas) (new ModelMapper()).map(personasDTO, Personas.class);
            personas = (Personas) this.personasRepository.saveAndFlush(personas);
            log.info("Se insertó correctamente a la persona");
            return new ResponseEntity(new Message("Persona Creada", "Se creó exitosamente a la persona", "success", personas, 200), HttpStatus.OK);
        } catch (Exception var4) {
            log.warn("No se pudo insertar a la persona");
            log.warn(var4.toString());
            return new ResponseEntity(new Message("Ocurrió un error al crear a la persona", "La persona no se pudo crear debido a un error inesperado.", "failed", (Object) null, 400), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping({"/{id}"})
    ResponseEntity<Object> actualizar(@PathVariable Integer id, @RequestBody @Validated PersonasDTO personasDTO) {
        try {
            Personas personasFromDb = (Personas) this.personasRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            ModelMapper mapper = new ModelMapper();
            mapper.map(personasDTO, personasFromDb);
            personasFromDb = this.personasRepository.saveAndFlush(personasFromDb);
            log.info("Se actualizó correctamente a la persona");
            return new ResponseEntity(new Message("Persona Actualizada Correctamente", "La persona se actualizó satisfactoriamente", "success", personasFromDb, 200), HttpStatus.OK);
        } catch (Exception var5) {
            log.warn("No se pudo actualizar a la persona");
            log.warn(var5.toString());
            return new ResponseEntity(new Message("Ocurrió un error al actualizar a la persona", "La persona no se pudo actualizar debido a un error inesperado.", "failed", (Object) null, 400), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"/{id}"})
    ResponseEntity<Object> eliminar(@PathVariable Integer id) {
        try {
            Personas personas = (Personas) this.personasRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            this.personasRepository.delete(personas);
            log.info("Se eliminó correctamente a la persona");
            return new ResponseEntity(new Message("Persona Eliminada Correctamente", "La persona se eliminó satisfactoriamente", "success", (Object) null, 200), HttpStatus.OK);
        } catch (Exception var3) {
            log.warn("No se pudo eliminar a la persona");
            log.warn(var3.toString());
            return new ResponseEntity(new Message("Ocurrió un error al eliminar a la persona", "La persona no se pudo eliminar debido a un error inesperado.", "failed", (Object) null, 400), HttpStatus.BAD_REQUEST);
        }
    }
}
