package com.devicemaster.service;

import ch.qos.logback.core.util.StringUtil;
import com.devicemaster.dto.DeviceDto;
import com.devicemaster.dto.UpdateDeviceDto;
import com.devicemaster.entity.Device;
import com.devicemaster.exception.DeviceNotFoundException;
import com.devicemaster.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DeviceService {

    private final DeviceRepository deviceRepository;

    /**
     * add a device into device master.
     *
     * @param deviceDto
     * @return device entity
     */
    public Device addDevice(DeviceDto deviceDto) {
        Device device = Device.builder()
                .name(deviceDto.getName())
                .brand(deviceDto.getBrand())
                .creationTime(LocalDateTime.now()).build();
        return deviceRepository.save(device);
    }

    /**
     * get a device by device Id
     *
     * @param id Integer
     * @return device entity
     */

    public Device getDeviceById(Integer id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("device is not found by id"));
    }

    /**
     *
     * @return List of device
     */

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }


    public Device updateDevice(Integer id, UpdateDeviceDto updateDeviceDto) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("device is not found {}", id);
                    return new DeviceNotFoundException("device is not found by id");
                });
        if (!StringUtil.isNullOrEmpty(updateDeviceDto.getBrand()))
            device.setBrand(updateDeviceDto.getBrand());
        if (!StringUtil.isNullOrEmpty(updateDeviceDto.getName()))
            device.setName(updateDeviceDto.getName());
        return deviceRepository.save(device);
    }

    public void deleteDevice(Integer id) {
        deviceRepository.deleteById(id);
    }

    public List<Device> searchDeviceByBrand(String brand) {
        return deviceRepository.findByBrand(brand);
    }
}
