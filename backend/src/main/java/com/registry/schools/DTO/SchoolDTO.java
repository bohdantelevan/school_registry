package com.registry.schools.DTO;

import com.registry.schools.Enums.SchoolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO {
    private String name;
    private int edrpou;
    private String region;
    private SchoolType type;
    private boolean isActive;
}

