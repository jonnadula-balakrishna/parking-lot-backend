package com.parkinglot.service;

import com.parkinglot.dao.AvailableSlot;
import com.parkinglot.entitys.ParkingVehicles;

import java.util.List;
import java.util.Optional;

public interface VehicleParkingService {

    List<ParkingVehicles> getAllParkingVehicles();

    List<AvailableSlot> getAvailableSlots();

    ParkingVehicles saveVehicleReg(ParkingVehicles slot)  throws Exception;

    void leaveOnParkingVehicleReg(String slotCode);

    List<ParkingVehicles> getAllVehiclesListWithStatus();

    Optional<ParkingVehicles> getSlotInfo(String slotCode);
}
