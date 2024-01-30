package com.example.schooljournal.data.entity.core;

import com.example.schooljournal.data.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "t_address")
public class Address extends BaseEntity {
    @NotBlank
    private String countryCode;

    private String houseNumber;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
}
