package com.registry.schools.Controller;

import com.registry.schools.DTO.SchoolDTO;
import com.registry.schools.Service.SchoolService;
import com.registry.schools.Entity.School;
import com.registry.schools.Enums.SchoolType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    private final SchoolService service;

    public SchoolController(SchoolService service) {
        this.service = service;
    }

    @GetMapping
    public List<School> listSchools(
            @RequestParam(defaultValue = "") String region,
            @RequestParam(required = false) SchoolType type,
            @RequestParam(required = false) Boolean isActive) {
        return service.getSchools(region, type, isActive);
    }

    @PostMapping
    public School create(@RequestBody SchoolDTO dto) {
        return service.createSchool(dto);
    }

    @PatchMapping("/{id}/deactivate")
    public void deactivate(@PathVariable UUID id) {
        service.deactivateSchool(id);
    }

    @PatchMapping("/{id}/activate")
    public void activate(@PathVariable UUID id) {
        service.activateSchool(id);
    }
}

