package com.devicemaster.controller;

import com.devicemaster.dto.DeviceDto;
import com.devicemaster.dto.UpdateDeviceDto;
import com.devicemaster.entity.Device;
import com.devicemaster.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Device Operations")
@RestController
@AllArgsConstructor
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Operation(summary = "Get Device")
    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable Integer id) {
        return deviceService.getDeviceById(id);
    }

    @Operation(summary = "Get All Devices")
    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @Operation(summary = "Search Devices")
    @GetMapping("/search")
    public List<Device> searchDevicesByBrand(@RequestParam String brand) {
        return deviceService.searchDeviceByBrand(brand);
    }

    @Operation(summary = "Add Device")
    @PostMapping("/add-device")
    public Device addDevice(@RequestBody @Valid DeviceDto deviceDto) {
        return deviceService.addDevice(deviceDto);
    }

    @Operation(summary = "Update Devices")
    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable Integer id, @RequestBody UpdateDeviceDto updateDeviceDto) {
        return deviceService.updateDevice(id, updateDeviceDto);
    }

    @Operation(summary = "Delete Devices")
    @DeleteMapping("/{id}")
    public String deleteDevice(@PathVariable Integer id) {
        deviceService.deleteDevice(id);
        return "device is deleted Successfully";
    }
}
