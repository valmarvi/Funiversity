package com.switchfully.domain.repositories;


import com.switchfully.domain.models.Professor;
import com.switchfully.exception.exceptions.AlreadyExistsException;
import com.switchfully.exception.exceptions.NotFoundException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorRepository {
    protected final List<Professor> professorsDatabase = new ArrayList<>(
            List.of(new Professor("Albert", "Schmitt"),
                    new Professor("Robert", "Kean"),
                    new Professor("Salvador", "Bernard")));

    public void addProfessor(Professor professorToAdd) {
        if (professorsDatabase.contains(professorToAdd)) {
            throw new AlreadyExistsException("This Professor Already Exists.");
        }
        professorsDatabase.add(professorToAdd);
    }

    public List<Professor> getAllProfessors() {
        return this.professorsDatabase;
    }

    public Professor getProfessorById(String id) {
        return professorsDatabase.stream()
                .filter(professor -> professor.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No Professor was Found with the Provided ID"));
    }

    public void deleteProfessorById(String id) {
        Professor professorToDelete = getProfessorById(id);
        professorsDatabase.remove(professorToDelete);
    }
}
