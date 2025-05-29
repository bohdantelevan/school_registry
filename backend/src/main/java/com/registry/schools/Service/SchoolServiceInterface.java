package com.registry.schools.Service;

import com.registry.schools.DTO.SchoolDTO;
import com.registry.schools.Entity.School;
import com.registry.schools.Enums.SchoolType;

import java.util.List;
import java.util.UUID;

public interface SchoolServiceInterface {

    List<School> getSchools(String region, SchoolType type, Boolean isActive);
    School createSchool(SchoolDTO schoolDTO);
    void deactivateSchool(UUID id);
    void activateSchool(UUID id);

}
