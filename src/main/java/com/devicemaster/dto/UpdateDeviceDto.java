package com.devicemaster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
public class UpdateDeviceDto {
    private String name;
    private String brand;
}
