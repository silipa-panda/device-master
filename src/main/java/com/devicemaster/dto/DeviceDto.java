package com.devicemaster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceDto {

    @NotBlank
    private String name;

    @NotBlank
    private String brand;

}
