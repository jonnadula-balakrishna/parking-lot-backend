package com.parkinglot.controller;

import com.parkinglot.dao.AvailableSlot;
import com.parkinglot.entitys.ParkingVehicles;
import com.parkinglot.service.VehicleParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class VehicleParkingController {

    @Autowired
    VehicleParkingService vehicleParkingService;


    //  Parking Vehicles & slotCodes
    @GetMapping("/getAll_parking_vehicles") // all parking vehicles list
    public ResponseEntity<?> getAllParkingVehicles() {
        List<ParkingVehicles> allParkingVehicles = null;
        allParkingVehicles = vehicleParkingService.getAllParkingVehicles();
        if (allParkingVehicles.isEmpty()) {
            allParkingVehicles = new ArrayList<>();
        }
        return ResponseEntity.ok(allParkingVehicles);
    }

    // Dash Board
    @GetMapping("/available_slots")
    public ResponseEntity<?> getAvailableSlots() {
        List<AvailableSlot> availableSlots = vehicleParkingService.getAvailableSlots();
        return ResponseEntity.ok(availableSlots);
    }

    // parking  registration
    @PostMapping("/vehicle_registration")
    public ResponseEntity<?> saveVehicleRegistration(@RequestBody ParkingVehicles slot) throws Exception {
        ParkingVehicles availableSlots = vehicleParkingService.saveVehicleReg(slot);
        return ResponseEntity.ok(availableSlots);
    }
    // UN-parking vehicles
    @PutMapping("/vehicle/unParking/{slotCode}")
    public ResponseEntity<?> getVehicleParkingService(@PathVariable String slotCode) {
        vehicleParkingService.leaveOnParkingVehicleReg(slotCode);
        return ResponseEntity.ok("un parked successful!");
    }

    @GetMapping("/get_all_vehicle_list")
    public ResponseEntity<?> getAllVehicleList() {
        List<ParkingVehicles> allVehiclesListWithStatus = vehicleParkingService.getAllVehiclesListWithStatus();
        return ResponseEntity.ok(allVehiclesListWithStatus);
    }



}
