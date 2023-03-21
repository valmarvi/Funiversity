package com.switchfully.service;

import com.switchfully.domain.models.Professor;
import com.switchfully.domain.repositories.ProfessorRepository;
import com.switchfully.service.dto.ProfessorDTO;
import com.switchfully.service.mappers.ProfessorMapper;
import com.switchfully.service.dto.UpdateProfessorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorDTO addProfessor(ProfessorDTO professorToAdd) {
        Professor professor = professorMapper.toDomain(professorToAdd, 0);
        professorRepository.addProfessor(professor);
        return professorMapper.toDTO(professor);
    }

    public List<ProfessorDTO> getAllProfessors() {
        return professorMapper.toDTO(professorRepository.getAllProfessors());
    }

    public ProfessorDTO getProfessorById(String id) {
        return professorMapper.toDTO(professorRepository.getProfessorById(id));
    }

    public void deleteProfessorById(String id) {
        professorRepository.deleteProfessorById(id);
    }

    public ProfessorDTO updateProfessorById(UpdateProfessorDTO updateProfessorDTO, String id) {
        Professor professorToUpdate = professorRepository.getProfessorById(id);
        professorToUpdate.setFirstName(updateProfessorDTO.getFirstName());
        professorToUpdate.setLastName(updateProfessorDTO.getLastName());
        return professorMapper.toDTO(professorToUpdate);
    }
}
