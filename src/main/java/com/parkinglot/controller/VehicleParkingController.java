package com.parkinglot.controller;

import com.parkinglot.dao.AvailableSlot;
import com.parkinglot.entitys.ParkingVehicles;
import com.parkinglot.service.VehicleParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class VehicleParkingController {

    @Autowired
    VehicleParkingService vehicleParkingService;


    //  Parking Vehicles & slotCodes
    @GetMapping("/getAll_parking_vehicles")
    public ResponseEntity<?> getAllParkingVehicles() {
        List<ParkingVehicles> allParkingVehicles = null;
        try {
            allParkingVehicles = vehicleParkingService.getAllParkingVehicles();
            if (allParkingVehicles.isEmpty()) {
                allParkingVehicles = new ArrayList<>();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(allParkingVehicles);
    }

    // Dash Board
    @GetMapping("/available_slots")
    public ResponseEntity<?> getAvailableSlots() {
        List<AvailableSlot> availableSlots = null;
        try {
            availableSlots = vehicleParkingService.getAvailableSlots();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(availableSlots);
    }

    // parking  registration
    @PostMapping("/vehicle_registration")
    public ResponseEntity<?> saveVehicleRegistration(@RequestBody ParkingVehicles slot) throws Exception {
        ParkingVehicles availableSlots = null;
        try {
            availableSlots = vehicleParkingService.saveVehicleReg(slot);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(availableSlots);
    }
    // UN-parking vehicles
    @PutMapping("/vehicle/unParking/{slotCode}")
    public ResponseEntity<?> getVehicleParkingService(@PathVariable String slotCode) {
        try {
            vehicleParkingService.leaveOnParkingVehicleReg(slotCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("un parked successful!");
    }

    @GetMapping("/get_all_vehicle_list")
    public ResponseEntity<?> getAllVehicleList() {
        List<ParkingVehicles> allVehiclesListWithStatus = null;
        try {
            allVehiclesListWithStatus = vehicleParkingService.getAllVehiclesListWithStatus();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(allVehiclesListWithStatus);
    }


    @GetMapping("/getSlotInfo/{slotCode}")
    public ResponseEntity<?> getSlotInfo(@PathVariable String slotCode) {
        Optional<ParkingVehicles> allVehiclesListWithStatus = null;
        try {
            allVehiclesListWithStatus = vehicleParkingService.getSlotInfo(slotCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(allVehiclesListWithStatus);
    }
}
