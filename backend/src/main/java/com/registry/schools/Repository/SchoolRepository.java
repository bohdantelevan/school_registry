package com.registry.schools.Repository;

import com.registry.schools.Entity.School;
import com.registry.schools.Enums.SchoolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SchoolRepository extends JpaRepository<School, UUID> {

    List<School> findByRegionContainingIgnoreCaseAndTypeAndIsActive(String region, SchoolType type, boolean isActive);
    List<School> findByRegionContainingIgnoreCaseAndType(String region, SchoolType type);
    List<School> findByRegionContainingIgnoreCaseAndIsActive(String region, boolean isActive);
    List<School> findByRegionContainingIgnoreCase(String region);

}
