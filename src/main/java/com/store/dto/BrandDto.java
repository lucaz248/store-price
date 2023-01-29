package com.store.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.store.entity.Brand} entity
 */
@Data
public class BrandDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
}