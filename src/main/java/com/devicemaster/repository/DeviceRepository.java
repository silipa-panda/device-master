package com.devicemaster.repository;

import com.devicemaster.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    List<Device> findByBrand(String brand);
}
