package com.registry.schools.Service;

import com.registry.schools.DTO.SchoolDTO;
import com.registry.schools.Entity.School;
import com.registry.schools.Enums.SchoolType;
import com.registry.schools.SchoolsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SchoolsApplication.class)
public class SchoolServiceTest {

    @Autowired
    private SchoolService service;

    @Test
    public void testCreateAndGetSchools() {
        SchoolDTO dto = new SchoolDTO();
        dto.setName("Test School");
        dto.setEdrpou(123456);
        dto.setRegion("Kyiv");
        dto.setType(SchoolType.ГІМНАЗІЯ);
        dto.setActive(false);

        School created = service.createSchool(dto);
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();

        List<School> schools = service.getSchools("Kyiv", SchoolType.ГІМНАЗІЯ, false);
        assertThat(schools).isNotEmpty();
        assertThat(schools).extracting(School::getName).contains("Test School");
    }

    @Test
    public void testDeactivateSchool() {
        SchoolDTO dto = new SchoolDTO();
        dto.setName("Deactivate Test");
        dto.setEdrpou(654321);
        dto.setRegion("Lviv");
        dto.setType(SchoolType.ЛІЦЕЙ);

        School created = service.createSchool(dto);
        UUID id = created.getId();

        service.deactivateSchool(id);

        List<School> schools = service.getSchools("Lviv", SchoolType.ЛІЦЕЙ, false);
        assertThat(schools).extracting(School::getId).contains(id);
    }
}
