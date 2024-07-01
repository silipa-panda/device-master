package com.devicemaster.service;

import com.devicemaster.dto.DeviceDto;
import com.devicemaster.dto.UpdateDeviceDto;
import com.devicemaster.entity.Device;
import com.devicemaster.exception.DeviceNotFoundException;
import com.devicemaster.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @InjectMocks
    private DeviceService deviceService;

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private Slf4j log;

    @Test
    public void addDeviceTest() {
        deviceService.addDevice(getDeviceDto());
        Mockito.verify(deviceRepository, times(1)).save(Mockito.any());
    }

    @Test
    public void getDeviceTest() {
        when(deviceRepository.findById(1)).thenReturn(Optional.of(getDeviceEntity()));
        Device device = deviceService.getDeviceById(1);
        Mockito.verify(deviceRepository, times(1)).findById(1);
        assertEquals(getDeviceEntity().getBrand(), device.getBrand());
    }

    @Test
    public void getAllDeviceTest() {
        when(deviceRepository.findAll()).thenReturn(List.of(getDeviceEntity()));
        List<Device> device = deviceService.getAllDevices();
        Mockito.verify(deviceRepository, times(1)).findAll();
        assertEquals(getDeviceEntity().getBrand(), device.get(0).getBrand());
    }

    @Test
    public void updateDeviceTest() {
        when(deviceRepository.findById(1)).thenReturn(Optional.of(getDeviceEntity()));
        when(deviceRepository.save(Mockito.any())).thenReturn(getDeviceEntity());
        Device device = deviceService.updateDevice(1, gedtUpdateDeviceDto());
        Mockito.verify(deviceRepository, times(1)).findById(1);
        assertEquals(getDeviceEntity().getBrand(), device.getBrand());
    }

    @Test()
    void updateDeviceWithIdNotFoundTest() {
        when(deviceRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(DeviceNotFoundException.class, () -> {
            when(deviceService.updateDevice(2, gedtUpdateDeviceDto())).thenThrow(new DeviceNotFoundException("device is not found by id"));
            deviceService.updateDevice(2, gedtUpdateDeviceDto());
        });
        Mockito.verify(deviceRepository, times(1)).findById(2);
    }

    @Test
    public void updateDeviceWithOnlyNameUpdateTest() {
        when(deviceRepository.findById(1)).thenReturn(Optional.of(getDeviceEntity()));
        when(deviceRepository.save(Mockito.any())).thenReturn(getDeviceEntity());
        Device device = deviceService.updateDevice(1, getUpdateDeviceNameDto());
        Mockito.verify(deviceRepository, times(1)).findById(1);
        assertEquals(getDeviceEntity().getBrand(), device.getBrand());
    }

    @Test
    public void updateDeviceWithOnlyBrandUpdateTest() {
        when(deviceRepository.findById(1)).thenReturn(Optional.of(getDeviceEntity()));
        when(deviceRepository.save(Mockito.any())).thenReturn(getDeviceEntity());
        Device device = deviceService.updateDevice(1, gedtUpdateDeviceBrandDto());
        Mockito.verify(deviceRepository, times(1)).findById(1);
        assertEquals(getDeviceEntity().getBrand(), device.getBrand());
    }

    @Test
    public void deleteDeviceTest() {
        doNothing().when(deviceRepository).deleteById(Mockito.any());
        deviceService.deleteDevice(1);
        Mockito.verify(deviceRepository, times(1)).deleteById(1);
    }

    @Test
    public void searchDeviceByBrandTest() {
        when(deviceRepository.findByBrand("Apple")).thenReturn(List.of(getDeviceEntity()));
        List<Device> device = deviceService.searchDeviceByBrand("Apple");
        Mockito.verify(deviceRepository, times(1)).findByBrand(anyString());
        assertEquals(1, device.size());

    }


    private UpdateDeviceDto getUpdateDeviceNameDto() {
        return UpdateDeviceDto.builder()
                .name("Ipad").build();
    }

    private static Device getDeviceEntity() {
        return Device.builder()
                .id(1)
                .brand("Apple")
                .name("Mobile")
                .creationTime(LocalDateTime.now()).build();
    }

    private static DeviceDto getDeviceDto() {
        return DeviceDto.builder()
                .brand("Apple")
                .name("Mobile").build();
    }

    private static UpdateDeviceDto gedtUpdateDeviceDto() {
        return UpdateDeviceDto.builder()
                .brand("Apple")
                .name("Ipad").build();
    }

    private static UpdateDeviceDto gedtUpdateDeviceBrandDto() {
        return UpdateDeviceDto.builder()
                .brand("Apple").build();
    }

}