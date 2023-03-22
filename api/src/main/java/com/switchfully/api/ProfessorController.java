package com.switchfully.api;

import com.switchfully.domain.models.Feature;
import com.switchfully.service.SecurityService;
import com.switchfully.service.dto.ProfessorDTO;
import com.switchfully.service.ProfessorService;
import com.switchfully.service.dto.UpdateProfessorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "professors")
public class ProfessorController {
    private final ProfessorService professorService;
    private final SecurityService securityService;
    private final Logger myLogger = LoggerFactory.getLogger(ProfessorController.class);

    public ProfessorController(ProfessorService professorService, SecurityService securityService) {
        this.professorService = professorService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json") //http://localhost:8080/professors
    public ProfessorDTO addProfessor(@RequestBody ProfessorDTO professorToAdd, @RequestHeader String authorization) {
        myLogger.info("Adding a New Professor to the Database.");
        securityService.validateAuthorization(authorization, Feature.ADD_PROFESSOR);
        return professorService.addProfessor(professorToAdd);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json") //http://localhost:8080/professors
    public List<ProfessorDTO> getAllProfessors(@RequestHeader String authorization) {
        myLogger.info("Retrieving All the Professors from the Database.");
        securityService.validateAuthorization(authorization, Feature.GET_ALL_PROFESSORS);
        return professorService.getAllProfessors();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", path = "{id}") //http://localhost:8080/professors/{id}
    public ProfessorDTO getProfessorById(@PathVariable String id, @RequestHeader String authorization) {
        myLogger.info("Retrieving Professor from the Database with id: " + id);
        securityService.validateAuthorization(authorization, Feature.GET_PROFESSOR_BY_ID);
        return professorService.getProfessorById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = "application/json", produces = "application/json", path = "{id}") //http://localhost:8080/professors/{id}
    public ProfessorDTO updateProfessorById(@RequestBody UpdateProfessorDTO updateProfessorDTO, @PathVariable String id, @RequestHeader String authorization) {
        myLogger.info("Updating Professor from the Database with id: " + id);
        securityService.validateAuthorization(authorization, Feature.UPDATE_PASSWORD_BY_ID);
        return professorService.updateProfessorById(updateProfessorDTO, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "{id}") //http://localhost:8080/professors/{id}
    public void deleteProfessorById(@PathVariable String id, @RequestHeader String authorization) {
        myLogger.info("Deleting Professor from the Database with id: " + id);
        securityService.validateAuthorization(authorization, Feature.DELETE_PROFESSOR_BY_ID);
        professorService.deleteProfessorById(id);
    }
}
