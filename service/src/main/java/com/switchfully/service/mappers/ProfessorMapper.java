package com.switchfully.service.mappers;

import com.switchfully.domain.models.Professor;
import com.switchfully.service.dto.ProfessorDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfessorMapper {

    //Professor -> ProfessorDTO
    public ProfessorDTO toDTO(Professor professor) {
        return new ProfessorDTO(professor.getId(), professor.getFirstName(), professor.getLastName());
    }

    //ProfessorDTO -> Professor
    public Professor toDomain(ProfessorDTO professorDTO, double salary) {
        return new Professor(professorDTO.getFirstName(), professorDTO.getLastName());
    }

    //List<Professor> -> List<ProfessorDTO>
    public List<ProfessorDTO> toDTO(List<Professor> professorList) {
        return professorList.stream()
                .map(this::toDTO)
                .toList();
    }
}
