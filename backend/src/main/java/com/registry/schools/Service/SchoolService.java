package com.registry.schools.Service;

import com.registry.schools.DTO.SchoolDTO;
import com.registry.schools.Repository.SchoolRepository;
import com.registry.schools.Entity.School;
import com.registry.schools.Enums.SchoolType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SchoolService implements SchoolServiceInterface {

    private final SchoolRepository repository;

    public SchoolService(SchoolRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<School> getSchools(String region, SchoolType type, Boolean isActive) {
        if (type != null && isActive != null)
            return repository.findByRegionContainingIgnoreCaseAndTypeAndIsActive(region, type, isActive);
        else if (type != null)
            return repository.findByRegionContainingIgnoreCaseAndType(region, type);
        else if (isActive != null)
            return repository.findByRegionContainingIgnoreCaseAndIsActive(region, isActive);
        else
            return repository.findByRegionContainingIgnoreCase(region);
    }

    @Override
    public School createSchool(SchoolDTO dto) {
        School school = new School();
        school.setName(dto.getName());
        school.setEdrpou(dto.getEdrpou());
        school.setRegion(dto.getRegion());
        school.setType(dto.getType());
        school.setActive(dto.isActive());
        return repository.save(school);
    }

    @Override
    public void deactivateSchool(UUID id) {
        School school = repository.findById(id).orElseThrow();
        school.setActive(false);
        repository.save(school);
    }

    @Override
    public void activateSchool(UUID id) {
        School school = repository.findById(id).orElseThrow();
        school.setActive(true);
        repository.save(school);
    }

}
